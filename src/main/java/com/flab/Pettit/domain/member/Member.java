package com.flab.Pettit.domain.member;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.flab.Pettit.domain.TimeEntity;
import javax.persistence.*;

/**
 * 아이디와 비밀번호, 이름, 닉네임, 나이를 입력받습니다.
 * 아이디는 중복될 수 없습니다.
 * 비밀번호와 이름, 닉네임과 나이는 변경할 수 있습니다.
 * 비밀번호는 암호화되어 데이터베이스에 저장됩니다.
 * 회원 엔티티는 데이터베이스에 자장될 때, 등록 시간과 업데이트 시간을 반영되도록 하였습니다.
 *
 * @author Gidae Hong
 * @Since 1.0
 * **/
@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AllArgsConstructor
@Builder
public class Member extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    private String password;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String nickName;

    @Column(nullable = false, length = 30)
    private Integer age;



    //정보 수정
    public void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateNickName(String nickName){
        this.nickName = nickName;
    }

    public void updateAge(int age){
        this.age = age;
    }

    //패스워드 암호화
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

}
