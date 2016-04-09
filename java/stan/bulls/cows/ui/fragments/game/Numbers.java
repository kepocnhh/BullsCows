package stan.bulls.cows.ui.fragments.game;

import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.github.rahatarmanahmed.cpv.CircularProgressViewListener;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Random;

import stan.bulls.cows.R;
import stan.bulls.cows.core.Offer;
import stan.bulls.cows.core.game.ResultGame;
import stan.bulls.cows.core.game.difficults.NumbersDifficults;
import stan.bulls.cows.core.number.NumberOffer;
import stan.bulls.cows.db.ContentDriver;
import stan.bulls.cows.db.SQliteApi;
import stan.bulls.cows.helpers.TimeHelper;
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
    private TextView offers_list_submessage;
    private TextView offers_list_timer;
    //    private ProgressBar time_game;
    private CircularProgressView time_game;

    //_______________FIELDS
    private int amount;
    private long date;
    private int amount_offers;
    private long timeGame = TimeHelper.getMillisecsFromSec(30);
    private Date time;
//    private Handler timerSpend;
//    private Runnable runnableSpend;
    private CountDownTimer timerAllGame;
    private CountDownTimer timerOneOffer;
    private IGameDialogListener gameDialogListener = new IGameDialogListener()
    {
        @Override
        public void addOffer(String value)
        {
            Offer offer;
            if (date == 0)
            {
                setSecret();
                offer = Logic.checkCountBullsAndCows(new NumberOffer(value), secret);
                if (offer.bulls == secret.getLenght())
                {
                    while (offer.bulls == secret.getLenght())
                    {
                        setSecret();
                        offer = Logic.checkCountBullsAndCows(new NumberOffer(value), secret);
                    }
                }
            }
            else
            {
                offer = Logic.checkCountBullsAndCows(new NumberOffer(value), secret);
            }
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
            amount_offers++;
            if (offer.bulls == secret.getLenght())
            {
                endWinGame(true);
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
        time_game = (CircularProgressView) v.findViewById(R.id.time_game);
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
        amount_offers = 0;
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
        Log.e("GameFragment", "createSecret - " + value);
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
            date = new Date().getTime();
            offers_list_submessage.setText(R.string.offers_list_submessage_begin_game);
            offers_list_timer.setVisibility(View.VISIBLE);
            resetTimerAllGame();
        }
        else if (count == 2)
        {
            offers_list_submessage.setVisibility(View.GONE);
        }
        offers_list_timer.setText(TimeHelper.getZeroSecondsStringWithSec(getActivity()));
        resetTimerOneOffer();
    }

    private void resetTimerOneOffer()
    {
        if (timerOneOffer != null)
        {
            timerOneOffer.cancel();
        }
        timerOneOffer = new CountDownTimer(TimeHelper.MAX_MILLISEC, TimeHelper.getMillisecsFromSec(1))
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                offers_list_timer.setText(TimeHelper.getSecondsStringWithSec(getActivity(), TimeHelper.getTimeSpend(time.getTime())));
            }

            @Override
            public void onFinish()
            {
                offers_list_timer.setText(R.string.much);
            }
        }.start();
    }
    private void resetTimerAllGame()
    {
        if (timerAllGame != null)
        {
            timerAllGame.cancel();
        }
        timerAllGame = new CountDownTimer(timeGame - TimeHelper.getTimeSpend(date), TimeHelper.getMillisecsFromSec(2))
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
            }

            @Override
            public void onFinish()
            {
                endWinGame(false);
            }
        }.start();
    }

    private void endWinGame(boolean win)
    {
        offers_list_submessage.setVisibility(View.GONE);
        if (timerOneOffer != null)
        {
            timerOneOffer.cancel();
            timerOneOffer = null;
        }
        if (timerAllGame != null)
        {
            timerAllGame.cancel();
            timerAllGame = null;
        }
        if(win)
        {
            getListener().result(endWinGame());
        }
        else
        {
            getListener().result(endLostGame());
        }
    }
    private ResultGame endWinGame()
    {
        ResultGame resultGame = new ResultGame();
        resultGame.date = date;
        resultGame.time_spend = TimeHelper.getTimeSpend(date);
        resultGame.game_type = 0;
        resultGame.win = true;
        resultGame.amount_offers = amount_offers;
        resultGame.game_settings = "";
        return resultGame;
    }
    private ResultGame endLostGame()
    {
        ResultGame resultGame = new ResultGame();
        resultGame.win = false;
        return resultGame;
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if (timerOneOffer != null)
        {
            timerOneOffer.cancel();
        }
        if (timerAllGame != null)
        {
            timerAllGame.cancel();
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        if (timerOneOffer != null)
        {
            timerOneOffer.cancel();
            resetTimerOneOffer();
        }
        if (timerAllGame != null)
        {
            timerAllGame.cancel();
            resetTimerAllGame();
        }
    }

    private INumbersListener getListener()
    {
        return (INumbersListener) listener;
    }
}