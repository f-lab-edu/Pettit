package com.flab.Pettit.service;

import com.flab.Pettit.domain.member.Member;
import com.flab.Pettit.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * 로그인 서비스 - DB에서 username에 해당하는 값을 찾아 반환
 * @author Gidae Hong
 * @since 1.0
 **/
@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new BadCredentialsException("인증오류"));

        return User.builder().username(member.getUsername())
                .password(member.getPassword())
                .build();
    }
}
