package hk.com.entities;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cnt",
        "list"
})
public class Weather {

    @JsonProperty("cnt")
    private Integer cnt;
    @JsonProperty("list")
    private java.util.List<WeatherList> list = null;

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
                "cnt=" + cnt +
                ", list=" + list +
                '}';
    }
}
