package stan.bulls.cows.ui.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stan.bulls.cows.listeners.dialogs.IStanDialogListener;

public abstract class StanDialog
        extends DialogFragment
{
    protected IStanDialogListener listener;

    int lay;

    public StanDialog(int l)
    {
        this.lay = l;
    }

    public DialogFragment init(IStanDialogListener cL)
    {
        this.listener = cL;
        return this;
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(null);
        listener.onDismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(this.lay, null);
        realize(v);
        return v;
    }

    protected abstract void realize(View v);
}