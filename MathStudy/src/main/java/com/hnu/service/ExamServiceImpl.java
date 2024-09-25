package com.hnu.service;

import com.hnu.entity.Question;
import com.hnu.utils.AbstractGenerator;
import com.hnu.utils.GeneratorJunior;
import com.hnu.utils.GeneratorPrimary;
import com.hnu.utils.GeneratorSenior;
import org.springframework.stereotype.Service;

import net.objecthunter.exp4j.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class ExamServiceImpl implements ExamServiceInterface {

    @Override
    public List<Question> generateExam(String identity, int num) {
        AbstractGenerator generator;
        if (identity.equals("1")) {
            generator = new GeneratorPrimary();
        } else if (identity.equals("2")) {
            generator = new GeneratorJunior();
        } else {
            generator = new GeneratorSenior();
        }
        Set<Integer> quesCodes = new HashSet<>();
        List<Question> questions = new ArrayList<>();
        Random ran = new Random();
        for (int i = 0; i < num; i++) {
            Question aQuestion = new Question();
            String generatedQuestion = generator.generatQues(ran.nextInt(4) + 2);
            int questionHashCode = generatedQuestion.hashCode();

            if (quesCodes.contains(questionHashCode)) {
                i--;
            } else {
                quesCodes.add(questionHashCode);
                aQuestion.setQuestion(generatedQuestion);

                String parsedExpression = parseExpression(aQuestion.getQuestion());
                String result = evaluateQues(parsedExpression);

                int correctIndex = ran.nextInt(4);
                aQuestion.setAnswer(String.valueOf(correctIndex));
                String[] options = generateRandomOption(Double.parseDouble(result), correctIndex);
                aQuestion.setOptions(options);
                questions.add(aQuestion);
            }
        }
        return questions;
    }

    @Override
    public String evaluateQues(String question) {
        Expression e = new ExpressionBuilder(question).build();
        double result = e.evaluate();

        return String.format("%.2f", result);
    }

    @Override
    public String parseExpression(String question) {
        // 处理三角函数 sin, cos, tan 和角度转换为弧度
        question = question.replaceAll("30°", "(pi/6)");
        question = question.replaceAll("45°", "(pi/4)");
        question = question.replaceAll("60°", "(pi/3)");

        // 处理平方符号，将²替换为^2
        question = question.replaceAll("²", "^2");

        // 处理平方根，将√x替换为sqrt(x)
        Pattern sqrtPattern = Pattern.compile("√(\\d+)");
        Matcher sqrtMatcher = sqrtPattern.matcher(question);
        while (sqrtMatcher.find()) {
            String number = sqrtMatcher.group(1);
            question = question.replace("√" + number, "sqrt(" + number + ")");
        }

        if (question.endsWith(" = ")) {
            question = question.substring(0, question.length() - 3);
        }

        return question;
    }

    @Override
    public String[] generateRandomOption(double correctAnswer, int correctIndex) {
        String[] options = new String[4];
        options[correctIndex] = String.valueOf(correctAnswer);
        Random ran = new Random();
        Set<Double> generatedValues = new HashSet<>();

        // 生成偏差值的函数
        while (generatedValues.size() < 3) {
            double minDeviationFactor = ran.nextDouble() * 5; // 根据需要调整
            double deviationFactor = Math.max(minDeviationFactor, (0.2 * (correctAnswer + ran.nextInt(3) - 1)));

            // 随机生成一个偏差值
            double deviation = (ran.nextDouble() - 0.5) * deviationFactor;
            double optionValue = Double.parseDouble(String.format("%.2f", correctAnswer + deviation));

            // 确保选项唯一
            if (!generatedValues.contains(optionValue) && optionValue != correctAnswer) {
                generatedValues.add(optionValue);
            }
        }

        // 将唯一值填入选项中
        int index = 0;
        for (Double value : generatedValues) {
            if (index < correctIndex) {
                options[index] = String.valueOf(value);
            } else {
                options[index + 1] = String.valueOf(value);
            }
            index++;
        }

        return options;
    }


}
