package kr.ac.dankook.army.service;

import kr.ac.dankook.army.dto.entity.ArmyCertificate;
import kr.ac.dankook.army.dto.response.CertificateRecommend;
import kr.ac.dankook.army.repository.ArmyCertificateEntityManager;
import kr.ac.dankook.army.repository.ArmyCertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateRecommendService {

    private final ArmyCertificateRepository armyCertificateRepository;
    private final ArmyCertificateEntityManager armyCertificateEntityManager;

    public List<String> getCategoryListProcess(String armyCode){
        return armyCertificateEntityManager.findDistinctCategoryByArmyCode(armyCode);
    }

    public List<ArmyCertificate> getRecommendProcess(String armyCode, String category){
        List<ArmyCertificate> targetList = armyCertificateRepository.findByArmyCodeAndCategory(armyCode,category);
        List<ArmyCertificate> recommendList = new ArrayList<>();
        List<CertificateRecommend> scoreList = new ArrayList<>();

        for(ArmyCertificate target : targetList){
            double totalScore = 0;

            double passingRate = target.getPassingRate();
            long totalExamination = target.getTotalExamination();
            String certificateName = target.getCertificateName();

            if (certificateName.contains("산업기사")){
                totalScore += 50;
            }else if (certificateName.contains("기사")){
                totalScore += 100;
            }else if (certificateName.contains("기능사")){
                totalScore += 30;
            }
            totalScore += passingRate * 65 + totalExamination * 45;
            scoreList.add(new CertificateRecommend(
                    target,totalScore
            ));
        }
        scoreList.sort((o1, o2) -> Double.compare(o2.getTotalScore(), o1.getTotalScore()));
        scoreList = scoreList.subList(0, Math.min(3, scoreList.size()));
        for(CertificateRecommend highScoreCertificate : scoreList){
            recommendList.add(highScoreCertificate.getArmyCertificate());
        }
        return recommendList;
    }
}
