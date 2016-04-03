package stan.bulls.cows.ui.fragments.game.types;

import android.os.Bundle;
import android.view.View;

import stan.bulls.cows.R;

public class WordGameTypeFragment
        extends GameTypeFragment
{
    static public WordGameTypeFragment newInstance()
    {
        WordGameTypeFragment fragment = new WordGameTypeFragment();
        Bundle bundle = fragment.getArguments();
        fragment.setArguments(bundle);
        return fragment;
    }

    public WordGameTypeFragment()
    {
        super(R.layout.word_game_type_fragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
    }
}