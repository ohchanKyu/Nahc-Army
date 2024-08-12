package kr.ac.dankook.army.dto.entity;

import jakarta.persistence.*;
import kr.ac.dankook.army.dto.common.Authority;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String userId;
    private String password;
    private String address;
    private String addressLatitude;
    private String addressLongitude;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String name,
                  String userId,
                  String password,
                  String address,
                  Authority authority,
                  String addressLatitude,
                  String addressLongitude,
                  LocalDate date){
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.address = address;
        this.authority = authority;
        this.addressLatitude = addressLatitude;
        this.addressLongitude = addressLongitude;
        this.date = date;
    }
}
