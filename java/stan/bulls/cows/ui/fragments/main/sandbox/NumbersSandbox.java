package stan.bulls.cows.ui.fragments.main.sandbox;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import stan.bulls.cows.R;
import stan.bulls.cows.core.game.difficults.NumbersDifficults;
import stan.bulls.cows.listeners.fragments.main.sandbox.INumbersSandboxFragmentListener;
import stan.bulls.cows.ui.fragments.StanFragment;

public class NumbersSandbox
        extends StanFragment
{
    //___________________VIEWS
    private TextView game_count_value;
    private SeekBar game_count_seek;
    private View difficult_easy;
    private View difficult_medium;
    private View difficult_hard;
    private View difficult_easy_cardview;
    private View difficult_medium_cardview;
    private View difficult_hard_cardview;
    private TextView game_max_amount_text;

    //_______________FIELDS
    private int countMax = 6;
    private int countMin = 3;
    private int count;
    private int amountDifficult;
    View.OnClickListener difficultsClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.difficult_easy:
                    setDifficultEasy();
                    break;
                case R.id.difficult_medium:
                    setDifficultMedium();
                    break;
                case R.id.difficult_hard:
                    setDifficultHard();
                    break;
            }
        }
    };

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
        difficult_easy = v.findViewById(R.id.difficult_easy);
        difficult_medium = v.findViewById(R.id.difficult_medium);
        difficult_hard = v.findViewById(R.id.difficult_hard);
        difficult_easy.setOnClickListener(difficultsClickListener);
        difficult_medium.setOnClickListener(difficultsClickListener);
        difficult_hard.setOnClickListener(difficultsClickListener);
        difficult_easy_cardview = v.findViewById(R.id.difficult_easy_cardview);
        difficult_medium_cardview = v.findViewById(R.id.difficult_medium_cardview);
        difficult_hard_cardview = v.findViewById(R.id.difficult_hard_cardview);
        game_max_amount_text = (TextView) v.findViewById(R.id.game_max_amount_text);
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
                if(amountDifficult == NumbersDifficults.AMOUNT_DIFFICULT_EASY)
                {
                    getListener().beginNumbersEasyGame(count);
                }
                else if(amountDifficult == NumbersDifficults.AMOUNT_DIFFICULT_MEDIUM)
                {
                    getListener().beginNumbersMediumGame(count);
                }
                else if(amountDifficult == NumbersDifficults.AMOUNT_DIFFICULT_HARD)
                {
                    getListener().beginNumbersHardGame(count);
                }
            }
        });
        init();
    }

    private void setDifficultEasy()
    {
        amountDifficult = NumbersDifficults.AMOUNT_DIFFICULT_EASY;
        difficult_easy.setVisibility(View.GONE);
        difficult_medium.setVisibility(View.VISIBLE);
        difficult_hard.setVisibility(View.VISIBLE);
        difficult_easy_cardview.setVisibility(View.VISIBLE);
        difficult_medium_cardview.setVisibility(View.GONE);
        difficult_hard_cardview.setVisibility(View.GONE);
        game_max_amount_text.setText(NumbersDifficults.AMOUNT_DIFFICULT_EASY+"");
    }
    private void setDifficultMedium()
    {
        amountDifficult = NumbersDifficults.AMOUNT_DIFFICULT_MEDIUM;
        difficult_easy.setVisibility(View.VISIBLE);
        difficult_medium.setVisibility(View.GONE);
        difficult_hard.setVisibility(View.VISIBLE);
        difficult_easy_cardview.setVisibility(View.GONE);
        difficult_medium_cardview.setVisibility(View.VISIBLE);
        difficult_hard_cardview.setVisibility(View.GONE);
        game_max_amount_text.setText(NumbersDifficults.AMOUNT_DIFFICULT_MEDIUM + "");
    }
    private void setDifficultHard()
    {
        amountDifficult = NumbersDifficults.AMOUNT_DIFFICULT_HARD;
        difficult_easy.setVisibility(View.VISIBLE);
        difficult_medium.setVisibility(View.VISIBLE);
        difficult_hard.setVisibility(View.GONE);
        difficult_easy_cardview.setVisibility(View.GONE);
        difficult_medium_cardview.setVisibility(View.GONE);
        difficult_hard_cardview.setVisibility(View.VISIBLE);
        game_max_amount_text.setText(NumbersDifficults.AMOUNT_DIFFICULT_HARD + "");
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
        setCount(countMin);
        setDifficultEasy();
    }

    private INumbersSandboxFragmentListener getListener()
    {
        return (INumbersSandboxFragmentListener) listener;
    }
}