package org.gnu.gnucash;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class GnuCashXml extends CashBase {

	private Book book;

	public GnuCashXml(final InputStream io) throws ParserConfigurationException, SAXException, IOException {
		loadDocument(io);
	}

	public void readFile() throws Exception {

		this.book = new Book(evaluateXPath("//gnc:book"));
		final ReadData data = new ReadData();
		book.read(data);
	}

	public int getBookCount() {
		return 1;
	}

}
