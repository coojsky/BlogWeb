package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {


    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody Member member, HttpServletRequest request){
        //db연동
        if(true){
            HttpSession session = request.getSession();
            Member m = new Member();
            m.setId(member.getId());
            // DB를 통한 권한 받아오기 m.setLevel("vip");
            session.setAttribute("member",m);
            return ResponseEntity.ok().body(m);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
