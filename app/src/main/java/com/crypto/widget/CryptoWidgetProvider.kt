package com.crypto.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

class CryptoWidgetProvider : AppWidgetProvider() {
    
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // This loop updates every copy of the widget if the user dragged multiple to their screen
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout)
            
            // For now, we are hardcoding a placeholder value. 
            // We will add the live API fetcher in the next step!
            views.setTextViewText(R.id.crypto_price_text, "BTC: $65,000")
            
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
