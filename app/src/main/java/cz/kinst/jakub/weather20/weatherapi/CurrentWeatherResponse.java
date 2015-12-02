package cz.kinst.jakub.weather20.weatherapi;

/**
 * Created by jakubkinst on 10/11/15.
 */
public class CurrentWeatherResponse {
    WeatherMain main;

    public WeatherMain getMain() {
        return main;
    }

    public String getCurrentTemperature() {
        return TemperatureUtility.getFormattedTemperature(getMain().getTemp());
    }

    public class WeatherMain {
        double temp;

        public double getTemp() {
            return temp;
        }
    }
}
