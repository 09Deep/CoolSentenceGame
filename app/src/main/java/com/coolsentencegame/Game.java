package com.coolsentencegame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Collections;



public class Game extends AppCompatActivity {

    private String testString;
    private ArrayList<String> tokens;
    private View clickedView; // This feels wrong

    private FlexboxLayout topFlex;  // Users answer
    private FlexboxLayout btmFlex;  // Users choices
    private TextView textStatus;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tokens = new ArrayList<>();

        topFlex = findViewById(R.id.topLayout2);
        btmFlex = findViewById(R.id.btmLayout2);
        textStatus = (TextView)findViewById(R.id.textStatus);

        // This will be replaced by the database stuff
        testString = "This is a very good test, very nice!";

        Collections.addAll(tokens, testString.split(" "));

        for(String s : tokens) {
            LinearLayout btmLayout = layoutFactory();
            LinearLayout topLayout = layoutFactory();
            Button btn = new Button(this);

            btn.setText(s);
            btn.setTextSize(24);
            btn.setOnLongClickListener(v -> {
                clickedView = v;
                ClipData.Item item = new ClipData.Item((CharSequence) s);
                ClipData dragData = new ClipData(
                        (CharSequence) s,
                        new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item
                );

                View.DragShadowBuilder shadow = new MyDragShadowBuilder(btn);

                v.startDragAndDrop(dragData, shadow, null, 0);

                return true;
            });

            // Set up the answer containers
            topLayout.setOnDragListener( (v, e) -> {
                // Handles each of the expected events.
                switch(e.getAction()) {

                    case DragEvent.ACTION_DRAG_STARTED:
                        // Determines if this View can accept the dragged data.
                        if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
//                            v.invalidate();
                            return true;
                        }

                        // Returns false to indicate that, during the current drag and drop operation,
                        // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                        return false;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        ((LinearLayout)v).getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                        v.invalidate();

                        // Returns true; the value is ignored.
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        // Ignore the event.
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:
                        ((LinearLayout)v).getBackground().clearColorFilter();
                        v.invalidate();

                        return true;

                    case DragEvent.ACTION_DROP:
                        // Gets the item containing the dragged data.
                        ClipData.Item item = e.getClipData().getItemAt(0);

                        // Gets the text data from the item.
                        CharSequence dragData = item.getText();

                        // Displays a message containing the dragged data.
                        textStatus.setText("Dragged data is [" + dragData + "]");

                        ViewGroup parent = (ViewGroup) clickedView.getParent();
                        parent.removeView(clickedView);
                        ((LinearLayout)v).addView(clickedView);
                        System.out.println(clickedView);

                        ((LinearLayout)v).getBackground().clearColorFilter();
                        v.invalidate();

                        // Returns true. DragEvent.getResult() will return true.
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        ((LinearLayout)v).getBackground().clearColorFilter();
                        v.invalidate();

                        // Returns true; the value is ignored.
                        return true;

                    // An unknown action type was received.
                    default:
                        Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                        break;
                }

                return false;
            });

            btmLayout.addView(btn);
            btmFlex.addView(btmLayout);
            topFlex.addView(topLayout);
        }

    }

    private LinearLayout layoutFactory()
    {
        LinearLayout layout = new LinearLayout(this);
        layout.setMinimumWidth(150);
        layout.setMinimumHeight(100);
        layout.setBackgroundColor(Color.LTGRAY);
        layout.setPadding(10,10,10,10);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(20, 20, 20, 20);

        layout.setLayoutParams(layoutParams);
        return layout;
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        // The drag shadow image, defined as a drawable object.
        private static Drawable shadow;

        // Constructor
        public MyDragShadowBuilder(View v) {

            // Stores the View parameter.
            super(v);

            // Creates a draggable image that fills the Canvas provided by the system.
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        // Defines a callback that sends the drag shadow dimensions and touch point
        // back to the system.
        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {

            // Defines local variables
            int width, height;

            // Set the width of the shadow to half the width of the original View.
            width = getView().getWidth();

            // Set the height of the shadow to half the height of the original View.
            height = getView().getHeight();

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the
            // same as the Canvas that the system provides. As a result, the drag shadow
            // fills the Canvas.
            shadow.setBounds(0, 0, width, height);

            // Set the size parameter's width and height values. These get back to the
            // system through the size parameter.
            size.set(width, height);

            // Set the touch point's position to be in the middle of the drag shadow.
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system
        // constructs from the dimensions passed to onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {
            // Draw the ColorDrawable on the Canvas passed in from the system.
//            shadow.draw(canvas);
            getView().draw(canvas);
        }
    }
}