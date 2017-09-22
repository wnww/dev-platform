package com.yhhl.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {

	public static long getNowDateTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		String t = dateTime.toString();
		t = t.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").replaceAll("T", "");
		t = t.substring(0, t.indexOf("."));
		return Long.valueOf(t);
	}

	public static long getTadyDate() {
		LocalDate today = LocalDate.now();
		return Long.valueOf(today.toString().replaceAll("-", ""));
	}
}
