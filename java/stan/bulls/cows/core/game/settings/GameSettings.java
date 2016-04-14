package stan.bulls.cows.core.game.settings;

import stan.bulls.cows.core.game.boosters.Booster;

public abstract class GameSettings
{
    public Booster booster;
    public long timeGame;
    public int timeGameStatus;

    public GameSettings(Booster b)
    {
        this.booster = b;
        initTimeGameStatus();
    }
    private void initTimeGameStatus()
    {
        if(this.booster.timeGameStatus == SettingStatuses.NOT_INTEREST)
        {
            this.timeGameStatus = SettingStatuses.NOT_INTEREST;
        }
        else
        {
            this.timeGameStatus = SettingStatuses.REWARD;
        }
    }
}