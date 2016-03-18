package stan.bulls.cows.ui.fragments.game;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import stan.bulls.cows.R;
import stan.bulls.cows.core.Offer;
import stan.bulls.cows.db.ContentDriver;
import stan.bulls.cows.db.SQliteApi;
import stan.bulls.cows.listeners.fragments.game.IGameFragmentListener;
import stan.bulls.cows.logic.Logic;
import stan.bulls.cows.ui.adapters.game.OffersAdapter;
import stan.bulls.cows.ui.fragments.StanFragment;

public class GameFragment
        extends StanFragment
{

    //___________________VIEWS
    private RecyclerView offer_list;
    private EditText offer_value;

    //_______________FIELDS
    private OffersAdapter adapter;
    private final String secret_value = "moloko";

    static public GameFragment newInstance()
    {
        return new GameFragment();
    }

    public GameFragment()
    {
        super(R.layout.game_fragment, R.string.GameFragment);
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
        offer_value = (EditText) v.findViewById(R.id.offer_value);
        offer_list = (RecyclerView) v.findViewById(R.id.offer_list);
        initList();
        init();
    }
    private void initList()
    {
        adapter = new OffersAdapter(getActivity());
        offer_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        offer_list.setAdapter(adapter);
    }
    private void init()
    {
        SQliteApi.clearGameTemp();
    }

    private void addOffer()
    {
        String value = offer_value.getText().toString();
        if(value.length() != secret_value.length())
        {
            return;
        }
        SQliteApi.insertGameTempOffer(ContentDriver.getContentValuesOfferForGameTemp(Logic.checkCountBullsAndCows(value, secret_value)));
        adapter.swapCursor(SQliteApi.getGameTemp());
        offer_value.setText("");
    }

    private IGameFragmentListener getListener()
    {
        return (IGameFragmentListener) clickListener;
    }
}