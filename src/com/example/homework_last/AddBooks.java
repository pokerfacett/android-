package com.example.homework_last;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBooks extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addbooks);
		Button add = (Button) findViewById(R.id.addbutton);
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				final EditText name=(EditText) findViewById(R.id.name);
				final EditText page=(EditText) findViewById(R.id.page);
				final EditText love=(EditText) findViewById(R.id.love);
				final EditText description=(EditText) findViewById(R.id.description);
				
				if(name.getText().toString().length() == 0 ) {
					Toast.makeText(AddBooks.this, "书名不能为空", Toast.LENGTH_LONG).show();
				} else if (page.getText().toString().length() == 0 ){
					Toast.makeText(AddBooks.this, "页数不能为空", Toast.LENGTH_LONG).show();
				} else if (love.getText().toString().length() == 0) {
					Toast.makeText(AddBooks.this, "喜欢程度不能为空", Toast.LENGTH_LONG).show();
				} else if (description.getText().toString().length() == 0) {
					Toast.makeText(AddBooks.this, "描述不能为空", Toast.LENGTH_LONG).show();
				} else {
					DBHelper helper = new DBHelper(AddBooks.this);
					String book_name = name.getText().toString();
					int book_page = Integer.valueOf(page.getText().toString());
					int book_love = Integer.valueOf(love.getText().toString());
					String book_description = description.getText().toString();
					//System.out.println("dedcription"+book_description);
					Books books = new Books(book_name,book_page,book_love,book_description);
					//System.out.println("dedcription: "+books.getDescription());
					helper.insert(books);
					finish();
/*					Intent intent = new Intent();
					intent.setClass(AddBooks.this, MainActivity.class);
					startActivity(intent);*/
				}
				
			}
		});
	}
}
