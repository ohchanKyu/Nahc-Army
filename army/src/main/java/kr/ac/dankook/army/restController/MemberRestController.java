package kr.ac.dankook.army.restController;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.dankook.army.dto.entity.Member;
import kr.ac.dankook.army.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Long id){
        return ResponseEntity.ok(memberService.findMemberById(id));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<Member> updateMemberName(@RequestParam("name") String name,@PathVariable("id") Long id){
        return ResponseEntity.ok(memberService.editMemberName(name,id));
    }
    @PatchMapping("/{id}/id")
    public ResponseEntity<Member> updateMemberId(@RequestParam("userId") String userId,@PathVariable("id") Long id){
        return ResponseEntity.ok(memberService.editMemberId(userId,id));
    }
    @PatchMapping("/{id}/password")
    public ResponseEntity<Member> updateMemberPassword(@RequestParam("password") String userPassword,@PathVariable("id") Long id){
        return ResponseEntity.ok(memberService.editMemberPassword(userPassword,id));
    }
    @PatchMapping("/{id}/address")
    public ResponseEntity<Member> updateMemberAddress(@RequestParam("address") String address,@PathVariable("id") Long id){
        return ResponseEntity.ok(memberService.editMemberAddress(address,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMember(@PathVariable Long id, HttpServletRequest request){
        return ResponseEntity.ok(memberService.deleteMemberById(id,request));
    }

}
