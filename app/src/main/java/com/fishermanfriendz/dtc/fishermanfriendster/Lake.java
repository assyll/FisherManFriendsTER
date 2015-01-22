package com.fishermanfriendz.dtc.fishermanfriendster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Assyl on 22/01/2015.
 */
public class Lake {

    private int nbMaxFishInLake = 0;
    private int nbFishInLake = 0;
    private ArrayList<Poisson> listePoissons = new ArrayList<Poisson>();
    Bitmap bmp1, bmp2;
    private int height, width;

    public Lake(int nbMaxFishInLake, int nbFishInLake, ArrayList<Poisson> listePoissons, Bitmap bmp2, int height, int width, Bitmap bmp1) {
        this.nbMaxFishInLake = nbMaxFishInLake;
        this.nbFishInLake = nbFishInLake;
        this.listePoissons = listePoissons;
        this.bmp2 = bmp2;
        this.height = height;
        this.width = width;
        this.bmp1 = bmp1;
    }

    public int getNbMaxFishInLake() {

        return nbMaxFishInLake;
    }

    public void setNbMaxFishInLake(int nbMaxFishInLake) {
        this.nbMaxFishInLake = nbMaxFishInLake;
    }

    public ArrayList<Poisson> getListePoissons() {
        return listePoissons;
    }

    public void setListePoissons(ArrayList<Poisson> listePoissons) {
        this.listePoissons = listePoissons;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void addFishToNbMaxFish()
    {
        int randomNum = 1 + (int)(Math.random()*4);

        if (nbFishInLake < nbMaxFishInLake)
        {
            switch (randomNum)
            {
                case 1 :
                    randomNum = 1 + (int)(Math.random()*width);
                    listePoissons.add(new Poisson(Poisson.Side.Top, Poisson.DirectionType.Straight, randomNum, 0 , 25, 25,  bmp1, bmp2));
                    nbFishInLake++;
                    return;

                case 2:
                    randomNum = 1 + (int)(Math.random()*width);
                    listePoissons.add(new Poisson(Poisson.Side.Bottom, Poisson.DirectionType.Straight, randomNum, height , 25, 25,  bmp1, bmp2));
                    nbFishInLake++;
                    return;

                case 3:
                    randomNum = 1 + (int)(Math.random()*height);
                    listePoissons.add(new Poisson(Poisson.Side.Left, Poisson.DirectionType.DiagonalDownRight, 0, randomNum , 25, 25,  bmp1, bmp2));
                    nbFishInLake++;
                    return;

                case 4:
                    randomNum = 1 + (int)(Math.random()*height);
                    listePoissons.add(new Poisson(Poisson.Side.Right, Poisson.DirectionType.Straight, width, randomNum , 15, 15,  bmp1, bmp2));
                    nbFishInLake++;
                    return;
            }
        }
    }
    public int getNbFishInLake() {
        return nbFishInLake;
    }

    public void setNbFishInLake(int nbFishInLake) {
        this.nbFishInLake = nbFishInLake;
    }

    public void updateNbFish()
    {
        ArrayList<Poisson> toDelete = new ArrayList<Poisson>();

        for( Poisson p : listePoissons)
        {
            if ((p.getCoordX() <= 0 || p.getCoordX() >= width) && (p.getCoordY() <= 0 || p.getCoordY() >= height))
            {
                toDelete.add(p);
            }
        }


        for (int i = 0; i < toDelete.size(); i++)
        {
            listePoissons.remove(toDelete.get(i));
            nbFishInLake--;
        }
    }

}
