package stan.bulls.cows.ui.activities;

import stan.bulls.cows.R;
import stan.bulls.cows.ui.fragments.game.GameFragment;

public class Game
        extends StanActivity
{
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
        addFragment(GameFragment.newInstance());
    }
}