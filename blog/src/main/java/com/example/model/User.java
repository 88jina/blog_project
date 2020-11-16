package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //유저클래스가 mysql에 테이블 생성이 됨
public class User {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 DB의 넘버링 전략을 따름
    private int id;
    @Column(nullable = false,length = 30)
    private String username;
    @Column(nullable = false,length = 100)
    private String password;
    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; //원래 Enum을 써서 데이터에 도메인을 걸어줘야함(admin, user, manager)

    @CreationTimestamp //시간이 자동입력
    private Timestamp createDate;


}
