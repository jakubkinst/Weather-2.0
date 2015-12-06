package cz.kinst.jakub.weather20.api.openweathermap;

import java.text.DecimalFormat;

import cz.kinst.jakub.weather20.Preferences;


/**
 * Created by jakubkinst on 02/12/15.
 */
public class TemperatureUtility {
	public static double kelvinToCelsius(double kelvins) {
		return kelvins - 273.15;
	}


	public static double kelvinToFahrenheit(double kelvins) {
		return kelvins * 9 / 5 - 459.67;
	}


	public static String getFormattedTemperature(double temp) {
		double degrees = Preferences.get().isTempMetric() ? TemperatureUtility.kelvinToCelsius(temp) : TemperatureUtility.kelvinToFahrenheit(temp);
		return new DecimalFormat("#").format(degrees) + "°";
	}
}
