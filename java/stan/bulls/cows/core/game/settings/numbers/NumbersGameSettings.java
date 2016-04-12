package stan.bulls.cows.core.game.settings.numbers;

import stan.bulls.cows.core.game.boosters.Booster;
import stan.bulls.cows.core.game.settings.GameSettings;
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
    }

    private void initTimeGame()
    {
        int temp = this.count + this.amount;
        temp *= 2;
        temp += 4 * (this.count - 3);
        this.timeGame = TimeHelper.getMillisecsFromSec(temp * 10);
    }
}