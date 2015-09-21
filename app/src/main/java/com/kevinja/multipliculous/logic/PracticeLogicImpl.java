package com.kevinja.multipliculous.logic;

import com.kevinja.multipliculous.domain.MultiplicationProblem;
import com.kevinja.multipliculous.domain.Problem;
import com.kevinja.multipliculous.domain.ProblemImpl;
import com.kevinja.multipliculous.domain.ProblemParameters;

/**
 *
 */
public abstract class PracticeLogicImpl implements PracticeLogic {

    protected int total;
    protected int problemsSolved;
    private ProblemImpl problem;
    private ProblemParameters problemParameters;

    public PracticeLogicImpl(final ProblemParameters problemParameters, final int total) {
        problemsSolved = 0;
        this.problemParameters = problemParameters;
        this.total = total;
    }

    @Override
    public void startPractice() {
        newProblem();
    }

    @Override
    public void endPractice() {

    }

    @Override
    public boolean checkAnswer(int answer) {
        return answer == problem.getAnswer();
    }

    @Override
    public Problem newProblem() {
        problem = new MultiplicationProblem(problemParameters);
        return problem;
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public int getProgress() {
        return problemsSolved;
    }

    @Override
    public int getSolved() {
        return problemsSolved;
    }

    @Override
    public String getSolvedString() {
        return String.format("Solved: %d", problemsSolved);
    }

}
