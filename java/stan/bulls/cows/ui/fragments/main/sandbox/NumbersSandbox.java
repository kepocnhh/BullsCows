package stan.bulls.cows.ui.fragments.main.sandbox;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import stan.bulls.cows.R;
import stan.bulls.cows.listeners.fragments.main.sandbox.INumbersSandboxFragmentListener;
import stan.bulls.cows.ui.fragments.StanFragment;

public class NumbersSandbox
        extends StanFragment
{
    //___________________VIEWS
    private TextView game_count_value;
    private SeekBar game_count_seek;

    //_______________FIELDS
    private int countMax = 6;
    private int countMin = 3;
    private int count;

    static public NumbersSandbox newInstance(INumbersSandboxFragmentListener l)
    {
        NumbersSandbox fragment = new NumbersSandbox();
        fragment.listener = l;
        return fragment;
    }

    public NumbersSandbox()
    {
        super(R.layout.numbers_sandbox, R.string.NumbersSandbox);
    }

    @Override
    protected void findViews(View v)
    {
        super.findViews(v);
        game_count_value = (TextView) v.findViewById(R.id.game_count_value);
        game_count_seek = (SeekBar) v.findViewById(R.id.game_count_seek);
        game_count_seek.setMax(countMax-countMin);
        game_count_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                setCount(i+countMin);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
        v.findViewById(R.id.begin_game).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getListener().beginNumbersEasyGame(count);
            }
        });
        init();
    }

    private void setCount(int c)
    {
        if(c >= countMin && c <= countMax)
        {
            count = c;
            game_count_value.setText(""+count);
        }
    }

    private void init()
    {
        count = 3;
        game_count_value.setText(""+count);
    }

    private INumbersSandboxFragmentListener getListener()
    {
        return (INumbersSandboxFragmentListener) listener;
    }
}