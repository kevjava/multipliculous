package com.kevinja.multipliculous;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 *
 */
public class ProblemsActivity extends AppCompatActivity {

    private ProblemsPresenter presenter;

    private enum Params {
        MODE, NUM_QUESTIONS, NUM_SECONDS
    }

    enum Modes {
        NORMAL, TIMED_TRIAL
    }

    public static void launchNormal(MainActivity fromActivity, int numberOfQuestions) {
        Intent intent = new Intent(fromActivity, ProblemsActivity.class);
        intent.putExtra(Params.MODE.toString(), Modes.NORMAL.toString());
        intent.putExtra(Params.NUM_QUESTIONS.toString(), numberOfQuestions);
        fromActivity.startActivityForResult(intent, MainActivity.REQUEST_CODE_PROBLEMS);
    }

    private static void launchTimedTrial(Activity fromActivity, int numberOfSeconds) {
        Intent intent = new Intent(fromActivity, ProblemsActivity.class);
        intent.putExtra(Params.MODE.toString(), Modes.TIMED_TRIAL.toString());
        intent.putExtra(Params.NUM_SECONDS.toString(), numberOfSeconds);
        fromActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);

        presenter = new ProblemsPresenter(this);

        Bundle extras = getIntent().getExtras();
        String modeString = extras.getString(Params.MODE.toString());
        if (modeString != null && modeString.equals(Modes.TIMED_TRIAL.toString())) {
            presenter.setMode(Modes.TIMED_TRIAL);
        } else {
            presenter.setMode(Modes.NORMAL);
        }

        switch (presenter.getMode()) {
            case NORMAL:
                presenter.setNumProblems(extras.getInt(Params.NUM_QUESTIONS.toString()));
                break;
            case TIMED_TRIAL:
                presenter.setNumSeconds(extras.getInt(Params.NUM_SECONDS.toString()));
                break;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        presenter.setProgressBarFragment((ProgressBarFragment) getFragmentManager().findFragmentById(R.id.progressBarFragment));
        presenter.setNumberpadFragment((NumberpadFragment) getFragmentManager().findFragmentById(R.id.numberPadFragment));

        presenter.startPractice();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
