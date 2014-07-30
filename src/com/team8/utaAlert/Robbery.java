package com.team8.utaAlert;

import java.io.File;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Robbery extends Activity implements OnClickListener {
	
	private static final int RESULT_LOAD_IMAGE = 1;
	 String picturePath;
	 String title;
	 
	 TextView txtAttach;
	 
	 Button btnAttach, btnClear, btnSendMail;
	 TextView txtTitle;
	 EditText editTextMail;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robbery);
        

        //
        btnAttach = (Button) findViewById(R.id.btnAttach);
        btnAttach.setOnClickListener(this);
        
        btnClear = (Button) findViewById(R.id.btnClearText);
        btnClear.setOnClickListener(this);
        
        btnSendMail = (Button) findViewById(R.id.btnSendRobberyLink);
        btnSendMail.setOnClickListener(this);
        
        editTextMail = (EditText) findViewById(R.id.editTextMail);
        editTextMail.setOnClickListener(this);
        
        txtAttach= (TextView) findViewById(R.id.textAttach);
 
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            title=extras.getString("title");
        
        txtTitle.setText(title);
        txtTitle.setTextColor(Color.parseColor("#FF4500"));
        //android:textAppearance="?android:attr/textAppearanceLarge"
        
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId()==R.id.btnSendRobberyLink)
		{
			//send mail 
			String address[] = {Constants.EmailID};//change it //"vishal.labhade123@gmail.com"
			String subject = title;
			Uri uri=null;
			
			if(picturePath!=null)
			{
				File F = new File(picturePath);
				uri = Uri.fromFile(F);
			}
			
		
			//if(No attachment)
			{
				final EditText i = (EditText) findViewById(R.id.editTextMail);

				
				 Intent email = new Intent(android.content.Intent.ACTION_SEND);
				 email.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail"); //use this only if u specifically want gmail

				 email.putExtra(android.content.Intent.EXTRA_EMAIL, address);
				 email.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
				 //email.putExtra(Intent.EXTRA_STREAM, Uri.parse(picturePath));
		            /* Fill it with Data */
		            email.setType("plain/text");   
		            email.setType("image/png");
		            email.setType("video/3gp");
		            email.setType("audio/mp3");


				email.putExtra(android.content.Intent.EXTRA_TEXT, 
						" "+i.getText().toString()); //for text
				
				if(uri!=null)
				{
					email.putExtra(Intent.EXTRA_STREAM, uri); //for image
				}

				 //startActivity(Intent.createChooser(email, "Send mail...")); //for all send optinos like whatsapp, viber..
				startActivity(email);// only for gmail

				 
				
				// picturePath=null;
				 //uri=null;				
				 
			}
		}
		
		if(v.getId()==R.id.btnClearText)
		{
			//send mail 
			editTextMail.setText("");
			txtAttach.setText("");
			txtAttach.setHint("(optional)");
			picturePath=null;
		}
		
		if(v.getId()==R.id.btnAttach)
		{
			//send image 

			//open gallery(requires onActivityResult meth) //only for image
//			Intent i = new Intent(
//					Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//					 
//					startActivityForResult(i, RESULT_LOAD_IMAGE);
					
		   			//open gallery for all image, video, audio(requires onActivityResult meth)
					final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
					galleryIntent.setType("*/*");  //this line is must for video as well as image 
					startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);	
		}
	}
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	         
	        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };
	 
	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();
	            
	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            picturePath = cursor.getString(columnIndex);
	            cursor.close();
	             
//	            ImageView imageView = (ImageView) findViewById(R.id.imgView);
//	            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	            
	            
	            File objFile = new File(picturePath);
	            objFile.getName();
	            String str= objFile.getName();
	            txtAttach.setText(str);
	            txtAttach.setTextColor(Color.parseColor("#FF4500"));

	         
	        }
	 }

	
}
