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
    private Bitmap bmp;
    private int coordX;
    private int coordY;
    private int speedX;
    private int speedY;
    private boolean left;

    public Poisson(Side side, DirectionType directionType, int coordX, int coordY, int speedX, int speedY,  Bitmap img1, Bitmap img2) {
        this.side = side;
        this.directionType = directionType;
        this.bmp1 = img1;
        this.bmp2 = img2;
        this.bmp = img1;
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

    public Bitmap getBmp1() {
        return bmp1;
    }

    public void setBmp1(Bitmap bmp1) {
        this.bmp1 = bmp1;
    }

    public Bitmap getBmp2() {
        return bmp2;
    }

    public void setBmp2(Bitmap bmp2) {
        this.bmp2 = bmp2;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void move()
    {
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
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(0, speedY);
                        break;
                    case DiagonalDownRight:
                        matrix.postRotate(135);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(speedX, speedY);
                        break;
                    case DiagonalUpLeft:
                        matrix.postRotate(225);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, speedY);
                        break;
                }
                break;

            case Bottom:
                switch (directionType) {
                    case Straight:
                        matrix.postRotate(0);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(0, -speedY);
                        break;
                    case DiagonalDownRight:
                        matrix.postRotate(45);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(speedX, -speedY);
                        break;
                    case DiagonalUpLeft:
                        matrix.postRotate(315);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, -speedY);
                        break;
                }
                break;

            case Right:
                switch (directionType) {
                    case Straight:
                        matrix.postRotate(270);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, 0);
                        break;
                    case DiagonalDownRight:
                        matrix.postRotate(225);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, speedY);
                        break;
                    case DiagonalUpLeft:
                        matrix.postRotate(315);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, -speedY);
                        break;
                }
            case Left:
                switch (directionType) {
                    case Straight:
                        matrix.postRotate(90);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(speedX, 0);
                        break;
                    case DiagonalDownRight:
                        matrix.postRotate(135);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(speedX, speedY);
                        break;
                    case DiagonalUpLeft:
                        matrix.postRotate(315);
                        bmp = Bitmap.createBitmap(bmp , 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                        updateCoord(-speedX, -speedY);
                        break;
                }
        }
    }

    private void updateCoord(int x, int y)
    {
        coordX += x;
        coordY += y;
    }





}
