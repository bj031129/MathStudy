package com.hnu.utils;

import java.util.Random;

public abstract class AbstractGenerator {
    //双目运算符
    protected StringBuilder[] doubleOperands = {
            new StringBuilder(" + "),
            new StringBuilder(" - "),
            new StringBuilder(" * "),
            new StringBuilder(" / ")
    };

    //使用单目运算符包裹
    protected abstract StringBuilder wrapVal(StringBuilder val);

    protected abstract void specialNeed();

    private class TreeNode {
        StringBuilder val;
        TreeNode left;
        TreeNode right;

        public TreeNode(StringBuilder val) {
            this.val = val;
            this.left = this.right = null;
        }

        public TreeNode(TreeNode left, StringBuilder val, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public StringBuilder treeToString() {
            StringBuilder aQuestion = new StringBuilder();
            if (left != null && right != null) {
                aQuestion.append(left.treeToString());
                aQuestion.append(val);
                aQuestion.append(right.treeToString());
            } else {
                aQuestion.append(val);
            }
            return aQuestion;
        }
    }

    private TreeNode createTree(int size) {
        Random ran = new Random();
        if (size == 1)
            return new TreeNode(wrapVal(new StringBuilder(String.valueOf(ran.nextInt(100) + 1))));

        TreeNode sub1 = createTree(size / 2);
        TreeNode sub2 = createTree((size + 1) / 2);

        if (Math.random() < 0.5)
            return new TreeNode(sub1, doubleOperands[ran.nextInt(4)], sub2);
        else
            return new TreeNode(sub2, doubleOperands[ran.nextInt(4)], sub1);
    }

    public String generatQues(int size) {
        specialNeed();
        //使用二叉树来构造题目
        StringBuilder aQuestion = createTree(size).treeToString();
        aQuestion.append(" = ");
        return aQuestion.toString();
    }


}