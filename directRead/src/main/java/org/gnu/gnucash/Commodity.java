package org.gnu.gnucash;

import org.w3c.dom.Node;

import com.niranjanrao.gnucash.GnuCashBase;

public class Commodity extends GnuCashBase {

	public Commodity(final Node node) {
		super(node);
	}

	@Override
	public void read(final ReadData data) throws Exception {
		super.read(data);

	}
}
