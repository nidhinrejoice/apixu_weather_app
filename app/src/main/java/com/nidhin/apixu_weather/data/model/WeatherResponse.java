
package com.nidhin.apixu_weather.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse implements Parcelable
{

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("current")
    @Expose
    private Current current;
    @SerializedName("forecast")
    @Expose
    private Forecast forecast;
    public final static Parcelable.Creator<WeatherResponse> CREATOR = new Creator<WeatherResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public WeatherResponse createFromParcel(Parcel in) {
            return new WeatherResponse(in);
        }

        public WeatherResponse[] newArray(int size) {
            return (new WeatherResponse[size]);
        }

    }
    ;

    protected WeatherResponse(Parcel in) {
        this.location = ((Location) in.readValue((Location.class.getClassLoader())));
        this.current = ((Current) in.readValue((Current.class.getClassLoader())));
        this.forecast = ((Forecast) in.readValue((Forecast.class.getClassLoader())));
    }

    public WeatherResponse() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(location);
        dest.writeValue(current);
        dest.writeValue(forecast);
    }

    public int describeContents() {
        return  0;
    }

}
