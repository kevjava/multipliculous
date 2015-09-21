package com.kevinja.multipliculous.logic;

import android.os.Parcelable;

import com.kevinja.multipliculous.domain.Problem;

/**
 *
 */
public interface PracticeLogic {
    void startPractice();
    boolean checkAnswer(int answer);
    Problem newProblem();
    void endPractice();
    int getTotal();
    int getProgress();
    String getToGoString();
    String getSolvedString();
    void answerRight();
    void answerWrong();
    boolean isDone();
    int getSolved();
}
