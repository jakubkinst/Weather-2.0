package cz.kinst.jakub.weather20.api.openweathermap;

/**
 * Created by jakubkinst on 10/11/15.
 */
public class CurrentWeatherResponse {
	WeatherMain main;
	String name;


	public WeatherMain getMain() {
		return main;
	}


	public String getCurrentTemperature() {
		return TemperatureUtility.getFormattedTemperature(getMain().getTemp());
	}


	public String getName() {
		return name;
	}


	public class WeatherMain {
		double temp;


		public double getTemp() {
			return temp;
		}
	}
}
