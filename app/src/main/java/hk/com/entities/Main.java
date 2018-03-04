package hk.com.entities;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "temp",
        "pressure",
        "humidity",
        "sea_level",
        "grnd_level",
        "temp_min",
        "temp_max",
        "temp_kf"
})
public class Main {

    @JsonProperty("grnd_level")
    private Double grndLevel;
    @JsonProperty("temp")
    private Integer temp;
    @JsonProperty("pressure")
    private Integer pressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("temp_min")
    private Integer tempMin;
    @JsonProperty("temp_max")
    private Integer tempMax;
    @JsonProperty("sea_level")
    private Double seaLevel;
    @JsonProperty("temp_kf")
    private Integer tempKf;

    @JsonProperty("temp_kf")
    public Integer getTempKf() {
        return tempKf;
    }

    @JsonProperty("temp_kf")
    public void setTempKf(Integer tempKf) {
        this.tempKf = tempKf;
    }

    @JsonProperty("grnd_level")
    public Double getGrndLevel() {
        return grndLevel;
    }

    @JsonProperty("grnd_level")
    public void setGrndLevel(Double grndLevel) {
        this.grndLevel = grndLevel;
    }

    @JsonProperty("sea_level")
    public Double getSeaLevel() {
        return seaLevel;
    }

    @JsonProperty("sea_level")
    public void setSeaLevel(Double seaLevel) {
        this.seaLevel = seaLevel;
    }
    @JsonProperty("temp")
    public Integer getTemp() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    @JsonProperty("pressure")
    public Integer getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    @JsonProperty("humidity")
    public Integer getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("temp_min")
    public Integer getTempMin() {
        return tempMin;
    }

    @JsonProperty("temp_min")
    public void setTempMin(Integer tempMin) {
        this.tempMin = tempMin;
    }

    @JsonProperty("temp_max")
    public Integer getTempMax() {
        return tempMax;
    }

    @JsonProperty("temp_max")
    public void setTempMax(Integer tempMax) {
        this.tempMax = tempMax;
    }

}
