package com.kevinja.multipliculous;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kev on 2015-09-20.
 */
public class NumberpadFragment extends Fragment {

    @Bind(R.id.button0) Button button0;
    @Bind(R.id.button1) Button button1;
    @Bind(R.id.button2) Button button2;
    @Bind(R.id.button3) Button button3;
    @Bind(R.id.button4) Button button4;
    @Bind(R.id.button5) Button button5;
    @Bind(R.id.button6) Button button6;
    @Bind(R.id.button7) Button button7;
    @Bind(R.id.button8) Button button8;
    @Bind(R.id.button9) Button button9;
    @Bind(R.id.buttonOK) Button buttonOK;
    @Bind(R.id.buttonClear) Button buttonClear;
    private NumberpadClickListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_numberpad, container);
        Log.i("NUMBERPAD", "Inflating");

        ButterKnife.bind(this, v);

        return v;
    }

    public void setClickListener(NumberpadClickListener listener) {
        this.listener = listener;
    }

    @OnClick( { R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9 })
    public void onNumberClicked(View v) {
        int num;
        switch(v.getId()) {
            case R.id.button1:
                num = 1;
                break;
            case R.id.button2:
                num = 2;
                break;
            case R.id.button3:
                num = 3;
                break;
            case R.id.button4:
                num = 4;
                break;
            case R.id.button5:
                num = 5;
                break;
            case R.id.button6:
                num = 6;
                break;
            case R.id.button7:
                num = 7;
                break;
            case R.id.button8:
                num = 8;
                break;
            case R.id.button9:
                num = 9;
                break;
            case R.id.button0:
                num = 0;
                break;
            default:
                num = -1;
                break;
        }
        listener.onNumberClicked(num);
    }

    @OnClick(R.id.buttonOK)
    public void onOKClicked() {
        listener.onOKClicked();
    }

    @OnClick(R.id.buttonClear)
    public void onClearClicked() {
        listener.onClearClicked();
    }
}
