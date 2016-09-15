package nussu.checker.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.LinkedList;

public class CheckerStorage {
	public static final String FILE_NAME = "storage.txt";
	public static final Charset CHARSET_ASCII = Charset.forName("US-ASCII");
	
	private static CheckerStorage _storage = null;
	
	private LinkedList <String> _storageList = new LinkedList<String>(); 
	private HashSet <String> _storageSet = new HashSet<String>();
	
	private CheckerStorage() {
		initCheckerStorage();
	}
	
	public static CheckerStorage getInstance() {
		if (_storage == null) {
			_storage = new CheckerStorage();
		}
		return _storage;
	}
	
	public boolean addEntry(String entry) {
		entry = entry.trim().toLowerCase();
		
		if (_storageSet.contains(entry)) {
			return false;
		}
						
		addLine(entry);
		return true;
	}
	
	
	private void addLine(String line) {
		File file = retrieveStorageFile();
		line = line.trim().toLowerCase();
		
		_storageSet.add(line);
	    _storageList.add(line);
		
		
		try (FileWriter fw = new FileWriter(file, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw)){
			    out.println(line);

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}
	
	private void initCheckerStorage() {
		File file = retrieveStorageFile();
		
		if (file.exists()) {
			getDataFromFile(file);
		} else {
			createFile(file);
		}
		
	}
	
	private void createFile(File file) {
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getDataFromFile(File file) {
		try (BufferedReader reader = Files.newBufferedReader(file.toPath(), CHARSET_ASCII)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        line = line.trim().toLowerCase();
		        _storageList.add(line);
		        _storageSet.add(line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	private File retrieveStorageFile() {
		File file = new File(FILE_NAME);
		return file;
	}
}
