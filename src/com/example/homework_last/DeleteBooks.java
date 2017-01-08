package com.example.homework_last;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteBooks extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletebooks);
		Button delete = (Button) findViewById(R.id.deletebutton);
		delete.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
			// TODO �Զ����ɵķ������
			final EditText name=(EditText) findViewById(R.id.name);
				
			if(name.getText().toString().length() == 0 ) {
				Toast.makeText(DeleteBooks.this, "�鼮��Ų���Ϊ��", Toast.LENGTH_LONG).show();
			} else {
				DBHelper helper = new DBHelper(DeleteBooks.this);
				int book_number = Integer.valueOf(name.getText().toString());
				int allbooks = helper.getallbooks();
				if (book_number > allbooks){
					Toast.makeText(DeleteBooks.this, "��Ŀ������󣡣�", Toast.LENGTH_LONG).show();
				} else {
					Books[] books = helper.queryall();
					Books databook = helper.query(books[book_number-1].getId());
					if (null == databook) {
						Toast.makeText(DeleteBooks.this, "��Ŀ������󣡣�", Toast.LENGTH_LONG).show();
					} else {
						helper.delete(books[book_number-1].getId());
						//finish();
						Intent intent = new Intent();
						intent.setClass(DeleteBooks.this, MainActivity.class);
						startActivity(intent);
					}
				}
			}
				
		}
		});
	}
}