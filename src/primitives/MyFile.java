package primitives;

import java.io.File;

public class MyFile {
	private int id;
	private	String title;
	private	String letterNo;
	File file;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return file.getName();
	}
	public void setFile(File f) {
		this.file = f;
	}
	public File getFile(){
		return file;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLetterNo() {
		return letterNo;
	}
	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}
	public String getAbsolutePath(){
		return file.getAbsolutePath();
	}
	
}
