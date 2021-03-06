package stan.bulls.cows.ui.fragments.main;

import android.view.View;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.main.IMainFragmentListener;
import stan.bulls.cows.ui.fragments.StanFragment;

public class MainFragment
        extends StanFragment
{

    //___________________VIEWS

    //_______________FIELDS

    static public MainFragment newInstance(IMainFragmentListener l)
    {
        MainFragment fragment = new MainFragment();
        fragment.listener = l;
        return fragment;
    }

    public MainFragment()
    {
        super(R.layout.main_fragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        v.findViewById(R.id.main_sandbox).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getListener().sandbox();
            }
        });
        v.findViewById(R.id.main_profile).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getListener().profile();
            }
        });
        init();
    }

    private void init()
    {
    }

    private IMainFragmentListener getListener()
    {
        return (IMainFragmentListener) listener;
    }
}