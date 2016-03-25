package stan.bulls.cows.ui.dialogs.game.numbers;

import android.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.dialogs.game.IGameDialogListener;
import stan.bulls.cows.ui.dialogs.StanDialog;

public class NumbersAddOfferDialog
    extends StanDialog
{
    static public void showNumbersAddOfferDialog(FragmentManager fragmentManager, IGameDialogListener listener)
    {
        new NumbersAddOfferDialog()
                .init(listener)
                .show(fragmentManager, "NumbersAddOfferDialog");
    }

    //_______________VIEWS

    public NumbersAddOfferDialog()
    {
        super(R.layout.numbers_add_offer_dialog);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.StanDialog);
    }

    @Override
    protected void realize(View v)
    {
        v.findViewById(R.id.add_offer).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getListener().addOffer("123");
            }
        });
    }

    private IGameDialogListener getListener()
    {
        return (IGameDialogListener) listener;
    }
}