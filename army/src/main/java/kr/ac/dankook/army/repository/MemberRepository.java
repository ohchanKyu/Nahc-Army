package kr.ac.dankook.army.repository;

import kr.ac.dankook.army.dto.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUserIdAndPassword(String userId, String password);
    Optional<Member> findByUserId(String userId);
    List<Member> findByName(String name);
    Optional<Member> findByNameAndUserId(String name, String userId);
}
