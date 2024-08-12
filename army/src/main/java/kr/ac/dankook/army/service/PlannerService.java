package kr.ac.dankook.army.service;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.dankook.army.dto.entity.Member;
import kr.ac.dankook.army.dto.entity.Planner;
import kr.ac.dankook.army.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final PlannerComponentService plannerComponentService;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public List<Planner> getAllPlannerProcess(HttpServletRequest request){
        Member loginMember = memberService.findMemberBySession(request);
        return plannerRepository.findByMemberId(loginMember.getId());
    }

    @Transactional(readOnly = true)
    public Planner getOnePlannerProcess(Long plannerId){
        Optional<Planner> targetPlanner = plannerRepository.findById(plannerId);
        return targetPlanner.orElse(null);
    }

    @Transactional
    public Planner createNewPlannerProcess(HttpServletRequest request, Planner newPlanner) {
        Member loginMember = memberService.findMemberBySession(request);
        newPlanner.setMemberId(loginMember.getId());
        newPlanner.setPlannerComponentList(
                plannerComponentService.addPlannerComponentsByDaysBetween(newPlanner)
        );
        return plannerRepository.save(newPlanner);
    }

    @Transactional
    public boolean deletePlannerById(Long plannerId){
        plannerRepository.deleteById(plannerId);
        return true;
    }

    @Transactional
    public Planner editPlannerTitleProcess(Long plannerId,String newTitle){
        Planner targetPlanner = plannerRepository.findById(plannerId).orElse(null);
        assert targetPlanner != null;
        targetPlanner.setTitle(newTitle);
        return plannerRepository.save(targetPlanner);
    }

    @Transactional
    public Planner editPlannerDateProcess(HttpServletRequest request,Long plannerId, Planner editPlanner){
        Planner targetPlanner = plannerRepository.findById(plannerId).orElse(null);
        assert targetPlanner != null;

        plannerRepository.deleteById(plannerId);
        Planner newPlanner = createNewPlannerProcess(request,editPlanner);
        newPlanner.setTitle(targetPlanner.getTitle());
        return plannerRepository.save(newPlanner);
    }
}
