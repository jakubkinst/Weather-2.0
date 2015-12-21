package cz.kinst.jakub.weather20.tool;

import android.util.Log;


public class Logcat {
	private static boolean sEnabled = true;
	private static String sTag = "LOGCAT";

	private static boolean sShowCodeLocation = true;
	private static boolean sShowCodeThread = false;
	private static boolean sShowCodeLine = true;


	private static String formatMessage(String msg, Object... args) {
		return args.length == 0 ? msg : String.format(msg, args);
	}


	private static CodeLocation getCodeLocation() {
		return getCodeLocation(3);
	}


	private static CodeLocation getCodeLocation(int depth) {
		StackTraceElement[] stackTrace = new Throwable().getStackTrace();
		StackTraceElement[] filteredStackTrace = new StackTraceElement[stackTrace.length - depth];
		System.arraycopy(stackTrace, depth, filteredStackTrace, 0, filteredStackTrace.length);
		return new CodeLocation(filteredStackTrace);
	}


	private Logcat() {
	}


	public static void initialize(String tag, boolean logsEnabled) {
		sTag = tag;
		sEnabled = logsEnabled;
	}


	public static void setEnabled(boolean enabled) {
		sEnabled = enabled;
	}


	public static void setTag(String tag) {
		sTag = tag;
	}


	public static void setShowCodeLocation(boolean showCodeLocation) {
		sShowCodeLocation = showCodeLocation;
	}


	public static void setShowCodeThread(boolean showCodeThread) {
		sShowCodeThread = showCodeThread;
	}


	public static void setShowCodeLine(boolean showCodeLine) {
		sShowCodeLine = showCodeLine;
	}


	public static void d(String msg, Object... args) {
		if(sEnabled) Log.d(sTag, getCodeLocation().toString() + formatMessage(msg, args));
	}


	public static void e(String msg, Object... args) {
		if(sEnabled) Log.e(sTag, getCodeLocation().toString() + formatMessage(msg, args));
	}


	public static void e(Throwable tr, String msg, Object... args) {
		if(sEnabled) Log.e(sTag, getCodeLocation().toString() + formatMessage(msg, args), tr);
	}


	public static void i(String msg, Object... args) {
		if(sEnabled) Log.i(sTag, getCodeLocation().toString() + formatMessage(msg, args));
	}


	public static void v(String msg, Object... args) {
		if(sEnabled) Log.v(sTag, getCodeLocation().toString() + formatMessage(msg, args));
	}


	public static void w(String msg, Object... args) {
		if(sEnabled) Log.w(sTag, getCodeLocation().toString() + formatMessage(msg, args));
	}


	public static void wtf(String msg, Object... args) {
		if(sEnabled) Log.wtf(sTag, getCodeLocation().toString() + formatMessage(msg, args));
	}


	private static class CodeLocation {
		public final String mThread;
		public final String mClassName;
		public final String mMethod;
		public final int mLineNumber;
		public StackTraceElement[] mStackTrace;


		CodeLocation(StackTraceElement[] stackTrace) {
			mStackTrace = stackTrace;
			StackTraceElement root = stackTrace[0];
			mThread = Thread.currentThread().getName();
			String className = root.getClassName();
			mClassName = className.substring(className.lastIndexOf('.') + 1);
			mMethod = root.getMethodName();
			mLineNumber = root.getLineNumber();
		}


		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			if(sShowCodeLocation) {
				builder.append('[');
				if(sShowCodeThread) {
					builder.append(mThread);
					builder.append('.');
				}
				builder.append(mClassName);
				builder.append('.');
				builder.append(mMethod);
				if(sShowCodeLine) {
					builder.append(':');
					builder.append(mLineNumber);
				}
				builder.append("] ");
			}
			return builder.toString();
		}
	}
}