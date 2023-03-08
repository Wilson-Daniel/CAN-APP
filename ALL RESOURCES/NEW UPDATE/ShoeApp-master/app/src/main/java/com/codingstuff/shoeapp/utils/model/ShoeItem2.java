package com.codingstuff.shoeapp.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ShoeItem2 implements Parcelable {

    private String drinkName, drinkBrandName;
    private int drinkImage;
    private double drinkPrice;

    public ShoeItem2(String drinkName, String drinkBrandName, int drinkImage, double drinkPrice) {
        this.drinkName = drinkName;
        this.drinkBrandName = drinkBrandName;
        this.drinkImage = drinkImage;
        this.drinkPrice = drinkPrice;
    }

    protected ShoeItem2(Parcel in) {
        drinkName = in.readString();
        drinkBrandName = in.readString();
        drinkImage = in.readInt();
        drinkPrice = in.readDouble();
    }

    public static final Creator<ShoeItem2> CREATOR = new Creator<ShoeItem2>() {
        @Override
        public ShoeItem2 createFromParcel(Parcel in) {
            return new ShoeItem2(in);
        }

        @Override
        public ShoeItem2[] newArray(int size) {
            return new ShoeItem2[size];
        }
    };

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkBrandName() {
        return drinkBrandName;
    }

    public void setDrinkBrandName(String drinkBrandName) {
        this.drinkBrandName = drinkBrandName;
    }

    public int getDrinkImage() {
        return drinkImage;
    }

    public void setDrinkImage(int drinkImage) {
        this.drinkImage = drinkImage;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(double drinkPrice) {
        this.drinkPrice = drinkPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(drinkName);
        parcel.writeString(drinkBrandName);
        parcel.writeInt(drinkImage);
        parcel.writeDouble(drinkPrice);
    }
}
