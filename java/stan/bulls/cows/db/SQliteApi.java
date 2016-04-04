package stan.bulls.cows.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class SQliteApi
{
    public static DatabaseHelper dbHelper;
    public static volatile SQLiteDatabase sdb;
    public static String DB_NAME = "bullscows";
    public static int DB_VERSION = 1604041858;

    public static void createDb(Context context)
    {
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        sdb = dbHelper.getWritableDatabase();
        createDBTables(sdb);
    }

    public static void startTransaction()
    {
        sdb.beginTransaction();
    }

    public static void endTransaction()
    {
        sdb.setTransactionSuccessful();
        sdb.endTransaction();
    }

    //____________________________INSERT
    public static long insertGameTempOffer(ContentValues content)
    {
        return sdb.insert(Tables.GameTemp.TABLE_NAME, null, content);
    }
    public static long insertStatisticsGamesResultGame(ContentValues content)
    {
        return sdb.insert(Tables.StatisticsGames.TABLE_NAME, null, content);
    }

    //____________________________CLEAR_TABLE
    public static void clearGameTemp()
    {
        sdb.execSQL("drop table if exists " + Tables.GameTemp.TABLE_NAME);
        sdb.execSQL(Tables.GameTemp.createTable());
    }

    //____________________________GET_ALL
    public static Cursor getGameTemp()
    {
        return sdb.query(Tables.GameTemp.TABLE_NAME, null, null, null, null, null, null);
    }
    public static Cursor getStatisticsGames()
    {
        return sdb.query(Tables.StatisticsGames.TABLE_NAME, null, null, null, null, null, null);
    }

    // GET MANY BY PARAMS
    /* ************************************************************************ */

//    public static Cursor getPostSimpleFromPage(int p)
//    {
//        Cursor cursor = sdb.rawQuery(
//                "SELECT * "
//                        + "FROM " + Tables.PostSimple.TABLE_NAME + " "
//                        + "ORDER BY " + Tables.PostSimple.date_COLUMN + " DESC" + " "
//                        + "LIMIT " + p*20 + "; "
//                , new String[]{});
//        return cursor;
//    }

    // GET ONE
    /* ************************************************************************ */
//    public static Cursor getOnePostSimpleFromId(String id)
//    {
//        Cursor cursor = sdb.query(Tables.PostSimple.TABLE_NAME, null, BaseColumns._ID + " = ?",
//                new String[]{id}, null, null, null);
//        return cursor;
//    }

    // CLEAR DB TABLES
    /* ************************************************************************ */
    public static void clearDB(SQLiteDatabase db)
    {
        db.execSQL("drop table if exists " + Tables.GameTemp.TABLE_NAME);
        db.execSQL("drop table if exists " + Tables.StatisticsGames.TABLE_NAME);
    }

    // CREATE DB TABLES
    /* ************************************************************************ */
    public static void createDBTables(SQLiteDatabase db)
    {
        db.execSQL(Tables.GameTemp.createTable());
        db.execSQL(Tables.StatisticsGames.createTable());
    }
}