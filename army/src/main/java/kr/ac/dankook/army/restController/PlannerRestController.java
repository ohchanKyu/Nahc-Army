package kr.ac.dankook.army.restController;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.dankook.army.dto.entity.Planner;
import kr.ac.dankook.army.dto.entity.PlannerComponent;
import kr.ac.dankook.army.service.PlannerComponentService;
import kr.ac.dankook.army.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planner")
@RequiredArgsConstructor
public class PlannerRestController {

    private final PlannerService plannerService;
    private final PlannerComponentService plannerComponentService;

    @PostMapping("/add")
    public ResponseEntity<Planner> addNewPlanner(@RequestBody Planner planner, HttpServletRequest request){
        return ResponseEntity.ok(plannerService.createNewPlannerProcess(
                request,planner
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePlanner(@PathVariable("id") Long plannerId){
        return ResponseEntity.ok(plannerService.deletePlannerById(plannerId));
    }

    @PatchMapping("/patch/title/{id}")
    public ResponseEntity<Planner> fetchPlanner(
            @PathVariable("id") Long plannerId,
            @RequestParam("title") String title){
        return ResponseEntity.ok(plannerService.editPlannerTitleProcess(plannerId,title));
    }

    @PatchMapping("/patch/date/{id}")
    public ResponseEntity<Planner> fetchPlanner(
            HttpServletRequest request,
            @PathVariable("id") Long plannerId,
            @RequestBody Planner editPlanner){
        return ResponseEntity.ok(plannerService.editPlannerDateProcess(request,plannerId,editPlanner));
    }

    @PatchMapping("/patch/component/{id}")
    public ResponseEntity<PlannerComponent> fetchPlannerComponent(
            @PathVariable("id") Long plannerComponentId,
            @RequestBody PlannerComponent editPlannerComponent){
        return ResponseEntity.ok(plannerComponentService.editPlannerComponent(editPlannerComponent,plannerComponentId));
    }

}
