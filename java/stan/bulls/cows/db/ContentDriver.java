package stan.bulls.cows.db;

import android.content.ContentValues;

import stan.bulls.cows.core.Offer;
import stan.bulls.cows.core.game.ResultGame;

public class ContentDriver
{
    //__________________________________GET_CONTENT_VALUES
//    static public ContentValues getContentValuesOfferForGameTemp(Offer offer, String timeSpend)
    static public ContentValues getContentValuesOfferForGameTemp(Offer offer, int timeSpend, boolean quality)
    {
        ContentValues content = new ContentValues();
        content.put(Tables.GameTemp.Columns.time_spend, timeSpend);
        if(quality)
        {
            content.put(Tables.GameTemp.Columns.quality, 1);
        }
        else
        {
            content.put(Tables.GameTemp.Columns.quality, 0);
        }
        content.put(Tables.GameTemp.Columns.offer_value, offer.getValue());
        content.put(Tables.GameTemp.Columns.offer_bulls, offer.bulls);
        content.put(Tables.GameTemp.Columns.offer_cows, offer.cows);
        return content;
    }

    static public ContentValues getContentValuesResultGameForStatisticsGames(ResultGame resultGame)
    {
        ContentValues content = new ContentValues();
        content.put(Tables.StatisticsGames.Columns.date, resultGame.date + "");
        content.put(Tables.StatisticsGames.Columns.game_type, resultGame.game_type);
        content.put(Tables.StatisticsGames.Columns.game_settings, resultGame.game_settings);
        if(resultGame.win)
        {
            content.put(Tables.StatisticsGames.Columns.win, 1);
        }
        else
        {
            content.put(Tables.StatisticsGames.Columns.win, 0);
        }
        content.put(Tables.StatisticsGames.Columns.amount_offers, resultGame.amount_offers);
        content.put(Tables.StatisticsGames.Columns.time_spend, resultGame.time_spend + "");
        return content;
    }

    static public ContentValues getContentValuesGoldEarnedFromGame(ResultGame resultGame, int gameId)
    {
        ContentValues content = new ContentValues();
        content.put(Tables.GoldTransactions.Columns.date, resultGame.date + "");
        content.put(Tables.GoldTransactions.Columns.earned_type, Tables.GoldTransactions.EarnedTypes.game);
        content.put(Tables.GoldTransactions.Columns.game_id, gameId);
        content.put(Tables.GoldTransactions.Columns.gold, resultGame.gold_earned);
        return content;
    }
}