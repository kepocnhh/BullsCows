package stan.bulls.cows.listeners.dialogs.game;

import stan.bulls.cows.listeners.dialogs.IStanDialogListener;

public interface IGameDialogListener
    extends IStanDialogListener
{
    void addOffer(String value);
}