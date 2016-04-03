package stan.bulls.cows.ui.fragments.main;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.main.ISandboxFragmentListener;
import stan.bulls.cows.ui.adapters.pagers.GameTypePagerAdapter;
import stan.bulls.cows.ui.fragments.StanFragment;
import stan.bulls.cows.ui.fragments.game.types.NumbersGameTypeFragment;
import stan.bulls.cows.ui.fragments.game.types.WordGameTypeFragment;
import stan.bulls.cows.ui.fragments.main.sandbox.NumbersSandbox;

public class SandboxFragment
        extends StanFragment
{
    static public SandboxFragment newInstance(ISandboxFragmentListener l)
    {
        SandboxFragment fragment = new SandboxFragment();
        fragment.listener = l;
        return fragment;
    }

    //___________________VIEWS
    private View begin_game;
    ViewPager game_types_view_pager;
    CollapsingToolbarLayout coordinatorlayout_collapsing;

    //_______________FIELDS
    GameTypePagerAdapter adapter;


    public SandboxFragment()
    {
        super(R.layout.sandbox_coordinator);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        begin_game = v.findViewById(R.id.begin_game);
        game_types_view_pager = (ViewPager) v.findViewById(R.id.game_types_view_pager);
        coordinatorlayout_collapsing = (CollapsingToolbarLayout) v.findViewById(R.id.coordinatorlayout_collapsing);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(NumbersGameTypeFragment.newInstance());
        fragments.add(WordGameTypeFragment.newInstance());
        coordinatorlayout_collapsing.setTitle(getActivity().getResources().getString(R.string.numbers_game_type));
        clearOldFragments(getActivity().getSupportFragmentManager(), fragments);
        adapter = new GameTypePagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        game_types_view_pager.setAdapter(adapter);
        game_types_view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                if(adapter.getItem(position) instanceof NumbersGameTypeFragment)
                {
                    coordinatorlayout_collapsing.setTitle(getActivity().getResources().getString(R.string.numbers_game_type));
                } else if(adapter.getItem(position) instanceof WordGameTypeFragment)
                {
                    coordinatorlayout_collapsing.setTitle(getActivity().getResources().getString(R.string.word_game_type));
                } else
                {
                    coordinatorlayout_collapsing.setTitle(getActivity().getResources().getString(R.string.error_game_type));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
        init();
    }

    private void clearOldFragments(FragmentManager fm, List<Fragment> fragments)
    {
        for(int i=0; i<fragments.size(); i++)
        {
            for(int j=0; j<fm.getFragments().size(); j++)
            {
                if (fm.getFragments().get(j) instanceof StanFragment && fragments.get(i) instanceof StanFragment)
                {
                    if(!((StanFragment) fm.getFragments().get(j)).getFragmentTag().equals(MainFragment.class.toString())
                            && !((StanFragment) fm.getFragments().get(j)).getFragmentTag().equals(SandboxFragment.class.toString())
                            && !((StanFragment) fm.getFragments().get(j)).getFragmentTag().equals(NumbersSandbox.class.toString()))
                    {
                        fm.beginTransaction().remove(fm.getFragments().get(j)).commit();
                    }
                }
            }
        }
    }

    private void init()
    {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.sandbox_frame, NumbersSandbox.newInstance(getListener(), begin_game)).commit();
    }

    private ISandboxFragmentListener getListener()
    {
        return (ISandboxFragmentListener) listener;
    }
}