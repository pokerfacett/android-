package com.example.homework_last;

public class Books {
	private int id; // �������id
	private String name; // ��������
	private int pages; // ҳ��
	private int love; // ϲ���̶� 1,2,3
	private String description; // ��ϸ����
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