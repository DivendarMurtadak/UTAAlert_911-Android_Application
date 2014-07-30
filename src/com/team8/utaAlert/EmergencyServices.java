package com.team8.utaAlert;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EmergencyServices extends Activity implements OnClickListener{

	Button btnUtaHealthService, btnUtArlingtonPolice, btnArlingtonPoliceDept, btnOfficeOfStudentConduct, btnFacilitiesManagement, btnRVSPProgram, btnCounselingService, btnEnvironmentalSafety;
	
	   
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergencyservices);
        
        btnUtaHealthService = (Button) findViewById(R.id.UtaHealthService);
        btnUtaHealthService.setOnClickListener(this);
        
        btnUtArlingtonPolice = (Button) findViewById(R.id.UtArlingtonPolice);
        btnUtArlingtonPolice.setOnClickListener(this);
        
        btnArlingtonPoliceDept = (Button) findViewById(R.id.ArlingtonPoliceDept);
        btnArlingtonPoliceDept.setOnClickListener(this);
        
        btnOfficeOfStudentConduct = (Button) findViewById(R.id.OfficeOfStudentConduct);
        btnOfficeOfStudentConduct.setOnClickListener(this);
        
        btnFacilitiesManagement = (Button) findViewById(R.id.FacilitiesManagement);
        btnFacilitiesManagement.setOnClickListener(this);
        
        btnRVSPProgram = (Button) findViewById(R.id.RVSPProgram);
        btnRVSPProgram.setOnClickListener(this);
        
        btnEnvironmentalSafety = (Button) findViewById(R.id.EnvironmentalSafety);
        btnEnvironmentalSafety.setOnClickListener(this);
        
        btnCounselingService = (Button) findViewById(R.id.CounselingService);
        btnCounselingService.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Intent callIntent = new Intent(Intent.ACTION_CALL);

		   if(v.getId()==R.id.ArlingtonPoliceDept)
			{
		    	//callIntent.setData(Uri.parse("tel:+"+btnBDS.getHint().toString().trim()));
				callIntent.setData(Uri.parse("tel:"+Constants.ArlingtonPoliceDept.trim()));
			}
			
			if(v.getId()==R.id.EnvironmentalSafety)
			{
				callIntent.setData(Uri.parse("tel:"+Constants.EnvironmentalSafety.trim()));
			}
			
			if(v.getId()==R.id.FacilitiesManagement)
			{
				callIntent.setData(Uri.parse("tel:"+Constants.FacilitiesManagement.trim()));
			}
			
			if(v.getId()==R.id.UtaHealthService)
			{
				callIntent.setData(Uri.parse("tel:"+Constants.UtaHealthService.trim()));
			}
			
			if(v.getId()==R.id.UtArlingtonPolice)
			{
				callIntent.setData(Uri.parse("tel:"+Constants.UtArlingtonPolice.trim()));
			}
			if(v.getId()==R.id.CounselingService)
			{
				callIntent.setData(Uri.parse("tel:"+Constants.CounselingService.trim()));
			}if(v.getId()==R.id.RVSPProgram)
			{
				callIntent.setData(Uri.parse("tel:"+Constants.RVSPProgram.trim()));
			}if(v.getId()==R.id.OfficeOfStudentConduct)
			{
				callIntent.setData(Uri.parse("tel:"+Constants.OfficeOfStudentConduct.trim()));
			}
			
	    	startActivity(callIntent );

	}

}
