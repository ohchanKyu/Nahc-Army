package kr.ac.dankook.army.dto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
// Only 군인데이터만 존재
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 자격증 종류 (ex 기능사,산업기사)
    private String type;
    // 자격증 이름
    private String name;
    @Column(length = 500)
    // 자격증 기본 정보
    private String description;
    @Column(length = 500)
    // 자격증 수행 직무
    private String jobDescription ;
    @Column(length = 700)
    // 자격증 미래 전망
    private String prospects ;
    // 총 응시 수
    private int totalExamination;
    // 군인 응시율
    private double examinationRate;
    // 군인 합격율
    private double passingRate;
    // 상세 url
    private String url;
}
