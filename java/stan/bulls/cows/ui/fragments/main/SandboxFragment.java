package stan.bulls.cows.ui.fragments.main;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
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
    //    Toolbar coordinatorlayout_toolbar;
    CollapsingToolbarLayout coordinatorlayout_collapsing;

    //_______________FIELDS
    GameTypePagerAdapter adapter;


    public SandboxFragment()
    {
        super(R.layout.sandbox_coordinator, R.string.SandboxFragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        begin_game = v.findViewById(R.id.begin_game);
        game_types_view_pager = (ViewPager) v.findViewById(R.id.game_types_view_pager);
        //        coordinatorlayout_toolbar = (Toolbar) v.findViewById(R.id.coordinatorlayout_toolbar);
        coordinatorlayout_collapsing = (CollapsingToolbarLayout) v.findViewById(R.id.coordinatorlayout_collapsing);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(NumbersGameTypeFragment.newInstance());
        fragments.add(WordGameTypeFragment.newInstance());
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

    private void init()
    {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.sandbox_frame, NumbersSandbox.newInstance(getListener(), begin_game)).commit();
    }

    private ISandboxFragmentListener getListener()
    {
        return (ISandboxFragmentListener) listener;
    }
}