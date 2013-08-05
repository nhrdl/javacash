package com.niranjanrao.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.codemodel.JPackage;
import com.sun.codemodel.writer.FileCodeWriter;

public class CodeWriter extends FileCodeWriter {

	private final ArrayList<File> fileList;

	public CodeWriter(final File target) throws IOException {
		super(target);
		this.fileList = new ArrayList<File>();
	}

	@Override
	protected File getFile(final JPackage pkg, final String fileName) throws IOException {
		final File file = super.getFile(pkg, fileName);
		fileList.add(file);
		return file;
	}

	public ArrayList<File> getFileList() {
		return fileList;
	}
}
