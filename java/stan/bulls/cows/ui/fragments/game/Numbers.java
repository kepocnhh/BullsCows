package stan.bulls.cows.ui.fragments.game;

import android.os.Bundle;
import android.view.View;

import java.util.Random;

import stan.bulls.cows.R;
import stan.bulls.cows.core.Offer;
import stan.bulls.cows.db.ContentDriver;
import stan.bulls.cows.db.SQliteApi;
import stan.bulls.cows.listeners.fragments.game.INumbersListener;
import stan.bulls.cows.logic.Logic;
import stan.bulls.cows.ui.adapters.StanRecyclerAdapter;
import stan.bulls.cows.ui.adapters.game.NumbersOffersAdapter;

public class Numbers
        extends GameFragment
{
    static public final String AMOUNT_KEY = "stan.bulls.cows.ui.fragments.game.Numbers.amount_key";

    //___________________VIEWS

    //_______________FIELDS
    protected int amount;

    static public Numbers newInstance(int count, int amount)
    {
        Numbers fragment = new Numbers();
        Bundle bundle = fragment.getArguments();
        bundle.putInt(COUNT_KEY, count);
        bundle.putInt(AMOUNT_KEY, amount);
        fragment.setArguments(bundle);
        return fragment;
    }

    public Numbers()
    {
        super(R.layout.numbers_game_fragment, R.string.Numbers);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
    }
    @Override
    protected StanRecyclerAdapter createAdapter()
    {
        return new NumbersOffersAdapter(getActivity());
    }

    @Override
    protected void init()
    {
        count = getArguments().getInt(COUNT_KEY);
        amount = getArguments().getInt(AMOUNT_KEY);
    }

    @Override
    protected Offer createSecret()
    {
        String value = "";
        Random random = new Random();
        for(int i=0; i<count; i++)
        {
            value += random.nextInt(amount+1)+"";
        }
        return new Offer(value);
    }

    protected void addOffer()
    {
        String value = offer_value.getText().toString();
        if(value.length() != secret.getLenght())
        {
            return;
        }
        Offer offer = Logic.checkCountBullsAndCows(value, secret.getValue());
        SQliteApi.insertGameTempOffer(ContentDriver.getContentValuesOfferForGameTemp(offer));
        swapCursor(SQliteApi.getGameTemp());
        if(offer.bulls == secret.getLenght())
        {
            getListener().result();
        }
        offer_value.setText("");
    }

    private INumbersListener getListener()
    {
        return (INumbersListener) clickListener;
    }
}