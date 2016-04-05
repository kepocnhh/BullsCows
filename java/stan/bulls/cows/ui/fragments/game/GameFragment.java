package stan.bulls.cows.ui.fragments.game;

import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import stan.bulls.cows.R;
import stan.bulls.cows.core.Offer;
import stan.bulls.cows.db.SQliteApi;
import stan.bulls.cows.listeners.fragments.game.IGameFragmentListener;
import stan.bulls.cows.ui.adapters.StanRecyclerAdapter;
import stan.bulls.cows.ui.fragments.StanFragment;

public abstract class GameFragment
        extends StanFragment
{
    static public final String COUNT_KEY = "stan.bulls.cows.ui.fragments.game.GameFragment.count_key";

    //___________________VIEWS
    private RecyclerView offer_list;

    //_______________FIELDS
    private StanRecyclerAdapter adapter;
    protected Offer secret;
    protected int count;

    public GameFragment(int lay)
    {
        super(lay);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        v.findViewById(R.id.add_offer).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addOffer();
            }
        });
        offer_list = (RecyclerView) v.findViewById(R.id.offer_list);
        initList();
        initGameFragment();
    }
    private void initList()
    {
        adapter = createAdapter();
        offer_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        offer_list.setAdapter(adapter);
    }
    protected abstract StanRecyclerAdapter createAdapter();
    private void initGameFragment()
    {
        init();
        SQliteApi.clearGameTemp();
    }
    protected void smoothScrollToEnd()
    {
        offer_list.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    protected abstract void init();

    protected void swapCursor(Cursor c)
    {
        adapter.swapCursor(c);
    }

    protected void setSecret()
    {
        secret = createSecret();
    }

    protected abstract Offer createSecret();

    protected abstract void addOffer();

    private IGameFragmentListener getListener()
    {
        return (IGameFragmentListener) listener;
    }
}