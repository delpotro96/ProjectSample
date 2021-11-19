package com.example.day1119_finalprojectsample.dao;

import com.example.day1119_finalprojectsample.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDAO {
    public void memberInsert(MemberVO memberVO) throws Exception;

    public String login(MemberVO memberVO) throws Exception;
}
