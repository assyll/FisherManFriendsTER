package com.fishermanfriendz.dtc.fishermanfriendster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Assyl on 22/01/2015.
 */
public class LakeView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private LakeLoopThread gameLoopThread;
    private int x = 0;
    private int xSpeed = 1;
    boolean left = true;

    public LakeView(Context context) {
        super(context);
        gameLoopThread = new LakeLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (left)  bmp = BitmapFactory.decodeResource(getResources(), R.drawable.catfish1);
        else    bmp = BitmapFactory.decodeResource(getResources(), R.drawable.catfish2);

        Matrix matrix = new Matrix();
        matrix.postRotate(180);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);

        if (x == bmp.getHeight()) {
            xSpeed = -10;
        }
        if (x == 0) {
            xSpeed = 20;
        }
        x = x + xSpeed;
        canvas.drawColor(Color.CYAN);



        left = !left;
         canvas.drawBitmap(rotatedBitmap, 10 , x, null);
    }
}