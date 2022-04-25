package com.coolsentencegame.ui;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.coolsentencegame.R;
import com.coolsentencegame.application.Services;
import com.coolsentencegame.logic.GameLogic;
import com.coolsentencegame.objects.GameParams;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private GameLogic gameLogic; // Handles logic layer
    private View clickedView;

    private Button btnCheck;
    private Button btnStart;
    private Button btnFinish;
    private FlexboxLayout topFlex;  // Users answer
    private FlexboxLayout btmFlex;  // Users choices
    private TextView textTitle;
    private TextView textScore;
    private TextView textTimer;
    private CountDownTimer timer;
    int delay = 4000;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(Utils.getTheme());
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        GameParams gameParams = (GameParams)intent.getSerializableExtra("params");

        if(gameParams.getSpeed() == GameParams.Speed.NORMAL)
            delay = 4000;
        else
            delay = 2000;

        gameLogic = new GameLogic(gameParams, Services.getScorePersistence(), Services.getSentencePersistence());

        btnCheck = findViewById(R.id.btnCheck);
        btnStart = findViewById(R.id.btnStart);
        btnFinish = findViewById(R.id.btnFinish);
        btnCheck.setVisibility(View.GONE);
        btnFinish.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);

        topFlex = findViewById(R.id.topLayout2);
        btmFlex = findViewById(R.id.btmLayout2);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textScore = (TextView) findViewById(R.id.textScore);
        textTimer = (TextView) findViewById(R.id.textTimer);

        startPhase1();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void startPhase1()
    {
        btnStart.setVisibility(View.VISIBLE);
        btnCheck.setVisibility(View.GONE);
        btnFinish.setVisibility(View.VISIBLE);

        btmFlex.removeAllViews();
        topFlex.removeAllViews();

        gameLogic.newSentence();
        textTimer.setVisibility(View.VISIBLE);
        textTitle.setText(gameLogic.getSentence().toString());

        // After <delay> seconds, move to next phase
        timer = new CountDownTimer(delay, 1000) {
            public void onTick(long millisUntilFinished) {
                String msg = "" + ((millisUntilFinished / 1000)+1);
                textTimer.setText(msg);
            }
            public void onFinish() {
                startPhase2();
            }
        };
        timer.start();
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startPhase2()
    {
        btnStart.setVisibility(View.GONE);
        btnCheck.setVisibility(View.VISIBLE);
        btnFinish.setVisibility(View.VISIBLE);
        textTimer.setVisibility(View.GONE);

        btnCheck.setText("Check");
        btnCheck.setEnabled(true);
        textTitle.setText("Rebuild the sentence!");

        ContextThemeWrapper themeLayout = new ContextThemeWrapper(this, R.style.gameui_container);
        ContextThemeWrapper themeButton = new ContextThemeWrapper(this, R.style.gameui_button);

        for (String s : gameLogic.getTokensRandomized()) {
            LinearLayout btmLayout = new LinearLayout(themeLayout);
            LinearLayout topLayout = new LinearLayout(themeLayout);
            Button btn = new Button(themeButton);
            btn.setText(s);

            btn.setOnTouchListener((v, e) -> {
                clickedView = v;
                ClipData.Item item = new ClipData.Item((CharSequence) s);
                ClipData dragData = new ClipData(
                    (CharSequence) s,
                    new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                    item
                );

                View.DragShadowBuilder shadow = new DragShadowTemplate(btn);

                v.startDragAndDrop(dragData, shadow, null, 0);

                return true;
            });

            // Set up the answer containers
            topLayout.setOnDragListener(this::dragListener);

            btmLayout.addView(btn);
            btmFlex.addView(btmLayout);
            topFlex.addView(topLayout);
        }
    }

    private boolean dragListener(View v, DragEvent e)
    {
        // Handles each of the expected events.
        switch (e.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data.
                if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    return true;
                }

                // Returns false to indicate that, during the current drag and drop operation,
                // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                ((LinearLayout) v).getBackground().setColorFilter(Color.rgb(150, 50, 150), PorterDuff.Mode.MULTIPLY);
                v.invalidate();
                return true;  // the value is ignored.

            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event.
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
            case DragEvent.ACTION_DRAG_ENDED:
                ((LinearLayout) v).getBackground().clearColorFilter();
                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                handleDragDrop(v);
                return true; // DragEvent.getResult() will return true.

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.");
                break;
        }

        return false;
    }

    private void handleDragDrop(View v)
    {
        ViewGroup clickedParent = (ViewGroup) clickedView.getParent();
        LinearLayout targetLayout = (LinearLayout) v;

        // Move a token to an empty answer slot
        if (targetLayout.getChildCount() == 0) {
            clickedParent.removeView(clickedView);
            targetLayout.addView(clickedView);
            btmFlex.removeView(clickedParent);
        }

        // Swap two tokens in their slots (unless trying to swap token with itself)
        else if (targetLayout.getChildCount() == 1 && clickedView != targetLayout.getChildAt(0)) {
            View targetView = targetLayout.getChildAt(0);
            targetLayout.removeView(targetView);
            clickedParent.removeView(clickedView);
            targetLayout.addView(clickedView);
            clickedParent.addView(targetView);
        }

        targetLayout.getBackground().clearColorFilter();
        v.invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onCheckBtnClick(View view)
    {
        ArrayList<String> playerTokens = new ArrayList<>();

        // Build the tokens from the UI
        for (int i = 0; i < topFlex.getChildCount(); i++) {
            LinearLayout v = (LinearLayout) topFlex.getChildAt(i);
            if (v.getChildCount() > 0) {
                Button contents = (Button) v.getChildAt(0);
                playerTokens.add((String) contents.getText());
            }
        }

        boolean success = gameLogic.isPlayerSentenceCorrect(playerTokens);
        textScore.setText("" + gameLogic.getCorrectGuesses());
        Snackbar snackbar;
        if(success)
             snackbar = Snackbar.make(textTitle, "Correct!", Snackbar.LENGTH_SHORT);
        else
            snackbar = Snackbar.make(textTitle, getString(R.string.snackbar_message), Snackbar.LENGTH_SHORT);

        snackbar.show();
        startPhase1();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onStartBtnClick(View view)
    {
        timer.cancel();
        startPhase2();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onFinishBtnClick(View view)
    {
        gameLogic.finish();
        Intent intent = new Intent(GameActivity.this, GameSummary.class);
        String msg = ""+gameLogic.getCorrectGuesses()+"/"+gameLogic.getCurrentRoundNumber();
        intent.putExtra("msg", msg);
        startActivity(intent);
        finish();
    }

}