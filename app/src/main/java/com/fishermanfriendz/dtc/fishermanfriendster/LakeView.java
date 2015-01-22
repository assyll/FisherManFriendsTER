package com.fishermanfriendz.dtc.fishermanfriendster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Assyl on 22/01/2015.
 */
public class LakeView extends SurfaceView {
    private Bitmap bmp;
    private SurfaceHolder holder;
    private LakeLoopThread gameLoopThread;
    private int x = 0;
    private int xSpeed = 1;
    private int y = 0;
    private int ySpeed = 1;
    boolean left = true;
    private int degree = 0;
    private ArrayList<Poisson> listePoissons= new ArrayList<Poisson>(), toDelete;
    Lake lac;
    private int nb = 0, nbFished = 0;

    public LakeView(Context context) {

            super(context);
            toDelete = new ArrayList<>();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Bitmap bmp1 =  BitmapFactory.decodeResource(getResources(), R.drawable.catfish1);
            Bitmap bmp2 =  BitmapFactory.decodeResource(getResources(), R.drawable.catfish2);
            listePoissons.add(new Poisson(Poisson.Side.Top, Poisson.DirectionType.Straight, 30, 0 , 25, 25,  bmp1, bmp2));
            listePoissons.add(new Poisson(Poisson.Side.Bottom, Poisson.DirectionType.Straight, 500, display.getHeight() , 25, 25,  bmp1, bmp2));
            listePoissons.add(new Poisson(Poisson.Side.Left, Poisson.DirectionType.DiagonalDownRight, 0, 500 , 25, 25,  bmp1, bmp2));
            listePoissons.add(new Poisson(Poisson.Side.Right, Poisson.DirectionType.DiagonalDownRight, display.getWidth(), 100 , 30, 30,  bmp1, bmp2));
            listePoissons.add(new Poisson(Poisson.Side.Right, Poisson.DirectionType.Straight, display.getWidth(), 500 , 15, 15,  bmp1, bmp2));
            lac = new Lake(5, 0, listePoissons, bmp2, display.getHeight(), display.getWidth(), bmp1);
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
        canvas.drawColor(Color.CYAN);
        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("Score  = " + nbFished , 10, 50, paint);
        nb++;

        if(nb == 6) {
            lac.addFish();
            nb = 0;
        }


        for (Poisson p : lac.getListePoissons())
        {
            p.move();
            canvas.drawBitmap(p.getBmp(), p.getCoordX(), p.getCoordY(), null);


        }


        for(Poisson p : toDelete)
        {
            lac.getListePoissons().remove(p);
            nbFished++;
        }

        toDelete.clear();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         x = (int)event.getX();
         y = (int)event.getY();

        toDelete = new ArrayList<>();

        for(Poisson p : lac.getListePoissons())
        {
           if ( x >= p.getCoordX() && x <= (p.getCoordX()+  p.getBmp().getHeight()) && y >= p.getCoordY() && y <= (p.getCoordY()+  p.getBmp().getHeight()))
           {
               toDelete.add(p);
           }
        }


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }
        return false;
    }

}