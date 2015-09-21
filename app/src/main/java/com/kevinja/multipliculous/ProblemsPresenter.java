package com.kevinja.multipliculous;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;

import com.kevinja.multipliculous.domain.MultiplicationProblem;
import com.kevinja.multipliculous.domain.MultiplicationProblemParameters;
import com.kevinja.multipliculous.domain.Problem;
import com.kevinja.multipliculous.domain.ProblemParameters;
import com.kevinja.multipliculous.logic.NormalPracticeLogic;
import com.kevinja.multipliculous.logic.PracticeLogic;
import com.kevinja.multipliculous.logic.TimedTrialLogic;

import java.util.HashMap;
import java.util.Timer;

/**
 *
 */
public class ProblemsPresenter implements NumberpadClickListener {

    private final ProblemsActivity activity;
    private final Timer timer;
    private ProblemsActivity.Modes mode;
    private int numProblems;
    private int numSeconds;
    private int currentAnswer = 0;

    private PracticeLogic practiceLogic;
    private Problem currentProblem;
    private NumberpadFragment numberpadFragment;
    private ProgressBarFragment progressBarFragment;

    public ProblemsPresenter(ProblemsActivity problemsActivity) {
        activity = problemsActivity;
        timer = new Timer("problemTimer", true);
    }

    public void setMode(ProblemsActivity.Modes mode) {
        this.mode = mode;
    }

    public ProblemsActivity.Modes getMode() {
        return mode;
    }

    public void setNumProblems(int numProblems) {
        this.numProblems = numProblems;
    }

    public void setNumSeconds(int numSeconds) {
        this.numSeconds = numSeconds;
    }

    public void startPractice() {
        switch(mode) {
            case TIMED_TRIAL:
                startTimedTrialPractice();
                break;
            case NORMAL:
                startNormalPractice();
                break;
        }
    }

    private void startNormalPractice() {
        practiceLogic = new NormalPracticeLogic(getParameters(), numProblems);
        practiceLogic.startPractice();

        ProgressBarFragment progressBarFragment = (ProgressBarFragment)
                activity.getFragmentManager().findFragmentById(R.id.progressBarFragment);
        progressBarFragment.setMax(practiceLogic.getTotal());
        updateProgressBar();

        newProblem();
    }

    private void newProblem() {
        currentProblem = practiceLogic.newProblem();
        currentAnswer = 0;
        replaceProblemFragment(currentProblem);
    }

    private void replaceProblemFragment(Problem currentProblem) {

        ProblemFragment problemFragment = new ProblemFragment();
        Bundle args = new Bundle();
        args.putInt(ProblemFragment.Args.OPERAND1.toString(), ((MultiplicationProblem) currentProblem).getOperand1());
        args.putInt(ProblemFragment.Args.OPERAND2.toString(), ((MultiplicationProblem) currentProblem).getOperand2());
        problemFragment.setArguments(args);

        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(R.id.problem_layout, problemFragment, "problem");
        transaction.commit();
    }

    private void updateProgressBar() {
        ProgressBarFragment progressBarFragment = (ProgressBarFragment) activity.getFragmentManager().findFragmentById(R.id.progressBarFragment);
        progressBarFragment.update(practiceLogic.getProgress(), practiceLogic.getToGoString(),
                practiceLogic.getSolvedString());
    }

    private void startTimedTrialPractice() {
        practiceLogic = new TimedTrialLogic(getParameters(), numSeconds);
        practiceLogic.startPractice();
    }

    private ProblemParameters getParameters() {
        // TODO: Implement settings.
        return new MultiplicationProblemParameters(new HashMap<Integer, Boolean>() {{
            put(1, true);
            put(2, true);
            put(3, true);
            put(4, true);
            put(5, true);
            put(6, true);
            put(7, true);
            put(8, true);
            put(9, true);
            put(0, true);
        }});
    }

    @Override
    public void onNumberClicked(int number) {
        if (currentAnswer < 10) {
            currentAnswer = currentAnswer * 10 + number;
            updateAnswer();
        }
    }

    private void updateAnswer() {
        ProblemFragment problemFragment = (ProblemFragment) activity.getFragmentManager().findFragmentByTag("problem");
        problemFragment.setAnswer(currentAnswer);
    }

    @Override
    public void onClearClicked() {
        currentAnswer = 0;
        updateAnswer();
    }

    @Override
    public void onOKClicked() {
        if (practiceLogic.checkAnswer(currentAnswer)) {
            flashText(R.id.answer_right);
            practiceLogic.answerRight();
        } else {
            flashText(R.id.answer_wrong);
            practiceLogic.answerWrong();
        }
        newProblem();
        updateProgressBar();

        if (practiceLogic.isDone()) {
            practiceLogic.endPractice();
            endPractice();
        }
    }

    private void endPractice() {
//        ReportActivity.launch(activity, practiceLogic.getSolved(), practiceLogic.getTotal());
        Intent resultIntent = new Intent();
        resultIntent.putExtra(ReportActivity.Params.COMPLETED.toString(), practiceLogic.getTotal());
        resultIntent.putExtra(ReportActivity.Params.CORRECT.toString(), practiceLogic.getSolved());
        activity.setResult(Activity.RESULT_OK, resultIntent);
        activity.finish();
    }

    private void flashText(@IdRes int resId) {
        TextView textView = (TextView) activity.findViewById(resId);
        textView.setVisibility(View.VISIBLE);
        textView.setAlpha(1.0f);

        textView.animate().alpha(0.0f).setDuration(1000).start();
    }

    public void setNumberpadFragment(NumberpadFragment numberpadFragment) {
        this.numberpadFragment = numberpadFragment;
        numberpadFragment.setClickListener(this);
    }

    public void setProgressBarFragment(ProgressBarFragment progressBarFragment) {
        this.progressBarFragment = progressBarFragment;
    }
}
