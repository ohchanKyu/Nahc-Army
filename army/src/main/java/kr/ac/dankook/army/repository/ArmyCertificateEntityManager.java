package kr.ac.dankook.army.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArmyCertificateEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<String> findDistinctCategoryByArmyCode(String armyCode) {
        return entityManager.createQuery(
                        "SELECT DISTINCT ac.category FROM ArmyCertificate ac WHERE ac.armyCode = :armyCode", String.class)
                .setParameter("armyCode", armyCode)
                .getResultList();
    }
}
