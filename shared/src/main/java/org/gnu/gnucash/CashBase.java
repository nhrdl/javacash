package org.gnu.gnucash;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CashBase {

	private Document document;

	final XPathFactory xpathFactory = XPathFactory.newInstance();
	final NamespaceContext nameSpaceContext = new NamespaceContextMap("rng", "http://relaxng.org/ns/structure/1.0");

	public Document getDocument() {
		return document;
	}

	public NodeList evaluateXPathList(final String query) throws Exception {
		return evaluateXPathList(query, document);
	}

	public NodeList evaluateXPathList(final String query, final Node context) throws Exception {
		final XPath xpath = xpathFactory.newXPath();
		xpath.setNamespaceContext(nameSpaceContext);
		return (NodeList) xpath.evaluate(query, context, XPathConstants.NODESET);
	}

	public Node evaluateXPath(final String query) throws Exception {
		return evaluateXPath(query, document);
	}

	public Node evaluateXPath(final String query, final Node context) throws Exception {
		final XPath xpath = xpathFactory.newXPath();
		xpath.setNamespaceContext(nameSpaceContext);
		return (Node) xpath.evaluate(query, context, XPathConstants.NODE);
	}

	public void loadDocument(final InputStream in) throws ParserConfigurationException, SAXException, IOException {
		try {
			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setNamespaceAware(true); // never forget this!
			final DocumentBuilder builder = docFactory.newDocumentBuilder();
			this.document = builder.parse(in);
		} finally {
			in.close();
		}
	}

}
