package com.team8.utaAlert;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ShowInstructions extends Activity implements OnClickListener {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction);
        
        Button btnNext;
    	btnNext = (Button) findViewById(R.id.btnNext);
    	btnNext.setOnClickListener(this);
}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId()==R.id.btnNext)
		{ 
			finish();
		}
	}
}