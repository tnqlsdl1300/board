package com.subin.board.springboot.config.auth;

import com.subin.board.springboot.config.auth.dto.OAuthAttributes;
import com.subin.board.springboot.config.auth.dto.SessionUser;
import com.subin.board.springboot.domain.user.User;
import com.subin.board.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

// 소셜 로그인 이후 가져온 사용자의 정보(name, email, picture 등)들을 기반으로 가입 및 정보수정, 세션 저장등의 기능을 지원하는 클래스

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        /*
        - registrationId
            - 현재 로그인 진행 중인 서비스를 구분하는 코드
            - 지금은 구글만 사용하는 불필요한 값이지만, 이후 네이버 로그인 연동 시에 네이버 로그인인지, 구글 로그인인지 구분하기 위해 사용
        - userNameAttributeName
            - OAuth2 로그인 진행 시 키가 되는 필드값을 이야기 함. Primary Key와 같은 의미
            - 구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 기본으로 지원하지 않음. 구글의 기본 코드는 "sub"임
            - 이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용
        - OAuthAttributes
            - OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
            - 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용
        - SessionUser
            - 세션에 사용자 정보를 저장하기 위한 Dto 클래스
            - User 클래스를 쓰지 않고 새로 만들어서 쓰는 이유는 entitiy의 경우 직렬화를 구현해 줘야 하기 때문에 번거로워서
         */

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes =  OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
                , attributes.getAttributes()
                , attributes.getNameAttributeKey());

    }

    // 소셜 사용자 정보가 업데이트 되었을 때를 대비하여 update기능 구현
    // 사용자의 이름이나 프로필 사진이 변경되면 User 엔티티에도 반영
    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}
