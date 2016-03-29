package stan.bulls.cows.ui.fragments.main;

import android.view.View;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.main.ISandboxFragmentListener;
import stan.bulls.cows.ui.fragments.StanFragment;
import stan.bulls.cows.ui.fragments.main.sandbox.NumbersSandbox;

public class SandboxFragment
        extends StanFragment
{

    //___________________VIEWS

    //_______________FIELDS

    static public SandboxFragment newInstance(ISandboxFragmentListener l)
    {
        SandboxFragment fragment = new SandboxFragment();
        fragment.listener = l;
        return fragment;
    }

    public SandboxFragment()
    {
        super(R.layout.sandbox, R.string.SandboxFragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        init();
    }

    private void init()
    {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.sandbox_frame, NumbersSandbox.newInstance(getListener())).commit();
    }

    private ISandboxFragmentListener getListener()
    {
        return (ISandboxFragmentListener) listener;
    }
}