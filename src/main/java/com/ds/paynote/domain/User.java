package com.ds.paynote.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @Column(name = "user_id", length = 100)
    private String userId;  // VARCHAR(100) PRIMARY KEY

    @Column(name = "user_mail", length = 100, nullable = false)
    private String userMail; // NOT NULL

    @Column(name = "user_pw", length = 255, nullable = false)
    private String userPw; // NOT NULL

    @Column(name = "user_nm", length = 50, nullable = false)
    private String userNm; // NOT NULL

    @Column(name = "user_phone_num", length = 50, nullable = false)
    private String userPhoneNum; // NOT NULL

    @Column(name = "user_join_dt")
    private LocalDateTime userJoinDt; // default CURRENT_TIMESTAMP

    @Column(name = "del_yn")
    private char delYn = 'N'; // ENUM('Y','N') default 'N'

    @Column(name = "fst_dt")
    private LocalDateTime fstDt; // default CURRENT_TIMESTAMP

    @Column(name = "lst_dt")
    private LocalDateTime lstDt;
}
