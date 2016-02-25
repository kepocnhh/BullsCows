package stan.bulls.cows;

import android.app.Application;
import android.content.Context;

import stan.bulls.cows.db.SQliteApi;


public class App
        extends Application
{
    public static Context app_context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        app_context = getApplicationContext();
        SQliteApi.createDb(app_context);
    }
}