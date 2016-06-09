package stan.bulls.cows.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import stan.bulls.cows.R;
import stan.bulls.cows.core.game.ResultGame;
import stan.bulls.cows.core.game.difficults.NumbersDifficults;
import stan.bulls.cows.db.ContentDriver;
import stan.bulls.cows.db.SQliteApi;
import stan.bulls.cows.db.Tables;
import stan.bulls.cows.listeners.dialogs.game.IResultGameDialogListener;
import stan.bulls.cows.listeners.fragments.game.INumbersListener;
import stan.bulls.cows.ui.dialogs.game.ResultGameDialog;
import stan.bulls.cows.ui.fragments.game.Numbers;

public class Game
        extends StanActivity
        implements INumbersListener
{
    static public final int GAME_REQUEST_CODE = 11;
    static public final int GAME_RESULT_ERROR_BADGAMETYPE_CODE = -1;
    static public final int GAME_RESULT_ERROR_BADNUMBERSDIFFICULTS_CODE = -1;

    static public final String TYPE_GAME_KEY = "stan.bulls.cows.ui.activities.Game.type_game_key";
    static public final int GAME_NUMBERS = 0;

    static public void startForNumbersEasy(Activity activity, int count)
    {
        Game.startForNumbers(activity, count, NumbersDifficults.AMOUNT_DIFFICULT_EASY);
    }
    static public void startForNumbersMedium(Activity activity, int count)
    {
        Game.startForNumbers(activity, count, NumbersDifficults.AMOUNT_DIFFICULT_MEDIUM);
    }
    static public void startForNumbersHard(Activity activity, int count)
    {
        Game.startForNumbers(activity, count, NumbersDifficults.AMOUNT_DIFFICULT_HARD);
    }
    static private void startForNumbers(Activity activity, int count, int amount)
    {
        Intent i = new Intent(activity, Game.class);
        i.putExtra(Game.TYPE_GAME_KEY, GAME_NUMBERS);
        i.putExtra(Numbers.COUNT_KEY, count);
        i.putExtra(Numbers.AMOUNT_KEY, amount);
        activity.startActivityForResult(i, GAME_REQUEST_CODE);
    }

    //_______________VIEWS

    //_______________FRAGMENTS

    //_______________FIELDS

    public Game()
    {
        super(R.layout.game, R.id.game_frame);
    }

    @Override
    protected void initViews()
    {

    }

    @Override
    protected void init()
    {
        int type_game = getIntent().getIntExtra(Game.TYPE_GAME_KEY, -1);
        if(type_game == GAME_NUMBERS)
        {
            int count = getIntent().getIntExtra(Numbers.COUNT_KEY, -1);
            int amount = getIntent().getIntExtra(Numbers.AMOUNT_KEY, -1);
            if(amount == NumbersDifficults.AMOUNT_DIFFICULT_EASY || amount == NumbersDifficults.AMOUNT_DIFFICULT_MEDIUM || amount == NumbersDifficults.AMOUNT_DIFFICULT_HARD)
            {
                addFragment(Numbers.newInstance(count, amount, this));
            }
            else
            {
                setResult(GAME_RESULT_ERROR_BADNUMBERSDIFFICULTS_CODE);
                finish();
            }
        } else
        {
            setResult(GAME_RESULT_ERROR_BADGAMETYPE_CODE);
            finish();
        }
    }

    @Override
    public void result(ResultGame resultGame)
    {
        SQliteApi.insertStatisticsGamesResultGame(ContentDriver.getContentValuesResultGameForStatisticsGames(resultGame));
        Cursor cursor = SQliteApi.getGameFromDate(resultGame.date + "");
        cursor.moveToFirst();
        int gameId = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
        cursor.close();
        SQliteApi.insertGoldEarnedTrans(ContentDriver.getContentValuesGoldEarnedFromGame(resultGame, gameId));
        ResultGameDialog.createNumbersAddOfferDialog(new IResultGameDialogListener()
        {
            @Override
            public void ok(DialogFragment dialogFragment)
            {
                dialogFragment.dismiss();
                finish();
            }
            @Override
            public void onDismiss()
            {

            }
        }, resultGame).show(getSupportFragmentManager());
    }
}