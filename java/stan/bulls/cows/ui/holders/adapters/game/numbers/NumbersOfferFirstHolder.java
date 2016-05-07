package stan.bulls.cows.ui.holders.adapters.game.numbers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import stan.bulls.cows.R;

public class NumbersOfferFirstHolder
        extends RecyclerView.ViewHolder
{
    public TextView offer_value;
    public TextView offer_bulls;
    public TextView offer_cows;
    public View bulls_frame;
    public View cows_frame;

    public NumbersOfferFirstHolder(View v)
    {
        super(v);
        offer_value = (TextView)v.findViewById(R.id.offer_value);
        offer_bulls = (TextView)v.findViewById(R.id.offer_bulls);
        offer_cows = (TextView)v.findViewById(R.id.offer_cows);
        bulls_frame = v.findViewById(R.id.bulls_frame);
        cows_frame = v.findViewById(R.id.cows_frame);
    }
}