package kr.ac.dankook.army.service;

import kr.ac.dankook.army.dto.entity.Planner;
import kr.ac.dankook.army.dto.entity.PlannerComponent;
import kr.ac.dankook.army.repository.PlannerComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlannerComponentService {

    private final PlannerComponentRepository plannerComponentRepository;

    @Transactional
    public List<PlannerComponent> addPlannerComponentsByDaysBetween(Planner planner){
        long daysBetween = ChronoUnit.DAYS.between(
                planner.getStartDate(), planner.getEndDate());
        int daysDifference = Math.toIntExact(daysBetween);

        List<PlannerComponent> plannerComponentList = new ArrayList<>();
        for(int i=0;i<=daysDifference;i++){
            PlannerComponent newPlannerComponent = new PlannerComponent();
            newPlannerComponent.setPlanner(planner);
            newPlannerComponent.setDate(
                    planner.getStartDate().plusDays(i)
            );
            plannerComponentList.add(plannerComponentRepository.save(newPlannerComponent));
        }
        return plannerComponentList;
    }

    @Transactional
    public PlannerComponent editPlannerComponent(PlannerComponent newPlannerComponent,Long editId){
        PlannerComponent oldPlannerComponent = plannerComponentRepository.findById(editId).orElse(null);
        assert oldPlannerComponent != null;

        String title = newPlannerComponent.getTitle();
        String content = newPlannerComponent.getContent();
        String type = newPlannerComponent.getType();

        if (title != null) {
            oldPlannerComponent.setTitle(title);
        }
        if (content != null){
            oldPlannerComponent.setContent(content);
        }
        oldPlannerComponent.setType(type);
        return plannerComponentRepository.save(oldPlannerComponent);
    }
}
