package org.gnu.gnucash;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.niranjanrao.gnucash.GnuCashBase;

public class Book extends GnuCashBase {

	private final ArrayList<Commodity> commodities;

	Logger log = Logger.getLogger(Book.class);

	public Book(final Node node) {
		super(node);
		this.commodities = new ArrayList<Commodity>();
	}

	@Override
	public void read(final ReadData data) throws Exception {
		final String version = evaluateXPathString("./@version");
		if ("2.0.0".equals(version) == false) {
			throw new Exception("Version mismatch. Found version " + version);
		}

		readCommodities(data);
	}

	private void readCommodities(final ReadData readData) throws Exception {

		final int expectedCount = Integer.parseInt(evaluateXPathString("./gnc:count-data[@cd:type='commodity']"));
		log.info("Expected commodity count " + expectedCount);

		forEachNodeDo("./gnc:commodity", new INodeWorker() {

			@Override
			public void doWork(final int index, final Node node, final Object... data) throws Exception {
				final Commodity commodity = new Commodity(node);
				commodities.add(commodity);
				commodity.read(readData);
			}
		});
	}

}
