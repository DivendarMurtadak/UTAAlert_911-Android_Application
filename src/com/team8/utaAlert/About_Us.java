package com.team8.utaAlert;


import com.example.detectinternetconnection.ConnectionDetector;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class About_Us extends Activity implements OnClickListener{
	
	TextView email, website;
    ConnectionDetector cd; 

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        
     // creating connection detector class instance
     	cd = new ConnectionDetector(getApplicationContext());
        email = (TextView) findViewById(R.id.email);
        email.setOnClickListener(this);
        
        email = (TextView) findViewById(R.id.web);
        email.setOnClickListener(this);// comment it if website is not ready
        
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 if(v.getId()==R.id.email)
		 {
			
	        		Intent sendIntent = new Intent(Intent.ACTION_VIEW);
	   			 sendIntent.setType("plain/text");
	   			// sendIntent.setData(Uri.parse("abc@gmail.com"));
	   			 sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
	   			 sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { Constants.contactEmailID });
	   			 //sendIntent.putExtra(Intent.EXTRA_SUBJECT, "");
	   			// sendIntent.putExtra(Intent.EXTRA_TEXT, "Insert text");
	   			 startActivity(sendIntent);
	        	
		 }
		
		 if(v.getId()==R.id.web)
		 {
			 boolean isInternetPresent = cd.isConnectingToInternet();
	        	if(!isInternetPresent)
	        			{
					Toast.makeText(getApplicationContext(), "Data connection unavailable", Toast.LENGTH_SHORT).show();

	        			}
	        	else
	        	{
	        		 Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
	                 myWebLink.setData(Uri.parse("http://www.uta.edu")); 
	                 startActivity(myWebLink);
	        	}
			 
			
		 }
	}

}
