package testhdfs;

import hdfs.jsr203.HadoopFileSystemProvider;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;

import junit.framework.TestCase;

public class HadoopFileSystemTest extends TestCase {

	private static final int port = 8020;
	public static String host = "nc-h04";


	public void testCreateTemp() throws URISyntaxException, IOException {
		URI uri = new URI("hdfs://" + host + ":" + port + "/tmp/toto");
		Path path = Paths.get(uri);
		// Check that dir doesn't exists
		if (Files.exists(path))
			Files.delete(path);
		Files.createDirectory(path);
		assertTrue(Files.exists(path));
	}

	public void testCreateDeleteTemp() throws URISyntaxException, IOException {
		URI uri = new URI("hdfs://" + host + ":" + port + "/tmp/toto");
		Path path = Paths.get(uri);
		// Check that dir doesn't exists
		if (Files.exists(path))
			Files.delete(path);
		
		Files.createDirectory(path);
		assertTrue(Files.exists(path));
		Files.delete(path);
		assertFalse(Files.exists(path));
	}

	public void testDefaults() throws URISyntaxException, IOException {
		URI uri = new URI("hdfs://" + host + ":" + port + "/tmp/toto");
		Path path = Paths.get(uri);
		Files.deleteIfExists(path);
	}

	public void testLastModifiedTime() throws URISyntaxException, IOException {
		URI uri = new URI("hdfs://" + host + ":" + port + "/tmp/toto");
		Path path = Paths.get(uri);
		Files.createDirectory(path);
		assertTrue(Files.exists(path));
		Files.getLastModifiedTime(path);
	}
	
	public void testCheckRead() throws URISyntaxException, IOException {
		URI uri = new URI("hdfs://" + host + ":" + port + "/tmp/test_file");
		Path path = Paths.get(uri);
		if (Files.exists(path))
			Files.delete(path);
		assertFalse(Files.exists(path));
		Files.createFile(path);
		assertTrue(Files.exists(path));
	}
	
	public void testFileStore() throws URISyntaxException, IOException {
		URI uri = new URI("hdfs://" + host + ":" + port + "/tmp/test_file");
		Path path = Paths.get(uri);
		if (Files.exists(path))
			Files.delete(path);
		assertFalse(Files.exists(path));
		Files.createFile(path);
		assertTrue(Files.exists(path));
		FileStore st = Files.getFileStore(path);
		assertNotNull(st);
	}
	
	
	
}
