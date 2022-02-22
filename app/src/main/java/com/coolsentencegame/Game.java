package com.coolsentencegame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;



public class Game extends AppCompatActivity {

    private String testString;
    private ArrayList<String> tokens;

    private LinearLayout topLayout;
    private LinearLayout btmLayout;
    private TextView textStatus;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tokens = new ArrayList<>();

        topLayout = (LinearLayout)findViewById(R.id.topLayout);
        btmLayout = (LinearLayout)findViewById(R.id.btmLayout);
        textStatus = (TextView)findViewById(R.id.textStatus);

        // This will be replaced buy the database stuff
        testString = "This is a very good test";

        Collections.addAll(tokens, testString.split(" "));

        for(String s : tokens) {
            TextView tv = new TextView(this);
            tv.setText(s);
            tv.setPadding(10,10,10,10);
            tv.setTextSize(24);

            tv.setOnLongClickListener(v -> {
                ClipData.Item item = new ClipData.Item((CharSequence) s);
                ClipData dragData = new ClipData(
                        (CharSequence) s,
                        new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN},
                        item
                );

                View.DragShadowBuilder shadow = new MyDragShadowBuilder(tv);

                v.startDragAndDrop(dragData, shadow, null, 0);
                System.out.println("fuck face fucker");
                return true;
            });




            btmLayout.addView(tv);
        }

        for(String s : tokens) {
            LinearLayout layout = new LinearLayout(this);
            layout.setMinimumWidth(250);
            layout.setMinimumHeight(100);
            layout.setBackgroundColor(Color.RED);
            layout.setPadding(10,10,10,10);
            topLayout.addView(layout);

            // Set the drag event listener for the View.
            layout.setOnDragListener( (v, e) -> {

                // Handles each of the expected events.
                switch(e.getAction()) {

                    case DragEvent.ACTION_DRAG_STARTED:

                        // Determines if this View can accept the dragged data.
                        if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                            // As an example of what your application might do, applies a blue color tint
                            // to the View to indicate that it can accept data.
                            ((LinearLayout)v).setBackgroundColor(Color.RED);

                            // Invalidate the view to force a redraw in the new tint.
                            v.invalidate();

                            // Returns true to indicate that the View can accept the dragged data.
                            return true;
                        }

                        // Returns false to indicate that, during the current drag and drop operation,
                        // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                        return false;

                    case DragEvent.ACTION_DRAG_ENTERED:

                        // Applies a green tint to the View.
                        ((LinearLayout)v).setBackgroundColor(Color.GREEN);

                        // Invalidates the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true; the value is ignored.
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:

                        // Ignore the event.
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:

                        // Resets the color tint to blue.
                        ((LinearLayout)v).setBackgroundColor(Color.BLUE);

                        // Invalidates the view to force a redraw in the new tint.
                        v.invalidate();

                        // Returns true; the value is ignored.
                        return true;

                    case DragEvent.ACTION_DROP:

                        // Gets the item containing the dragged data.
                        ClipData.Item item = e.getClipData().getItemAt(0);

                        // Gets the text data from the item.
                        CharSequence dragData = item.getText();

                        // Displays a message containing the dragged data.
//                        Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_LONG).show();
                        textStatus.setText("Dragged data is [" + dragData + "]");

                        // Invalidates the view to force a redraw.
                        v.invalidate();

                        // Returns true. DragEvent.getResult() will return true.
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:

                        // Turns off any color tinting.
//                        ((TextView)v).clearColorFilter();

                        // Invalidates the view to force a redraw.
                        v.invalidate();

                        // Does a getResult(), and displays what happened.
                        if (e.getResult()) {
//                            TextView t2 = new TextView(this);
//                            t2.setText("FUCK YOU");
//                            btmLayout.addView(t2);
                            System.out.println("DROP");
                        } else {
//                            Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG).show();
                        }

                        // Returns true; the value is ignored.
                        return true;

                    // An unknown action type was received.
                    default:
                        Log.e("DragDrop Example","Unknown action type received by View.OnDragListener.");
                        break;
                }

                return false;

            });
        }

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
            shadow.draw(canvas);
        }
    }
}