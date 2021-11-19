package com.example.day1119_finalprojectsample.controller;

import com.example.day1119_finalprojectsample.service.MemberService;
import com.example.day1119_finalprojectsample.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("memberInsert")
    @ResponseBody
    public String memberInsert(MemberVO memberVO) {
        System.out.println(memberVO);
        try {
            memberService.memberInsert(memberVO);
            return "완료";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @PostMapping("login")
    @ResponseBody
    public String login(MemberVO memberVO, HttpSession session) {
        System.out.println("try login");
        String name;
        try {
            name = memberService.login(memberVO);

            if(name!=null&&!name.equals("")){
                session.setAttribute("memberVO", memberVO);
                return memberVO.getId();
            }else{
                return "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }
    @PostMapping("logout")
    @ResponseBody
    public void logout(HttpSession session){
        session.invalidate();
    }
}
