package com.niranjanrao.gnucash;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;

public class CashGenerator {

	static Logger log = Logger.getLogger(CashGenerator.class);

	private final JCodeModel model;
	HashMap<String, Node> defineElementMap;

	public CashGenerator() {
		this.model = new JCodeModel();
		defineElementMap = new HashMap<>();

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

	public void processRNGFile(final InputStream in) throws XPathExpressionException, IOException {
		try {
			final InputSource is = new InputSource(in);
			final NamespaceContext context = new NamespaceContextMap("rng", "http://relaxng.org/ns/structure/1.0");
			final XPathFactory factory = XPathFactory.newInstance();
			final XPath xpath = factory.newXPath();
			xpath.setNamespaceContext(context);
			final XPathExpression expression = xpath.compile("//rng:grammar/rng:define");
			final NodeList result = (NodeList) expression.evaluate(is, XPathConstants.NODESET);
			log.info("Got elements {}" + result.getLength());
			Node node;
			String name;
			for (int i = 0, iMax = result.getLength(); i < iMax; i++) {
				node = result.item(i);
				name = xpath.evaluate("./@name", node);
				defineElementMap.put(name, node);
			}
		} finally {
			in.close();
		}
	}

	public boolean hasDefinedElement(final String string) {
		return defineElementMap.containsKey(string);
	}
}
