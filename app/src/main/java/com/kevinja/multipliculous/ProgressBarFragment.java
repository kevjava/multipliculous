package com.kevinja.multipliculous;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kevinja.multipliculous.logic.PracticeLogic;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class ProgressBarFragment extends Fragment {

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.label_togo)
    TextView toGoLabel;

    @Bind(R.id.label_solved)
    TextView solvedLabel;

    public enum Args {
        PRACTICE_LOGIC;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_progressbar, container);
        Log.i("PROGRESS", "Inflating");

        ButterKnife.bind(this, v);

        return v;
    }

    public void setMax(int max) {
        progressBar.setMax(max);
    }

    public void update(int progress, String toGoString, String solvedString) {
        progressBar.setProgress(progress);
        toGoLabel.setText(toGoString);
        solvedLabel.setText(solvedString);
    }
}
