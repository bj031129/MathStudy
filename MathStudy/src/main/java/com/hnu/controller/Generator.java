package com.hnu.controller;

import com.hnu.entity.Question;
import com.hnu.service.ExamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // 允许来自该地址的请求
public class Generator {

    @Autowired
    private ExamServiceInterface examService;

    @PostMapping("/generate-questions")
    public List<Question> generate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String identity = request.getParameter("identity");
        int questionCount = Integer.parseInt(request.getParameter("questionCount"));

        List<Question> questions = examService.generateExam(identity, questionCount);

        response.setStatus(HttpServletResponse.SC_OK);
        return questions;
    }
}
