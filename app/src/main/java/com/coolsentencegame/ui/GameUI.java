package com.coolsentencegame.ui;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.coolsentencegame.R;
import com.coolsentencegame.logic.GameLogic;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GameUI extends AppCompatActivity {

    private GameLogic gameLogic; // Handles logic layer
    private View clickedView;
    private boolean startClicked; //to only load the scene once. either with the timer or the check btn

    private Button btnCheck;
    private Button btnStart;
    private Button btnFinish;
    private FlexboxLayout topFlex;  // Users answer
    private FlexboxLayout btmFlex;  // Users choices
    private TextView textTitle;
    private TextView textScore;
    private Timer timer;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameLogic = new GameLogic();
        timer = new Timer();
        
        btnCheck = findViewById(R.id.btnCheck);
        btnStart = findViewById(R.id.btnStart);
        btnFinish = findViewById(R.id.btnFinish);
        btnCheck.setVisibility(View.GONE);
        btnStart.setVisibility(View.VISIBLE);
        btnFinish.setVisibility(View.VISIBLE);

        topFlex = findViewById(R.id.topLayout2);
        btmFlex = findViewById(R.id.btmLayout2);
        textTitle = (TextView)findViewById(R.id.textTitle);
        textScore = (TextView)findViewById(R.id.textScore);


            startPhase1();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void startPhase1()
    {
        startClicked = false;
        btnStart.setVisibility(View.VISIBLE);
        btnCheck.setVisibility(View.GONE);
        btnFinish.setVisibility(View.VISIBLE);

        btmFlex.removeAllViews();
        topFlex.removeAllViews();

        gameLogic.newSentence();


        //if yoy receive the sentence then show it on the screen
        //if don't then the game is probably over
        if(!gameLogic.getFlag()){

            textTitle.setText(gameLogic.getSentence().toString());

            // After 4 seconds, move to next phase
            timer.schedule(new MemorizeTimer(), 4000);
        }
        else{

            startPhase3();

        }


    }

    // This is somehow causing a memory leak, should
    // probably fix that?
    public Handler mHandler =new  Handler() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void handleMessage(Message msg) {

            if (!startClicked) {
                startPhase2();
            }
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startPhase2()
    {
        btnStart.setVisibility(View.GONE);
        btnCheck.setVisibility(View.VISIBLE);
        btnFinish.setVisibility(View.VISIBLE);

        btnCheck.setText("Check");
        btnCheck.setEnabled(true);
        textTitle.setText("Rebuild the sentence!");

        for(String s : gameLogic.getTokensRandomized()) {
            LinearLayout btmLayout = layoutFactory();
            LinearLayout topLayout = layoutFactory();
            Button btn = buttonFactory(s);

            btn.setOnTouchListener((v, e) -> {
                clickedView = v;
                ClipData.Item item = new ClipData.Item((CharSequence) s);
                ClipData dragData = new ClipData(
                        (CharSequence) s,
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item
                );

                View.DragShadowBuilder shadow = new MyDragShadowBuilder(btn);

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

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startPhase3(){
        btnStart.setVisibility(View.GONE);
        btnCheck.setVisibility(View.GONE);
        btnFinish.setVisibility(View.VISIBLE);
    }

    private boolean dragListener(View v, DragEvent e)
    {
        // Handles each of the expected events.
        switch(e.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data.
                if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    return true;
                }

                // Returns false to indicate that, during the current drag and drop operation,
                // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                ((LinearLayout)v).getBackground().setColorFilter(Color.rgb(150,50,150), PorterDuff.Mode.MULTIPLY);
                v.invalidate();

                // Returns true; the value is ignored.
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event.
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
            case DragEvent.ACTION_DRAG_ENDED:
                ((LinearLayout)v).getBackground().clearColorFilter();
                v.invalidate();

                return true;

            case DragEvent.ACTION_DROP:
                ViewGroup clickedParent = (ViewGroup) clickedView.getParent();
                LinearLayout targetLayout = (LinearLayout)v;

                // Move a token to an empty answer slot
                if(targetLayout.getChildCount() == 0) {
                    clickedParent.removeView(clickedView);
                    targetLayout.addView(clickedView);
                    btmFlex.removeView(clickedParent);
                }

                // Swap two tokens in their slots (unless trying to swap token with itself)
                else if(targetLayout.getChildCount() == 1 && clickedView != targetLayout.getChildAt(0)) {
                    View targetView = targetLayout.getChildAt(0);
                    targetLayout.removeView(targetView);
                    clickedParent.removeView(clickedView);
                    targetLayout.addView(clickedView);
                    clickedParent.addView(targetView);
                }

                targetLayout.getBackground().clearColorFilter();
                v.invalidate();

                // Returns true. DragEvent.getResult() will return true.
                return true;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                break;
        }

        return false;
    }

    private LinearLayout layoutFactory()
    {
        LinearLayout layout = new LinearLayout(this);
        layout.setMinimumWidth(150);
        layout.setMinimumHeight(100);
        layout.setBackgroundColor(Color.LTGRAY);
        layout.setPadding(10,10,10,10);
        layout.setBackgroundResource(R.drawable.rounded);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(20, 20, 20, 20);

        layout.setLayoutParams(layoutParams);
        return layout;
    }

    private Button buttonFactory(String s)
    {
        Button btn = new Button(this);
        btn.setText(s);
        btn.setTextSize(24);
        btn.setBackgroundColor(Color.TRANSPARENT);
        return btn;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onCheckBtnClick(View view)
    {
        ArrayList<String> playerTokens = new ArrayList<>();

        // Build the tokens from the UI
        for(int i = 0; i < topFlex.getChildCount(); i++) {
            LinearLayout v = (LinearLayout) topFlex.getChildAt(i);
            if(v.getChildCount() > 0) {
                Button contents = (Button) v.getChildAt(0);
                playerTokens.add((String) contents.getText());
            }
        }

        boolean success = gameLogic.isPlayerSentenceCorrect(playerTokens);
        textScore.setText("" + gameLogic.getCorrectGuesses());
        if(success) {
            startPhase1();
        }
        else{
            backToLevels(view);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onStartBtnClick(View view)
    {
        startClicked = true;
        startPhase2();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onFinishBtnClick(View view){
        Intent toMainMenu = new Intent(view.getContext(),MainActivity.class);
        startActivity(toMainMenu);
    }

    public void backToLevels(View view){
        Intent toGameLevels = new Intent(view.getContext(),GameLevelActivity.class);
        startActivity(toGameLevels);
    }


    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        // The drag shadow image, defined as a drawable object.
        private static Drawable shadow;

        // Constructor
        public MyDragShadowBuilder(View v)
        {
            super(v);

            // Creates a draggable image that fills the Canvas provided by the system.
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        // Defines a callback that sends the drag shadow dimensions and touch point
        // back to the system.
        @Override
        public void onProvideShadowMetrics (Point size, Point touch)
        {
            int width, height;

            width = getView().getWidth();
            height = getView().getHeight();

            shadow.setBounds(0, 0, width, height);
            size.set(width, height);

            // Set the touch point's position to be in the middle of the drag shadow.
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system
        // constructs from the dimensions passed to onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {
            // Draw the ColorDrawable on the Canvas passed in from the system.
            shadow.draw(canvas);
            getView().draw(canvas);
        }
    }

    private class MemorizeTimer extends TimerTask {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() {
            mHandler.obtainMessage(1).sendToTarget();
        }

    }
}