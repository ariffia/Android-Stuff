package com.example.simple_gps;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ShowLocationActivity extends ActionBarActivity implements LocationListener{
	
	//
	private LocationManager location_manager;
	private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);
        
        //location manager
        location_manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        //get the best provider
        Criteria criteria = new Criteria();
        provider = location_manager.getBestProvider(criteria, false);
        
        //get the location
        Location location = location_manager.getLastKnownLocation(provider);
        
        //print to UI
        if(location != null) {
        	onLocationChanged(location);
        } else {
        	TextView text_view = (TextView) findViewById(R.id.textView1);
    		text_view.setText("-");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_location, menu);
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


	@Override
	public void onLocationChanged(Location location) {
		
		//get latitude and longitude
		int lat = (int) (location.getLatitude());
		int lng = (int) (location.getLongitude());
		
		//print to UI
		String lat_lng_string = Integer.toString(lat) + ", " + Integer.toString(lng);
		TextView text_view = (TextView) findViewById(R.id.textView1);
		text_view.setText(lat_lng_string);
	}


	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
}
