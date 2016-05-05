package stan.bulls.cows.ui.holders.adapters.game.numbers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import stan.bulls.cows.R;

public class NumbersOfferHolder
        extends RecyclerView.ViewHolder
{
//    public TextView time_spend;
    public FrameLayout clock_frame;
//    public ImageView time_icon;
//    public FrameLayout quality_frame;
//    public ImageView quality_icon;
//    public View quality_frame;
//    public View quality_good_icon;
    public View quality_good_frame;
    public View quality_bad_frame;
    public TextView offer_value;
    public TextView offer_bulls;
    public TextView offer_cows;

    public NumbersOfferHolder(View v)
    {
        super(v);
//        time_spend = (TextView)v.findViewById(R.id.time_spend);
//        time_icon = (ImageView)v.findViewById(R.id.time_icon);
        clock_frame = (FrameLayout)v.findViewById(R.id.clock_frame);
//        quality_frame = (FrameLayout)v.findViewById(R.id.quality_frame);
//        quality_icon = (ImageView)v.findViewById(R.id.quality);
//        quality_frame = v.findViewById(R.id.quality_frame);
//        quality_good_icon = v.findViewById(R.id.quality_good_icon);
        quality_good_frame = v.findViewById(R.id.quality_good_frame);
        quality_bad_frame = v.findViewById(R.id.quality_bad_frame);
        offer_value = (TextView)v.findViewById(R.id.offer_value);
        offer_bulls = (TextView)v.findViewById(R.id.offer_bulls);
        offer_cows = (TextView)v.findViewById(R.id.offer_cows);
    }
}