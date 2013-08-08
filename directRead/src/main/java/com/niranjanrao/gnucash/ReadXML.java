package com.niranjanrao.gnucash;

import org.apache.log4j.Logger;

public class ReadXML {

	static final Logger log = Logger.getLogger(ReadXML.class);

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		// final JAXBContext jaxbContext = JAXBContext.newInstance(GncV2.class);
		//
		// final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		// final StreamSource src = new StreamSource(ReadXML.class.getResourceAsStream("/g.xsd"));
		// final Schema schema = schemaFactory.newSchema(src);
		//
		// final Unmarshaller un = jaxbContext.createUnmarshaller();
		// un.setSchema(schema);
		//
		// final StreamSource inputSource = new StreamSource(new GZIPInputStream(new FileInputStream("/finovera/niranjan/.gnucash/gnucash.xml.gz")));
		//
		// final JAXBElement<GncV2> jpe = un.unmarshal(inputSource, GncV2.class);
		// final GncV2 GncV2 = jpe.getValue();

	}

}
