package com.niranjanrao.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.tools.JavaFileObject;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JPackage;

public class MemoryCodeWriter extends CodeWriter {

	HashMap<String, JavaSourceFromString> sourceMap = new HashMap<String, JavaSourceFromString>();
	ByteArrayOutputStream bos;
	private String lastEntry;

	public MemoryCodeWriter() {
		lastEntry = null;
	}

	@Override
	public OutputStream openBinary(final JPackage pkg, final String fileName) throws IOException {
		if (null != lastEntry) {
			saveStreamData();

		}
		this.lastEntry = pkg.name() + "." + fileName.replace(".java", "");
		bos = new ByteArrayOutputStream();
		return bos;
	}

	void saveStreamData() throws IOException {
		bos.flush();
		bos.close();
		final String code = new String(bos.toByteArray(), Charset.defaultCharset());
		final JavaSourceFromString obj = new JavaSourceFromString(lastEntry, code);
		sourceMap.put(lastEntry, obj);
	}

	@Override
	public void close() throws IOException {
		saveStreamData();
	}

	public Iterable<? extends JavaFileObject> getCompilationUnits() {
		return sourceMap.values();
	}

	public void dump() {
		for (final Entry<String, JavaSourceFromString> e : sourceMap.entrySet()) {
			System.out.println("******************** " + e.getKey() + "*************");
			System.out.println(e.getValue().code);
		}

	}

}
