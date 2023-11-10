package com.example.restdoc1_app.user;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 디폴트 생성자로 생성 못하게함
@Entity
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 20, nullable = false)
    private String username;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 100, nullable = false)
    private String email;

//    의미있는 세터 만들기
//    public void updatePassword(String password){
//        this.password = password;
//    }

    @Builder // 빌더는 컬렉션만 없는 것만 잡아서 달아주는 것을 추천. 맨 위에 붙이면 컬렉션이 들어오면 터진다.
    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}