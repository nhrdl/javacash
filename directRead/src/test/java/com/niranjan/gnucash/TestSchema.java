package com.niranjan.gnucash;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.gnu.gnucash.CashBase;
import org.gnu.gnucash.GnuCashV2;
import org.junit.Before;
import org.junit.Test;

public class TestSchema {

	private JAXBContext jaxbContext;
	private Unmarshaller un;
	private CashBase cash;

	@Before
	public void setup() throws Exception {
		jaxbContext = JAXBContext.newInstance(GnuCashV2.class);
		un = jaxbContext.createUnmarshaller();

		// final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		//
		// final Schema schema = schemaFactory.newSchema(new StreamSource[] { new StreamSource(ReadXML.class.getResourceAsStream("/gnucash.xsd")),
		// new StreamSource(ReadXML.class.getResourceAsStream("/cd.xsd")), new StreamSource(ReadXML.class.getResourceAsStream("/gnc.xsd")) });
		// un.setSchema(schema);

		cash = new CashBase();
		cash.loadDocument(TestSchema.class.getResourceAsStream("/testFile.xml"));
	}

	@Test
	public void testBookCount() throws Exception {
		final JAXBElement<GnuCashV2> jaxElem = un.unmarshal(cash.getDocument(), GnuCashV2.class);
		final GnuCashV2 cash2 = jaxElem.getValue();
		System.out.println(cash2.getBookCount());
		// final GnuCashV2 GncV2 = jpe.getValue();
	}
}
