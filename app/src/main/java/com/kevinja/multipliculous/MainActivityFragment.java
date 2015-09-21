package com.kevinja.multipliculous;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Bind(R.id.problems_spinner)
    Spinner problemSpinner;

    private Integer[] problemArray = new Integer[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

    public MainActivityFragment() {
    }

    @OnClick(R.id.go_button)
    public void onGoButtonClick(View view) {
        ProblemsActivity.launchNormal((MainActivity)getActivity(), getNumQuestions());
    }

    private int getNumQuestions() {
        return (Integer) problemSpinner.getSelectedItem();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);

        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, problemArray);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        problemSpinner.setAdapter(spinnerAdapter);

        return v;
    }
}
