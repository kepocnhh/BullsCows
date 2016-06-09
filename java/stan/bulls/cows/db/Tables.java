package stan.bulls.cows.db;

import android.provider.BaseColumns;

public class Tables
{

    public static class GameTemp
    {
        public static final String TABLE_NAME = "gametemp" + "_table";
        public static class Columns
        {
            public static final String quality = "quality";
            public static final String time_spend = "time_spend";
            public static final String offer_bulls = "offer_bulls";
            public static final String offer_cows = "offer_cows";
            public static final String offer_value = "offer_value";
        }

        public static String createTable()
        {
            return "create table if not exists " + TABLE_NAME + " (" +
                    BaseColumns._ID + " integer primary key autoincrement, " +
                    //Columns.time_spend + " text" + "," +
                    Columns.quality + " integer" + "," +
                    Columns.time_spend + " integer" + "," +
                    Columns.offer_bulls + " integer" + "," +
                    Columns.offer_cows + " integer" + "," +
                    Columns.offer_value + " text" +
                    ");";
        }
    }

    public static class StatisticsGames
    {
        public static final String TABLE_NAME = "statisticsgames" + "_table";
        public static class Columns
        {
            public static final String date = "date";
            public static final String game_type = "game_type";
            public static final String game_settings = "game_settings";
            public static final String win = "win";
            public static final String amount_offers = "amount_offers";
            public static final String time_spend = "time_spend";
        }

        public static String createTable()
        {
            return "create table if not exists " + TABLE_NAME + " (" +
                    BaseColumns._ID + " integer primary key autoincrement, " +
                    Columns.date + " text" + "," +
                    Columns.game_type + " integer" + "," +
                    Columns.game_settings + " text" + "," +
                    Columns.win + " integer" + "," +
                    Columns.amount_offers + " integer" + "," +
                    Columns.time_spend + " text" +
                    ");";
        }
    }

    public static class GoldTransactions
    {
        public static final String TABLE_NAME = "goldtransactions" + "_table";
        public static class EarnedTypes
        {
            public static final int game = 0;
        }
        public static class Columns
        {
            public static final String date = "date";
            public static final String earned_type = "earned_type";
            public static final String game_id = "game_id";
            public static final String gold = "gold";
        }

        public static String createTable()
        {
            return "create table if not exists " + TABLE_NAME + " (" +
                    BaseColumns._ID + " integer primary key autoincrement, " +
                    Columns.date + " text" + "," +
                    Columns.earned_type + " integer" + "," +
                    Columns.game_id + " integer" + "," +
                    Columns.gold + " integer" +
                    ");";
        }
    }
}