package stan.bulls.cows.core.game.boosters;

import stan.bulls.cows.core.game.settings.SettingStatuses;

public class DefaultBooster
    extends Booster
{
    public DefaultBooster()
    {
        this.statuses.timeGameStatus = SettingStatuses.NEUTRAL;
        this.statuses.attemptsLeftStatus = SettingStatuses.REWARD;
        this.statuses.timeOneOfferStatus = SettingStatuses.REWARD;
    }
}