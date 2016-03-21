package stan.bulls.cows.db;

import android.content.ContentValues;

import stan.bulls.cows.core.Offer;

public class ContentDriver
{
    //__________________________________GET_CONTENT_VALUES
    static public ContentValues getContentValuesOfferForGameTemp(Offer offer)
    {
        ContentValues content = new ContentValues();
        content.put(Tables.GameTemp.offer_value_COLUMN, offer.getValue());
        content.put(Tables.GameTemp.offer_bulls_COLUMN, offer.bulls);
        content.put(Tables.GameTemp.offer_cows_COLUMN, offer.cows);
        return content;
    }
}