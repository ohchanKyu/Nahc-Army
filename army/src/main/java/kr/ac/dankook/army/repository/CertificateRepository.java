package kr.ac.dankook.army.repository;

import kr.ac.dankook.army.dto.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate,Long> {
    List<Certificate> findByType(String type);
    List<Certificate> findByTypeContainingOrNameContaining(String typeKeyword,String nameKeyword);
    List<Certificate> findAllByOrderByTotalExaminationDesc();
    List<Certificate> findAllByOrderByPassingRateDesc();
}
