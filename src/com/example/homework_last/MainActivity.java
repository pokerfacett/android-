package com.example.homework_last;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // ����鼮
        Button add = (Button) findViewById(R.id.add);
        Button modify = (Button) findViewById(R.id.modify);
        Button delete = (Button) findViewById(R.id.delete);
        
        add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, AddBooks.class);
				startActivity(intent);
			}
		});
        // �޸��鼮
        modify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, ModifyBooks.class);
				startActivity(intent);
			}
		});
        
        // ɾ���鼮
        delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, DeleteBooks.class);
				startActivity(intent);
			}
		});
    }

    @Override
    public void onResume()
    {
    	super.onResume();
    	DBHelper helper = new DBHelper(MainActivity.this);
        // �����ݿ����Ƿ����鼮
        int allbooks = helper.getallbooks();
        if (0==allbooks) {
        	
        } else {
        	System.out.println(allbooks);
        	ListView listView=(ListView) findViewById(R.id.listview);
        	Books[] bookarray = helper.queryall();
        	String[] books =new String[allbooks];
        	for (int i=0;i<allbooks;i++) {
        		int j = i+1;
        		books[i] =j + ". " + bookarray[i].getBookname();
        	}
        	ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,books);
        	listView.setAdapter(adapter);
        	
        	// �����ʾ�����Ϣ
        	listView.setOnItemClickListener(new OnItemClickListener() {
        		@Override
        		public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
        			//String result = parent.getItemAtPosition(pos).toString();
        			DBHelper helper = new DBHelper(MainActivity.this);
        			Books[] bookarray1 = helper.queryall();
        			Books book = helper.query(bookarray1[pos].getId());
        			String result= "������" + book.getBookname() + " ҳ����" + book.getPages() + " ϲ���̶ȣ�" + book.getLove() + " ��ϸ������" + book.getDescription();
        			Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        		}
			});
        	/*String[] books = new String[] {"1","2"};
        	ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,books);
        	listView.setAdapter(adapter);*/
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
