package com.flab.Pettit.repository;

import com.flab.Pettit.domain.member.Member;
import com.flab.Pettit.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @AfterEach
    private void after(){
        em.clear();
    }
    @Test
    public void memberSave() throws Exception {
      //given
        Member member = Member.builder().username("username").password("1234567890").name("Member1").nickName("NickName1").age(22).build();

      //when
        Member saveMember = memberRepository.save(member);

      //then
        Member findMember = memberRepository.findById(saveMember.getId()).orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다"));//아직 예외 클래스를 만들지 않았기에 RuntimeException으로 처리하겠습니다.

        assertThat(findMember).isSameAs(saveMember);
        assertThat(findMember).isSameAs(member);
    }

    @Test
    public void memberSaveNoUserName() throws Exception {
      //given
        Member member = Member.builder().password("1234567890").name("Member1").nickName("NickName1").age(22).build();

      //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void memberSaveNoName() throws Exception {
      //given
        Member member = Member.builder().username("username").password("1234567890").nickName("NickName1").age(22).build();


      //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void memberSaveNoNickName() throws Exception {
      //given
        Member member = Member.builder().username("username").password("1234567890").name("Member1").age(22).build();

      //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void memberSaveNoAge() throws Exception {
      //given
        Member member = Member.builder().username("username").password("1234567890").name("Member1").nickName("NickName1").build();

      //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member));
    }

    @Test
    public void memberSaveDuplicatedUsername() throws Exception {
      //given
        Member member1 = Member.builder().username("username").password("1234567890").name("Member1").nickName("NickName1").age(22).build();
        Member member2 = Member.builder().username("username").password("1111111111").name("Member2").nickName("NickName2").age(22).build();


        memberRepository.save(member1);


      //when, then
        assertThrows(Exception.class, () -> memberRepository.save(member2));

    }

    @Test
    public void deleteMember() throws Exception {
      //given
        Member member1 = Member.builder().username("username").password("1234567890").name("Member1").nickName("NickName1").age(22).build();
        memberRepository.save(member1);


      //when
        memberRepository.delete(member1);


      //then
        assertThrows(Exception.class, () -> memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception()));
    }

    @Test
    public void existByUsername() throws Exception {
      //given
        String username = "username";
        Member member1 = Member.builder().username(username).password("1234567890").name("Member1").nickName("NickName1").age(22).build();
        memberRepository.save(member1);

      //when, then
        assertThat(memberRepository.existsByUsername(username)).isTrue();
        assertThat(memberRepository.existsByUsername(username+"123")).isFalse();

    }

    @Test
    public void findByUsername() throws Exception {
      //given
        String username = "username";
        Member member1 = Member.builder().username(username).password("1234567890").name("Member1").nickName("NickName1").age(22).build();
        memberRepository.save(member1);
        em.clear();


      //when, then
        assertThat(memberRepository.findByUsername(username).get().getUsername()).isEqualTo(member1.getUsername());
        assertThat(memberRepository.findByUsername(username).get().getName()).isEqualTo(member1.getName());
        assertThat(memberRepository.findByUsername(username).get().getId()).isEqualTo(member1.getId());
        assertThrows(Exception.class,
                () -> memberRepository.findByUsername(username+"123")
                        .orElseThrow(() -> new Exception()));

    }
    @Test
    public void memberSaveCheckTime() throws Exception {
      //given
        Member member1 = Member.builder().username("username").password("1234567890").name("Member1").nickName("NickName1").age(22).build();
        memberRepository.save(member1);
        em.clear();

      //when
        Member findMember = memberRepository.findById(member1.getId()).orElseThrow(() -> new Exception());

      //then
        assertThat(findMember.getCreatDate()).isNotNull();
        assertThat(findMember.getModifiedDate()).isNotNull();

    }
  
}