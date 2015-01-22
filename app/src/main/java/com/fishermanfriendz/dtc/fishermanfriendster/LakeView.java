package com.fishermanfriendz.dtc.fishermanfriendster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

/**
 * Created by Assyl on 22/01/2015.
 */
public class LakeView extends View {

    private Bitmap bmp;

    public LakeView(Context context) {
        super(context);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.catfish1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bmp, 10, 10, null);
    }

}
