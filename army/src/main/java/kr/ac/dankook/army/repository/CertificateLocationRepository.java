package kr.ac.dankook.army.repository;

import kr.ac.dankook.army.dto.entity.CertificateLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateLocationRepository extends JpaRepository<CertificateLocation,Long> {
    List<CertificateLocation> findByAddressContaining(String regionCode);
}
