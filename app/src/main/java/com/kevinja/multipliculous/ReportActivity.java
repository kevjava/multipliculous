package com.kevinja.multipliculous;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class ReportActivity extends AppCompatActivity {

    @Bind(R.id.completed)
    TextView completedText;

    @Bind(R.id.correct)
    TextView correctText;

    @Bind(R.id.percentage)
    TextView percentageText;

    @Bind(R.id.go_again_button)
    TextView goAgainButton;

    @OnClick(R.id.go_again_button)
    public void onGoAgainClick(View v) {
        finish();
    }

    public enum Params {
        COMPLETED, CORRECT
    }

    public static void launch(Activity fromActivity, int completed, int correct) {
        Intent intent = new Intent(fromActivity, ReportActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Params.COMPLETED.toString(), completed);
        intent.putExtra(Params.CORRECT.toString(), correct);
        fromActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        int completedNumber = extras.getInt(Params.COMPLETED.toString());
        completedText.setText(String.format("%s %d", getString(R.string.label_completed), completedNumber));
        int correctNumber = extras.getInt(Params.CORRECT.toString());
        correctText.setText(String.format("%s %d", getString(R.string.label_correct), correctNumber));
        float percentageNumber = ((float) correctNumber / (float) completedNumber  * 100.f);
        percentageText.setText(String.format("%s %3.1f%%", getString(R.string.label_percentage), percentageNumber));


    }
}
