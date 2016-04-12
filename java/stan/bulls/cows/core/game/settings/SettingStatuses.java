package stan.bulls.cows.core.game.settings;

public class SettingStatuses
{
    static public final int NOT_INTEREST = 0;

    static public final int REWARD = 1;
    static public final int NEUTRAL = 2;
    static public final int MULCT = 3;

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
        else
        {
            return -1;
        }
    }
}