package com.example.homework_last;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyBooks extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modifybooks);
		Button add = (Button) findViewById(R.id.modifybutton);
		add.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			final EditText number=(EditText) findViewById(R.id.number);
			final EditText name=(EditText) findViewById(R.id.name);
			final EditText page=(EditText) findViewById(R.id.page);
			final EditText love=(EditText) findViewById(R.id.love);
			final EditText description=(EditText) findViewById(R.id.description);
			
			if (number.getText().toString().length() == 0) {
				Toast.makeText(ModifyBooks.this, "书的编号不能为空", Toast.LENGTH_LONG).show();
			} else if(name.getText().toString().length() == 0 ) {
				Toast.makeText(ModifyBooks.this, "书名不能为空", Toast.LENGTH_LONG).show();
			} else if (page.getText().toString().length() == 0 ){
				Toast.makeText(ModifyBooks.this, "页数不能为空", Toast.LENGTH_LONG).show();
			} else if (love.getText().toString().length() == 0) {
				Toast.makeText(ModifyBooks.this, "喜欢程度不能为空", Toast.LENGTH_LONG).show();
			} else if (description.getText().toString().length() == 0) {
				Toast.makeText(ModifyBooks.this, "描述不能为空", Toast.LENGTH_LONG).show();
			} else {
				DBHelper helper = new DBHelper(ModifyBooks.this);
				
				int book_number = Integer.valueOf(number.getText().toString());
				String book_name = name.getText().toString();
				int book_page = Integer.valueOf(page.getText().toString());
				int book_love = Integer.valueOf(love.getText().toString());
				String book_description = description.getText().toString();
				Books books = new Books(book_name,book_page,book_love,book_description);
				int allbook = helper.getallbooks();
				Books[] allbooks = new Books[allbook];
				for (int i=0;i<allbook;i++)
				{
					Books temp = new Books();
					allbooks[i] = temp;
					//System.out.println(allbooks[i].getBookname());
				}
				allbooks = helper.queryall();
				if (book_number > allbook) {
					Toast.makeText(ModifyBooks.this, "书编号超出现有书籍！！", Toast.LENGTH_LONG).show();
				} else {
					Books databook = helper.query(allbooks[book_number-1].getId());
					if (null == databook) {
						Toast.makeText(ModifyBooks.this, "书目编号有误！！", Toast.LENGTH_LONG).show();
					} else {
						helper.modify(books,allbooks[book_number-1].getId());
						finish();
					}
				}
			}
				
		}
		});
	}
}
