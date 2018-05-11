package com.secretk.move.bean;

public class PicBean implements Comparable<PicBean>{



	private Long id;


	private String name;

	private String path;

	private String time;


	private Long size;
	

	private Long time_compare;
	

	public Long getTime_compare() {
		return time_compare;
	}

	public void setTime_compare(Long time_compare) {
		this.time_compare = time_compare;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(PicBean another) {
		int num=this.getTime_compare().compareTo(another.getTime_compare());
		return -num;
	}
}
