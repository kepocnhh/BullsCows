package stan.bulls.cows.ui.activities;

import android.app.Activity;
import android.content.Intent;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.game.IGameFragmentListener;
import stan.bulls.cows.ui.fragments.game.Numbers;

public class Game
        extends StanActivity
        implements IGameFragmentListener
{
    static public final int GAME_REQUEST_CODE = 11;
    static public final int GAME_RESULT_ERROR_BADGAMETYPE_CODE = -1;

    static public final String TYPE_GAME_KEY = "stan.bulls.cows.ui.activities.Game.type_game_key";
    static public final int GAME_NUMBERS = 0;

    static public void startForNumbers(Activity activity, int count, int amount)
    {
        Intent i = new Intent(activity, Game.class);
        i.putExtra(Game.TYPE_GAME_KEY, GAME_NUMBERS);
        i.putExtra(Numbers.COUNT_KEY, count);
        i.putExtra(Numbers.AMOUNT_KEY, amount);
        activity.startActivityForResult(i, GAME_REQUEST_CODE);
    }

    //_______________VIEWS

    //_______________FRAGMENTS

    //_______________FIELDS

    public Game()
    {
        super(R.layout.game, R.id.game_frame);
    }

    @Override
    protected void initViews()
    {

    }

    @Override
    protected void init()
    {
        int type_game = getIntent().getIntExtra(Game.TYPE_GAME_KEY, -1);
        if(type_game == GAME_NUMBERS)
        {
            int count = getIntent().getIntExtra(Numbers.COUNT_KEY, -1);
            int amount = getIntent().getIntExtra(Numbers.AMOUNT_KEY, -1);
            addFragment(Numbers.newInstance(count, amount));
        }
        else
        {
            setResult(GAME_RESULT_ERROR_BADGAMETYPE_CODE);
            finish();
        }
    }
}