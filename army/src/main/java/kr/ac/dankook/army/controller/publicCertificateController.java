package kr.ac.dankook.army.controller;

import kr.ac.dankook.army.dto.entity.Certificate;
import kr.ac.dankook.army.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/public/certificate")
@RequiredArgsConstructor
public class publicCertificateController {

    private final CertificateService certificateService;

    @RequestMapping("")
    public String certificatePage(Model model,
              @RequestParam(value="keyword",required = false) String keyword,
              @RequestParam(value = "type",required = false, defaultValue = "0") String type){
        List<Certificate> targetList = certificateService.getMatchCertificateProcess(type,keyword);

        model.addAttribute("list",targetList);
        model.addAttribute("size",targetList.size());
        model.addAttribute("passingTop",certificateService.getMaxPassingRateProcess());
        model.addAttribute("examinationTop",certificateService.getMaxTotalExaminationProcess());
        return "page/certificate/certificateList.html";
    }

    @RequestMapping("/{id}")
    public String certificateDetailPage(Model model,@PathVariable("id") Long id){
        model.addAttribute("certificate",certificateService.getCertificateByIdProcess(id));

        return "page/certificate/certificateDetail.html";
    }
}
