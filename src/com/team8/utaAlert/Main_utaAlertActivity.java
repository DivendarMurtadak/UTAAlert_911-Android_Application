package com.team8.utaAlert;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.detectinternetconnection.ConnectionDetector;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.team8.utaAlert.Services;

public class Main_utaAlertActivity extends Activity implements OnClickListener, android.content.DialogInterface.OnClickListener{
	
	Button btnShowLocation, btnEmergencyServices, btnInformIllegalActivity, btnHelpline, btnContacts,
	btnPermissions, btnUserGuide,btnMore, btnNext;
		
	//DB
    SharedPreferences preferences; 
	String welcomeScreenShownPref = "welcomeScreenShown";
	Boolean welcomeScreenShown;// = preferences.getBoolean(welcomeScreenShownPref, false);

	
	// flag for Internet connection status
	Boolean isInternetPresent;
	// Connection detector class
	ConnectionDetector cd; 
		
	
	// cell info
		TelephonyManager telephonyManager;
		GsmCellLocation cellLocation;
		String networkOperator;
		String mcc, mnc;
		int cid , lac;
		double latitude,longitude,accuracy;
        String lat;
        String lng;
		String cellID;
		String LAC ;
		String MCC ;
		String MNC ;
		String contact1, contact2;
		
	//
		String[] stringArray;
		String emergancyType;


		//for quick action popup
		private static final int ID_DESCLAIMER     = 1;
		private static final int ID_ABOUT_US   = 2;
		private static final int RATE_US     = 3;
		

		//for location_
		 ProgressBar progress;
		 LocationManager locManager;
		 LocationListener locListener;//= new MyLocationListener();
		 boolean gps_enabled=false;
		 boolean network_enabled=false;
	    // private ProgressDialog dialog=null;
		  ProgressDialog progressDialog;
		  boolean locationFound = false;
		  
		  //
		  boolean cellIDfound=false;
          
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //onStart();
        isInternetPresent = false;

        startService(new Intent(getBaseContext(),Services.class));
		Log.d("Test", "Power Button Service Started");
        
        //push noti
        Parse.initialize(this, Constants.appication_id, Constants.client_key); 
        ParseAnalytics.trackAppOpened(getIntent());
        PushService.setDefaultPushCallback(this, PushNoti.class);//change the class where u want to go after clicking on noti
        ParseInstallation.getCurrentInstallation().saveInBackground();
        
      //push*/ 
        
     // creating connection detector class instance
     	cd = new ConnectionDetector(getApplicationContext());
        
        
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        // show location button click event
        btnShowLocation.setOnClickListener(this); 
        
        btnEmergencyServices = (Button) findViewById(R.id.btnEmergencyServices);
        btnEmergencyServices.setOnClickListener(this);
      
        btnInformIllegalActivity = (Button) findViewById(R.id.btnInformIllegalActivity);
        btnInformIllegalActivity.setOnClickListener(this);
        
       //
   /*     btnHelpline = (Button) findViewById(R.id.btnHelpline);
        btnHelpline.setOnClickListener(this);*/
        
        
        btnContacts = (Button) findViewById(R.id.btnContacts);
        btnContacts.setOnClickListener(this);
        
        /*btnPermissions = (Button) findViewById(R.id.btnPermissions);
        btnPermissions.setOnClickListener(this);*/
        
        btnUserGuide = (Button) findViewById(R.id.btnUserGuide);
        btnUserGuide.setOnClickListener(this);
        
        btnMore = (Button) findViewById(R.id.btnMore);
        btnMore.setOnClickListener(this);
			
        
        //location
      //location
        progress=(ProgressBar) findViewById(R.id.progBar);
    	progress.setVisibility(View.GONE);
        //dialog.dismiss();
    	//progressDialog.dismiss();


    	
    	locManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    	locListener= new MyLocationListener();
    	
    	
        //welcome screen
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        welcomeScreenShown = preferences.getBoolean(welcomeScreenShownPref, false);
        if (!welcomeScreenShown)
		 {

			 startActivity(new Intent (Main_utaAlertActivity.this, ShowInstructions.class));
			 
			 SharedPreferences.Editor editor = preferences.edit();
		        editor.putBoolean(welcomeScreenShownPref, true);
		        editor.commit(); 
			 
		 }
        
        

    }
    
	
    	public void getCellInfo()
    	{

    		 telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			  cellLocation = (GsmCellLocation)telephonyManager.getCellLocation();

			 networkOperator = telephonyManager.getNetworkOperator();
			 mcc = networkOperator.substring(0, 3);
			 mnc = networkOperator.substring(3);

			 cid = cellLocation.getCid();
			 lac = cellLocation.getLac();
	        
			 cellID = String.valueOf(cid);
			 LAC = String.valueOf(lac);
			 MCC = mcc;
			 MNC= mnc;
			
    	
    	}

		@Override
		public void onClick(View v) 
		{
			// TODO Auto-generated method stub


			if(v.getId()==R.id.btnShowLocation)
			{	
				
				showPopupDialog();
			
			}
			
	if(v.getId()==R.id.btnEmergencyServices)
	{
		//onStart();
		startActivity(new Intent (Main_utaAlertActivity.this, EmergencyServices.class));
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);  //ext for transition
	}
	
	if(v.getId()==R.id.btnInformIllegalActivity)
	{
		startActivity(new Intent (Main_utaAlertActivity.this, InformIllegalActivity.class));
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right); 

	}
	 
	
	if(v.getId()==R.id.btnContacts)
	{	
		startActivity(new Intent (Main_utaAlertActivity.this, Profile.class));
	}
	
	if(v.getId()==R.id.btnUserGuide)
	{	
		startActivity(new Intent (Main_utaAlertActivity.this, UserGuide.class));
	}
	
	if(v.getId()==R.id.btnMore)
	{	
		//for quick action popup
		onStop();
		ActionItem nextItem 	= new ActionItem(ID_ABOUT_US, "About us", null);
		ActionItem prevItem 	= new ActionItem(ID_DESCLAIMER, "Disclaimer", null);//getResources().getDrawable(R.drawable.ic_launcher)
		 ActionItem rateItem 		= new ActionItem(RATE_US, "Rate us", null);
		 //ActionItem rateItem 		= new ActionItem(RATE_US, "OK", getResources().getDrawable(R.drawable.menu_ok));

        
        //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
//         prevItem.setSticky(true);
//         nextItem.setSticky(true);
        
 		final QuickAction quickAction =new QuickAction(this, QuickAction.VERTICAL);

		//add action items into QuickAction
        quickAction.addActionItem(nextItem);
		quickAction.addActionItem(prevItem);
       quickAction.addActionItem(rateItem);
        
      //Set listener for action item clicked
      		quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {			
      			@Override
      			public void onItemClick(QuickAction source, int pos, int actionId) {				
      				ActionItem actionItem = quickAction.getActionItem(pos);
                       
      				//here we can filter which action item was clicked with pos or actionId parameter
      				if (actionId == ID_DESCLAIMER) {
      					startActivity(new Intent (Main_utaAlertActivity.this, Desclaimer.class));
      	             
      				} else if (actionId == ID_ABOUT_US) {
      					startActivity(new Intent (Main_utaAlertActivity.this, About_Us.class));
      				} else if (actionId == RATE_US) {
      					
      					isInternetPresent = cd.isConnectingToInternet();
    		        	if(isInternetPresent)
    		        			{
    		        		Uri uri = Uri.parse("market://details?id=" + "com.team8.utaAlert");
    		        	    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
      	                    startActivity(goToMarket);

    		        			}
    		        	else
    		        	{
          					Toast.makeText(getApplicationContext(), "Data connection unavailable", Toast.LENGTH_SHORT).show();

    		        	}
      				}
      				else {
      					Toast.makeText(getApplicationContext(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
      				}
      			}
      		});
      		
      		//set listnener for on dismiss event, this listener will be called only if QuickAction dialog was dismissed
      		//by clicking the area outside the dialog.
      		quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {			
      			@Override
      			public void onDismiss() {
      			}
      		});

		
		
		quickAction.show(v);//suffitiant for downward popup
		quickAction.setAnimStyle(QuickAction.ANIM_REFLECT); // this line only for upward popup
	}
	
	
	
  }
		
		public void showSettingsAlert()
		{
			
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();

			// Setting Dialog Title
			alertDialog.setTitle("Warning");

			// Setting Dialog Message
			alertDialog.setMessage("Add Emergency Contacts first.");

			// Setting Icon to Dialog
			//alertDialog.setIcon(R.drawable.tick);

			// Setting OK Button
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            // Write your code here to execute after dialog closed
            	startActivity(new Intent (Main_utaAlertActivity.this, Profile.class));
            	dialog.dismiss(); 

            }
		  });

			// Showing Alert Message
			alertDialog.show();
	 }

		
		public void showPopupDialog()
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Emergency Type:");

			final ListView modeList = new ListView(this);
			stringArray = new String[] { "Sexual Assault", "Robbery","Gun Firing","Hostile Situation", "Other" };
			ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
			modeList.setAdapter(modeAdapter);
			//modeList.setBackgroundColor(color.white);
			builder.setView(modeList);
			
			//ext cancel btn
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialoggg, int id) {
	                
	            	dialoggg.dismiss();
	          	            }
	        }); 
			//ext cancel btn */
			
			final Dialog dialog = builder.create();
			dialog.show();
			
		modeList.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) 
				{
				// TODO Auto-generated method stub

				emergancyType = (modeList.getItemAtPosition(position).toString());		
				
				cellIDfound=false;//ext
				locationFound=false;//ext
				
				//ext to indicate widget
			     Globals isWidgetUsed = (Globals)getApplication();
			     isWidgetUsed.setData(false);
				
			    showLocationAndSendMessage(emergancyType);
			  //  startTimerMeth();//will send cell id if unable to get loc in 40sec
			    dialog.cancel();
			    
			 }
			
		});
			
	 }
		
   @Override
   protected void onNewIntent(Intent intent) {  
     super.onNewIntent(intent);
		try {
		 	showLocationAndSendMessage("Urgent");
		} catch (Exception e) {
			Log.d("Test", "Message Failed");
			e.printStackTrace();
		}
     Log.d("Test", "New Broadcast only on 4");
     } // End of onNewIntent(Intent intent)
		
   public void showLocationAndSendMessage(String eType)
		{
			//find cell ID, MCC, MNC, LAC*/
			getCellInfo(); 
			
			startTimerMeth();//ext

			emergancyType=eType;
    		//get Contacts info
			 preferences = PreferenceManager.getDefaultSharedPreferences(this);
	       
			 contact1 = preferences.getString("cGuardian1Number", "");
		     contact2 = preferences.getString("cGuardian2Number", "");
		     
		     
		     //location
		     progress.setVisibility(View.VISIBLE);
		     //progressDialog = ProgressDialog.show(this, "", "Searching for Location...");

				
		     
				try{
					network_enabled=locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

				}catch(Exception ex){
				}
				try{
					gps_enabled=locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

				}catch(Exception ex){
				}
				
				
				//if location services are not enabled ->just send message along with cellid, ... and popup settings
				if(!network_enabled && !gps_enabled)//&& !gps_enabled ->add/remove this if u want to use/remove gps loc
				{
					AlertDialog.Builder builder = new Builder(this);
					builder.setTitle("Alert");
					builder.setMessage("Location service is not enabled. Do you want to go to settings menu?");
					builder.setPositiveButton("Setting",  this);
					builder.setNeutralButton("Cancel",  this);
					builder.create().show();
					progress.setVisibility(View.GONE);
		           // dialog.dismiss();
				//	progressDialog.dismiss();


					sendMessageWithCellID();
					
				}
				else
				{
				
				if(network_enabled)
				{
		        	//Toast.makeText(getApplicationContext(), "network_enabled" , Toast.LENGTH_LONG).show();	
		        	
					isInternetPresent = cd.isConnectingToInternet();
		        	if(!isInternetPresent && !gps_enabled) //&& !gps_enabled
					{

					  sendMessageWithCellID();
					}
		        	else //if(isInternetPresent )
		        	{
			        	locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);

		        	}
				}
				else if(gps_enabled )
					{
			        	//Toast.makeText(getApplicationContext(), "gps_enabled" , Toast.LENGTH_LONG).show();	
						locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,  locListener);
						 //sendMessageWithCellID();    //***if u want to use gps loc then comment this line

					}
				
				}
		}
		
		
		void sendMessageWithCellID()
		{

			cellIDfound=true;//for timer
			
			getCellInfo(); 
			contact1 = preferences.getString("cGuardian1Number", "");
		    contact2 = preferences.getString("cGuardian2Number", "");
		 
		    progress.setVisibility(View.GONE);
			locManager.removeUpdates(locListener);
		   

     		 Toast.makeText(
						   getApplicationContext(),
			                "Location not available" + "\nCell ID: " + cid + "\n"
			                        + "MCC: " + mcc + "\n" + "MNC:" + mnc + "\nLAC:" + lac , Toast.LENGTH_LONG).show();
		    		String messageToSend = "Please Help.I am in an emergency situation, help me\n".concat("Emergency Type: ").concat(emergancyType).concat("\n").concat("Cell ID=").concat(cellID).concat("\n").concat("MCC=").concat(MCC).concat("\n").concat("MNC=").concat(MNC).concat("\nLAC=").concat(LAC);
		    		try {
		    			if(contact1!="" || contact2!="")
			        	{
		    				if(contact1!="")
		    				{
		    					SmsManager smsManager = SmsManager.getDefault();
			        			smsManager.sendTextMessage(contact1, null, messageToSend, null, null);
			        			Toast.makeText(getApplicationContext(), "SMS Sent to "+contact1,
			        					Toast.LENGTH_LONG).show();		
		    				}
		    				if(contact2!="")
		    				{

		    					SmsManager smsManager = SmsManager.getDefault();
			        			smsManager.sendTextMessage(contact1, null, messageToSend, null, null);
			        			Toast.makeText(getApplicationContext(), "SMS Sent to "+contact2,
			        					Toast.LENGTH_LONG).show();
		    				}
			        	}
		    			else
		        		{
							
							Globals g = (Globals)getApplication();
							boolean isWidgetUsed=g.getData();
						
							if(!isWidgetUsed)
							{
		        			  showSettingsAlert();
		        			}
							else
							{
								Toast.makeText(getApplicationContext(), "Add Emergency Contact first" , Toast.LENGTH_LONG).show();
							}
		        		}
	        			
	        		} catch (Exception e) {
	        			Toast.makeText(getApplicationContext(),
	        					"SMS faild, please try again later!", Toast.LENGTH_LONG)
	        					.show();
	        			e.printStackTrace();
	        		}
		    		/*if(contact1!="" || contact2!="")
		        	{
		    			if(contact1!="")
	        			{
	        	        	Toast.makeText(getApplicationContext(), "sending message" , Toast.LENGTH_LONG).show();	
	        				SmsManager.getDefault().sendTextMessage(contact1, null, messageToSend, null,null);
	        				cellIDfound=false;
	        			}
	        			
	        			if(contact2!="")
	        			{
	        	        	Toast.makeText(getApplicationContext(), "sending message" , Toast.LENGTH_LONG).show();	
	        				SmsManager.getDefault().sendTextMessage(contact2, null, messageToSend, null,null);
	        			}
		        	}
		    		else
	        		{	
	        			//showSettingsAlert();
	        			Globals g = (Globals)getApplication();
						boolean isWidgetUsed=g.getData();
					
						if(!isWidgetUsed)
						{
	        			  showSettingsAlert();
	        			}
						else
						{
							Toast.makeText(getApplicationContext(), "Add Emergency Contact first" , Toast.LENGTH_LONG).show();
						}
	        		}*/
		}
		//
		/////////////////
		class MyLocationListener implements LocationListener
		{

			@Override
			public void onLocationChanged(Location location) 
			{
				// TODO Auto-generated method stub
				

				//find cell ID, MCC, MNC, LAC*/
				getCellInfo(); 
				contact1 = preferences.getString("cGuardian1Number", "");
			    contact2 = preferences.getString("cGuardian2Number", "");

				isInternetPresent = cd.isConnectingToInternet();
				gps_enabled=locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			

				
			  if(!isInternetPresent && !gps_enabled) //&& !gps_enabled ->add/remove this if u want to use/remove gps loc
				{

				  sendMessageWithCellID();
				}
			  
			  else if(location!=null)
		        {

				    locationFound=true;
					locManager.removeUpdates(locListener);
					progress.setVisibility(View.GONE);

		    	   latitude = location.getLatitude();
		           longitude = location.getLongitude();
		           accuracy = (double) location.getAccuracy();
		           
		           String lati = Double.toString(latitude);
		           String longi = Double.toString(longitude);
		           String accu = Double.toString(accuracy);


//				String accuracy ="ACCURACY:  "+(location.getAccuracy());
//				String time ="TIME:  "+(location.getTime());
//				String alti = "alti:  "+(location.getAltitude());
		

			
				Toast.makeText(getApplicationContext(), "Lat:"+lati+"\nLong:"+longi+"\nAccuracy(in meters):"+accuracy , Toast.LENGTH_LONG).show();	

				String messageToSend ="Please help.I am in an emergency situation\n".concat("Type: ").concat(emergancyType).concat("\nLocation= http://maps.google.com/?q=").concat(lati).concat(",").concat(longi);
				if(contact1!="" || contact2!="")
        		{
				try {
					if(contact1!="")
					{
						SmsManager smsManager = SmsManager.getDefault();
	        			smsManager.sendTextMessage(contact1, null, messageToSend, null, null);
	        			Toast.makeText(getApplicationContext(), "SMS Sent to "+contact1,
	        					Toast.LENGTH_LONG).show();	
					}
        			
        			if(contact2!="")
        			{
        				SmsManager smsManager = SmsManager.getDefault();
	        			smsManager.sendTextMessage(contact2, null, messageToSend, null, null);
	        			Toast.makeText(getApplicationContext(), "SMS Sent to "+contact2,
	        					Toast.LENGTH_LONG).show();	
        			}
        		} catch (Exception e) {
        			Toast.makeText(getApplicationContext(),
        					"SMS faild, please try again later!", Toast.LENGTH_LONG)
        					.show();
        			e.printStackTrace();
        		}
        		}else
        		{
					
					Globals g = (Globals)getApplication();
					boolean isWidgetUsed=g.getData();
				
					if(!isWidgetUsed)
					{
        			  showSettingsAlert();
        			}
					else
					{
						Toast.makeText(getApplicationContext(), "Add Emergency Contact first" , Toast.LENGTH_LONG).show();
					}
        		}
	        	
/*	        		if(contact1!="" || contact2!="")
	        		{
	        			if(contact1!="")
	        			{
	        	        	Toast.makeText(getApplicationContext(), "sending message 1" , Toast.LENGTH_LONG).show();	
	        				SmsManager.getDefault().sendTextMessage(contact1, null, messageToSend, null,null);
	        			}
	        			
	        			if(contact2!="")
	        			{
	        	        	Toast.makeText(getApplicationContext(), "sending message 2" , Toast.LENGTH_LONG).show();	
	        				SmsManager.getDefault().sendTextMessage(contact2, null, messageToSend, null,null);

	        			}
	        		}
	        		else
	        		{
						
						Globals g = (Globals)getApplication();
						boolean isWidgetUsed=g.getData();
					
						if(!isWidgetUsed)
						{
	        			  showSettingsAlert();
	        			}
						else
						{
							Toast.makeText(getApplicationContext(), "Add Emergency Contact first" , Toast.LENGTH_LONG).show();
						}
	        		}*/

			}
	}
	    
			
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			
		}



		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			
			if(which ==DialogInterface.BUTTON_NEUTRAL)
			{
				//eText.setText(".");
			}
			else if(which ==DialogInterface.BUTTON_POSITIVE)
			{
				startActivity(new Intent (android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
			}
		}

		
		 void startTimerMeth()
		{
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
			  @Override
			  public void run() {
			    //Do something after 100ms
				  if(!locationFound && !cellIDfound)
				  {
					  sendMessageWithCellID();
				  }
			  }
			}, 45000); //40sec
		}
		
		 
		 
}
