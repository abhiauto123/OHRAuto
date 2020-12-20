package com.qa.api.orangehr.pojo;

public class JobTitle {
	
     private String jobTitleName;
     private String jobDescription;
     private String note;
     private String currentJobSpecification;
     private JobSpecification jobSpecification;
     
     public String getJobTitleName() {
		return jobTitleName;
	}

	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCurrentJobSpecification() {
		return currentJobSpecification;
	}

	public void setCurrentJobSpecification(String currentJobSpecification) {
		this.currentJobSpecification = currentJobSpecification;
	}

	public JobSpecification getJobSpecification() {
		return jobSpecification;
	}

	public void setJobSpecification(JobSpecification jobSpecification) {
		this.jobSpecification = jobSpecification;
	}

	
     
     public JobTitle(String jobTitleName, String jobDescription, String note, String currentJobSpecification,
			JobSpecification jobSpecification) {
		this.jobTitleName = jobTitleName;
		this.jobDescription = jobDescription;
		this.note = note;
		this.currentJobSpecification = currentJobSpecification;
		this.jobSpecification = jobSpecification;
	}
	
     
}
