package com.flab.Pettit.global.login.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 로그인 성공처리를 담당하는 Handler
 * @author Gidae Hong
 * @since 1.0
 **/
@Slf4j
public class LoginSuccessJWTProvideHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("로그인 성공 토큰 발급. username:{}", userDetails.getUsername());

        response.getWriter().write("성공");
        //JWT 발급하는 코드 작성 예정
    }
}
