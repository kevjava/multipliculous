package com.kevinja.multipliculous;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevinja.multipliculous.domain.MultiplicationProblem;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class ProblemFragment extends Fragment {

    @Bind(R.id.operand1) TextView operand1;
    @Bind(R.id.operand2) TextView operand2;
    @Bind(R.id.answer) TextView answer;

    public enum Args {
        OPERAND1, OPERAND2
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_problem, null, false);
        Log.i("PROBLEM", "Inflating");

        ButterKnife.bind(this, v);

        if (null != getArguments()) {
            operand1.setText(String.valueOf(getArguments().getInt(Args.OPERAND1.toString())));
            operand2.setText(String.valueOf(getArguments().getInt(Args.OPERAND2.toString())));
        }

        return v;
    }

    public void setAnswer(int newAnswer) {
        answer.setText(String.valueOf(newAnswer));
    }

}
