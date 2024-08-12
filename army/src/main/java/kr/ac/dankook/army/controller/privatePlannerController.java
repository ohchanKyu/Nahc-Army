package kr.ac.dankook.army.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.dankook.army.dto.entity.Planner;
import kr.ac.dankook.army.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/private/planner")
@RequiredArgsConstructor
public class privatePlannerController {

    private final PlannerService plannerService;

    @RequestMapping("")
    public String plannerBasicPage(HttpServletRequest request, Model model) {
        List<Planner> memberPlanners = plannerService.getAllPlannerProcess(request);
        model.addAttribute("memberPlanners", memberPlanners);
        return "page/planner/plannerList.html";
    }

    @RequestMapping("/{id}")
    public String plannerDetailPage(@PathVariable("id") Long plannerId, Model model){
        Planner targetPlanner = plannerService.getOnePlannerProcess(plannerId);
        model.addAttribute("planner",targetPlanner);
        return "page/planner/planner.html";
    }
}
