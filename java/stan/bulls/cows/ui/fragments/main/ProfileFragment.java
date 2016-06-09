package stan.bulls.cows.ui.fragments.main;

import android.database.Cursor;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import stan.bulls.cows.R;
import stan.bulls.cows.db.SQliteApi;
import stan.bulls.cows.db.Tables;
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
    TextView gold_earned;
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
        gold_earned = (TextView) v.findViewById(R.id.gold_earned);
        games_played = (TextView) v.findViewById(R.id.games_played);
        loadStatistics();
    }

    private void loadStatistics()
    {
        loadGoldStat();
        loadGameStat();
    }

    private void loadGoldStat()
    {
        int gold = 0;
        Cursor cursor = SQliteApi.getAllGold();
        if(cursor.moveToFirst())
        {
            do
            {
                gold += cursor.getInt(cursor.getColumnIndex(Tables.GoldTransactions.Columns.gold));
            } while(cursor.moveToNext());
        }
        cursor.close();
        gold_earned.setText(gold + "");
    }

    private void loadGameStat()
    {
        Cursor cursor = SQliteApi.getStatisticsGames();
        games_played.setText(cursor.getCount() + "");
        cursor.close();
    }

    private IProfileFragmentListener getListener()
    {
        return (IProfileFragmentListener) listener;
    }
}