package kr.ac.dankook.army.repository;

import kr.ac.dankook.army.dto.entity.PlannerComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlannerComponentRepository extends JpaRepository<PlannerComponent,Long> {

}
