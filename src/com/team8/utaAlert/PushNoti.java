package com.team8.utaAlert;



import android.app.Activity;

import android.os.Bundle;
import android.widget.TextView;

public class PushNoti extends Activity{
	
	TextView pushMessage; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.push_notification);
       
        pushMessage=(TextView) findViewById(R.id.pushMsg);
        
//        Toast.makeText(getApplicationContext(), "tp",  Toast.LENGTH_SHORT).show();
//        Log.d("Push ", "Noti..");
//        pushMessage.setText("tp");
        

	}
	
}
