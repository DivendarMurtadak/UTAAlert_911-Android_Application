package com.team8.utaAlert;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Widget_DialogBox extends Main_utaAlertActivity {
	
	public boolean isWidget=false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       setContentView(R.layout.dialog_box_activity);//press back button img
       showPopupDialog();
       
       
       //findViewById(R.id.v).setBackgroundColor(getResources().getColor(android.R.color.transparent));
	}
	
	public void showPopupDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Emergency Type:");

		final ListView modeList = new ListView(this);
		String[] stringArray = new String[] { "Sexual Assault", "Robbery","Gun Firing","Hostile Situation", "Other" };
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
		modeList.setAdapter(modeAdapter);

		builder.setView(modeList);
		
		//ext cancel btn
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialoggg, int id) {
                
            	dialoggg.dismiss();
            	finish();
        		//Toast.makeText(getApplicationContext(), " press back button to exit...", Toast.LENGTH_SHORT).show();

            }
        }); 
		//ext cancel btn */
		
		final Dialog dialog = builder.create();
		dialog.show();
		//RelativeLayout yourRelLay = (RelativeLayout) Dialog.getParent();
		
		
	modeList.setOnItemClickListener(new OnItemClickListener()
	{
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) 
			{
			// TODO Auto-generated method stub

			 String  emergancyType = (modeList.getItemAtPosition(position).toString());
		     showLocationAndSendMessage(emergancyType);		     
		     isWidget=false; //ext
		     
		     //ext to indicate widget
		     Globals isWidgetUsed = (Globals)getApplication();
		     isWidgetUsed.setData(true);
		     
		    dialog.dismiss();
		    finish();
		 }
		
	});

	}

	
//	@Override
//	public void onBackPressed() {
//		//i++;
//		
//		//if(i==1)
//	    //dialog.cancel();
//	    super.onBackPressed();
//	    finish();
//		//Toast.makeText(getApplicationContext(), " press back button to exit", Toast.LENGTH_SHORT).show();
//		
//	}
	

}
