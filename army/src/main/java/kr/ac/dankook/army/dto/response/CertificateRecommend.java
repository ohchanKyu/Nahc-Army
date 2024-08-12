package kr.ac.dankook.army.dto.response;

import kr.ac.dankook.army.dto.entity.ArmyCertificate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CertificateRecommend {

    private ArmyCertificate armyCertificate;
    double totalScore;
}
