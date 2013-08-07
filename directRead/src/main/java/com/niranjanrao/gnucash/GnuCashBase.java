package com.niranjanrao.gnucash;

import org.gnu.gnucash.CashBase;
import org.gnu.gnucash.ReadData;
import org.w3c.dom.Node;

public class GnuCashBase extends CashBase {

	public GnuCashBase() {
	}

	public GnuCashBase(final Node root) {
		super(root);
	}

	public void read(final ReadData data) throws Exception {

	}
}
