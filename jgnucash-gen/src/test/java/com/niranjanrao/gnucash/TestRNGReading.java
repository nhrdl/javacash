package com.niranjanrao.gnucash;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.niranjanrao.test.TesterBase;

public class TestRNGReading extends TesterBase {

	@Test
	public void testFileReading() throws Exception {

		final InputStream is = super.getRNGFileResource();
		gen.processRNGFile(is);

		Assert.assertTrue("Could not find GunCashXml element", gen.hasDefinedElement("GnuCashXml"));
	}

	@Test
	public void testPackageName() {

	}

	public void testCodeGeneration() throws Exception {

		final InputStream is = super.getRNGFileResource();
		gen.generate(is, this.memoryWriter);

	}
}
