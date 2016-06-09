package stan.bulls.cows.ui.dialogs.game;

import android.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import stan.bulls.cows.R;
import stan.bulls.cows.core.game.ResultGame;
import stan.bulls.cows.helpers.TimeHelper;
import stan.bulls.cows.listeners.dialogs.game.IResultGameDialogListener;
import stan.bulls.cows.ui.dialogs.StanDialog;

public class ResultGameDialog
        extends StanDialog
{
    static public ResultGameDialog createNumbersAddOfferDialog(IResultGameDialogListener listener, ResultGame resultGame)
    {
        ResultGameDialog dialog = new ResultGameDialog();
        dialog.setCancelable(false);
        dialog.init(listener);
        dialog.resultGame = resultGame;
        return dialog;
    }

    //_______________VIEWS
    TextView amount_offers;
    TextView time_spend;
    TextView gold_earned;

    //_______________FIELDS
    private ResultGame resultGame;

    public ResultGameDialog()
    {
        super(R.layout.result_game_dialog_cardview, ResultGameDialog.class.getName());
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.StanDialog);
    }

    @Override
    protected void realize(View v)
    {
        v.findViewById(R.id.result_ok).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getListener().ok(ResultGameDialog.this);
            }
        });
        gold_earned = (TextView) v.findViewById(R.id.gold_earned);
        gold_earned.setText(resultGame.gold_earned + "");
        amount_offers = (TextView) v.findViewById(R.id.amount_offers);
        time_spend = (TextView) v.findViewById(R.id.time_spend);
        amount_offers.setText(resultGame.amount_offers + "");
        time_spend.setText(TimeHelper.getSecondsString(resultGame.time_spend));
    }

    private IResultGameDialogListener getListener()
    {
        return (IResultGameDialogListener) listener;
    }
}