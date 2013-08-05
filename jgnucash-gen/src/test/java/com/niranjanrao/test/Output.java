package com.niranjanrao.test;

import java.io.ByteArrayOutputStream;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

class Output extends SimpleJavaFileObject {
	private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

	Output(final String name, final Kind kind) {
		super(URI.create("memo:///" + name.replace('.', '/') + kind.extension), kind);
	}

	byte[] toByteArray() {
		return this.baos.toByteArray();
	}

	@Override
	public ByteArrayOutputStream openOutputStream() {
		return this.baos;
	}
}