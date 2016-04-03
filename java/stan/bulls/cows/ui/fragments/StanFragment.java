package stan.bulls.cows.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stan.bulls.cows.listeners.fragments.IStanFragmentListener;

public abstract class StanFragment
        extends Fragment
{
    protected View container;
    private String fragmentTag;

    public String getFragmentTag()
    {
        return fragmentTag;
    }

    protected IStanFragmentListener listener;

    public StanFragment(int lay)
    {
        Bundle args = new Bundle();
        args.putInt("layout", lay);
        setArguments(args);
        fragmentTag = this.getClass().toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(getArguments().getInt("layout", 0), container, false);
        findViews(v);
        return v;
    }

    protected void findViews(View v)
    {
        container = v;
    }
}