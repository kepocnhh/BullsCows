package stan.bulls.cows.core.game.settings;

import stan.bulls.cows.core.game.boosters.Booster;

public abstract class GameSettings
{
    public Booster booster;
    public long timeGame;
    public Statuses statuses;

    public GameSettings(Booster b)
    {
        this.booster = b;
        this.statuses = new Statuses();
        initTimeGameStatus();
    }
    private void initTimeGameStatus()
    {
        if(this.booster.statuses.timeGameStatus == SettingStatuses.NOT_INTEREST)
        {
            this.statuses.timeGameStatus = SettingStatuses.NOT_INTEREST;
        }
        else
        {
            this.statuses.timeGameStatus = SettingStatuses.REWARD;
        }
    }
    public boolean checkTimeGameStatusFromBoosterStatus()
    {
        return this.statuses.timeGameStatus == this.booster.statuses.timeGameStatus;
    }
    public void setNextTimeGameStatus()
    {
        this.statuses.timeGameStatus = SettingStatuses.getNextStatus(this.statuses.timeGameStatus);
    }
}