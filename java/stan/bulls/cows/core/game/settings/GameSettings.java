package stan.bulls.cows.core.game.settings;

import stan.bulls.cows.core.game.boosters.Booster;

public abstract class GameSettings
{
    public Booster booster;
    public long timeGame;

    public GameSettings(Booster b)
    {
        this.booster = b;
    }
}