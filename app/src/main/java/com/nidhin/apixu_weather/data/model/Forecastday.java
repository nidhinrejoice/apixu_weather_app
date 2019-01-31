
package com.nidhin.apixu_weather.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecastday implements Parcelable
{

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_epoch")
    @Expose
    private Long dateEpoch;
    @SerializedName("day")
    @Expose
    private Day day;
    @SerializedName("astro")
    @Expose
    private Astro astro;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getDateEpoch() {
        return dateEpoch;
    }

    public void setDateEpoch(Long dateEpoch) {
        this.dateEpoch = dateEpoch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Astro getAstro() {
        return astro;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeValue(this.dateEpoch);
        dest.writeParcelable(this.day, flags);
        dest.writeParcelable(this.astro, flags);
    }

    public Forecastday() {
    }

    protected Forecastday(Parcel in) {
        this.date = in.readString();
        this.dateEpoch = (Long) in.readValue(Long.class.getClassLoader());
        this.day = in.readParcelable(Day.class.getClassLoader());
        this.astro = in.readParcelable(Astro.class.getClassLoader());
    }

    public static final Creator<Forecastday> CREATOR = new Creator<Forecastday>() {
        @Override
        public Forecastday createFromParcel(Parcel source) {
            return new Forecastday(source);
        }

        @Override
        public Forecastday[] newArray(int size) {
            return new Forecastday[size];
        }
    };
}
