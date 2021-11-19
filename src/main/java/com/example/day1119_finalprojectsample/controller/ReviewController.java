package com.example.day1119_finalprojectsample.controller;

import com.example.day1119_finalprojectsample.service.ReviewService;
import com.example.day1119_finalprojectsample.service.TTS_Service;
import com.example.day1119_finalprojectsample.vo.MemberVO;
import com.example.day1119_finalprojectsample.vo.ReviewVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    TTS_Service ttsService;

    @PostMapping("reviewInsert")
    @ResponseBody
    public String reviewInsert(ReviewVO reviewVO, HttpSession session){

        JSONObject jo = new JSONObject();

        try {
            System.out.println(reviewVO);
            MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
            if(memberVO!=null&&!memberVO.equals("")) {
                reviewVO.setId(memberVO.getId());
                reviewService.reviewInsert(reviewVO);
                String fileName = ttsService.tts("등록되었습니다");
                jo.put("fileName", fileName);

            } else{
                jo.put("msg", "로그인 해주세요");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("msg", e.getMessage());
        }

        return jo.toString();
    }
}
