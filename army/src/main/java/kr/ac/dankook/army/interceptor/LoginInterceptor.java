package kr.ac.dankook.army.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.dankook.army.dto.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Member checkMember = (Member) session.getAttribute("member");
        if (checkMember == null){
            log.info("Exception URL - {}",request.getRequestURL());
            response.sendRedirect("/public/login");
            return false;
        }
        log.info("Request URL - {}",request.getRequestURL());
        return true;
    }
}
