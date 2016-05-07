package stan.bulls.cows.ui.holders.adapters.game.numbers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import stan.bulls.cows.R;

public class NumbersOfferHolder
        extends RecyclerView.ViewHolder
{
    public ImageView time_icon;
    public View quality;
    public TextView offer_value;
    public TextView offer_bulls;
    public TextView offer_cows;
    public View bulls_frame;
    public View cows_frame;

    public NumbersOfferHolder(View v)
    {
        super(v);
        time_icon = (ImageView)v.findViewById(R.id.time_icon);
        quality = v.findViewById(R.id.quality);
        offer_value = (TextView)v.findViewById(R.id.offer_value);
        offer_bulls = (TextView)v.findViewById(R.id.offer_bulls);
        offer_cows = (TextView)v.findViewById(R.id.offer_cows);
        bulls_frame = v.findViewById(R.id.bulls_frame);
        cows_frame = v.findViewById(R.id.cows_frame);
    }
}