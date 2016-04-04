package stan.bulls.cows.helpers;

import android.content.Context;

import java.util.Date;

import stan.bulls.cows.R;

public class TimeHelper
{
    static public long getTimeSpend(long time)
    {
        return new Date().getTime() - time;
    }
    static public String getSecondsStringWithSec(Context context, long time)
    {
        return (time / 1000) + " " + context.getResources().getString(R.string.seconds);
    }
    static public String getZeroSecondsStringWithSec(Context context)
    {
        return "0 " + context.getResources().getString(R.string.seconds);
    }
}