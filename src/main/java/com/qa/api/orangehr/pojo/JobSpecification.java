package com.qa.api.orangehr.pojo;

public class JobSpecification {
	
	private String base64;
	private String filename;
	private String filesize;
	private String filetype;
	
	
	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	
	
	public JobSpecification(String base64, String filename, String filesize, String filetype) {
		this.base64 = base64;
		this.filename = filename;
		this.filesize = filesize;
		this.filetype = filetype;
	}
	

}
