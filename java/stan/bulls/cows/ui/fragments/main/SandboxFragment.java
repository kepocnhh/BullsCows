package stan.bulls.cows.ui.fragments.main;

import android.view.View;
import android.widget.TextView;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.main.ISandboxFragmentListener;
import stan.bulls.cows.ui.fragments.StanFragment;

public class SandboxFragment
        extends StanFragment
{

    //___________________VIEWS
    private TextView game_count_value;

    //_______________FIELDS
    private int countMax = 6;
    private int countMin = 3;
    private int count;

    static public SandboxFragment newInstance()
    {
        return new SandboxFragment();
    }

    public SandboxFragment()
    {
        super(R.layout.sandbox, R.string.SandboxFragment);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        game_count_value = (TextView) v.findViewById(R.id.game_count_value);
        v.findViewById(R.id.begin_game).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getListener().beginNumbersEasyGame(count);
            }
        });
        v.findViewById(R.id.game_count_dec).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                countDecrement();
            }
        });
        v.findViewById(R.id.game_count_inc).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                countIncrement();
            }
        });
        init();
    }

    private void countDecrement()
    {
        if(count > countMin)
        {
            count--;
            game_count_value.setText(""+count);
        }
    }

    private void countIncrement()
    {
        if(count < countMax)
        {
            count++;
            game_count_value.setText(""+count);
        }
    }

    private void init()
    {
        count = 3;
        game_count_value.setText(""+count);
    }

    private ISandboxFragmentListener getListener()
    {
        return (ISandboxFragmentListener) clickListener;
    }
}