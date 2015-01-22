package com.fishermanfriendz.dtc.fishermanfriendster;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import static com.fishermanfriendz.dtc.fishermanfriendster.Poisson.Side.*;

/**
 * Created by Assyl on 22/01/2015.
 */
public class Poisson {

    public enum Side {
        Top,
        Bottom,
        Left,
        Right
    }

    public enum DirectionType{
        Straight,
        DiagonalUpLeft,
        DiagonalDownRight
    }

    private Side side;
    private DirectionType directionType;
    private Bitmap bmp1;
    private Bitmap bmp2;
    private int coordX;
    private int coordY;
    private int speedX;
    private int speedY;
    private boolean left;

    public Poisson(Side side, DirectionType directionType, int coordX, int speedX, int speedY, int coordY, Bitmap img1, Bitmap img2) {
        this.side = side;
        this.directionType = directionType;
        this.bmp1 = img1;
        this.bmp1 = img2;
        this.coordX = coordX;
        this.speedX = speedX;
        this.speedY = speedY;
        this.coordY = coordY;
        left = false;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public DirectionType getDirectionType() {
        return directionType;
    }

    public void setDirectionType(DirectionType directionType) {
        this.directionType = directionType;
    }


    public void move()
    {
        Bitmap bmp;
        if (left)  bmp = bmp1;
        else    bmp = bmp2;

        left = !left;
        Matrix matrix = new Matrix();
        Bitmap rotatedBitmap;

        switch (side)
        {
            case Top:
                switch (directionType) {
                    case Straight:
                        matrix.postRotate(180);
                        rotatedBitmap = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(0, speedY);
                        return;
                    case DiagonalDownRight:
                        matrix.postRotate(135);
                        rotatedBitmap = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(speedX, speedY);
                        return;
                    case DiagonalUpLeft:
                        matrix.postRotate(225);
                        rotatedBitmap = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, speedY);
                        return;
                }
                return;

            case Bottom:
                switch (directionType) {
                    case Straight:
                        matrix.postRotate(180);
                        rotatedBitmap = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(0, -speedY);
                        return;
                    case DiagonalDownRight:
                        matrix.postRotate(45);
                        rotatedBitmap = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(speedX, -speedY);
                        return;
                    case DiagonalUpLeft:
                        matrix.postRotate(315);
                        rotatedBitmap = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, -speedY);
                        return;
                }
                return;

            case Right:
                switch (directionType) {
                    case Straight:
                        matrix.postRotate(90);
                        rotatedBitmap = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, 0);
                        return;
                    case DiagonalDownRight:
                        matrix.postRotate(225);
                        rotatedBitmap = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, speedY);
                        return;
                    case DiagonalUpLeft:
                        matrix.postRotate(315);
                        rotatedBitmap = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, -speedY);
                        return;
                }
            case Left:

                switch (directionType) {
                    case Straight:
                        return;
                    case DiagonalDownRight:
                        return;
                    case DiagonalUpLeft:
                        return;
                }
                return;
        }
    }

    private void updateCoord(int x, int y)
    {
        coordX += x;
        coordY += y;
    }





}
