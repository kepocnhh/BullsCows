package stan.bulls.cows.ui.fragments.game;

import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.Date;
import java.util.Random;

import stan.bulls.cows.R;
import stan.bulls.cows.core.Offer;
import stan.bulls.cows.core.game.ResultGame;
import stan.bulls.cows.core.game.boosters.DefaultBooster;
import stan.bulls.cows.core.game.difficults.NumbersDifficults;
import stan.bulls.cows.core.game.settings.numbers.NumbersGameSettings;
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
    private CircularProgressView time_game;
    private CardView attempts_left_card;
    private TextView attempts_left_number;
    private View attempts_left;
    private View attempts_left_and_offers_list_timer;
    private CardView offers_list_timer_card;
    private ImageView time_circle;
    private View time_frame;

    //_______________FIELDS
//    private int amount;
//    private int count;
    private NumbersGameSettings gameSettings;
    private long date;
    private int amount_offers;
//    private long timeGame = TimeHelper.getMillisecsFromSec(120);
    private int attemptsLeftNumber = 10;
    private Date time;
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
                timeSpend = TimeHelper.getTimeSpend(time.getTime()) + "";
            }
            SQliteApi.insertGameTempOffer(ContentDriver.getContentValuesOfferForGameTemp(offer, timeSpend));
            Cursor cursor = SQliteApi.getGameTemp();
            swapCursor(cursor);
            amount_offers++;
            refreshUIFromOffersCount(cursor.getCount());
            smoothScrollToEnd();
            if (offer.bulls == secret.getLenght())
            {
                endWinGame(true);
                return;
            }
            if(attemptsLeftNumber - cursor.getCount() == 0)
            {
                endWinGame(false);
                return;
            }
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
        attempts_left_card = (CardView) v.findViewById(R.id.attempts_left_card);
        attempts_left_number = (TextView) v.findViewById(R.id.attempts_left_number);
        attempts_left = v.findViewById(R.id.attempts_left);
        offers_list_timer_card = (CardView) v.findViewById(R.id.offers_list_timer_card);
        attempts_left_and_offers_list_timer = v.findViewById(R.id.attempts_left_and_offers_list_timer);
        time_circle = (ImageView) v.findViewById(R.id.time_circle);
        time_game = (CircularProgressView) v.findViewById(R.id.time_game);
        time_frame = v.findViewById(R.id.time_frame);
        offers_list_timer = (TextView) v.findViewById(R.id.offers_list_timer);
        offers_list_submessage = (TextView) v.findViewById(R.id.offers_list_submessage);
        offers_list_submessage.setText(R.string.offers_list_submessage_empty);
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
        gameSettings = new NumbersGameSettings(new DefaultBooster(), TimeHelper.getMillisecsFromSec(120), getArguments().getInt(COUNT_KEY), getArguments().getInt(AMOUNT_KEY));
//        count = getArguments().getInt(COUNT_KEY);
//        amount = getArguments().getInt(AMOUNT_KEY);
        amount_offers = 0;
        attempts_left.setVisibility(View.INVISIBLE);
        offers_list_timer.setVisibility(View.INVISIBLE);
        attempts_left_and_offers_list_timer.setVisibility(View.INVISIBLE);
        time_frame.setVisibility(View.INVISIBLE);
        initProgress();
    }
    private void initProgress()
    {
        time_game.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_around_center_point));
        time_game.setMaxProgress(gameSettings.timeGame);
    }

    @Override
    protected Offer createSecret()
    {
        String value = "";
        Random random = new Random();
        for (int i = 0; i < gameSettings.count; i++)
        {
            value += random.nextInt(gameSettings.amount + 1) + "";
        }
        Log.e("GameFragment", "createSecret - " + value);
        return new NumberOffer(value);
    }

    protected void addOffer()
    {
        if (gameSettings.amount == NumbersDifficults.AMOUNT_DIFFICULT_EASY)
        {
            NumbersAddOfferDialog.createNumbersAddOfferDialogEasy(gameSettings.count, gameDialogListener).show(getActivity().getSupportFragmentManager());
        }
        else if (gameSettings.amount == NumbersDifficults.AMOUNT_DIFFICULT_MEDIUM)
        {
            NumbersAddOfferDialog.createNumbersAddOfferDialogMedium(gameSettings.count, gameDialogListener).show(getActivity().getSupportFragmentManager());
        }
        else if (gameSettings.amount == NumbersDifficults.AMOUNT_DIFFICULT_HARD)
        {
            NumbersAddOfferDialog.createNumbersAddOfferDialogHard(gameSettings.count, gameDialogListener).show(getActivity().getSupportFragmentManager());
        }
    }

    private void refreshUIFromOffersCount(int count)
    {
        if (count == 1)
        {
            date = new Date().getTime();
            offers_list_submessage.setText(R.string.offers_list_submessage_begin_game);
            offers_list_timer_card.setCardBackgroundColor(getActivity().getResources().getColor(R.color.green));
            attempts_left_card.setCardBackgroundColor(getActivity().getResources().getColor(R.color.green));
            animateTimeCircle();
            time_circle.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.circle_green));
            resetTimerAllGame();
        }
        else if (count == 2)
        {
            offers_list_submessage.setVisibility(View.GONE);
        }
        offers_list_timer.setText(TimeHelper.getZeroSecondsStringWithSec(getActivity()));
        attempts_left_number.setText(attemptsLeftNumber - count+"");
        resetTimerOneOffer();
    }
    private void animateAttemptsLeftAndOffersListTimer()
    {
        attempts_left_and_offers_list_timer.setVisibility(View.VISIBLE);
        Animation an = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_from_x);
        an.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                offers_list_timer.setVisibility(View.VISIBLE);
                attempts_left.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        attempts_left_and_offers_list_timer.startAnimation(an);
    }
    private void animateTimeCircle()
    {
        time_frame.setVisibility(View.VISIBLE);
        Animation an = new RotateAnimation(270.0f, 360.0f, time_frame.getPivotX(), time_frame.getPivotY() + time_frame.getHeight());
        an.setDuration(1000);               // duration in ms
        an.setRepeatCount(0);                // -1 = infinite repeated
        an.setRepeatMode(Animation.REVERSE); // reverses each repeat
        an.setFillAfter(false);               // keep rotation after animation
        an.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                animateAttemptsLeftAndOffersListTimer();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        time_frame.setAnimation(an);
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
        timerAllGame = new CountDownTimer(gameSettings.timeGame - TimeHelper.getTimeSpend(date), TimeHelper.getMillisecsFromSec(1))
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                time_game.setProgress(gameSettings.timeGame - millisUntilFinished);
            }

            @Override
            public void onFinish()
            {
                time_game.setProgress(gameSettings.timeGame);
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