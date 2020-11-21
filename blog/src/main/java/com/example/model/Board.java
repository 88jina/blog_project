package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content; //섬머노트 라이브러리 사용

    @ColumnDefault("0")
    private int count; //조회

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    //데이터베이스는 오브젝트를 저장할 수 없지만 자바는 오브젝트 저장가능
    //ORM을 사용하면 오브젝트를 저장할 수 있음

    //mappedBy가 적혀있으면 연관관계의 주인이 아니다(FK가 아님) DB에 컬럼을 생성하지 않음
    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;

}
