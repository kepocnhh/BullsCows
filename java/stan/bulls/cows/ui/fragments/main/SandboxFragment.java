package stan.bulls.cows.ui.fragments.main;

import android.view.View;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.main.ISandboxFragmentListener;
import stan.bulls.cows.ui.fragments.StanFragment;

public class SandboxFragment
        extends StanFragment
{

    //___________________VIEWS

    //_______________FIELDS

    static public SandboxFragment newInstance()
    {
        return new SandboxFragment();
    }

    public SandboxFragment()
    {
        super(R.layout.sandbox, R.string.SandboxFragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        v.findViewById(R.id.begin_game).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getListener().beginNumbersGame(3, 1);
            }
        });
        init();
    }

    private void init()
    {
    }

    private ISandboxFragmentListener getListener()
    {
        return (ISandboxFragmentListener) clickListener;
    }
}