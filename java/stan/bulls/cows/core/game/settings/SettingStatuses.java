package stan.bulls.cows.core.game.settings;

public class SettingStatuses
{
    static public final int NOT_INTEREST = -1;

    static public final int REWARD = 1;
    static public final int NEUTRAL = 2;
    static public final int MULCT = 3;
    static public final int END_GAME = 4;

    static public int getNextStatus(int status)
    {
        if(status == SettingStatuses.REWARD)
        {
            return SettingStatuses.NEUTRAL;
        }
        else if(status == SettingStatuses.NEUTRAL)
        {
            return SettingStatuses.MULCT;
        }
        else if(status == SettingStatuses.MULCT)
        {
            return SettingStatuses.END_GAME;
        }
        else
        {
            return -1;
        }
    }
}