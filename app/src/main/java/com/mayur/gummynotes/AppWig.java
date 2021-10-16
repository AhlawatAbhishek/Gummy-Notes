package com.mayur.gummynotes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.mayur.gummynotes.HomeActivity;
import com.mayur.gummynotes.R;

public class AppWig extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWigId : appWidgetIds){
            Intent intent = new Intent(context, HomeActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_lay);
            remoteViews.setOnClickPendingIntent(R.id.widgetTxtV, pendingIntent);
            appWidgetManager.updateAppWidget(appWigId, remoteViews);
        }
    }
}
