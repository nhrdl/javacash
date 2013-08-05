package com.niranjanrao.test;

import java.util.Map;

class MemoryClassLoader extends ClassLoader {
	private final Map<String, Output> classMap;

	public MemoryClassLoader(final Map<String, Output> map) {
		this.classMap = map;
	}

	@Override
	protected Class<?> findClass(final String name) throws ClassNotFoundException {
		synchronized (this.classMap) {
			final Output mc = classMap.get(name);
			if (mc != null) {
				final byte[] array = mc.toByteArray();
				return defineClass(name, array, 0, array.length);
			}
		}
		return super.findClass(name);
	}
}