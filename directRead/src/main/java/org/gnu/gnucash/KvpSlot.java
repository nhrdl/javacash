package org.gnu.gnucash;

import java.util.ArrayList;

import org.w3c.dom.Node;

public class KvpSlot extends GnuCashBase {

	public KvpSlot() {
	}

	public KvpSlot(final Node root) {
		super(root);
	}

	public static void readSlots(final String query, final Node queryNode, final ArrayList<KvpSlot> list) throws Exception {

	}
}
