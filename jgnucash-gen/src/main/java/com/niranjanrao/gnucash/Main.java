package com.niranjanrao.gnucash;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.gnu.gnucash.NamespaceContextMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage Main <rng schema> <outputDir>");
			System.exit(1);
		}

		new Main().process(args);
	}

	private void process(final String[] args) throws Exception {

		final FileInputStream in = new FileInputStream(args[0]);
		process(in, args[1]);
	}

	public void process(final InputStream in, final String string) throws Exception {
		try {
			final InputSource is = new InputSource(in);
			final NamespaceContext context = new NamespaceContextMap("rng", "http://relaxng.org/ns/structure/1.0");
			final XPathFactory factory = XPathFactory.newInstance();
			final XPath xpath = factory.newXPath();
			xpath.setNamespaceContext(context);
			final XPathExpression expression = xpath.compile("//rng:grammar/rng:define");
			final NodeList result = (NodeList) expression.evaluate(is, XPathConstants.NODESET);
			System.out.println("Got elements " + result.getLength());
		} finally {
			in.close();
		}
	}

}
