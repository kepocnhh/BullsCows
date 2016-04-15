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
import android.widget.ImageView;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.github.rahatarmanahmed.cpv.CircularProgressViewListener;

import java.util.Date;
import java.util.Random;

import stan.bulls.cows.R;
import stan.bulls.cows.core.Offer;
import stan.bulls.cows.core.game.ResultGame;
import stan.bulls.cows.core.game.boosters.DefaultBooster;
import stan.bulls.cows.core.game.difficults.NumbersDifficults;
import stan.bulls.cows.core.game.settings.SettingStatuses;
import stan.bulls.cows.core.game.settings.numbers.NumbersGameSettings;
import stan.bulls.cows.core.number.NumberOffer;
import stan.bulls.cows.db.ContentDriver;
import stan.bulls.cows.db.SQliteApi;
import stan.bulls.cows.helpers.AllGameTimeHelper;
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
    private View time_is_over;

    //_______________FIELDS
    private NumbersGameSettings gameSettings;
    private long date;
    private int offersCount;
    private int attemptsLeftNumber = 10;
    private Date time;
    private CountDownTimer timerAllGame;
    private CountDownTimer timerOneOffer;
    private IGameDialogListener gameDialogListener = new IGameDialogListener()
    {
        @Override
        public void addOffer(String value)
        {
            newOffer(value);
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
        time_is_over = v.findViewById(R.id.time_is_over);
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
        gameSettings = new NumbersGameSettings(new DefaultBooster(), getArguments().getInt(COUNT_KEY), getArguments().getInt(AMOUNT_KEY));
        Log.e("NumbersGameSettings","timeGame = " + gameSettings.timeGame + "\tamount = " + gameSettings.amount + "\tcount = " + gameSettings.count);
        offersCount = 0;
        attempts_left.setVisibility(View.INVISIBLE);
        offers_list_timer.setVisibility(View.INVISIBLE);
        attempts_left_and_offers_list_timer.setVisibility(View.INVISIBLE);
        time_frame.setVisibility(View.INVISIBLE);
        time_is_over.setVisibility(View.INVISIBLE);
        initTimeGameProgress();
    }
    private void initTimeGameProgress()
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
    private void newOffer(String value)
    {
        Offer offer;
        String timeSpend;
        if (checkBeginGame())
        {
            date = new Date().getTime();
            timeSpend = "0";
            offer = setSecretFromNewOffer(value);
            if(gameSettings.booster.statuses.timeGameStatus != SettingStatuses.NOT_INTEREST)
            {
                resetTimerAllGame();
            }
        }
        else
        {
            timeSpend = TimeHelper.getTimeSpend(time.getTime()) + "";
            offer = Logic.checkCountBullsAndCows(new NumberOffer(value), secret);
        }
        SQliteApi.insertGameTempOffer(ContentDriver.getContentValuesOfferForGameTemp(offer, timeSpend));
        swapCursor(SQliteApi.getGameTemp());
        offersCount++;
        refreshUIFromOffersCount(offersCount);
        resetTimerOneOffer();
        if (checkWin(offer))
        {
            endWinGame(true);
        }
        else if(checkLoseAttemptsNoLeft(offersCount))
        {
            endWinGame(false);
        }
        time = new Date();
    }
    private Offer setSecretFromNewOffer(String value)
    {
        setSecret();
        Offer offer = Logic.checkCountBullsAndCows(new NumberOffer(value), secret);
        if (checkWin(offer))
        {
            while (checkWin(offer))
            {
                setSecret();
                offer = Logic.checkCountBullsAndCows(new NumberOffer(value), secret);
            }
        }
        return offer;
    }
    private boolean checkBeginGame()
    {
        return date == 0 && time == null;
    }

    private void refreshUIFromOffersCount(int count)
    {
        if (count == 1)
        {
            offers_list_submessage.setText(R.string.offers_list_submessage_begin_game);
            if(gameSettings.booster.statuses.timeGameStatus == SettingStatuses.NOT_INTEREST)
            {
                time_circle.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.circle_white));
            }
            else
            {
                time_circle.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.circle_green));
            }
            if(gameSettings.booster.statuses.attemptsLeftStatus != SettingStatuses.NOT_INTEREST || gameSettings.booster.statuses.timeOneOfferStatus != SettingStatuses.NOT_INTEREST)
            {
                if(gameSettings.booster.statuses.attemptsLeftStatus == SettingStatuses.NOT_INTEREST)
                {
                    attempts_left_card.setVisibility(View.GONE);
                }
                else
                {
                    attempts_left_card.setCardBackgroundColor(getActivity().getResources().getColor(R.color.green));
                }
                if(gameSettings.booster.statuses.timeOneOfferStatus == SettingStatuses.NOT_INTEREST)
                {
                    offers_list_timer_card.setVisibility(View.GONE);
                }
                else
                {
                    offers_list_timer_card.setCardBackgroundColor(getActivity().getResources().getColor(R.color.green));
                }
                initAndAnimateTimeCircleWithAttemptsLeftAndOffersListTimer();
            }
            else if(gameSettings.booster.statuses.timeGameStatus != SettingStatuses.NOT_INTEREST)
            {
                initAndAnimateTimeCircle(null);
            }
        }
        else if (count == 2)
        {
            offers_list_submessage.setVisibility(View.GONE);
        }
        offers_list_timer.setText(TimeHelper.getZeroSecondsStringWithSec(getActivity()));
        attempts_left_number.setText(attemptsLeftNumber - count + "");
        smoothScrollToEnd();
    }
    private void initAndAnimateTimeCircle(Animation.AnimationListener listener)
    {
        time_frame.setVisibility(View.VISIBLE);
        Animation an = animateTimeCircle();
        an.setAnimationListener(listener);
        time_frame.setAnimation(an);
    }
    private void initAndAnimateTimeCircleWithAttemptsLeftAndOffersListTimer()
    {
        initAndAnimateTimeCircle(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                animateAttemptsLeftAndOffersListTimer(new Animation.AnimationListener()
                {
                    @Override
                    public void onAnimationStart(Animation animation)
                    {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        attempts_left_and_offers_list_timer.setVisibility(View.VISIBLE);
                        offers_list_timer.setVisibility(View.VISIBLE);
                        attempts_left.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation)
                    {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }
    private Animation animateTimeCircle()
    {
        Animation an = new RotateAnimation(270.0f, 360.0f, time_frame.getPivotX(), time_frame.getPivotY() + time_frame.getHeight());
        an.setDuration(500);               // duration in ms
        an.setRepeatCount(0);                // -1 = infinite repeated
        an.setRepeatMode(Animation.REVERSE); // reverses each repeat
        an.setFillAfter(false);               // keep rotation after animation
        return an;
    }
    private void animateAttemptsLeftAndOffersListTimer(Animation.AnimationListener listener)
    {
        Animation an = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_from_x);
        an.setAnimationListener(listener);
        attempts_left_and_offers_list_timer.startAnimation(an);
    }

    private void resetTimerOneOffer()
    {
        if (timerOneOffer != null)
        {
            timerOneOffer.cancel();
        }
        timerOneOffer = new CountDownTimer(TimeHelper.MAX_MILLISEC, 500)
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
        refreshTimeCircleFromTimeGameStatus(gameSettings.statuses.timeGameStatus);
        if (timerAllGame != null)
        {
            timerAllGame.cancel();
            if(gameSettings.checkTimeGameStatusFromBoosterStatus())
            {
                timerAllGame = null;
                return;
            }
        }
        long time = AllGameTimeHelper.getTimeFromGameStatus(gameSettings, date);
        if(time <= 0)
        {
            gameSettings.statuses.timeGameStatus = AllGameTimeHelper.recheckTimeFromGameStatus(gameSettings, time * -1);
            if(gameSettings.checkTimeGameStatusFromBoosterStatus())
            {
                endAllGameTime();
            }
            resetTimerAllGame();
            return;
        }
        setAndStartAllGameTimer(time);
    }
    private void setAndStartAllGameTimer(long time)
    {
        timerAllGame = new CountDownTimer(time + 1000, 100)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                time_game.setProgress(TimeHelper.getTimeSpend(date));
            }

            @Override
            public void onFinish()
            {
                gameSettings.setNextTimeGameStatus();
                refreshTimeCircleFromTimeGameStatus(gameSettings.statuses.timeGameStatus);
                if(gameSettings.checkTimeGameStatusFromBoosterStatus())
                {
                    time_game.addListener(new CircularProgressViewListener()
                    {
                        @Override
                        public void onProgressUpdate(float currentProgress)
                        {

                        }

                        @Override
                        public void onProgressUpdateEnd(float currentProgress)
                        {
                            endAllGameTime();
                        }

                        @Override
                        public void onAnimationReset()
                        {

                        }

                        @Override
                        public void onModeChanged(boolean isIndeterminate)
                        {

                        }
                    });
                    time_game.setProgress(TimeHelper.getTimeSpend(gameSettings.timeGame));
                }
                else
                {
                    time_game.setProgress(TimeHelper.getTimeSpend(date));
                    resetTimerAllGame();
                }
            }
        }.start();
    }
    private void refreshTimeCircleFromTimeGameStatus(int status)
    {
        if(status == SettingStatuses.END_GAME)
        {
            time_circle.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.circle_red));
        }
        else if(status == SettingStatuses.MULCT)
        {
            time_circle.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.circle_red));
        }
        else if(status == SettingStatuses.NEUTRAL)
        {
            time_circle.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.circle_orange));
        }
        else if(status == SettingStatuses.REWARD)
        {
            time_circle.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.circle_green));
        }
        else if(status == SettingStatuses.NOT_INTEREST)
        {

        }
        else
        {

        }
    }
    private void endAllGameTime()
    {
        time_game.clearAnimation();
        time_game.setVisibility(View.INVISIBLE);
        time_is_over.setVisibility(View.VISIBLE);
        if(gameSettings.statuses.timeGameStatus == SettingStatuses.END_GAME)
        {
            endWinGame(false);
        }
    }

    private boolean checkWin(Offer offer)
    {
        return offer.bulls == secret.getLenght();
    }
    private boolean checkLoseAttemptsNoLeft(int offersCount)
    {
        return attemptsLeftNumber == offersCount;
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
        resultGame.amount_offers = offersCount;
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