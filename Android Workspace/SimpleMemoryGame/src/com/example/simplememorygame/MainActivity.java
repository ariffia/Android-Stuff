package com.example.simplememorygame;

import java.util.Calendar;
import Engine.MachineState;
import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	//game engine
	private static Engine.SMGM smgm;
	
	//UI field
	private static MainActivity mainActivity;
	private TextView number_display;	//display the numbers in display state
	
	//special field
	private static int i_static;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset);
        
        //load UI objects
        mainActivity = this;
        
        //load the game engine
        smgm = new Engine.SMGM();
        updateUiState();
    }
    
    //idle start button
    public void idleStart(View view) {
    	new Thread(new Runnable() {
    		public void run() {
    			smgm.gameStart();
    			updateUiState();
    			
    			//number display
    			long elapsed_time = 0;
    			long time_slice = smgm.getDisplayTime()/smgm.getNumbers().length;
    			boolean running = true;
    			long old_time;
    			final TextView text_view = (TextView) findViewById(R.id.display_number);
    			
    			for(int i = 0; i < smgm.getNumbers().length; i++) {
    				new DisplayNumberTextAsyncTask().execute(smgm.getNumbers()[i]);
    				elapsed_time = 0;
    				old_time = Calendar.getInstance().getTimeInMillis();
    				while(running) {
    					if(time_slice < elapsed_time) {
    						running = false;
    					}
    					elapsed_time = Calendar.getInstance().getTimeInMillis() - old_time;
    				}
    				running = true;
    			}
    			updateUiState();
    		}
    	}).start();
    }
    
    //continue the game
    public void gameContinue() {
    	updateUiState();
    	new Thread(new Runnable() {
    		public void run() {
    			updateUiState();
    			
    			//number display
    			long elapsed_time = 0;
    			long time_slice = smgm.getDisplayTime()/smgm.getNumbers().length;
    			boolean running = true;
    			long old_time;
    			final TextView text_view = (TextView) findViewById(R.id.display_number);
    			
    			for(int i = 0; i < smgm.getNumbers().length; i++) {
    				new DisplayNumberTextAsyncTask().execute(smgm.getNumbers()[i]);
    				elapsed_time = 0;
    				old_time = Calendar.getInstance().getTimeInMillis();
    				while(running) {
    					if(time_slice < elapsed_time) {
    						running = false;
    					}
    					elapsed_time = Calendar.getInstance().getTimeInMillis() - old_time;
    				}
    				running = true;
    			}
    			updateUiState();
    		}
    	}).start();
    }
    
    //display to keyboard timer
    public void displayToKeyboard() {
    	final long display_time = smgm.getDisplayTime() + 100;
    	new Thread(new Runnable() {
    		public void run() {
    			//sleep for a while
    			try {
					Thread.sleep(display_time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			
    			//bring up the keyboard
    			updateUiState();
    		}
    	}).start();
    }
    
    //display the numbers
    public void displayNumbers() {
    }
    
    //update UI state
    public void updateUiState() {
    	final MachineState state = smgm.getCurrentState();
    	
    	//UI worker thread
        new Thread(new Runnable() {
			@Override
			public void run() {
				if(state == MachineState.RESET) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mainActivity.setContentView(R.layout.reset);
						}
					});
				} else if(state == MachineState.IDLE) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mainActivity.setContentView(R.layout.idle);
						}
					});
				} else if(state == MachineState.DISPLAY) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mainActivity.setContentView(R.layout.display);
						}
					});
				} else if(state == MachineState.KEYBOARD) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mainActivity.setContentView(R.layout.keyboard);
						}
					});
				} else if(state == MachineState.GAMEOVER) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mainActivity.setContentView(R.layout.gameover);
						}
					});
				} else {
				}
			}
        }).start();
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
    
    //change display number text
    private class DisplayNumberTextAsyncTask extends AsyncTask<Integer, Void, String> {
		protected String doInBackground(Integer... params) {
			String int_string = new Integer(params[0]).toString();
			return int_string;
		}
		
		protected void onPostExecute(String int_string) {
			final TextView text_view = (TextView) findViewById(R.id.display_number);
			text_view.setText(int_string);
		}
    }
    
    //keyboard button press
    public void keyboardButtonPress(View view) {
    	Button button = (Button) view;
    	if(button.getText().equals("0")) {
    		smgm.press0();
    	} else if(button.getText().equals("1")) {
    		smgm.press1();
    	} else if(button.getText().equals("2")) {
    		smgm.press2();
    	} else if(button.getText().equals("3")) {
    		smgm.press3();
    	} else if(button.getText().equals("4")) {
    		smgm.press4();
    	} else if(button.getText().equals("5")) {
    		smgm.press5();
    	} else if(button.getText().equals("6")) {
    		smgm.press6();
    	} else if(button.getText().equals("7")) {
    		smgm.press7();
    	} else if(button.getText().equals("8")) {
    		smgm.press8();
    	} else if(button.getText().equals("9")) {
    		smgm.press9();
    	} else {
    	}
    	updateUiState();
    	
    	//check done
    	if(smgm.isKeyboardStateDone()) {
    		smgm.forceGoToDisplayState();
    		
    		//updateUiState
    		runOnUiThread(new Runnable() {
    			public void run() {
    				mainActivity.setContentView(R.layout.display);
    			}
    		});
    	} else {
    	}
    }
}
