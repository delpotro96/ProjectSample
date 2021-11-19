package com.example.day1119_finalprojectsample.service;

import com.example.day1119_finalprojectsample.dao.MemberDAO;
import com.example.day1119_finalprojectsample.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements MemberDAO {

    @Autowired
    MemberDAO memberDAO;

    @Override
    public void memberInsert(MemberVO memberVO) throws Exception {
        memberDAO.memberInsert(memberVO);
    }

    public String login(MemberVO memberVO) throws Exception {
        return memberDAO.login(memberVO);
    }
}
