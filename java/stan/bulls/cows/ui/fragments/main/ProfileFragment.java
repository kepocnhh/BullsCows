package stan.bulls.cows.ui.fragments.main;

import android.database.Cursor;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import stan.bulls.cows.R;
import stan.bulls.cows.db.SQliteApi;
import stan.bulls.cows.listeners.fragments.main.IProfileFragmentListener;
import stan.bulls.cows.ui.fragments.StanFragment;

public class ProfileFragment
        extends StanFragment
{
    static public ProfileFragment newInstance(IProfileFragmentListener l)
    {
        ProfileFragment fragment = new ProfileFragment();
        fragment.listener = l;
        return fragment;
    }

    //___________________VIEWS
    CollapsingToolbarLayout profile_collapsingtoolbarlayout;
    Toolbar profile_toolbar;
    //GAMES_PLAYED
    TextView games_played;

    //_______________FIELDS

    public ProfileFragment()
    {
        super(R.layout.profile_coordinator);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        profile_collapsingtoolbarlayout = (CollapsingToolbarLayout) v.findViewById(R.id.profile_collapsingtoolbarlayout);
        profile_toolbar = (Toolbar) v.findViewById(R.id.profile_toolbar);
//        profile_collapsingtoolbarlayout.setTitle("name");
//        profile_toolbar.setSubtitle("birth");
        games_played = (TextView) v.findViewById(R.id.games_played);
        loadStatistics();
    }

    private void loadStatistics()
    {
        Cursor cursor = SQliteApi.getStatisticsGames();
        games_played.setText(cursor.getCount() + "");
    }

    private IProfileFragmentListener getListener()
    {
        return (IProfileFragmentListener) listener;
    }
}