package com.team8.utaAlert;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Profile extends Activity implements OnClickListener, OnLongClickListener {


	
	private static final int PICK_CONTACT = 0;
 	SharedPreferences preferences;
 	SharedPreferences.Editor editor ;
 	
 	
 	String isContact1AddedPref = "isContact1Added";
 	String isContact2AddedPref = "isContact2Added";
 	
 	Boolean guardian1=false,guardian2=false;
 	Boolean isContact1Added, isContact2Added;
 	
 	 String g1Name, g2Name;
 	
	String guardian1Name;
    String guardian1Number; 
    String guardian2Name;
    String guardian2Number;
    
	
	Button btnContact1, btnContact2;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileh);
        
        
        
        btnContact1 = (Button) findViewById(R.id.buttonContact1);
        btnContact1.setOnClickListener(this);
        btnContact1.setOnLongClickListener(this);

        
        btnContact2 = (Button) findViewById(R.id.buttonContact2);
        btnContact2.setOnClickListener(this);
        btnContact2.setOnLongClickListener(this);

        
       //
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        
         guardian1Name = preferences.getString("cGuardian1Name","");
         guardian1Number = preferences.getString("cGuardian1Number",""); 
         guardian2Name = preferences.getString("cGuardian2Name","");
         guardian2Number = preferences.getString("cGuardian2Number",""); 
    	

    	
        if(guardian1Name!="" )
        {
        	btnContact1.setText(" "+guardian1Name);
        }
        if(guardian2Name!="")
        {
        	btnContact2.setText(" "+guardian2Name);
        }

        
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		

		if(v.getId()==R.id.buttonContact1 )
		{	
			Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(intent, PICK_CONTACT);
			guardian1=true;
			guardian2=false;
		}
		
		if(v.getId()==R.id.buttonContact2 )
		{	
			Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(intent, PICK_CONTACT);
			guardian2=true;
			guardian1=false;
		}
		
	}
	
	//
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data){
	    super.onActivityResult(reqCode, resultCode, data);
	 
	    switch(reqCode){
	       case (PICK_CONTACT):
	         if (resultCode == Activity.RESULT_OK){
	             Uri contactData = data.getData();
	             Cursor c = getContentResolver().query(contactData, null, null, null, null);
	 
	             
	             if (c.moveToFirst()){
	                 // other data is available for the Contact.  I have decided
	                 //    to only get the name of the Contact.
	            	 

	                 String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

	                 String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

	                   if (hasPhone.equalsIgnoreCase("1"))
	                   {
	                  Cursor phones = getContentResolver().query( 
	                               ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
	                               ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
	                               null, null);
	                     phones.moveToFirst();
	                    String Number = phones.getString(phones.getColumnIndex("data1"));
	                    Toast.makeText(getApplicationContext(), Number, Toast.LENGTH_SHORT).show();
	            	 
	                 String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
	                 Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
	                 preferences = PreferenceManager.getDefaultSharedPreferences(this);
	                 SharedPreferences.Editor editor = preferences.edit();

	                 
	                 
	               if(guardian1)  
	               {
	                 editor.putString("cGuardian1Name",name);
	                 editor.putString("cGuardian1Number",Number);
	                 editor.commit();
	                 
	                
	                 g1Name = preferences.getString("cGuardian1Name","");
	                //String g1Number = preferences.getString("cGuardian1Number",""); 
	                 
	                 btnContact1.setText(""+g1Name);
	                 //tv2.setText(""+nmbr);
	                 
	                 //isContact1Added=true;
	                 
	                 //
	                 preferences = PreferenceManager.getDefaultSharedPreferences(this);
	                 isContact1Added = preferences.getBoolean(isContact1AddedPref, false);
	                
	         			    editor = preferences.edit();
	         		        editor.putBoolean(isContact1AddedPref, true);
	         		        editor.commit(); 
	         			 
	                 	                 
	             }
	               if(guardian2)  
	               {
	                 editor.putString("cGuardian2Name",name);
	                 editor.putString("cGuardian2Number",Number);
	                 editor.commit();
	                 
	                
	                  g2Name = preferences.getString("cGuardian2Name","");
	                 //String g2Number = preferences.getString("cGuardian2Number",""); 
	                
	                  btnContact2.setText(""+g2Name);
	                 //tv2.setText(""+nmbr);
	                  
	                 // isContact2Added=true;
	                  preferences = PreferenceManager.getDefaultSharedPreferences(this);
		              isContact2Added = preferences.getBoolean(isContact2AddedPref, false);
		                
		         			    editor = preferences.edit();
		         		        editor.putBoolean(isContact2AddedPref, true);
		         		        editor.commit(); 
	               }	                 
	            }
	         }
	    }
	}
	 
}
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
        isContact1Added = preferences.getBoolean(isContact1AddedPref, false);
        isContact2Added = preferences.getBoolean(isContact2AddedPref, false);

		
		if(v.getId()==R.id.buttonContact1 && isContact1Added)
		{
			guardian1=true;
			guardian2=false;
			showPopupDialog();
		}
		
		if(v.getId()==R.id.buttonContact2 && isContact2Added)
		{
			guardian2=true;
			guardian1=false;
			showPopupDialog();
		}
		
		return false;
	}
	
	
	public void showPopupDialog()
	{
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		//
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select an action");

		final ListView modeList = new ListView(this);
		String[] stringArray = new String[] { "Delete" };
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
		modeList.setAdapter(modeAdapter);

		builder.setView(modeList);
		final Dialog dialog = builder.create();

		dialog.show();
		
	modeList.setOnItemClickListener(new OnItemClickListener()
	{
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) 
			{
			// TODO Auto-generated method stub

        	if(guardian1)
        	{
        		btnContact1.setText("Set Contact-1");
        		
        		//also clear preferences of Contact1
        		preferences.edit().remove("cGuardian1Name").commit();
        		preferences.edit().remove("cGuardian1Number").commit();
        		
        		//isContact1Added=false;
        		
                isContact1Added = preferences.getBoolean(isContact1AddedPref, false);
        			    
                editor = preferences.edit();
        	    editor.putBoolean(isContact1AddedPref, false);
        		editor.commit(); 
        			 

        	}
        	
        	if(guardian2)
        	{
        		btnContact2.setText("Set Contact-2");
        		
    			//also clear preferences of Contact2
    			preferences.edit().remove("cGuardian2Name").commit();
        		preferences.edit().remove("cGuardian2Number").commit();

        		//isContact2Added=false;
        		
        		isContact2Added = preferences.getBoolean(isContact2AddedPref, false);
			    
                editor = preferences.edit();
        	    editor.putBoolean(isContact2AddedPref, false);
        		editor.commit(); 
        	}

		    dialog.cancel();
		    
		 }
		
	});
 }

	
}
