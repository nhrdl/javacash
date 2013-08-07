package org.gnu.gnucash;

import org.w3c.dom.Node;

public class GnuCashBase extends CashBase {

	public GnuCashBase() {
	}

	public GnuCashBase(final Node root) {
		super(root);
	}

	public void read(final ReadData data) throws Exception {

	}

	public boolean isValid() {
		return true;
	}
}
