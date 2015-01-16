package com.example.rethinus;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class RethinusUI extends ActionBarActivity {
	
	//this activity
	static Activity start_activity;
	
	//input numbers
	public static int[] input_numbers;
	public static int keyboard_numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        
        //get this activity
        start_activity = this;
        
        //init
        keyboard_numbers = 0;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rethinus_ui, menu);
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
    
    //start button
    public void start(View view) {
    	runOnUiThread(new Runnable() {
    		public void run() {
    			setContentView(R.layout.display);
    		}
    	});
    	new Thread(new Runnable() {
    		public void run() {
    			RethinusCore.makeNewNumbers();
    			input_numbers = new int[RethinusCore.getNumbersLength()];
    	    	for(int i = 0; i < RethinusCore.getNumbersLength(); i++) {	//display those numbers
    	    		new DisplayAsyncTask().execute(RethinusCore.getNumbers()[i]);
    	    		long start_time = Calendar.getInstance().getTimeInMillis();
    	    		while(1000 > Calendar.getInstance().getTimeInMillis() - start_time) {
    	    		}
    	    	}
    	    	runOnUiThread(new Runnable() {	//bring up the keyboard
    	    		public void run() {
    	    			keyboard_numbers = 0;
    	    			setContentView(R.layout.keyboard);
    	    		}
    	    	});
    		}
    	}).start();
    }
    
    //cont
    public void cont() {
    	runOnUiThread(new Runnable() {
    		public void run() {
    			setContentView(R.layout.display);
    		}
    	});
    	new Thread(new Runnable() {
    		public void run() {
    			RethinusCore.makeNewNumbers();
    			input_numbers = new int[RethinusCore.getNumbersLength()];
    	    	for(int i = 0; i < RethinusCore.getNumbersLength(); i++) {	//display those numbers
    	    		new DisplayAsyncTask().execute(RethinusCore.getNumbers()[i]);
    	    		long start_time = Calendar.getInstance().getTimeInMillis();
    	    		while(1000 > Calendar.getInstance().getTimeInMillis() - start_time) {
    	    		}
    	    	}
    	    	runOnUiThread(new Runnable() {	//bring up the keyboard
    	    		public void run() {
    	    			keyboard_numbers = 0;
    	    			setContentView(R.layout.keyboard);
    	    		}
    	    	});
    		}
    	}).start();
    }
    
	//CLASS
	//display task
    private class DisplayAsyncTask extends AsyncTask<Integer, Void, String> {
		protected String doInBackground(Integer... params) {
			String int_string = Integer.valueOf(params[0]).toString();
			return int_string;
		}
		protected void onPostExecute(String arg) {
			final TextView text_view = (TextView) findViewById(R.id.number_display);
			text_view.setText(arg);
		}
    }
    
    //key board button press
    //if input number is empty, fill it in
    //if full go to next state and empty it
    public void keyboardButtonPress(View view) {
    	if(keyboard_numbers < RethinusCore.getNumbersLength()) {
    		Button button = (Button) view;
        	if(button.getText().equals("0")) {
        		input_numbers[keyboard_numbers] = 0;
        	} else if(button.getText().equals("1")) {
        		input_numbers[keyboard_numbers] = 1;
        	} else if(button.getText().equals("2")) {
        		input_numbers[keyboard_numbers] = 2;
        	} else if(button.getText().equals("3")) {
        		input_numbers[keyboard_numbers] = 3;
        	} else if(button.getText().equals("4")) {
        		input_numbers[keyboard_numbers] = 4;
        	} else if(button.getText().equals("5")) {
        		input_numbers[keyboard_numbers] = 5;
        	} else if(button.getText().equals("6")) {
        		input_numbers[keyboard_numbers] = 6;
        	} else if(button.getText().equals("7")) {
        		input_numbers[keyboard_numbers] = 7;
        	} else if(button.getText().equals("8")) {
        		input_numbers[keyboard_numbers] = 8;
        	} else if(button.getText().equals("9")) {
        		input_numbers[keyboard_numbers] = 9;
        	} else {
        		
        	}
        	keyboard_numbers++;
    	} else {
    	}
    	//go next state?
    	if(keyboard_numbers == RethinusCore.getNumbersLength()) {
        	boolean cont = true;
    		for(int i = 0; i < RethinusCore.getNumbersLength(); i++) {
    			if(input_numbers[i] != RethinusCore.getNumbers()[i]) {
    				cont = false;
    			}
    		}
    		if(cont) {
    			RethinusCore.levelUp();
    			cont();
    		} else {
    			RethinusCore.gameOver();
    			runOnUiThread(new Runnable() {
    	    		public void run() {
    	    			setContentView(R.layout.start);
    	    		}
    	    	});
    		}
    	}
    }
}
