package com.niranjanrao.gnucash;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.gnu.gnucash.INodeWorker;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.niranjanrao.test.TesterBase;

public class TestRNGReading extends TesterBase {

	static final Logger log = Logger.getLogger(TestRNGReading.class);

	@Test
	public void testFileReading() throws Exception {

		gen.processRNGFile();

		Assert.assertTrue("Could not find GunCashXml element", gen.hasDefinedElement("GnuCashXml"));
	}

	@Test
	public void testRefElements() throws Exception {
		final NodeList list = gen.evaluateXPathList("//rng:*[rng:ref]");
		gen.forEachNodeDo(list, new INodeWorker() {

			@Override
			public void doWork(final int index, final Node node, final Object... data) throws Exception {
				log.info(node.getNodeName());

			}
		});
	}

	@Test
	public void testCountData() throws Exception {

		final NodeList list = gen.evaluateXPathList("//rng:element[@name='gnc:count-data']/rng:attribute[@name='cd:type']/rng:value");
		log.info("We have " + list.getLength() + " count-data items");
		gen.forEachNodeDo(list, new INodeWorker() {

			@Override
			public void doWork(final int index, final Node node, final Object... data) throws Exception {
				final String mappedType = gen.getCountDataMappingName(node.getTextContent());
				Assert.assertNotNull(node.getTextContent(), mappedType);
				final Node ref = gen.evaluateXPath("//rng:ref[@name='" + mappedType + "']");
				Assert.assertNotNull("Should have found the ref node", ref);
			}
		});
	}

	@Test
	public void testCodeGeneration() throws Exception {

		final InputStream is = super.getRNGFileResource();
		gen.generate(is, this.memoryWriter);
		compileInMemory(memoryWriter);
	}

	@Test
	public void getFullyQualifiedNames() {
		final String result = gen.getFullyQualifiedName("Book", "gnc:book");
		Assert.assertEquals(result, "org.gnu.gnucash.gnc.book.Book");
	}
}
