package org.example.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.controller.Member;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//filter의 기능을 수행한다.
@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    //Controller에 들어가기 전에 전처리
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle() 호출됨");

        HttpSession session = request.getSession(false);
        if(session != null)
        {
            Member member = (Member)session.getAttribute("member");
            if(member != null){
                String level = member.getLevel();
                System.out.println(level);
                if(level != null && level.equals(("vip"))) {
                    return true;
                }
            }
        }

        return false;
        //return HandlerInterceptor.super.preHandle(request, response, handler)/* true인 경우 들어감, false인 경우 안들어감 */;
    }

    @Override
    //Controller에 나가기 전에 후처리
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle() 호출됨");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
