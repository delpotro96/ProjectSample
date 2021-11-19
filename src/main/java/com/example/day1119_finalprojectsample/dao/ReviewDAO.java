package com.example.day1119_finalprojectsample.dao;

import com.example.day1119_finalprojectsample.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReviewDAO {

    public void reviewInsert(ReviewVO reviewVO) throws Exception;
}
