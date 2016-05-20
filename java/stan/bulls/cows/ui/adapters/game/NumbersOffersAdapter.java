package stan.bulls.cows.ui.adapters.game;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;

import stan.bulls.cows.R;
import stan.bulls.cows.db.Tables;
import stan.bulls.cows.helpers.TimeHelper;
import stan.bulls.cows.ui.holders.adapters.game.numbers.NumbersOfferFirstHolder;
import stan.bulls.cows.ui.holders.adapters.game.numbers.NumbersOfferHolder;

public class NumbersOffersAdapter
        extends OffersAdapter
{
    private int animLast;

    public NumbersOffersAdapter(Context context)
    {
        super(context, R.layout.numbers_offer_list_item_light);
        animLast = -1;
    }

    @Override
    protected RecyclerView.ViewHolder getOfferFirstHolder(View v)
    {
        return new NumbersOfferFirstHolder(v);
    }
    @Override
    protected RecyclerView.ViewHolder initHolder(View v)
    {
        return new NumbersOfferHolder(v);
    }
    @Override
    protected int getOfferFirstLayoutID()
    {
        return R.layout.numbers_offer_first_list_item_light;
    }

    @Override
    protected int getOffersFooterLayoutID()
    {
        return R.layout.numbers_offers_list_footer_light;
    }

    @Override
    protected void initView(RecyclerView.ViewHolder h, int i)
    {
        if (i == 0)
        {
            initOfferFirst(getFirstHolder(h));
        }
        else
        {
            initOffer(getHolder(h), i);
        }
    }
    private void initOfferFirst(NumbersOfferFirstHolder holder)
    {
        holder.offer_value.setText(getString(Tables.GameTemp.Columns.offer_value));
        holder.offer_bulls.setText(getString(Tables.GameTemp.Columns.offer_bulls));
        holder.offer_cows.setText(getString(Tables.GameTemp.Columns.offer_cows));
        if(animLast < 0)
        {
            holder.bulls_frame.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.bulls_frame_anim));
            holder.cows_frame.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.cows_frame_anim));
            animLast = 0;
        }
    }
    private void initOffer(NumbersOfferHolder holder, int position)
    {
//        holder.time_spend.setVisibility(View.VISIBLE);
//        String time_spend = getString(Tables.GameTemp.Columns.time_spend);
        int time_spend = mCursor.getInt(mCursor.getColumnIndex(Tables.GameTemp.Columns.time_spend));
        if(time_spend == 1)
        {
            holder.time_icon.setBackgroundColor(mContext.getResources().getColor(R.color.green));
        }
        else if(time_spend == 2)
        {
            holder.time_icon.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
        }
        else if(time_spend == 3)
        {
            holder.time_icon.setBackgroundColor(mContext.getResources().getColor(R.color.red));
        }
        if(mCursor.getInt(mCursor.getColumnIndex(Tables.GameTemp.Columns.quality)) == 1)
        {
            holder.quality.setVisibility(View.GONE);
        }
        else
        {
            holder.quality.setVisibility(View.VISIBLE);
        }
        holder.offer_value.setText(getString(Tables.GameTemp.Columns.offer_value));
        holder.offer_bulls.setText(getString(Tables.GameTemp.Columns.offer_bulls));
        holder.offer_cows.setText(getString(Tables.GameTemp.Columns.offer_cows));
        int itemCount = getItemCount() - 1;
        if(itemCount > position && animLast < position)
        {
            holder.bulls_frame.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.bulls_frame_anim));
            holder.cows_frame.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.cows_frame_anim));
            holder.time_icon.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.time_icon_anim));
            animLast = position;
        }
    }

    NumbersOfferHolder getHolder(RecyclerView.ViewHolder holder)
    {
        return (NumbersOfferHolder) holder;
    }
    NumbersOfferFirstHolder getFirstHolder(RecyclerView.ViewHolder holder)
    {
        return (NumbersOfferFirstHolder) holder;
    }
}