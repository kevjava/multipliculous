package com.kevinja.multipliculous.logic;

import android.os.Parcel;

import com.kevinja.multipliculous.ProblemsPresenter;
import com.kevinja.multipliculous.domain.ProblemParameters;

/**
 * Created by kev on 2015-09-20.
 */
public class NormalPracticeLogic extends PracticeLogicImpl {

    private int toGo;

    public NormalPracticeLogic(ProblemParameters problemParameters, final int numProblems) {
        super(problemParameters, numProblems);

        toGo = numProblems;
    }

    @Override
    public void startPractice() {
        super.startPractice();
    }

    @Override
    public int getProgress() {
        return total - toGo;
    }

    @Override
    public String getToGoString() {
        return String.format("Problems to go: %d", toGo);
    }

    @Override
    public void answerRight() {
        toGo--;
        problemsSolved++;
    }

    @Override
    public void answerWrong() {
        toGo--;
    }

    @Override
    public boolean isDone() {
        return toGo <= 0;
    }

}
