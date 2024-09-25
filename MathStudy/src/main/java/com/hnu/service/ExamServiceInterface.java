package com.hnu.service;

import com.hnu.entity.Question;

import java.util.List;

public interface ExamServiceInterface {
    List<Question> generateExam(String identity, int num);

    String parseExpression(String question);

    String evaluateQues(String question);

    String[] generateRandomOption(double correctAnswer, int correctIndex);


}
