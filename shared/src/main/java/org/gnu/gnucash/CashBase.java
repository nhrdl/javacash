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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CashBase {

	private Node rootNode;

	final XPathFactory xpathFactory = XPathFactory.newInstance();
	final NamespaceContext nameSpaceContext = new NamespaceContextMap("rng", "http://relaxng.org/ns/structure/1.0", "gnc", "http://www.gnucash.org/XML/gnc",
			"act", "http://www.gnucash.org/XML/act", "book", "http://www.gnucash.org/XML/book", "cd", "http://www.gnucash.org/XML/cd", "cmdty",
			"http://www.gnucash.org/XML/cmdty", "price", "http://www.gnucash.org/XML/price", "slot", "http://www.gnucash.org/XML/slot", "split",
			"http://www.gnucash.org/XML/split", "sx", "http://www.gnucash.org/XML/sx", "trn", "http://www.gnucash.org/XML/trn", "ts",
			"http://www.gnucash.org/XML/ts", "fs", "http://www.gnucash.org/XML/fs", "bgt", "http://www.gnucash.org/XML/bgt", "recurrence",
			"http://www.gnucash.org/XML/recurrence", "lot", "http://www.gnucash.org/XML/lot", "addr", "http://www.gnucash.org/XML/addr", "owner",
			"http://www.gnucash.org/XML/owner", "billterm", "http://www.gnucash.org/XML/billterm", "bt-days", "http://www.gnucash.org/XML/bt-days", "bt-prox",
			"http://www.gnucash.org/XML/bt-prox", "cust", "http://www.gnucash.org/XML/cust", "employee", "http://www.gnucash.org/XML/employee", "entry",
			"http://www.gnucash.org/XML/entry", "invoice", "http://www.gnucash.org/XML/invoice", "job", "http://www.gnucash.org/XML/job", "order",
			"http://www.gnucash.org/XML/order", "taxtable", "http://www.gnucash.org/XML/taxtable", "tte", "http://www.gnucash.org/XML/tte", "vendor",
			"http://www.gnucash.org/XML/vendor");

	public CashBase() {

	}

	public CashBase(final Node root) {
		rootNode = root;
	}

	public Node getDocument() {
		return rootNode;
	}

	public String evaluateXPathString(final String query) throws Exception {
		final XPath xpath = xpathFactory.newXPath();
		xpath.setNamespaceContext(nameSpaceContext);
		return (String) xpath.evaluate(query, rootNode, XPathConstants.STRING);
	}

	public String evaluateXPathString(final String query, final Node context) throws Exception {
		final XPath xpath = xpathFactory.newXPath();
		xpath.setNamespaceContext(nameSpaceContext);
		return (String) xpath.evaluate(query, context, XPathConstants.STRING);
	}

	public NodeList evaluateXPathList(final String query) throws Exception {
		return evaluateXPathList(query, rootNode);
	}

	public NodeList evaluateXPathList(final String query, final Node context) throws Exception {
		final XPath xpath = xpathFactory.newXPath();
		xpath.setNamespaceContext(nameSpaceContext);
		return (NodeList) xpath.evaluate(query, context, XPathConstants.NODESET);
	}

	public Node evaluateXPath(final String query) throws Exception {
		return evaluateXPath(query, rootNode);
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
			this.rootNode = builder.parse(in);
		} finally {
			in.close();
		}
	}

	public void forEachNodeDo(final String xpath, final Node context, final INodeWorker iNodeWorker, final Object... data) throws Exception {
		forEachNodeDo(evaluateXPathList(xpath, context), iNodeWorker, data);
	}

	public void forEachNodeDo(final String xpath, final INodeWorker iNodeWorker, final Object... data) throws Exception {
		forEachNodeDo(evaluateXPathList(xpath), iNodeWorker, data);
	}

	public void forEachNodeDo(final NodeList list, final INodeWorker iNodeWorker, final Object... data) throws Exception {
		Node node;
		for (int i = 0, iMax = list.getLength(); i < iMax; i++) {
			node = list.item(i);
			iNodeWorker.doWork(i, node, data);
		}

	}
}
