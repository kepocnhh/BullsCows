package stan.bulls.cows.ui.activities;

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
        initList();
    }

    private void initList()
    {
    }

    @Override
    protected void init()
    {
        initRequest();
        addFragment(MainFragment.newInstance());
    }

    private void initRequest()
    {
    }
}