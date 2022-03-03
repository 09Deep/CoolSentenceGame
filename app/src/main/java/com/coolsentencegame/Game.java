package com.coolsentencegame;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
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

import com.coolsentencegame.interfaces.IDatabase;
import com.coolsentencegame.objects.MockDatabase;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


public class Game extends AppCompatActivity {

    private String lastString;
    private ArrayList<String> tokens;
    private View clickedView; // This feels wrong
    private int score;
    private boolean start; //to only load the scene once. either with the timer or the check btn
    private IDatabase db;

    //this int is set to the number of the next phase that the game should move to.
    // 1 for phase 1 and 2 for phase 2.
    private int gameManager;

    private Button btn;
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

        score = 0;
        tokens = new ArrayList<>();
        timer = new Timer();

        gameManager = 1;
        start = false;
        btn = findViewById(R.id.btnCheck);
        btn.setEnabled(false);

        topFlex = findViewById(R.id.topLayout2);
        btmFlex = findViewById(R.id.btmLayout2);
        textTitle = (TextView)findViewById(R.id.textTitle);
        textScore = (TextView)findViewById(R.id.textScore);

        db = new MockDatabase();

        startPhase1();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void startPhase1()
    {
        String string;
        gameManager = 2;
        btn.setText("Start");
        btn.setEnabled(true);

        tokens.clear();
        btmFlex.removeAllViews();
        topFlex.removeAllViews();

        // This will be replaced by the database stuff
        string = db.FetchRandomWord();
        while(string.equals(lastString)){
            string = db.FetchRandomWord();
        }
        lastString = string;
        Collections.addAll(tokens, string.split(" "));

        textTitle.setText(string);

        // After 4 seconds, move to next phase
        timer.schedule(new TestTimer(), 4000);
    }

    // This is somehow causing a memory leak, should
    // probably fix that?
    public Handler mHandler =new  Handler() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void handleMessage(Message msg) {

            if (!start) {
                gameManager = 2;
                startPhase2();
            }
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startPhase2()
    {
        /**
         * the code below should later be removed as the scene needs to either changed
         * to next level or just show a msg that the sentence was correct or not!
         * (success or failure)*/
        gameManager = 1;
        /**********/


        btn.setText("Check");
        btn.setEnabled(true);
        textTitle.setText("Rebuild the sentence!");

        for(String s : tokens) {
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
        start = true;
        btn.setEnabled(false);
        for(int i = 0; i < topFlex.getChildCount(); i++) {
            View v = topFlex.getChildAt(i);
            if(((LinearLayout)v).getChildCount() == 0) {
                System.out.println("FUCK YOU");
                return;
            }
            Button contents = (Button) ((LinearLayout)v).getChildAt(0);
            if(!contents.getText().equals(tokens.get(i))) {
                System.out.println("FUCK YOU");
                return;
            }
        }

        score++;
        textScore.setText("" + score);

        switch (gameManager) {
            case 1:
                startPhase1();
                break;
            case 2:
                startPhase2();
                break;
        }

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

    private class TestTimer extends TimerTask {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() {
            mHandler.obtainMessage(1).sendToTarget();
        }

    }
}