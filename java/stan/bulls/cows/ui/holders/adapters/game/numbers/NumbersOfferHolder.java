package stan.bulls.cows.ui.holders.adapters.game.numbers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import stan.bulls.cows.R;

public class NumbersOfferHolder
        extends RecyclerView.ViewHolder
{
    public FrameLayout clock_frame;
    public View quality;
    public TextView offer_value;
    public TextView offer_bulls;
    public TextView offer_cows;

    public NumbersOfferHolder(View v)
    {
        super(v);
        clock_frame = (FrameLayout)v.findViewById(R.id.clock_frame);
        quality = v.findViewById(R.id.quality);
        offer_value = (TextView)v.findViewById(R.id.offer_value);
        offer_bulls = (TextView)v.findViewById(R.id.offer_bulls);
        offer_cows = (TextView)v.findViewById(R.id.offer_cows);
    }
}