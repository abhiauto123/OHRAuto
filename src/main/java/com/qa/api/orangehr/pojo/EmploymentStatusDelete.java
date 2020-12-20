package com.qa.api.orangehr.pojo;

import java.util.List;

public class EmploymentStatusDelete {
	
	private List<String> data;

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public EmploymentStatusDelete(List<String> data) {
		this.data = data;
	}

}
