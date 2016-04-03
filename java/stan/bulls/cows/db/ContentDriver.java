package stan.bulls.cows.db;

import android.content.ContentValues;

import stan.bulls.cows.core.Offer;

public class ContentDriver
{
    //__________________________________GET_CONTENT_VALUES
    static public ContentValues getContentValuesOfferForGameTemp(Offer offer, String timeSpend)
    {
        ContentValues content = new ContentValues();
        content.put(Tables.GameTemp.Columns.time_spend, timeSpend);
        content.put(Tables.GameTemp.Columns.offer_value, offer.getValue());
        content.put(Tables.GameTemp.Columns.offer_bulls, offer.bulls);
        content.put(Tables.GameTemp.Columns.offer_cows, offer.cows);
        return content;
    }
}