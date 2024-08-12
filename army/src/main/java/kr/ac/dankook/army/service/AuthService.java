package kr.ac.dankook.army.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.dankook.army.dto.common.Authority;
import kr.ac.dankook.army.dto.common.Coordinates;
import kr.ac.dankook.army.dto.entity.Member;
import kr.ac.dankook.army.exception.ErrorCode;
import kr.ac.dankook.army.exception.UserNotFoundException;
import kr.ac.dankook.army.exception.UserNotInactiveException;
import kr.ac.dankook.army.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final ApiService apiService;

    @Value("${admin.id}")
    private String adminId;
    @Value("${admin.password}")
    private String adminPassword;

    @Transactional
    public Member createNewMember(Member member){
        Optional<Member> errorMember = memberRepository.findByUserIdAndPassword(member.getUserId(), member.getPassword());
        if (errorMember.isPresent()){
            return null;
        }
        String newUserId = member.getUserId();
        String newUserPassword = member.getPassword();
        Coordinates coordinates = apiService.getCoordinateByFullAddress(member.getAddress());

        member.setDate(LocalDate.now());
        member.setAddressLatitude(coordinates.getLatitude());
        member.setAddressLongitude(coordinates.getLongitude());

        if (newUserId.equals(adminId) && newUserPassword.equals(adminPassword)){
            member.setAuthority(Authority.valueOf("ROLE_ADMIN"));
        }else{
            member.setAuthority(Authority.valueOf("ROLE_USER"));
        }
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member loginProcess(String userId, String password, HttpServletRequest request){

        Optional<Member> targetMember = memberRepository.findByUserIdAndPassword(userId,password);
        if (targetMember.isEmpty()){
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }
        HttpSession session = request.getSession();
        session.setAttribute("member",targetMember.get());
        session.setMaxInactiveInterval(3600);
        return targetMember.get();
    }

    @Transactional(readOnly = true)
    public boolean logoutProcess(HttpServletRequest request){

        HttpSession session = request.getSession();
        Member checkMember = (Member) session.getAttribute("member");
        if (checkMember != null){
            session.removeAttribute("member");
            return true;
        }
        throw new UserNotInactiveException(ErrorCode.USER_NOT_INACTIVE);
    }

    @Transactional(readOnly = true)
    public boolean duplicatedIdProcess(String userId){
        Optional<Member> targetMember = memberRepository.findByUserId(userId);
        return targetMember.isEmpty();
    }

    @Transactional(readOnly = true)
    public List<String> findIdProcess(String name){
        List<Member> targetMembers = memberRepository.findByName(name);
        List<String> idList = new ArrayList<>();
        for (Member member : targetMembers){
            idList.add(member.getUserId());
        }
        return idList;
    }

    @Transactional(readOnly = true)
    public String findPasswordProcess(String name,String userId){
        Optional<Member> targetMember = memberRepository.findByNameAndUserId(name,userId);
        if (targetMember.isPresent()){
            return targetMember.get().getPassword();
        }else{
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }
    }
}
