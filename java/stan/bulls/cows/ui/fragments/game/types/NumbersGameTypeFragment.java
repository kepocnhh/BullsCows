package stan.bulls.cows.ui.fragments.game.types;

import android.os.Bundle;
import android.view.View;

import stan.bulls.cows.R;

public class NumbersGameTypeFragment
    extends GameTypeFragment
{
    static public NumbersGameTypeFragment newInstance()
    {
        NumbersGameTypeFragment fragment = new NumbersGameTypeFragment();
        Bundle bundle = fragment.getArguments();
        fragment.setArguments(bundle);
        return fragment;
    }

    public NumbersGameTypeFragment()
    {
        super(R.layout.numbers_game_type_fragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
    }
}