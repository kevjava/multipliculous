package com.kevinja.multipliculous.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class MultiplicationProblem extends ProblemImpl {

    private int operand1;
    private int operand2;

    public MultiplicationProblem(ProblemParameters problemParameters) {
        super(problemParameters);
        generate();
    }

    public int getOperand1() {
        return operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    @Override
    public void generate() {
        operand1 = generateOperand1();
        operand2 = generateOperand2();
        shuffleOperands();
        answer = operand1 * operand2;
    }

    private void shuffleOperands() {
        if (Math.random() * 2.0 > 1.0) {
            int tmp = operand1;
            operand1 = operand2;
            operand2 = tmp;
        }
    }

    public int generateOperand1() {
        Integer[] possibles = getPossibleOperands();
        return possibles[ (int) (Math.random() * possibles.length) ];
    }

    private Integer[] getPossibleOperands() {
        Map<Integer, Boolean> paramList = getProblemParameters().getParamList();
        List<Integer> possibles = new ArrayList<Integer>();
        for (Integer i : paramList.keySet()) {
            if (paramList.get(i)) {
                possibles.add(i);
            }
        }

        Integer[] ret = new Integer[possibles.size()];
        return possibles.toArray(ret);
    }

    public int generateOperand2() {
        return (int) (Math.random() * 10);
    }
}
