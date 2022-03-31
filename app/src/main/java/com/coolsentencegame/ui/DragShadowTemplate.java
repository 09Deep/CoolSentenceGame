package com.coolsentencegame.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public class DragShadowTemplate extends View.DragShadowBuilder {

    // The drag shadow image, defined as a drawable object.
    private static Drawable shadow;

    // Constructor
    public DragShadowTemplate(View v) {
        super(v);

        // Creates a draggable image that fills the Canvas provided by the system.
        shadow = new ColorDrawable(Color.LTGRAY);
    }

    // Defines a callback that sends the drag shadow dimensions and touch point
    // back to the system.
    @Override
    public void onProvideShadowMetrics(Point size, Point touch) {
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
