package com.example.rachit.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int teamA = 0;
    int teamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void reset(View v) {
        TextView scoreA = (TextView) findViewById(R.id.teamA_score);
        TextView scoreB = (TextView) findViewById(R.id.teamB_score);

        scoreA.setText(String.valueOf(0));
        scoreB.setText(String.valueOf(0));

        teamA = 0;
        teamB = 0;
    }

    public void setScore() {
        TextView scoreA = (TextView) findViewById(R.id.teamA_score);
        TextView scoreB = (TextView) findViewById(R.id.teamB_score);

        scoreA.setText(String.valueOf(teamA));
        scoreB.setText(String.valueOf(teamB));
    }

    public void increasePoint(View view) {
        switch (view.getId()) {
            case R.id.threeA: teamA += 3; break;
            case R.id.twoA: teamA += 2; break;
            case R.id.oneA: teamA += 1; break;

            case R.id.threeB: teamB += 3; break;
            case R.id.twoB: teamB += 2; break;
            case R.id.oneB: teamB += 1; break;
        }

        setScore();
    }
}
