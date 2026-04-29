package com.crypto.widget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import android.widget.RemoteViewsService.RemoteViewsFactory
import android.graphics.Color


class WidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return WidgetItemFactory(applicationContext)
    }
}

class WidgetItemFactory(private val context: Context) : RemoteViewsService.RemoteViewsFactory {
    private val coins = mutableListOf<CryptoCoin>()

    override fun onCreate() {
        // Dummy data for our scrolling test
        coins.add(CryptoCoin("BTC", "$65,240.50", "+2.5%", true))
        coins.add(CryptoCoin("ETH", "$3,450.12", "+1.2%", true))
        coins.add(CryptoCoin("BNB", "$590.00", "-0.5%", false))
        coins.add(CryptoCoin("SOL", "$145.20", "+5.4%", true))
        coins.add(CryptoCoin("XRP", "$0.58", "-1.1%", false))
        coins.add(CryptoCoin("ADA", "$0.45", "+0.8%", true))
        coins.add(CryptoCoin("DOGE", "$0.15", "-3.2%", false))
    }

    override fun onDataSetChanged() {}
    override fun onDestroy() { coins.clear() }
    override fun getCount(): Int = coins.size
    
    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_list_item)
        val coin = coins[position]
        
        // Populate the texts
        views.setTextViewText(R.id.item_symbol, coin.symbol)
        views.setTextViewText(R.id.item_price, coin.price)
        views.setTextViewText(R.id.item_change, coin.change)
        
        // Change text color based on positive/negative
                // Change text color based on positive/negative
        if (coin.isPositive) {
            views.setTextColor(R.id.item_change, Color.parseColor("#0ECB81")) // Crypto Green
        } else {
            views.setTextColor(R.id.item_change, Color.parseColor("#F6465D")) // Crypto Red
        }
        
        return views
    }
    
    override fun getLoadingView(): RemoteViews? = null
    override fun getViewTypeCount(): Int = 1
    override fun getItemId(position: Int): Long = position.toLong()
    override fun hasStableIds(): Boolean = true
}
