package kr.ac.dankook.army.restController;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.dankook.army.dto.entity.Member;
import kr.ac.dankook.army.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/member/signup")
    public ResponseEntity<Member> signupMember(@RequestBody Member member){
        return ResponseEntity.ok(authService.createNewMember(member));
    }

    @PostMapping("/member/login")
    public ResponseEntity<Member> loginMember(@RequestParam("id") String userId, @RequestParam("password") String password, HttpServletRequest request){
        return ResponseEntity.ok(authService.loginProcess(userId,password,request));
    }

    @PostMapping("/member/logout")
    public ResponseEntity<Boolean> logoutMember(HttpServletRequest request){
        return ResponseEntity.ok(authService.logoutProcess(request));
    }

    @GetMapping("/{id}/duplicated")
    public ResponseEntity<Boolean> checkDuplicatedId(@PathVariable String id){
        return ResponseEntity.ok(authService.duplicatedIdProcess(id));
    }

    @GetMapping("/{name}/getId")
    public ResponseEntity<List<String>> getIdByName(@PathVariable String name){
        return ResponseEntity.ok(authService.findIdProcess(name));
    }

    @GetMapping("/{name}/{id}/getPassword")
    public ResponseEntity<String> getPasswordByNameAndId(@PathVariable("name") String name, @PathVariable("id") String id){
        return ResponseEntity.ok(authService.findPasswordProcess(name,id));
    }
}
