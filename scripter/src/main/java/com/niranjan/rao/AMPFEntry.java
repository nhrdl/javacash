package com.niranjan.rao;

import java.io.BufferedReader;

public class AMPFEntry {

	public String symbol, description, value, quantity, price, endingValue, cost, gain, income, yield;

	public void readData(final BufferedReader rdr) throws Exception {
		symbol = rdr.readLine();
		description = rdr.readLine();
		final String s = rdr.readLine();
		if (s.startsWith("$")) {
			value = s;
		} else {
			description = description + " " + s;
			value = rdr.readLine();
		}
		quantity = rdr.readLine();
		price = rdr.readLine();
		endingValue = rdr.readLine();
		cost = rdr.readLine();
		gain = rdr.readLine();
		income = rdr.readLine();
		yield = rdr.readLine();
	}

	@Override
	public String toString() {

		final StringBuffer buff = new StringBuffer();
		buff.append(symbol).append("|");
		buff.append(description).append("|");
		buff.append(value).append("|");
		buff.append(quantity).append("|");
		buff.append(price).append("|");
		buff.append(endingValue).append("|");
		buff.append(cost).append("|");
		buff.append(gain).append("|");
		buff.append(income).append("|");
		buff.append(yield).append("|");

		return buff.toString();
	}

}
