package stan.bulls.cows.core.game.settings.numbers;

import stan.bulls.cows.core.game.boosters.Booster;
import stan.bulls.cows.core.game.settings.GameSettings;
import stan.bulls.cows.core.game.settings.SettingStatuses;
import stan.bulls.cows.helpers.TimeHelper;

public class NumbersGameSettings
        extends GameSettings
{
    public int count;
    public int amount;

    public NumbersGameSettings(Booster b, int c, int a)
    {
        super(b);
        this.count = c;
        this.amount = a;
        initTimeGame();
        initTimeOneOffer();
        initQualityOfferCount();
    }

    private void initTimeGame()
    {
        if(booster != null)
        {
            if(booster.statuses.timeGameStatus == SettingStatuses.NOT_INTEREST)
            {
                return;
            }
        }
        else
        {
            return;
        }
        int temp = this.count + this.amount;
        temp *= 2;
        temp += 4 * (this.count - 3);
        this.timeGame = TimeHelper.getMillisecsFromSec(temp * 10);
        //        this.timeGame = TimeHelper.getMillisecsFromSec(12);
    }
    private void initTimeOneOffer()
    {
        if(booster != null)
        {
            if(booster.statuses.timeOneOfferStatus == SettingStatuses.NOT_INTEREST)
            {
                return;
            }
        }
        else
        {
            return;
        }
        int temp = this.count + this.amount;
        temp *= 2;
        temp += 4 * (this.count - 3);
        this.timeOneOffer = TimeHelper.getMillisecsFromSec(temp * 10 / 6);
    }
    private void initQualityOfferCount()
    {
        if(booster != null)
        {
            if(booster.statuses.qualityOfferCountStatus == SettingStatuses.NOT_INTEREST)
            {
                return;
            }
        }
        else
        {
            return;
        }
        int temp = this.count + this.amount;
        temp *= 2;
        temp += 4 * (this.count - 3);
        this.qualityOfferCount = temp/10;
    }
}