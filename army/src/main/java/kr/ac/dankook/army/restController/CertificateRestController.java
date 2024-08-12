package kr.ac.dankook.army.restController;

import kr.ac.dankook.army.dto.entity.ArmyCertificate;
import kr.ac.dankook.army.service.CertificateRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/certificate")
@RequiredArgsConstructor
public class CertificateRestController {

    private final CertificateRecommendService certificateRecommendService;

    @GetMapping("/recommend/{armyCode}/{category}")
    public ResponseEntity<List<ArmyCertificate>> getRecommendList(@PathVariable("armyCode") String armyCode,
                                                                  @PathVariable("category") String category){
        return ResponseEntity.ok(
                certificateRecommendService.getRecommendProcess(armyCode,category)
        );
    }

    @GetMapping("/category/{armyCode}")
    public ResponseEntity<List<String>> getCategoryList(@PathVariable("armyCode") String armyCode){
        return ResponseEntity.ok(
                certificateRecommendService.getCategoryListProcess(armyCode)
        );
    }

}
