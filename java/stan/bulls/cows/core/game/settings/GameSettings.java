package stan.bulls.cows.core.game.settings;

import stan.bulls.cows.core.game.boosters.Booster;

public abstract class GameSettings
{
    public Booster booster;
    public long timeGame;
    public long timeOneOffer;
    public int qualityOfferCount;
    public Statuses statuses;

    public GameSettings(Booster b)
    {
        this.booster = b;
        this.statuses = new Statuses();
        initTimeGameStatus();
        initTimeOneOfferStatus();
        initQualityOfferCountStatus();
    }

    //INIT_STATUSES
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
    private void initTimeOneOfferStatus()
    {
        if(this.booster.statuses.timeOneOfferStatus == SettingStatuses.NOT_INTEREST)
        {
            this.statuses.timeOneOfferStatus = SettingStatuses.NOT_INTEREST;
        }
        else
        {
            this.statuses.timeOneOfferStatus = SettingStatuses.REWARD;
        }
    }
    private void initQualityOfferCountStatus()
    {
        if(this.booster.statuses.qualityOfferCountStatus == SettingStatuses.NOT_INTEREST)
        {
            this.statuses.qualityOfferCountStatus = SettingStatuses.NOT_INTEREST;
        }
        else
        {
            this.statuses.qualityOfferCountStatus = SettingStatuses.REWARD;
        }
    }

    //CHECK_STATUSES_FROM_BOOSTER_STATUS
    public boolean checkTimeGameStatusFromBoosterStatus()
    {
        return this.statuses.timeGameStatus == this.booster.statuses.timeGameStatus;
    }
    public boolean checkTimeOneOfferStatusFromBoosterStatus()
    {
        return this.statuses.timeOneOfferStatus == this.booster.statuses.timeOneOfferStatus;
    }

    //SET_NEXT_STATUSES
    public void setNextTimeGameStatus()
    {
        this.statuses.timeGameStatus = SettingStatuses.getNextStatus(this.statuses.timeGameStatus);
    }
    public void setNextTimeOneOfferStatus()
    {
        this.statuses.timeOneOfferStatus = SettingStatuses.getNextStatus(this.statuses.timeOneOfferStatus);
    }
    public void setNextQualityOfferCountStatus()
    {
        this.statuses.qualityOfferCountStatus = SettingStatuses.getNextStatus(this.statuses.qualityOfferCountStatus);
    }
}