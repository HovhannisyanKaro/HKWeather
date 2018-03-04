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
        "cod",
        "message",
        "cnt",
        "list",
        "city"

})
public class Weather {

    @JsonProperty("city")
    private City city;
    @JsonProperty("message")
    private Double message;
    @JsonProperty("cod")
    private String cod;
    @JsonProperty("cnt")
    private Integer cnt;
    @JsonProperty("list")
    private java.util.List<WeatherList> list = null;

    @JsonProperty("city")
    public City getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(City city) {
        this.city = city;
    }

    @JsonProperty("message")
    public Double getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Double message) {
        this.message = message;
    }

    @JsonProperty("cod")
    public String getCod() {
        return cod;
    }

    @JsonProperty("cod")
    public void setCod(String cod) {
        this.cod = cod;
    }

    @JsonProperty("cnt")
    public Integer getCnt() {
        return cnt;
    }

    @JsonProperty("cnt")
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    @JsonProperty("list")
    public java.util.List<WeatherList> getList() {
        return list;
    }

    @JsonProperty("list")
    public void setList(java.util.List<WeatherList> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city=" + city +
                ", message=" + message +
                ", cod='" + cod + '\'' +
                ", cnt=" + cnt +
                ", list=" + list +
                '}';
    }
}
