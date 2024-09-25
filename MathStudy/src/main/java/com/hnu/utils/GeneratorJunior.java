package com.hnu.utils;

import java.util.Random;

public class GeneratorJunior extends AbstractGenerator {
    private final String[] unaryOperands = {"√", "²"};
    private final String[] specialValues = {"1", "4", "9", "16", "25", "36", "49", "64", "81"};
    private final AbstractGenerator lowerGenerator = new GeneratorPrimary();
    private int leftNeed;
    private int rightNeed;

    @Override
    protected StringBuilder wrapVal(StringBuilder val) {
        StringBuilder valA = new StringBuilder();
        int unaryIndex = -1;

        unaryIndex = takeIndex();

        if (unaryIndex == 0) {
            try {
                Integer.parseInt(val.toString());  // 尝试将字符串解析为整数
                val = toSpecialValue(val);
                valA.append(unaryOperands[unaryIndex]);
                valA.append(val);
            } catch (NumberFormatException e) {
                valA.append(val);
            }
        } else if (unaryIndex == 1) {
            try {
                Integer.parseInt(val.toString());  // 尝试将字符串解析为整数
                valA.append(val);
                valA.append(unaryOperands[unaryIndex]);
            } catch (NumberFormatException e) {
                valA.append(val);
            }
        } else {
            valA.append(val);
        }
        return lowerGenerator.wrapVal(valA);
    }


    @Override
    protected void specialNeed() {
        if (new Random().nextBoolean()) {
            leftNeed = 1;
            rightNeed = 0;
        } else {
            leftNeed = 0;
            rightNeed = 1;
        }
    }

    private StringBuilder toSpecialValue(StringBuilder value) {
        Random ran = new Random();
        boolean isSpecial = false;
        for (String special : specialValues) {
            if (value.toString().equals(special)) {
                isSpecial = true;
            }
        }

        if (!isSpecial) {
            value = new StringBuilder(specialValues[ran.nextInt(9)]);
        }
        return value;
    }

    private int takeIndex() {
        Random ran = new Random();

        int unaryIndex = -1;
        if (leftNeed == 1) {
            unaryIndex = 0;
            leftNeed = 0;
        } else if (rightNeed == 1) {
            unaryIndex = 1;
            rightNeed = 0;
        } else {
            if (ran.nextInt(100) < 5) {
                unaryIndex = 0;
            } else if (ran.nextInt(100) < 10) {
                unaryIndex = 1;
            }
        }
        return unaryIndex;
    }
}
