package com.niranjanrao;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import biz.wolschon.fileformats.gnucash.GnucashWritableAccount;
import biz.wolschon.fileformats.gnucash.GnucashWritableTransaction;
import biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileWritingImpl;
import biz.wolschon.numbers.FixedPointNumber;

public class TestFileOperations {

	private GnucashFileWritingImpl writer;

	@Before
	public void setup() throws IOException, JAXBException {
		writer = new GnucashFileWritingImpl(new File("/finovera/niranjan/.gnucash/gnucash.xml.gz"));
	}

	@Test
	public void testFindAccount() throws Exception {
		final GnucashWritableAccount pge = writer.getAccountByName("PGE");
		Assert.assertNotNull("Should have found PGE", pge);

		final GnucashWritableAccount checking = writer.getAccountByName("Checking Account");
		Assert.assertNotNull("Should have found Checkings", checking);

		final GnucashWritableTransaction trans = writer.createWritableTransaction();

		final GnucashWritableTransactionSplit firstSplit = trans.createWritingSplit(checking);

		firstSplit.setValue(new FixedPointNumber(-120));

		final GnucashWritableTransactionSplit second = trans.createWritingSplit(pge);
		second.setValue(new FixedPointNumber(120));

		Assert.assertNotNull("First split is null", firstSplit);
		final File f = new File("/tmp/abcd.gnucash.gz");
		if (f.exists()) {
			f.delete();
		}
		writer.writeFile(f);
	}
}
