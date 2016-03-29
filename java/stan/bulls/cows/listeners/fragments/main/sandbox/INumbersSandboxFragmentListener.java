package stan.bulls.cows.listeners.fragments.main.sandbox;

import stan.bulls.cows.listeners.fragments.IStanFragmentListener;

public interface INumbersSandboxFragmentListener
        extends IStanFragmentListener
{
    void beginNumbersEasyGame(int count);
    void beginNumbersMediumGame(int count);
    void beginNumbersHardGame(int count);
}