package com.niranjanrao.gnucash;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.gnu.gnucash.CashBase;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;

public class CashGenerator extends CashBase {

	static Logger log = Logger.getLogger(CashGenerator.class);

	private final JCodeModel model;
	HashMap<String, Node> defineElementMap;
	Map<String, String> countDataMap;

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

		for (final Entry<String, Node> entry : defineElementMap.entrySet()) {
			final String classPath = getClassPath(entry);
			final JDefinedClass cls = model._getClass(classPath);
			if (null == cls) {
				log.info("Going to create class " + classPath);
			}
		}
	}

	private String getClassPath(final Entry<String, Node> entry) throws Exception {
		final Node elm = evaluateXPath("./rng:element/@name", entry.getValue());
		String pkg = "";
		if (elm == null) {
			log.debug("Could not find element element for define " + entry.getKey());

		} else {
			pkg = elm.getTextContent();
		}
		return getFullyQualifiedName(entry.getKey(), pkg);
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

	static final String PACAKGE_PATH = "org.gnu.gnucash.";

	public String getFullyQualifiedName(final String className, final String namespacePath) {
		final StringBuffer buff = new StringBuffer(PACAKGE_PATH);
		String temp = namespacePath.replaceAll("-", "_");
		temp = temp.replace(":", ".");
		buff.append(temp);
		if (buff.toString().endsWith(".") == false) {
			buff.append(".");
		}
		buff.append(className);
		return buff.toString();
	}

}
