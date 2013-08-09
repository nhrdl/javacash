package com.niranjanrao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import biz.wolschon.fileformats.gnucash.GnucashWritableAccount;
import biz.wolschon.fileformats.gnucash.GnucashWritableTransaction;
import biz.wolschon.fileformats.gnucash.GnucashWritableTransactionSplit;
import biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileWritingImpl;
import biz.wolschon.numbers.FixedPointNumber;

import com.niranjan.rao.AMPFEntry;
import com.niranjan.rao.AccountUtil;

public class TestAMPFOperations {

	private GnucashFileWritingImpl writer;

	@Before
	public void setup() throws IOException, JAXBException {
		writer = new GnucashFileWritingImpl(new File("/finovera/niranjan/.gnucash/gnucash.master.xml.gz"));
	}

	// @Test
	public void testAMPFReadData() throws Exception {

		final AccountUtil acctUtil = new AccountUtil();
		final ArrayList<AMPFEntry> list = acctUtil.readAMPFEntries("/tmp/5943.txt");
		acctUtil.setWriter(writer);
		for (final AMPFEntry entry : list) {
			System.out.println(entry);
		}
		System.out.println("Found " + list.size() + " entries");
	}

	// @Test
	public void testAMPFMatchAccountData() throws Exception {

		final AccountUtil acctUtil = new AccountUtil();
		acctUtil.setWriter(writer);
		final ArrayList<AMPFEntry> list = acctUtil.readAMPFEntries("/tmp/5943.txt");
		for (final AMPFEntry entry : list) {
			final String s = acctUtil.getCurrencyMatch(entry);
			Assert.assertNotNull("No match for currency" + entry.symbol, s);
		}
	}

	@Test
	public void addPurchases() throws Exception {
		final AccountUtil acctUtil = new AccountUtil();
		acctUtil.setWriter(writer);
		final GnucashWritableAccount parentAccount = acctUtil
				.findAccountByPath("/Assets/Investments/Brokerage Account/Ameriprise/85685934 at ameriprise.com (code ameriprise.com)");
		Assert.assertNotNull("Could not find account", parentAccount);
		final GnucashWritableAccount openingBalance = acctUtil.findAccountByPath("/Equity/Opening Balances");
		Assert.assertNotNull("No opening balace account", openingBalance);
		final ArrayList<AMPFEntry> list = acctUtil.readAMPFEntries("/tmp/5943.txt");
		for (final AMPFEntry entry : list) {
			final GnucashWritableAccount child = writer.createWritableAccount();
			child.setName(entry.symbol + " " + entry.description);
			child.setCurrencyID(entry.symbol);
			child.setCurrencyNameSpace("AMEX");
			child.setParentAccount(parentAccount);
			child.setType("MUTUAL");
			addTransaction(child, openingBalance, entry);
		}
	}

	private void addTransaction(final GnucashWritableAccount child, final GnucashWritableAccount openingBalance, final AMPFEntry entry) throws Exception {
		final GnucashWritableTransaction trans = writer.createWritableTransaction();
		final Calendar ct = Calendar.getInstance();
		ct.set(2013, 5, 28);
		trans.setDateEntered(ct.getTime());
		trans.setDatePosted(ct.getTime());
		final GnucashWritableTransactionSplit firstSplit = trans.createWritingSplit(child);

		firstSplit.setSplitAction("Buy");
		FixedPointNumber num = new FixedPointNumber(entry.quantity);
		firstSplit.setQuantity(num);
		firstSplit.setValue(num);

		final GnucashWritableTransactionSplit openSplit = trans.createWritingSplit(openingBalance);
		num = new FixedPointNumber("-" + entry.quantity);
		openSplit.setValue(num);
		openSplit.setQuantity(num);
	}

	// @Test
	public void testAccountFind() {
		final AccountUtil acctUtil = new AccountUtil();
		acctUtil.setWriter(writer);
		final GnucashWritableAccount acct = acctUtil
				.findAccountByPath("/Assets/Investments/Brokerage Account/Ameriprise/85685934 at ameriprise.com (code ameriprise.com)");
		Assert.assertNotNull("Could not find account", acct);

		final GnucashWritableAccount acct1 = acctUtil
				.findAccountByPath("Assets/Investments/Brokerage Account/Ameriprise/85685934 at ameriprise.com (code ameriprise.com)");
		Assert.assertNotNull("Could not find account", acct1);

		Assert.assertEquals("Not same accounts", acct1, acct);
	}

	@After
	public void cleanup() throws Exception {
		final File f = new File("/tmp/abcd.gnucash.gz");
		if (f.exists()) {
			f.delete();
		}
		writer.writeFile(f);
	}
}
