package stan.bulls.cows.core.game.settings;

import stan.bulls.cows.core.game.boosters.Booster;

public class GameSettings
{
    public Booster booster;
    public long timeGame;

    public GameSettings(Booster b, long tg)
    {
        this.booster = b;
        this.timeGame = tg;
    }
}