package stan.bulls.cows.ui.fragments.game;

import android.view.View;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.game.IGameFragmentListener;
import stan.bulls.cows.ui.fragments.StanFragment;

public class GameFragment
        extends StanFragment
{

    //___________________VIEWS

    //_______________FIELDS

    static public GameFragment newInstance()
    {
        return new GameFragment();
    }

    public GameFragment()
    {
        super(R.layout.game_fragment, R.string.GameFragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        init();
    }

    private void init()
    {
    }

    private IGameFragmentListener getListener()
    {
        return (IGameFragmentListener) clickListener;
    }
}