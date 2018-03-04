package hk.com.enums;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public enum Constants {
    TABLE_NAME("table_name"),
    COUNTRY("country"),
    COUNTRY_NAME("country_name"),
    COUNTRY_LISTS("country_lists");


    private String key;

    Constants(String toString) {
        key = toString;
    }


    @Override
    public String toString() {
        return key;

    }
}
