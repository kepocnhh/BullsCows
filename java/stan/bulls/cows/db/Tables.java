package stan.bulls.cows.db;

import android.provider.BaseColumns;

public class Tables
{

    //_________________________GAMETEMP
    public static class GameTemp
    {
        public static final String TABLE_NAME = "gametemp" + "_table";
        public static final String time_spend_COLUMN = "time_spend";
        public static final String offer_value_COLUMN = "offer_value";
        public static final String offer_bulls_COLUMN = "offer_bulls";
        public static final String offer_cows_COLUMN = "offer_cows";

        public static String createTable()
        {
            return "create table if not exists " + TABLE_NAME + " (" +
                    BaseColumns._ID + " integer primary key autoincrement, " +
                    time_spend_COLUMN + " text" + "," +
                    offer_cows_COLUMN + " integer" + "," +
                    offer_bulls_COLUMN + " integer" + "," +
                    offer_value_COLUMN + " text" +
                    ");";
        }
    }
}