package com.team8.utaAlert;

import android.app.Application;

public class Globals extends Application{
	   
	private boolean isWidgetUsed=false;
	 
	   public boolean getData(){
	     return this.isWidgetUsed;
	   }
	 
	   public void setData(boolean d){
	     this.isWidgetUsed=d;
	   }
	}