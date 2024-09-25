package com.hnu.utils;

public class GeneratorPrimary extends AbstractGenerator {
    //小学题目没有单目运算符
    @Override
    protected StringBuilder wrapVal(StringBuilder val) {
        return val;
    }

    //小学题目没有特殊需求
    @Override
    protected void specialNeed() {
    }
}
