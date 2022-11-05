package com.flab.Pettit.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    private static String PASSWORD = "홍기대f-lab";

 // 인코딩 양식으로 출력이 되는지 확인
    @Test
    public void passwordEncode() throws Exception {

      //when
        String encodePassword = passwordEncoder.encode(PASSWORD);
      //then
        assertThat(encodePassword).startsWith("{");
        assertThat(encodePassword).contains("{bcrypt}");
        assertThat(encodePassword).isNotEqualTo(PASSWORD);
    }
 // 비밀번호 인코딩이 랜덤하게 되는지 확인
    @Test
    public void passwordEncodeRandomly() throws Exception{

      //when
        String encodePassword1 = passwordEncoder.encode(PASSWORD);
        String encodePassword2 = passwordEncoder.encode(PASSWORD);
      //then
        assertThat(encodePassword1).isNotEqualTo(encodePassword2);
    }
 // 인코딩된 비밀번호가 매칭이 되는지 확인
    @Test void encodedPasswordMatch() throws Exception{

      //when
        String encodePassword = passwordEncoder.encode(PASSWORD);
      //then
        assertThat(passwordEncoder.matches(PASSWORD, encodePassword)).isTrue();

    }

}