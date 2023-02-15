package constants;

import java.net.URI;

public class Constants {


    public static final String API_KEY = "HeWHJ8DPmb6xoW09xOrA8Dq4j0brYJAo";
    public static final URI NON_EXISTING_URL = URI.create("https://api.apilayer.com/whooops_doesnt_exist");
    private static final String BASE_URL = "https://api.apilayer.com/exchangerates_data/";
    public static final URI LATEST_URL = URI.create(BASE_URL + "latest");

    public static final URI LATEST_URL_FILTERED(String symbols, String base) {
        return URI.create(BASE_URL + "latest?symbols=" + symbols + "&base=" + base);
    }


}
