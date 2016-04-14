package stan.bulls.cows.core.game.boosters;

import stan.bulls.cows.core.game.settings.SettingStatuses;

public class DefaultBooster
    extends Booster
{
    public DefaultBooster()
    {
        this.timeGameStatus = SettingStatuses.NEUTRAL;
    }
}