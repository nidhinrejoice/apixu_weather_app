
package com.nidhin.apixu_weather.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Current implements Parcelable
{

    @SerializedName("last_updated_epoch")
    @Expose
    private Integer lastUpdatedEpoch;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("temp_c")
    @Expose
    private Integer tempC;
    @SerializedName("temp_f")
    @Expose
    private Double tempF;
    @SerializedName("is_day")
    @Expose
    private Integer isDay;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("wind_mph")
    @Expose
    private Double windMph;
    @SerializedName("wind_kph")
    @Expose
    private Double windKph;
    @SerializedName("wind_degree")
    @Expose
    private Integer windDegree;
    @SerializedName("wind_dir")
    @Expose
    private String windDir;
    @SerializedName("pressure_mb")
    @Expose
    private Integer pressureMb;
    @SerializedName("pressure_in")
    @Expose
    private Double pressureIn;
    @SerializedName("precip_mm")
    @Expose
    private Integer precipMm;
    @SerializedName("precip_in")
    @Expose
    private Integer precipIn;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("cloud")
    @Expose
    private Integer cloud;
    @SerializedName("feelslike_c")
    @Expose
    private Double feelslikeC;
    @SerializedName("feelslike_f")
    @Expose
    private Double feelslikeF;
    @SerializedName("vis_km")
    @Expose
    private Integer visKm;
    @SerializedName("vis_miles")
    @Expose
    private Integer visMiles;
    @SerializedName("uv")
    @Expose
    private Integer uv;
    public final static Parcelable.Creator<Current> CREATOR = new Creator<Current>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Current createFromParcel(Parcel in) {
            return new Current(in);
        }

        public Current[] newArray(int size) {
            return (new Current[size]);
        }

    }
    ;

    protected Current(Parcel in) {
        this.lastUpdatedEpoch = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.lastUpdated = ((String) in.readValue((String.class.getClassLoader())));
        this.tempC = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tempF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.isDay = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.condition = ((Condition) in.readValue((Condition.class.getClassLoader())));
        this.windMph = ((Double) in.readValue((Double.class.getClassLoader())));
        this.windKph = ((Double) in.readValue((Double.class.getClassLoader())));
        this.windDegree = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.windDir = ((String) in.readValue((String.class.getClassLoader())));
        this.pressureMb = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.pressureIn = ((Double) in.readValue((Double.class.getClassLoader())));
        this.precipMm = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.precipIn = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.humidity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.cloud = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.feelslikeC = ((Double) in.readValue((Double.class.getClassLoader())));
        this.feelslikeF = ((Double) in.readValue((Double.class.getClassLoader())));
        this.visKm = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.visMiles = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.uv = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Current() {
    }

    public Integer getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    public void setLastUpdatedEpoch(Integer lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getTempC() {
        return tempC;
    }

    public void setTempC(Integer tempC) {
        this.tempC = tempC;
    }

    public Double getTempF() {
        return tempF;
    }

    public void setTempF(Double tempF) {
        this.tempF = tempF;
    }

    public Integer getIsDay() {
        return isDay;
    }

    public void setIsDay(Integer isDay) {
        this.isDay = isDay;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getWindMph() {
        return windMph;
    }

    public void setWindMph(Double windMph) {
        this.windMph = windMph;
    }

    public Double getWindKph() {
        return windKph;
    }

    public void setWindKph(Double windKph) {
        this.windKph = windKph;
    }

    public Integer getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(Integer windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public Integer getPressureMb() {
        return pressureMb;
    }

    public void setPressureMb(Integer pressureMb) {
        this.pressureMb = pressureMb;
    }

    public Double getPressureIn() {
        return pressureIn;
    }

    public void setPressureIn(Double pressureIn) {
        this.pressureIn = pressureIn;
    }

    public Integer getPrecipMm() {
        return precipMm;
    }

    public void setPrecipMm(Integer precipMm) {
        this.precipMm = precipMm;
    }

    public Integer getPrecipIn() {
        return precipIn;
    }

    public void setPrecipIn(Integer precipIn) {
        this.precipIn = precipIn;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getCloud() {
        return cloud;
    }

    public void setCloud(Integer cloud) {
        this.cloud = cloud;
    }

    public Double getFeelslikeC() {
        return feelslikeC;
    }

    public void setFeelslikeC(Double feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public Double getFeelslikeF() {
        return feelslikeF;
    }

    public void setFeelslikeF(Double feelslikeF) {
        this.feelslikeF = feelslikeF;
    }

    public Integer getVisKm() {
        return visKm;
    }

    public void setVisKm(Integer visKm) {
        this.visKm = visKm;
    }

    public Integer getVisMiles() {
        return visMiles;
    }

    public void setVisMiles(Integer visMiles) {
        this.visMiles = visMiles;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lastUpdatedEpoch);
        dest.writeValue(lastUpdated);
        dest.writeValue(tempC);
        dest.writeValue(tempF);
        dest.writeValue(isDay);
        dest.writeValue(condition);
        dest.writeValue(windMph);
        dest.writeValue(windKph);
        dest.writeValue(windDegree);
        dest.writeValue(windDir);
        dest.writeValue(pressureMb);
        dest.writeValue(pressureIn);
        dest.writeValue(precipMm);
        dest.writeValue(precipIn);
        dest.writeValue(humidity);
        dest.writeValue(cloud);
        dest.writeValue(feelslikeC);
        dest.writeValue(feelslikeF);
        dest.writeValue(visKm);
        dest.writeValue(visMiles);
        dest.writeValue(uv);
    }

    public int describeContents() {
        return  0;
    }

}
