
package com.nidhin.apixu_weather.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day implements Parcelable {

    @SerializedName("maxtemp_c")
    @Expose
    private Double maxtempC;
    @SerializedName("maxtemp_f")
    @Expose
    private Double maxtempF;
    @SerializedName("mintemp_c")
    @Expose
    private Double mintempC;
    @SerializedName("mintemp_f")
    @Expose
    private Double mintempF;
    @SerializedName("avgtemp_c")
    @Expose
    private Double avgtempC;
    @SerializedName("avgtemp_f")
    @Expose
    private Double avgtempF;
    @SerializedName("maxwind_mph")
    @Expose
    private Double maxwindMph;
    @SerializedName("maxwind_kph")
    @Expose
    private Double maxwindKph;
    @SerializedName("totalprecip_mm")
    @Expose
    private Double totalprecipMm;
    @SerializedName("totalprecip_in")
    @Expose
    private Double totalprecipIn;
    @SerializedName("avgvis_km")
    @Expose
    private Double avgvisKm;
    @SerializedName("avgvis_miles")
    @Expose
    private Integer avgvisMiles;
    @SerializedName("avghumidity")
    @Expose
    private Double avghumidity;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("uv")
    @Expose
    private Double uv;

    public Double getMaxtempC() {
        return maxtempC;
    }

    public void setMaxtempC(Double maxtempC) {
        this.maxtempC = maxtempC;
    }

    public Double getMaxtempF() {
        return maxtempF;
    }

    public void setMaxtempF(Double maxtempF) {
        this.maxtempF = maxtempF;
    }

    public Double getMintempC() {
        return mintempC;
    }

    public void setMintempC(Double mintempC) {
        this.mintempC = mintempC;
    }

    public Double getMintempF() {
        return mintempF;
    }

    public void setMintempF(Double mintempF) {
        this.mintempF = mintempF;
    }

    public Double getAvgtempC() {
        return avgtempC;
    }

    public void setAvgtempC(Double avgtempC) {
        this.avgtempC = avgtempC;
    }

    public Double getAvgtempF() {
        return avgtempF;
    }

    public void setAvgtempF(Double avgtempF) {
        this.avgtempF = avgtempF;
    }

    public Double getMaxwindMph() {
        return maxwindMph;
    }

    public void setMaxwindMph(Double maxwindMph) {
        this.maxwindMph = maxwindMph;
    }

    public Double getMaxwindKph() {
        return maxwindKph;
    }

    public void setMaxwindKph(Double maxwindKph) {
        this.maxwindKph = maxwindKph;
    }

    public Double getTotalprecipMm() {
        return totalprecipMm;
    }

    public void setTotalprecipMm(Double totalprecipMm) {
        this.totalprecipMm = totalprecipMm;
    }

    public Double getTotalprecipIn() {
        return totalprecipIn;
    }

    public void setTotalprecipIn(Double totalprecipIn) {
        this.totalprecipIn = totalprecipIn;
    }

    public Double getAvgvisKm() {
        return avgvisKm;
    }

    public void setAvgvisKm(Double avgvisKm) {
        this.avgvisKm = avgvisKm;
    }

    public Integer getAvgvisMiles() {
        return avgvisMiles;
    }

    public void setAvgvisMiles(Integer avgvisMiles) {
        this.avgvisMiles = avgvisMiles;
    }

    public Double getAvghumidity() {
        return avghumidity;
    }

    public void setAvghumidity(Double avghumidity) {
        this.avghumidity = avghumidity;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getUv() {
        return uv;
    }

    public void setUv(Double uv) {
        this.uv = uv;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.maxtempC);
        dest.writeValue(this.maxtempF);
        dest.writeValue(this.mintempC);
        dest.writeValue(this.mintempF);
        dest.writeValue(this.avgtempC);
        dest.writeValue(this.avgtempF);
        dest.writeValue(this.maxwindMph);
        dest.writeValue(this.maxwindKph);
        dest.writeValue(this.totalprecipMm);
        dest.writeValue(this.totalprecipIn);
        dest.writeValue(this.avgvisKm);
        dest.writeValue(this.avgvisMiles);
        dest.writeValue(this.avghumidity);
        dest.writeParcelable(this.condition, flags);
        dest.writeValue(this.uv);
    }

    public Day() {
    }

    protected Day(Parcel in) {
        this.maxtempC = (Double) in.readValue(Double.class.getClassLoader());
        this.maxtempF = (Double) in.readValue(Double.class.getClassLoader());
        this.mintempC = (Double) in.readValue(Double.class.getClassLoader());
        this.mintempF = (Double) in.readValue(Double.class.getClassLoader());
        this.avgtempC = (Double) in.readValue(Double.class.getClassLoader());
        this.avgtempF = (Double) in.readValue(Double.class.getClassLoader());
        this.maxwindMph = (Double) in.readValue(Double.class.getClassLoader());
        this.maxwindKph = (Double) in.readValue(Double.class.getClassLoader());
        this.totalprecipMm = (Double) in.readValue(Double.class.getClassLoader());
        this.totalprecipIn = (Double) in.readValue(Double.class.getClassLoader());
        this.avgvisKm = (Double) in.readValue(Double.class.getClassLoader());
        this.avgvisMiles = (Integer) in.readValue(Integer.class.getClassLoader());
        this.avghumidity = (Double) in.readValue(Double.class.getClassLoader());
        this.condition = in.readParcelable(Condition.class.getClassLoader());
        this.uv = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel source) {
            return new Day(source);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };
}
