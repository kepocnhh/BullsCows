package stan.bulls.cows.ui.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stan.bulls.cows.listeners.dialogs.IStanDialogListener;

public abstract class StanDialog
        extends DialogFragment
{
    protected IStanDialogListener listener;
    private int lay;
    private String dialogTag;

    public String getDialogTag()
    {
        return dialogTag;
    }

    public StanDialog(int l, String tag)
    {
        this.lay = l;
        this.dialogTag = tag;
    }

    public DialogFragment init(IStanDialogListener cL)
    {
        this.listener = cL;
        return this;
    }

    public DialogFragment show(FragmentManager fragmentManager)
    {
        this.show(fragmentManager, getDialogTag());
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