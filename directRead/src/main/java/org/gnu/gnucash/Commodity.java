package org.gnu.gnucash;

import java.util.ArrayList;

import org.w3c.dom.Node;

public class Commodity extends VersionBase {

	public Commodity(final Node node) {
		super(node);
		slots = new ArrayList<>();
	}

	private String space;
	private String id;
	private String name;
	private String xcode;
	private String fraction;
	private boolean getQuotes;
	private String quote_source;
	private String quoteTimezon;

	ArrayList<KvpSlot> slots;

	public String getSpace() {
		return space;
	}

	public void setSpace(final String space) {
		this.space = space;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getXcode() {
		return xcode;
	}

	public void setXcode(final String xcode) {
		this.xcode = xcode;
	}

	public String getFraction() {
		return fraction;
	}

	public void setFraction(final String fraction) {
		this.fraction = fraction;
	}

	public boolean isGetQuotes() {
		return getQuotes;
	}

	public void setGetQuotes(final boolean getQuotes) {
		this.getQuotes = getQuotes;
	}

	public String getQuote_source() {
		return quote_source;
	}

	public void setQuote_source(final String quote_source) {
		this.quote_source = quote_source;
	}

	public String getQuoteTimezon() {
		return quoteTimezon;
	}

	public void setQuoteTimezon(final String quoteTimezon) {
		this.quoteTimezon = quoteTimezon;
	}

	public ArrayList<KvpSlot> getSlots() {
		return slots;
	}

	@Override
	public void read(final ReadData data) throws Exception {
		super.read(data);
		setId(evaluateXPathString("./cmdty:id"));
		setSpace(evaluateXPathString("./cmdty:space"));

		setName(evaluateXPathString("./cmdty:name"));
		setXcode(evaluateXPathString("./cmdty:xcode"));
		setFraction("./cmdty:fraction");

		final Node node = evaluateXPath("./cmdty:get_quotes");
		setGetQuotes(null != node);
		setQuote_source(evaluateXPathString("./cmdty:quote_source"));
		setQuoteTimezon(evaluateXPathString("./cmdty:quote_tz"));

		KvpSlot.readSlots("./cmdty:slots", getDocument(), getSlots());
	}
}
