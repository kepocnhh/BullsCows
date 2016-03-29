package stan.bulls.cows.ui.fragments.game;

import android.os.Bundle;
import android.view.View;

import java.util.Random;

import stan.bulls.cows.R;
import stan.bulls.cows.core.Offer;
import stan.bulls.cows.core.number.NumberOffer;
import stan.bulls.cows.db.ContentDriver;
import stan.bulls.cows.db.SQliteApi;
import stan.bulls.cows.listeners.dialogs.game.IGameDialogListener;
import stan.bulls.cows.listeners.fragments.game.INumbersListener;
import stan.bulls.cows.logic.Logic;
import stan.bulls.cows.ui.adapters.StanRecyclerAdapter;
import stan.bulls.cows.ui.adapters.game.NumbersOffersAdapter;
import stan.bulls.cows.ui.dialogs.game.numbers.NumbersAddOfferDialog;

public class Numbers
        extends GameFragment
    implements IGameDialogListener
{
    static public final String AMOUNT_KEY = "stan.bulls.cows.ui.fragments.game.Numbers.amount_key";

    //___________________VIEWS

    //_______________FIELDS
    private int amount;

    static public Numbers newInstance(int count, int amount, INumbersListener l)
    {
        Numbers fragment = new Numbers();
        Bundle bundle = fragment.getArguments();
        bundle.putInt(COUNT_KEY, count);
        bundle.putInt(AMOUNT_KEY, amount);
        fragment.setArguments(bundle);
        fragment.listener = l;
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
        return new NumberOffer(value);
    }

    protected void addOffer()
    {
        if(amount == NumbersAddOfferDialog.AMOUNT_DIFFICULT_EASY)
        {
            NumbersAddOfferDialog.createNumbersAddOfferDialogEasy(count, this)
                    .show(getActivity().getSupportFragmentManager());
        }
        else if(amount == NumbersAddOfferDialog.AMOUNT_DIFFICULT_MEDIUM)
        {
            NumbersAddOfferDialog.createNumbersAddOfferDialogMedium(count, this)
                    .show(getActivity().getSupportFragmentManager());
        }
        else if(amount == NumbersAddOfferDialog.AMOUNT_DIFFICULT_HARD)
        {
            NumbersAddOfferDialog.createNumbersAddOfferDialogHard(count, this)
                    .show(getActivity().getSupportFragmentManager());
        }
    }

    private INumbersListener getListener()
    {
        return (INumbersListener) listener;
    }

    @Override
    public void addOffer(String value)
    {
        if(value.length() != secret.getLenght())
        {
            return;
        }
        Offer offer = Logic.checkCountBullsAndCows(new NumberOffer(value), secret);
        SQliteApi.insertGameTempOffer(ContentDriver.getContentValuesOfferForGameTemp(offer));
        swapCursor(SQliteApi.getGameTemp());
        if(offer.bulls == secret.getLenght())
        {
            getListener().result();
        }
    }

    @Override
    public void onDismiss()
    {

    }
}