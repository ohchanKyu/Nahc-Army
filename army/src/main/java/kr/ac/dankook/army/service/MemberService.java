package kr.ac.dankook.army.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.dankook.army.dto.common.Coordinates;
import kr.ac.dankook.army.dto.entity.Member;
import kr.ac.dankook.army.exception.ErrorCode;
import kr.ac.dankook.army.exception.UserNotFoundException;
import kr.ac.dankook.army.exception.UserNotInactiveException;
import kr.ac.dankook.army.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ApiService apiService;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public Member findMemberById(Long id){
        Optional<Member> targetMember = memberRepository.findById(id);
        if (targetMember.isEmpty()){
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }
        return targetMember.get();
    }

    @Transactional(readOnly = true)
    public Member findMemberBySession(HttpServletRequest request){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("member");
        if (loginMember == null){
            throw new UserNotInactiveException(ErrorCode.USER_NOT_INACTIVE);
        }
        return memberRepository.findById(loginMember.getId()).get();
    }

    @Transactional
    public Member editMemberName(String newName,Long id){
        Member targetMember = findMemberById(id);
        targetMember.setName(newName);
        return memberRepository.save(targetMember);
    }
    @Transactional
    public Member editMemberId(String newMemberId,Long id){
        Member targetMember = findMemberById(id);
        targetMember.setUserId(newMemberId);
        return memberRepository.save(targetMember);
    }
    @Transactional
    public Member editMemberPassword(String newPassword,Long id){
        Member targetMember = findMemberById(id);
        targetMember.setPassword(newPassword);
        return memberRepository.save(targetMember);
    }
    @Transactional
    public Member editMemberAddress(String newAddress,Long id){
        Member targetMember = findMemberById(id);
        Coordinates coordinates = apiService.getCoordinateByFullAddress(newAddress);

        targetMember.setAddress(newAddress);
        targetMember.setAddressLatitude(coordinates.getLatitude());
        targetMember.setAddressLongitude(coordinates.getLongitude());
        return memberRepository.save(targetMember);
    }
    @Transactional
    public boolean deleteMemberById(Long id,HttpServletRequest request){
        authService.logoutProcess(request);
        memberRepository.deleteById(id);
        return true;
    }
}
