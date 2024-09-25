package com.hnu.utils;

import java.util.Random;

public class GeneratorSenior extends AbstractGenerator {
    private final String[] trigFunc = {"sin", "cos", "tan"};
    private final String[] specialValues = {"30", "45", "60"};
    private final AbstractGenerator lowerGenerator = new GeneratorJunior();
    private int sin;
    private int cos;
    private int tan;


    @Override
    protected StringBuilder wrapVal(StringBuilder val) {
        StringBuilder valA = new StringBuilder();
        Random ran = new Random();

        int trigIndex = -1;
        if (sin == 1) {
            trigIndex = 0; // sin
            sin = 0;
        } else if (cos == 1) {
            trigIndex = 1; // cos
            cos = 0;
        } else if (tan == 1) {
            trigIndex = 2; // tan
            tan = 0;
        } else {
            int randomVal = ran.nextInt(100);
            if (randomVal < 5) {
                trigIndex = 0; // sin
            } else if (randomVal < 10) {
                trigIndex = 1; // cos
            } else if (randomVal < 15) {
                trigIndex = 2; // tan
            }
        }

        if (trigIndex != -1) {
            val = toSpecialValue(val);
            valA.append(trigFunc[trigIndex]);
            valA.append(val);
            valA.append("°");
        } else {
            valA.append(val);
        }
        return lowerGenerator.wrapVal(valA);
    }

    @Override
    protected void specialNeed() {
        Random ran = new Random();
        if (ran.nextInt(100) < 33) {
            sin = 0;
            cos = 0;
            tan = 1;
        } else if (ran.nextInt(100) < 66) {
            sin = 0;
            cos = 1;
            tan = 0;
        } else {
            sin = 1;
            cos = 0;
            tan = 0;
        }
    }

    private StringBuilder toSpecialValue(StringBuilder value) {
        Random ran = new Random();
        boolean isSpecial = false;
        for (String special : specialValues) {
            if (value.toString().equals(special)) {
                isSpecial = true;
                break;
            }
        }

        if (!isSpecial) {
            value = new StringBuilder(specialValues[ran.nextInt(3)]);
        }
        return value;
    }
}
