package com.team8.utaAlert;


	

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

	public class Widget_SOS extends AppWidgetProvider  {

		public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
		{
			super.onUpdate(context, appWidgetManager, appWidgetIds);
			
			
			for(int i=0; i<appWidgetIds.length ; i++)
			{
				int appWidgetId = appWidgetIds[i];
				
				//Intent intent = new Intent(MainActivity.this, View2.class); 
				Intent intent = new Intent(Intent.ACTION_VIEW, null, context, Widget_DialogBox.class);     
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
//				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				//Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:/google.com"));
				PendingIntent pending = PendingIntent.getActivity(context, 0	, intent, 0);
			
				RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_activity);
				views.setOnClickPendingIntent(R.id.img1, pending);
				
				appWidgetManager.updateAppWidget(appWidgetId, views);
			}
			
		}


}
