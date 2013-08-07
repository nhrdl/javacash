package org.gnu.gnucash;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

public class Book extends VersionBase {

	private final ArrayList<Commodity> commodities;

	Logger log = Logger.getLogger(Book.class);

	public Book(final Node node) {
		super(node);
		this.commodities = new ArrayList<Commodity>();
	}

	@Override
	public void read(final ReadData data) throws Exception {
		super.read(data);

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
