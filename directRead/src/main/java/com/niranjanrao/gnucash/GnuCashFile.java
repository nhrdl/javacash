package com.niranjanrao.gnucash;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;
import org.gnu.gnucash.GnuCashXml;

public class GnuCashFile {

	Logger log = Logger.getLogger(GnuCashFile.class);

	public static void main(final String[] args) throws Exception {
		new GnuCashFile().process(args);
	}

	void process(final String[] args) throws Exception {

		final InputStream in = new GZIPInputStream(new FileInputStream("/finovera/niranjan/.gnucash/gnucash.xml.gz"));
		process(in);
	}

	public void process(final InputStream io) throws Exception {
		final GnuCashXml xml = new GnuCashXml(io);
		xml.readFile();
	}
}
