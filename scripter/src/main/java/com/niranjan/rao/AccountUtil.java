package com.niranjan.rao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import biz.wolschon.fileformats.gnucash.GnucashAccount;
import biz.wolschon.fileformats.gnucash.GnucashWritableAccount;
import biz.wolschon.numbers.FixedPointNumber;

public class AccountUtil extends BaseUtil {

	public GnucashWritableAccount findAccountByPath(final String path, final String splitter) {

		final String[] arr = path.split(Pattern.quote(splitter));
		int startIndex = 0;
		if (path.startsWith("/")) {
			startIndex = 1;
		}
		GnucashWritableAccount acct;
		acct = (GnucashWritableAccount) getWriter().getRootAccounts().iterator().next();
		for (int i = startIndex; i < arr.length; i++) {
			acct = findAccountByName(arr[i], acct);
			if (acct == null) {
				return null;
			}
		}

		return acct;
	}

	private GnucashWritableAccount findAccountByName(final String name, final GnucashWritableAccount acct) {
		for (final GnucashAccount child : acct.getChildren()) {
			if (child.getName().equals(name)) {
				return (GnucashWritableAccount) child;
			}
		}
		return null;
	}

	public ArrayList<AMPFEntry> readAMPFEntries(final String fileName) throws Exception {
		final BufferedReader reader = new BufferedReader(new FileReader(fileName));
		reader.mark(300);
		String line = reader.readLine();
		final ArrayList<AMPFEntry> list = new ArrayList<>();
		do {
			reader.reset();

			final AMPFEntry entry = new AMPFEntry();
			entry.readData(reader);
			list.add(entry);
			reader.mark(300);
			line = reader.readLine();
		} while (line != null);

		reader.close();
		return list;
	}

	public GnucashWritableAccount findAccountByPath(final String path) {
		return findAccountByPath(path, "/");
	}

	public String getCurrencyMatch(final AMPFEntry entry) {
		final FixedPointNumber num = getWriter().getCurrencyTable().getConversionFactor("AMEX", entry.symbol);
		if (num != null) {
			return num.toPlainString();
		}
		return null;
	}
}
