package stan.bulls.cows.listeners.fragments.game;

import stan.bulls.cows.core.game.ResultGame;
import stan.bulls.cows.listeners.fragments.IStanFragmentListener;

public interface IGameFragmentListener
        extends IStanFragmentListener
{
    void result(ResultGame resultGame);
}