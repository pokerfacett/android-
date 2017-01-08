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
			// TODO �Զ����ɵķ������
			final EditText number=(EditText) findViewById(R.id.number);
			final EditText name=(EditText) findViewById(R.id.name);
			final EditText page=(EditText) findViewById(R.id.page);
			final EditText love=(EditText) findViewById(R.id.love);
			final EditText description=(EditText) findViewById(R.id.description);
			
			if (number.getText().toString().length() == 0) {
				Toast.makeText(ModifyBooks.this, "��ı�Ų���Ϊ��", Toast.LENGTH_LONG).show();
			} else if(name.getText().toString().length() == 0 ) {
				Toast.makeText(ModifyBooks.this, "��������Ϊ��", Toast.LENGTH_LONG).show();
			} else if (page.getText().toString().length() == 0 ){
				Toast.makeText(ModifyBooks.this, "ҳ������Ϊ��", Toast.LENGTH_LONG).show();
			} else if (love.getText().toString().length() == 0) {
				Toast.makeText(ModifyBooks.this, "ϲ���̶Ȳ���Ϊ��", Toast.LENGTH_LONG).show();
			} else if (description.getText().toString().length() == 0) {
				Toast.makeText(ModifyBooks.this, "��������Ϊ��", Toast.LENGTH_LONG).show();
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
					Toast.makeText(ModifyBooks.this, "���ų��������鼮����", Toast.LENGTH_LONG).show();
				} else {
					Books databook = helper.query(allbooks[book_number-1].getId());
					if (null == databook) {
						Toast.makeText(ModifyBooks.this, "��Ŀ������󣡣�", Toast.LENGTH_LONG).show();
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
