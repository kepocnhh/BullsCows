package stan.bulls.cows.listeners.fragments.main;

import stan.bulls.cows.listeners.fragments.IStanFragmentListener;

public interface ISandboxFragmentListener
        extends IStanFragmentListener
{
    void beginNumbersEasyGame(int count);
    void beginNumbersMediumGame(int count);
    void beginNumbersHardGame(int count);
}