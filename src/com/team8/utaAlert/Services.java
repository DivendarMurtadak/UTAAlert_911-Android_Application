package com.team8.utaAlert;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class Services extends Service {
	

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
		//getApplicationContext().getContentResolver().unregisterContentObserver(mSettingsContentObserver);
		
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		// return super.onStartCommand(intent, flags, startId);
		Toast.makeText(this, "Service started", Toast.LENGTH_LONG).show();

		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		BroadcastReceiver mReceiver = new MyBroadCastReciever();
		registerReceiver(mReceiver, filter);
		
		

		// mSettingsContentObserver = new SettingsContentObserver(this,new
		// Handler());
		// getApplicationContext().getContentResolver().registerContentObserver(android.provider.Settings.System.CONTENT_URI,
		// true, mSettingsContentObserver );
		// elogCatTest();
		return START_STICKY;
	}
	
	
	
	
/*	protected void logCatTest (){
		
        for (int i = 0; i < 100000000; i++) {
            try {
                Thread.sleep(1000);
                Log.d("Test", "Long press "+i+" !");
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
		
	}*/

}
