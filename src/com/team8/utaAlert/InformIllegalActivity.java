package com.team8.utaAlert;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InformIllegalActivity extends Activity implements OnClickListener{

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inform_illegal_activity);
        
        Button btnRobbery, btnNarcoticDrugs, btnWantedCriminals, btnTerroristActivity, btnIllegalWeapons, btnMissingPerson, btnIIA_Other;
        
        btnRobbery = (Button) findViewById(R.id.btnRobbery);
        btnRobbery.setOnClickListener(this);
        
        btnNarcoticDrugs = (Button) findViewById(R.id.btnNarcoticDrugs);
        btnNarcoticDrugs.setOnClickListener(this);
        
        //
        btnWantedCriminals = (Button) findViewById(R.id.btnWanted);
        btnWantedCriminals.setOnClickListener(this);
        
        btnTerroristActivity = (Button) findViewById(R.id.btnTerrorist);
        btnTerroristActivity.setOnClickListener(this);

        btnIllegalWeapons = (Button) findViewById(R.id.btnWeapons);
        btnIllegalWeapons.setOnClickListener(this);
        
        btnMissingPerson = (Button) findViewById(R.id.btnMissingPersons);
        btnMissingPerson.setOnClickListener(this);
        
        btnIIA_Other = (Button) findViewById(R.id.btnOther);
        btnIIA_Other.setOnClickListener(this);

        
 
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(InformIllegalActivity.this, Robbery.class);                        


		if(v.getId()==R.id.btnRobbery)
		{
			intent.putExtra("title", "Robbery");
		}
		
		if(v.getId()==R.id.btnNarcoticDrugs)
		{
			intent.putExtra("title", "Narcotic Drugs");
		}
		
		if(v.getId()==R.id.btnWanted)
		{
			intent.putExtra("title", "Wanted Criminal");
		}
		
		if(v.getId()==R.id.btnTerrorist)
		{
			intent.putExtra("title", "Terrorist Activity");
		}
		
        if(v.getId()==R.id.btnWeapons)
		{
			intent.putExtra("title", "Illegal Weapons");
		}
		
		if(v.getId()==R.id.btnMissingPersons)
		{
			intent.putExtra("title", "Missing Person");
		}
		
		if(v.getId()==R.id.btnOther)
		{
			intent.putExtra("title", "Illegal Activity");
		}
		
		startActivity(intent);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right); 


	}
}
