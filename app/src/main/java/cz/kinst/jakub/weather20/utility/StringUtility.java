package cz.kinst.jakub.weather20.utility;

/**
 * Created by jakubkinst on 06/12/15.
 */
public class StringUtility {
	public static String capitalize(final String input) {
		return Character.toUpperCase(input.charAt(0)) + input.substring(1);
	}
}
