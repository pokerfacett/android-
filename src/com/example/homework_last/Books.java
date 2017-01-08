package com.example.homework_last;

public class Books {
	private int id; // 保存书的id
	private String name; // 保存书名
	private int pages; // 页数
	private int love; // 喜爱程度 1,2,3
	private String description; // 详细描述
	public Books() {
	}
	public Books(String name, int pages, int love, String description) {
		this.name = name;
		this.pages = pages;
		this.love =love;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookname() {
		return name;
	}
	public void setBookname(String name) {
		this.name = name;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love=love;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public String getDescription() {
		return description;
	}
}