package com.niranjanrao.gnucash;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.xpath.XPathExpressionException;

import junit.framework.Assert;

import org.junit.Test;

import com.niranjanrao.test.TesterBase;

public class TestRNGReading extends TesterBase {

	@Test
	public void testFileReading() throws XPathExpressionException, IOException {

		final InputStream is = super.getRNGFileResource();
		final CashGenerator gen = new CashGenerator();
		gen.processRNGFile(is);

		Assert.assertTrue("Could not find GunCashXml element", gen.hasDefinedElement("GnuCashXml"));
	}
}
