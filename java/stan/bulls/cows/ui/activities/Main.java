package stan.bulls.cows.ui.activities;

import android.content.Intent;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.main.IMainFragmentListener;
import stan.bulls.cows.ui.fragments.main.MainFragment;

public class Main
        extends StanActivity
        implements IMainFragmentListener
{
    //_______________VIEWS

    //_______________FRAGMENTS

    //_______________FIELDS

    public Main()
    {
        super(R.layout.main, R.id.main_frame);
    }

    @Override
    protected void initViews()
    {
    }

    @Override
    protected void init()
    {
        addFragment(MainFragment.newInstance());
    }

    //__________MAIN_FRAGMENT_________________________________
    @Override
    public void profile()
    {

    }

    @Override
    public void company()
    {

    }

    @Override
    public void sandbox()
    {
        startActivity(new Intent(this, Game.class));
    }

    @Override
    public void settings()
    {

    }
}