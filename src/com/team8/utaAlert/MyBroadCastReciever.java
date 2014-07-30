package com.team8.utaAlert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.gsm.SmsManager;
import android.util.Log;

@SuppressWarnings("deprecation")
public class MyBroadCastReciever extends BroadcastReceiver {

	Main_utaAlertActivity main = null;
	void setMainActivityHandler(Main_utaAlertActivity main){
	    this.main=main;
	}
	private int count = 0;
	long delta = 0;

	@Override
	public void onReceive(Context context, Intent intent) {

		Log.d("Test", "Long press test !");
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			count++;
			Log.d("Test", "Long press OFF ! " + count);
			checkActionReq(context);

		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			count++;
			Log.d("Test", "Long press ON ! " + count);
			checkActionReq(context);

		}
	}

	private void checkActionReq(Context context) {

		if (count == 1) {

			delta = System.currentTimeMillis();

		} else {

			if ((System.currentTimeMillis() - delta) > 6000) {
				count = 0;
				delta = System.currentTimeMillis();
			}

		}

		if (count == 4) {

			if ((System.currentTimeMillis() - delta) <= 6000) {
				Intent callingIntent = new Intent(context, Main_utaAlertActivity.class);
			    callingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			    callingIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			    context.startActivity(callingIntent);
				
				count = 0;
				delta = 0;
			}

		}
		// Take count of the screen off position

	}
	
}
