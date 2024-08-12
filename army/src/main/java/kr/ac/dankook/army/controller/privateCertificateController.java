package kr.ac.dankook.army.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.dankook.army.dto.entity.CertificateLocation;
import kr.ac.dankook.army.service.CertificateLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/private/certificate")
@RequiredArgsConstructor
public class privateCertificateController {

    private final CertificateLocationService certificateLocationService;

    @RequestMapping("")
    public String privateCertificatePage(HttpServletRequest httpServletRequest, Model model){
        List<CertificateLocation> dataBaseList = certificateLocationService.getDataBaseCertificationLocationList(httpServletRequest);

        model.addAttribute("apiList",certificateLocationService.getAroundCertificateLocationProcess(httpServletRequest));
        model.addAttribute("dataBaseList",dataBaseList);
        model.addAttribute("regionCode",certificateLocationService.getUserRegionCode(httpServletRequest));
        return "page/certificate/certificate.html";
    }
}
