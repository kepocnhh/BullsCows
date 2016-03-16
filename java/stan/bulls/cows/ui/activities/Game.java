package stan.bulls.cows.ui.activities;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.game.IGameFragmentListener;
import stan.bulls.cows.ui.fragments.game.GameFragment;

public class Game
        extends StanActivity
        implements IGameFragmentListener
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