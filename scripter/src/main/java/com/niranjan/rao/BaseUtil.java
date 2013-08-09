package com.niranjan.rao;

import biz.wolschon.fileformats.gnucash.jwsdpimpl.GnucashFileWritingImpl;

public class BaseUtil {

	private GnucashFileWritingImpl writer;

	public GnucashFileWritingImpl getWriter() {
		return writer;
	}

	public void setWriter(final GnucashFileWritingImpl writer) {
		this.writer = writer;
	}
}
