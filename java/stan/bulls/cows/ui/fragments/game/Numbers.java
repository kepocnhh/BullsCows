package stan.bulls.cows.ui.fragments.game;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

import stan.bulls.cows.R;
import stan.bulls.cows.core.Offer;
import stan.bulls.cows.core.game.difficults.NumbersDifficults;
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
{
    static public final String AMOUNT_KEY = "stan.bulls.cows.ui.fragments.game.Numbers.amount_key";

    //___________________VIEWS
    TextView offers_list_submessage;
    TextView offers_list_timer;

    //_______________FIELDS
    private int amount;
    private Date time;
    private Handler timer;
    private Runnable runnable;
    private IGameDialogListener gameDialogListener = new IGameDialogListener()
    {
        @Override
        public void addOffer(String value)
        {
            if (value.length() != secret.getLenght())
            {
                return;
            }
            Offer offer = Logic.checkCountBullsAndCows(new NumberOffer(value), secret);
            String timeSpend;
            if (time == null)
            {
                timeSpend = "0";
            }
            else
            {
                timeSpend = (new Date().getTime() - time.getTime()) + "";
            }
            SQliteApi.insertGameTempOffer(ContentDriver.getContentValuesOfferForGameTemp(offer, timeSpend));
            Cursor cursor = SQliteApi.getGameTemp();
            swapCursor(cursor);
            if (offer.bulls == secret.getLenght())
            {
                getListener().result();
                return;
            }
            refreshUIFromOffersCount(cursor.getCount());
            smoothScrollToEnd();
            time = new Date();
        }

        @Override
        public void onDismiss()
        {

        }
    };

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
        super(R.layout.numbers_game_fragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        offers_list_timer = (TextView) v.findViewById(R.id.offers_list_timer);
        offers_list_timer.setVisibility(View.GONE);
        offers_list_submessage = (TextView) v.findViewById(R.id.offers_list_submessage);
        offers_list_submessage.setText(R.string.offers_list_submessage_empty);
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
        for (int i = 0; i < count; i++)
        {
            value += random.nextInt(amount + 1) + "";
        }
        return new NumberOffer(value);
    }

    protected void addOffer()
    {
        if (amount == NumbersDifficults.AMOUNT_DIFFICULT_EASY)
        {
            NumbersAddOfferDialog.createNumbersAddOfferDialogEasy(count, gameDialogListener).show(getActivity().getSupportFragmentManager());
        }
        else if (amount == NumbersDifficults.AMOUNT_DIFFICULT_MEDIUM)
        {
            NumbersAddOfferDialog.createNumbersAddOfferDialogMedium(count, gameDialogListener).show(getActivity().getSupportFragmentManager());
        }
        else if (amount == NumbersDifficults.AMOUNT_DIFFICULT_HARD)
        {
            NumbersAddOfferDialog.createNumbersAddOfferDialogHard(count, gameDialogListener).show(getActivity().getSupportFragmentManager());
        }
    }

    private void refreshUIFromOffersCount(int count)
    {
        if (count == 1)
        {
            showOfferList();
            offers_list_submessage.setText(R.string.offers_list_submessage_begin_game);
            offers_list_timer.setVisibility(View.VISIBLE);
            offers_list_timer.setText("0 " + getActivity().getResources().getString(R.string.seconds));
            timer = new Handler();
            runnable = new Runnable()
            {
                @Override
                public void run()
                {
                    String timeSpend = (new Date().getTime() - time.getTime()) / 1000 + " " + getActivity().getResources().getString(R.string.seconds);
                    offers_list_timer.setText(timeSpend);
                    timer.postDelayed(this, 1000);
                }
            };
            timer.postDelayed(runnable, 1000);
        }
        else if (count == 2)
        {
            offers_list_submessage.setVisibility(View.GONE);
        }
        else if (count == 3)
        {
            offers_list_timer.setVisibility(View.GONE);
            timer.removeCallbacks(runnable);
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if (timer != null)
        {
            timer.removeCallbacks(runnable);
        }
    }

    private INumbersListener getListener()
    {
        return (INumbersListener) listener;
    }
}