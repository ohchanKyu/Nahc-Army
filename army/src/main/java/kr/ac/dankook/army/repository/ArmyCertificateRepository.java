package kr.ac.dankook.army.repository;

import kr.ac.dankook.army.dto.entity.ArmyCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArmyCertificateRepository extends JpaRepository<ArmyCertificate,Long> {
    List<ArmyCertificate> findByArmyCodeAndCategory(String armyCode, String category);
}
