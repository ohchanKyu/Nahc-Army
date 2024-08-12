package kr.ac.dankook.army.service;

import kr.ac.dankook.army.dto.entity.Certificate;
import kr.ac.dankook.army.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;

    @Transactional(readOnly = true)
    public List<Certificate> getAllCertificateProcess(){
        return certificateRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Certificate getCertificateByIdProcess(Long id){
        return certificateRepository.findById(id).orElse(null);
    }
    @Transactional(readOnly = true)
    public Certificate getMaxPassingRateProcess(){
        return certificateRepository.findAllByOrderByPassingRateDesc().getFirst();
    }
    @Transactional(readOnly = true)
    public Certificate getMaxTotalExaminationProcess(){
        return certificateRepository.findAllByOrderByTotalExaminationDesc().getFirst();
    }

    @Transactional(readOnly = true)
    public List<Certificate> getMatchCertificateProcess(String type,String keyword){
        return switch (type) {
            case "1" -> certificateRepository.findAllByOrderByPassingRateDesc();
            case "2" -> certificateRepository.findAllByOrderByTotalExaminationDesc();
            case "3" -> certificateRepository.findByType("기능사");
            case "4" -> certificateRepository.findByType("산업기사");
            case "5" -> certificateRepository.findByTypeContainingOrNameContaining(keyword,keyword);
            default -> getAllCertificateProcess();
        };
    }
}
