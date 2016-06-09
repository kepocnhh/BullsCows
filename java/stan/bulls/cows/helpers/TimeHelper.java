package stan.bulls.cows.helpers;

import android.content.Context;

import java.util.Date;

import stan.bulls.cows.R;

public class TimeHelper
{
    static public final int MAX_MILLISEC = 999999;
    static public final int MILLISECS_IN_SEC = 1000;

    static public long getMillisecsFromSec(long sec)
    {
        return sec * TimeHelper.MILLISECS_IN_SEC;
    }
    static public long getTimeSpend(long time)
    {
        return new Date().getTime() - time;
    }
    static public String getSecondsString(long time)
    {
        return (time / MILLISECS_IN_SEC) + "";
    }
    static public String getSecondsStringWithSec(Context context, long time)
    {
        return getSecondsString(time) + " " + context.getResources().getString(R.string.seconds);
    }
    static public String getZeroSecondsStringWithSec(Context context)
    {
        return "0 " + context.getResources().getString(R.string.seconds);
    }
}