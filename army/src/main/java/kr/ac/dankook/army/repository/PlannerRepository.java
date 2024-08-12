package kr.ac.dankook.army.repository;

import kr.ac.dankook.army.dto.entity.Planner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlannerRepository extends JpaRepository<Planner,Long> {

    List<Planner> findByMemberId(Long id);
}
