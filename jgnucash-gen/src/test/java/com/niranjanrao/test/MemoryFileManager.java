package com.niranjanrao.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

class MemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {
	private final Map<String, Output> map = new HashMap<String, Output>();

	MemoryFileManager(final JavaCompiler compiler) {
		super(compiler.getStandardFileManager(null, null, null));
	}

	@Override
	public JavaFileObject getJavaFileForOutput(final Location location, final String className, final javax.tools.JavaFileObject.Kind kind,
			final FileObject sibling) throws IOException {
		final Output mc = new Output(className, kind);
		this.map.put(className, mc);
		return mc;
	}

	public Map<String, Output> getMap() {
		return map;
	}

	public void dump() {
		// TODO Auto-generated method stub

	}

}