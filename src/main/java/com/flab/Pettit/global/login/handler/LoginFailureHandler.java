package com.flab.Pettit.global.login.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 로그인 실패처리를 담당하는 Handler
 * 보안상 상태코드르 200으로 반환
 * @author Gidae Hong
 * @since 1.0
 **/
@Slf4j
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);//

        response.getWriter().write("실패");
        log.info("로그인에 실패했습니다");
    }
}
