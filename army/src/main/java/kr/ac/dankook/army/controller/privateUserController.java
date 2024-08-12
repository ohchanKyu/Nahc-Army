package kr.ac.dankook.army.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.dankook.army.service.MemberService;
import kr.ac.dankook.army.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private/me")
@RequiredArgsConstructor
public class privateUserController {

    private final MemberService memberService;
    private final PlannerService plannerService;

    @RequestMapping("")
    public String certificatePage(Model model, HttpServletRequest request){
        model.addAttribute("user",memberService.findMemberBySession(request));
        model.addAttribute("user_role",memberService.findMemberBySession(request).getAuthority().toString());
        model.addAttribute("plannerCount",plannerService.getAllPlannerProcess(request).size());
        return "page/user.html";
    }
}
