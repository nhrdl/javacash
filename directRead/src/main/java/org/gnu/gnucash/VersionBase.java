package org.gnu.gnucash;

import org.w3c.dom.Node;

public class VersionBase extends GnuCashBase {

	public VersionBase(final Node node) {
		super(node);
	}

	public String getExpectedVersion() {
		return "2.0.0";
	}

	@Override
	public void read(final ReadData data) throws Exception {

		super.read(data);

		final String version = evaluateXPathString("./@version");
		if (getExpectedVersion().equals(version) == false) {
			throw new Exception("Version mismatch. Found version " + version);
		}
	}
}
