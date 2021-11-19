package com.example.day1119_finalprojectsample.service;

import com.example.day1119_finalprojectsample.dao.ReviewDAO;
import com.example.day1119_finalprojectsample.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements ReviewDAO{

    @Autowired
    ReviewDAO reviewDAO;

    public void reviewInsert(ReviewVO reviewVO) throws Exception {
        reviewDAO.reviewInsert(reviewVO);
    }
}
