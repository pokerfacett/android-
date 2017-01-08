package com.example.homework_last;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper {
	private static final String DATABASE_NAME = "Books";// 保存数据库名称
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "book";
	private static final String ID = "_id";
	private static final String NAME = "name";
	private static final String PAGE = "pages";
	private static final String LOVE = "love";
	private static final String DESCRIPTION = "des";
	
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	private static class DBOpenHelper extends SQLiteOpenHelper {
		public DBOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
	
		private static final String CREATE_TABLE = "create table " + TABLE_NAME +
			"(" + ID + " integer primary key autoincrement," + NAME + " text not null, " + PAGE + " int not null, " + LOVE + " int not null, " + DESCRIPTION +" text not null);";
	
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE);
		}
	
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists " + TABLE_NAME);
			onCreate(db);
		}
	}
	public DBHelper(Context context) {
		helper = new DBOpenHelper(context);
		db = helper.getWritableDatabase();
	}
	
	public void insert(Books books) {
		ContentValues values = new ContentValues();
		values.put(NAME, books.getBookname());
		values.put(PAGE, books.getPages());
		values.put(LOVE, books.getLove());
		values.put(DESCRIPTION, books.getDescription());
		//System.out.println("dedcription: "+books.getDescription());
		//System.out.println(values.get(DESCRIPTION));
		db.insert(TABLE_NAME, null, values);
		db.close(); 
	}
	
	public Books query(int id) {
		Books books = new Books();
		Cursor cursor = db.query(TABLE_NAME, new String[] { ID, NAME, PAGE, LOVE, DESCRIPTION }, "_id = " + id, null,null,null,null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			books.setId(cursor.getInt(0));
			books.setBookname(cursor.getString(1));
			books.setPages(cursor.getInt(2));
			books.setLove(cursor.getInt(3));
			books.setDescription(cursor.getString(4));
			//System.out.println("dedcription: "+books.getDescription());
			return books;
		}
		cursor.close();
		return null;
	}
	
	public int getallbooks() {
		Cursor cursor = db.query(TABLE_NAME, new String[] { NAME, PAGE, LOVE, DESCRIPTION }, null, null,null,null,null);
		return cursor.getCount();
	}
	
	public Books[] queryall() {
		Books books = new Books();
		Cursor cursor = db.query(TABLE_NAME, new String[] { ID,NAME, PAGE, LOVE, DESCRIPTION }, null, null,null,null,null);
		Books[] bookarray = new Books[cursor.getCount()];
		for(int j =0; j < cursor.getCount();j++){
			Books temp = new Books();
			bookarray[j] = temp;
		}
		//System.out.println(cursor.getCount());
		int i=0;
		cursor.moveToFirst();
		//bookarray[i].setBookname("");
		//System.out.println(cursor.getString(0).toString());
		bookarray[i].setId(cursor.getInt(0));
		bookarray[i].setBookname(cursor.getString(1).toString());
		bookarray[i].setPages(cursor.getInt(2));
		bookarray[i].setLove(cursor.getInt(3));
		bookarray[i].setDescription(cursor.getString(4));
		for (i=1;i<cursor.getCount();i++){
			cursor.moveToNext();
			bookarray[i].setId(cursor.getInt(0));
			bookarray[i].setBookname(cursor.getString(1).toString());
			bookarray[i].setPages(cursor.getInt(2));
			bookarray[i].setLove(cursor.getInt(3));
			bookarray[i].setDescription(cursor.getString(4));
			//System.out.println("dedcription-query-get: "+bookarray[i].getDescription());
		}
		cursor.close();
		return bookarray;
	}
	
	public void modify(Books books,int id) {
		ContentValues values = new ContentValues();
		values.put(NAME, books.getBookname());
		values.put(PAGE, books.getPages());
		values.put(LOVE, books.getLove());
		values.put(DESCRIPTION, books.getDescription());
		db.update(TABLE_NAME, values, "_id = " + id, null);
		db.close(); 
	}
	
	public void delete(int id) {
		db.delete(TABLE_NAME, "_id = '" + id + "'", null);
		//String delete = "delete from " + TABLE_NAME + " where _id = " + id;
		//db.execSQL(delete);
		db.close(); 
	}
}
