package com.kevinja.multipliculous.logic;

import com.kevinja.multipliculous.ProblemsPresenter;
import com.kevinja.multipliculous.R;
import com.kevinja.multipliculous.domain.ProblemParameters;

/**
 * Created by kev on 2015-09-20.
 */
public class TimedTrialLogic extends PracticeLogicImpl {
    public TimedTrialLogic(ProblemParameters parameters, int numSeconds) {
        super(parameters, numSeconds);
    }

    @Override
    public void startPractice() {
        super.startPractice();
    }

    @Override
    public String getToGoString() {
        return String.format("%s %d", R.string.seconds_left_label, (getTotal() - getProgress()));
    }

    @Override
    public void answerRight() {
        problemsSolved++;
    }

    @Override
    public void answerWrong() {

    }

    @Override
    public boolean isDone() {
        return (getTotal() - getProgress()) <= 0;
    }
}
