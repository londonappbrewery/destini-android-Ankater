package com.londonappbrewery.destini;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button mButtonTop;
    Button mButtonBottom;
    TextView mStoryTextView;
    int currentStep;
    // TODO: Steps 4 & 8 - Declare member variables here:


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        Log.d("Debug", "Create variables");

        if(savedInstanceState != null){
            currentStep = savedInstanceState.getInt("currentStep",1);
        }else{
            currentStep = 1;
        }
        mStoryTextView = findViewById(R.id.storyTextView);
        mButtonTop = findViewById(R.id.buttonTop);
        mButtonBottom = findViewById(R.id.buttonBottom);

        Log.d("Debug", "End Create variables");
        updateStage(1);
        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DebugTop","Top Debug");
                switch (currentStep){
                    case 1:
                    case 2:
                        updateStage(3);
                        break;
                    case 3:
                        updateStage(6);
                        break;
                }
            }
        });



        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DebugBottom", "Bottom Debug");
                switch (currentStep){
                    case 1:
                        updateStage(2);
                        break;
                    case 2:
                        updateStage(4);
                        break;
                    case 3:
                        updateStage(5);
                        break;
                }
            }
        });
    }

    private void updateStage(int nextStep){
        Log.d("DebugStep", ""+nextStep);
        currentStep = nextStep;
        Resources res = getResources();
        String nextStepText = "T" + nextStep;
        if(nextStep <= 3){
            String story = nextStepText + "_Story";
            String ans1 = nextStepText + "_Ans1";
            String ans2 = nextStepText + "_Ans2";

            int storyId = res.getIdentifier(story,"string",getPackageName());
            int ans1Id = res.getIdentifier(ans1,"string",getPackageName());
            int ans2Id = res.getIdentifier(ans2,"string",getPackageName());
            mStoryTextView.setText(res.getString(storyId));
            mButtonTop.setText(res.getString(ans1Id));
            mButtonBottom.setText(res.getString(ans2Id));
        }else{
            String storyEnd = nextStepText + "_End";
            int storyEndId = res.getIdentifier(storyEnd, "string", getPackageName());

            Log.d("DebugStep", ""+nextStep);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("End of the Story");
            alert.setCancelable(false);
            alert.setMessage(res.getString(storyEndId));

            alert.setPositiveButton("Close Story", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("currentStep", currentStep);
    }
}
