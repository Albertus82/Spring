package it.albertus.storage.local;

import it.albertus.storage.FileStorage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Required;

/**
 * Implementazione di {@link FileStorage} che opera su un file system locale.
 */
public class LocalFileStorage implements FileStorage {

	private String basePath;

	@Required
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	@Override
	public InputStream downloadAsStream(final String sourceFileName) throws FileNotFoundException {
		return new BufferedInputStream(new FileInputStream(basePath + File.separatorChar + sourceFileName));
	}

	@Override
	public void downloadToFile(final String sourceFileName, final String destinationPathFileName) throws IOException {
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(destinationPathFileName));
		downloadToStream(sourceFileName, os);
		os.close();
	}

	@Override
	public void downloadToStream(final String sourceFileName, final OutputStream outputStream) throws IOException {
		InputStream is = downloadAsStream(sourceFileName);
		IOUtils.copy(is, outputStream);
		is.close();
		outputStream.flush();
	}

	@Override
	public void uploadFromFile(String sourcePathFileName, String destinationFileName) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(sourcePathFileName));
		uploadAsStream(is, destinationFileName);
		is.close();
	}

	@Override
	public void uploadAsStream(InputStream inputStream, String destinationFileName) throws IOException {
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(basePath + File.separatorChar + destinationFileName));
		IOUtils.copy(inputStream, os);
		os.close();
	}

	@Override
	public boolean exists(String fileName) {
		File file = new File(basePath + File.separatorChar + fileName);
		return file.exists();
	}

	@Override
	public boolean delete(String fileName) {
		File file = new File(basePath + File.separatorChar + fileName);
		return file.delete();
	}

}
