package cz.kinst.jakub.weather20.weatherapi;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by jakubkinst on 02/12/15.
 */
public class WeatherForecastResponse {


	private java.util.List<Forecast> list;


	public java.util.List<Forecast> getList() {
		return list;
	}


	public static class Forecast {
		private long dt;

		private Temp temp;
		private double pressure;
		private int humidity;
		private double speed;
		private int deg;
		private int clouds;

		private java.util.List<Weather> weather;


		public Date getDate() {
			return new Date(dt * 1000);
		}


		public String getDayName() {
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
			return dateFormat.format(getDate());
		}


		public Temp getTemp() {
			return temp;
		}


		public String getTemperature() {
			return TemperatureUtility.getFormattedTemperature(getTemp().getDay());
		}


		public double getPressure() {
			return pressure;
		}


		public int getHumidity() {
			return humidity;
		}


		public double getSpeed() {
			return speed;
		}


		public int getDeg() {
			return deg;
		}


		public int getClouds() {
			return clouds;
		}


		public java.util.List<Weather> getWeather() {
			return weather;
		}


		public static class Temp {
			private double day;
			private double min;
			private double max;
			private double night;
			private double eve;
			private double morn;


			public double getDay() {
				return day;
			}


			public double getMin() {
				return min;
			}


			public double getMax() {
				return max;
			}


			public double getNight() {
				return night;
			}


			public double getEve() {
				return eve;
			}


			public double getMorn() {
				return morn;
			}
		}


		public static class Weather {
			private int id;
			private String main;
			private String description;
			private String icon;


			public int getId() {
				return id;
			}


			public String getMain() {
				return main;
			}


			public String getDescription() {
				return description;
			}


			public String getIcon() {
				return icon;
			}
		}
	}
}
