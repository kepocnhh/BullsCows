package stan.bulls.cows.ui.activities;

import android.content.Intent;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.main.IMainFragmentListener;
import stan.bulls.cows.listeners.fragments.main.ISandboxFragmentListener;
import stan.bulls.cows.ui.fragments.StanFragment;
import stan.bulls.cows.ui.fragments.main.MainFragment;
import stan.bulls.cows.ui.fragments.main.SandboxFragment;

public class Main
        extends StanActivity
        implements IMainFragmentListener, ISandboxFragmentListener
{
    //_______________VIEWS

    //_______________FRAGMENTS

    //_______________FIELDS

    public Main()
    {
        super(R.layout.main, R.id.main_frame);
    }

    @Override
    protected void onActivityResult(int request, int result, Intent intent)
    {
        super.onActivityResult(request, result, intent);
    }

    @Override
    protected void initViews()
    {
    }

    @Override
    protected void init()
    {
        addFragment(MainFragment.newInstance(this));
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
        addToBackStack(SandboxFragment.newInstance(this));
    }

    @Override
    public void settings()
    {

    }

    @Override
    public void beginNumbersEasyGame(int count)
    {
        Game.startForNumbersEasy(this, count);
    }

    @Override
    public void beginNumbersMediumGame(int count)
    {
        Game.startForNumbersMedium(this, count);
    }

    @Override
    public void beginNumbersHardGame(int count)
    {
        Game.startForNumbersHard(this, count);
    }
}