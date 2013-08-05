package com.niranjanrao.gnucash;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;

public class CashGenerator {

	static Logger log = Logger.getLogger(CashGenerator.class);

	private final JCodeModel model;
	HashMap<String, Node> defineElementMap;
	Map<String, String> countDataMap;

	private Document document;

	private static Map<String, String> toMap(final String... mappingPairs) throws Exception {
		if (0 != mappingPairs.length % 2) {
			throw new Exception("You should be passing pairs");
		}
		final Map<String, String> prefixMappings = new HashMap<String, String>(mappingPairs.length / 2);
		for (int i = 0; i < mappingPairs.length; i++) {
			prefixMappings.put(mappingPairs[i], mappingPairs[++i]);
		}
		return prefixMappings;
	}

	public CashGenerator() throws Exception {
		this.model = new JCodeModel();
		defineElementMap = new HashMap<>();

		countDataMap = toMap("book", "Book", "commodity", "Commodity", "account", "Account", "transaction", "Transaction", "schedxaction",
				"ScheduledTransaction", "budget", "Budget", "gnc:GncBillTerm", "BillTerm", "gnc:GncCustomer", "Customer", "gnc:GncEmployee", "Employee",
				"gnc:GncEntry", "Entry", "gnc:GncInvoice", "Invoice", "gnc:GncJob", "Job", "gnc:GncOrder", "Order", "gnc:GncTaxTable", "TaxTable",
				"gnc:GncVendor", "Vendor");
	}

	public String getCountDataMappingName(final String type) {
		return countDataMap.get(type);
	}

	public void generate(final InputStream in, final CodeWriter writer) throws Exception {
		loadDocument(in);
		processRNGFile();
		generate(writer);
	}

	public void generate(final CodeWriter writer) throws IOException {
		model.build(writer);
	}

	public void writeTo(final com.sun.codemodel.CodeWriter writer) throws IOException {
		closeClasses();
		model.build(writer);
	}

	private void closeClasses() {
		final Iterator<JPackage> it = model.packages();
		while (it.hasNext()) {
			closePackage(it.next());
		}
	}

	private void closePackage(final JPackage next) {
		final Iterator<JDefinedClass> it = next.classes();
		while (it.hasNext()) {
			closeClass(it.next());
		}
	}

	private void closeClass(final JDefinedClass _class) {

	}

	final XPathFactory xpathFactory = XPathFactory.newInstance();
	final NamespaceContext nameSpaceContext = new NamespaceContextMap("rng", "http://relaxng.org/ns/structure/1.0");

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

	public void processRNGFile() throws Exception {
		final NodeList result = evaluateXPathList("//rng:grammar/rng:define");

		log.info("Got elements " + result.getLength());
		Node node;
		String name;
		for (int i = 0, iMax = result.getLength(); i < iMax; i++) {
			node = result.item(i);
			name = evaluateXPath("./@name", node).getTextContent();
			defineElementMap.put(name, node);
		}

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

	public boolean hasDefinedElement(final String string) {
		return defineElementMap.containsKey(string);
	}

	public void forEachNodeDo(final NodeList list, final INodeWorker iNodeWorker, final Object... data) throws Exception {
		Node node;
		for (int i = 0, iMax = list.getLength(); i < iMax; i++) {
			node = list.item(i);
			iNodeWorker.doWork(i, node, data);
		}

	}

}
