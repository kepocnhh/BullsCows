package stan.bulls.cows.helpers;

import stan.bulls.cows.core.game.settings.GameSettings;
import stan.bulls.cows.core.game.settings.SettingStatuses;

public class AllGameTimeHelper
{
    static public int recheckTimeFromGameStatus(GameSettings gameSettings, long time)
    {
        if(gameSettings.booster.statuses.timeGameStatus == SettingStatuses.NEUTRAL)
        {
            return SettingStatuses.NEUTRAL;
        }
        else if(gameSettings.booster.statuses.timeGameStatus == SettingStatuses.MULCT)
        {
            if(time + gameSettings.timeGame/4*3 >= gameSettings.timeGame)
            {
                return SettingStatuses.MULCT;
            }
            else
            {
                return SettingStatuses.NEUTRAL;
            }
        }
        return -1;
    }
    static public long getTimeFromGameStatus(GameSettings gameSettings, long date)
    {
        if(gameSettings.booster.statuses.timeGameStatus == SettingStatuses.NEUTRAL)
        {
            return gameSettings.timeGame - TimeHelper.getTimeSpend(date);
        }
        else if(gameSettings.booster.statuses.timeGameStatus == SettingStatuses.MULCT)
        {
            if(gameSettings.statuses.timeGameStatus == SettingStatuses.REWARD)
            {
                return gameSettings.timeGame/4*3 - TimeHelper.getTimeSpend(date);
            }
            else
            {
                return gameSettings.timeGame - TimeHelper.getTimeSpend(date);
            }
        }
        else if(gameSettings.booster.statuses.timeGameStatus == SettingStatuses.END_GAME)
        {
            if(gameSettings.statuses.timeGameStatus == SettingStatuses.REWARD)
            {
                return gameSettings.timeGame/4*3 - TimeHelper.getTimeSpend(date);
            }
            else
            {
                return gameSettings.timeGame - TimeHelper.getTimeSpend(date);
            }
        }
        return -1;
    }
}