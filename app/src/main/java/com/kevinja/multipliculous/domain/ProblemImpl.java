package com.kevinja.multipliculous.domain;

/**
 * Created by kev on 2015-09-20.
 */
public abstract class ProblemImpl implements Problem {
    protected final ProblemParameters problemParameters;
    protected Integer answer;

    public ProblemImpl(ProblemParameters problemParameters) {
        this.problemParameters = problemParameters;
        this.answer = null;
    }

    public Integer getAnswer() {
        return answer;
    }

    public ProblemParameters getProblemParameters() {
        return problemParameters;
    }
}
