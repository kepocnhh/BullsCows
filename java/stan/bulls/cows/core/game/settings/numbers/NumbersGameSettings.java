package stan.bulls.cows.core.game.settings.numbers;

import stan.bulls.cows.core.game.boosters.Booster;
import stan.bulls.cows.core.game.settings.GameSettings;

public class NumbersGameSettings
    extends GameSettings
{
    public int count;
    public int amount;

    public NumbersGameSettings(Booster b, long tg, int c, int a)
    {
        super(b, tg);
        this.count = c;
        this.amount = a;
    }
}