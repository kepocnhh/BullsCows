package stan.bulls.cows.listeners.dialogs.game;

import android.support.v4.app.DialogFragment;

import stan.bulls.cows.listeners.dialogs.IStanDialogListener;

public interface IResultGameDialogListener
        extends IStanDialogListener
{
    void ok(DialogFragment dialogFragment);
}