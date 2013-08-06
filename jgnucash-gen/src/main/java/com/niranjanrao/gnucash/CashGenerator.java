package com.niranjanrao.gnucash;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.gnu.gnucash.CashBase;
import org.gnu.gnucash.INodeWorker;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JAssignment;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JStatement;

public class CashGenerator extends CashBase {

	static Logger log = Logger.getLogger(CashGenerator.class);

	private final JCodeModel model;
	HashMap<String, Node> defineElementMap;
	Map<String, String> countDataMap;
	HashMap<String, GenData> generationData;

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
		generationData = new HashMap<>();
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
		writeTo(writer);
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

		final GenData data = generationData.get(_class.fullName());

		final JMethod construct = _class.constructor(JMod.PUBLIC);
		for (final JStatement expr : data.constructorList) {
			construct.body().add(expr);
		}
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
			generateClass(classPath, entry.getValue());
		}
	}

	private String getClassPath(final Entry<String, Node> entry) throws Exception {
		return getClassPath(entry.getKey(), entry.getValue());
	}

	private JDefinedClass generateClass(final String classPath, final Node classData) throws Exception {
		JDefinedClass cls = model._getClass(classPath);
		if (cls != null) {
			return cls;
		}
		log.info("Going to create class " + classPath);
		final GenData data = new GenData();
		this.generationData.put(classPath, data);
		cls = model._class(classPath);
		cls._extends(CashBase.class);
		generateCounters(cls, classData);
		return cls;
	}

	private void generateCounters(final JDefinedClass cls, final Node classData) throws Exception {
		final NodeList counterList = evaluateXPathList(".//rng:element[@name='gnc:count-data']", classData);
		forEachNodeDo(counterList, new INodeWorker() {

			@Override
			public void doWork(final int index, final Node node, final Object... data) throws Exception {
				generateCounter(node, (JDefinedClass) data[0]);
			}

		}, cls);
	}

	private void generateCounter(final Node counterNode, final JDefinedClass cls) throws Exception {
		final Node constNode = evaluateXPath("./rng:value", counterNode);
		final Node typeNode = evaluateXPath("./rng:attribute[@name='cd:type']/rng:value", counterNode);
		final String objName = typeNode.getTextContent();
		final String typeName = getCountDataMappingName(objName);

		final String methodName = "get" + typeName + "Count";

		final JMethod method = cls.method(JMod.PUBLIC, model.INT, methodName);
		if (null != constNode) {
			method.body()._return(JExpr.lit(Integer.valueOf(constNode.getTextContent())));
		} else {
			final Node obj = evaluateXPath("//rng:ref[@name='" + typeName + "']");
			final String fullPath = getClassPath(typeName, obj);
			final JDefinedClass type = generateClass(fullPath, obj);
			final JClass list = model.ref(ArrayList.class).narrow(type);
			final JFieldVar field = cls.field(JMod.PRIVATE, list, "_" + typeName.toLowerCase() + "s");
			method.body()._return(field.invoke("size"));

			final JAssignment newStatement = (JAssignment) field.assign(JExpr._new(list));
			generationData.get(cls.fullName()).constructorList.add(newStatement);
		}
	}

	private String getClassPath(final String key, final Node value) throws Exception {
		final Node elm = evaluateXPath("./rng:element/@name", value);
		String pkg = "";
		if (elm == null) {
			log.debug("Could not find element element for define " + key);

		} else {
			pkg = elm.getTextContent();
		}
		return getFullyQualifiedName(key, pkg);
	}

	public boolean hasDefinedElement(final String string) {
		return defineElementMap.containsKey(string);
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
