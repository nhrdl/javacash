package com.niranjanrao.test;

import java.io.InputStream;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.niranjanrao.gnucash.CashGenerator;

public class TesterBase {
	protected MemoryCodeWriter memoryWriter;
	ClassLoader memoryClassLoader;
	protected CashGenerator gen;

	@Before
	public void setup() throws Exception {
		this.gen = new CashGenerator();
		gen.loadDocument(getRNGFileResource());
		memoryWriter = new MemoryCodeWriter();

	}

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {

		@Override
		public void failed(final Throwable e, final Description description) {
			memoryWriter.dump();
		};
	};

	ClassLoader compileInMemory(final MemoryCodeWriter writer) {
		final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		final DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		final MemoryFileManager fileMan = new MemoryFileManager(compiler);
		final CompilationTask task = compiler.getTask(null, fileMan, diagnostics, null, null, writer.getCompilationUnits());
		final boolean success = task.call();
		for (final Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
			System.out.println(diagnostic.getLineNumber());
			System.out.println(diagnostic.getMessage(Locale.US));
			// System.out.println(diagnostic.getCode());
			// System.out.println(diagnostic.getKind());
			// System.out.println(diagnostic.getPosition());
			// System.out.println(diagnostic.getStartPosition());
			// System.out.println(diagnostic.getEndPosition());
			// System.out.println(diagnostic.getSource());
			// System.out.println(diagnostic.getMessage(null));
			//

		}
		// writer.dump();

		Assert.assertTrue("Compilation failed", success);
		return new MemoryClassLoader(fileMan.getMap());
	}

	Class<?> compileAndLoad(final String fullClassName) throws Exception {

		gen.writeTo(memoryWriter);
		memoryClassLoader = compileInMemory(memoryWriter);
		return memoryClassLoader.loadClass(fullClassName);

	}

	public InputStream getRNGFileResource() {
		return TesterBase.class.getResourceAsStream("/gnucash-v2.rng.xml");
	}
}