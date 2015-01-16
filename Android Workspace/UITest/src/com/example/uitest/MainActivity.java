package com.example.uitest;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	static int static_i = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //change the text
//        changeSomething(); 
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
    
    //manipulating UI thread method 1
    public void changeSomething() {
    	new Thread(new Runnable() {
    		public void run() {
    			final TextView text_view = (TextView) findViewById(R.id.tv_hello_world);
    			text_view.post(new Runnable() {
    				public void run() {
    					text_view.setText("Method 1");
    				}
    			});
    		}
    	}).run();
    }
    
    //AsyncTask 1
    private class ChangeText extends AsyncTask<Integer, Void, String> {
		protected String doInBackground(Integer... params) {
			String string_int = new Integer(params[0]).toString();
			return string_int;
		}
		
		protected void onPostExecute(String string_int) {
			final TextView text_view = (TextView) findViewById(R.id.tv_hello_world);
			text_view.setText(string_int);
		}
    }
    
    //start async task 1
    public void startAsyncTask1(View view) {
    	new Thread(new Runnable() {
        	public void run() {	
        		for(int i = 0; i < 1000; i++) {
        			new ChangeText().execute(i);
        			boolean running = true;
        			long start_time = Calendar.getInstance().getTimeInMillis();
        			while(running) {
        				if(100 < Calendar.getInstance().getTimeInMillis() - start_time) {
        					running = false;
        				}
        			}
        		}
        	}
        }).start();
    }
}
