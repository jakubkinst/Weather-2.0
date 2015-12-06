package cz.kinst.jakub.weather20.utility;

/**
 * Created by jakubkinst on 06/12/15.
 */
public class StringUtility {
	public static String capitalize(final String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
}
