package stan.bulls.cows.ui.adapters.game;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import stan.bulls.cows.R;
import stan.bulls.cows.db.Tables;
import stan.bulls.cows.helpers.TimeHelper;
import stan.bulls.cows.ui.holders.adapters.game.numbers.NumbersOfferFirstHolder;
import stan.bulls.cows.ui.holders.adapters.game.numbers.NumbersOfferHolder;

public class NumbersOffersAdapter
        extends OffersAdapter
{
    public NumbersOffersAdapter(Context context)
    {
        super(context, R.layout.numbers_offer_list_item);
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
        return R.layout.numbers_offer_first_list_item;
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
            initOffer(getHolder(h));
        }
    }
    private void initOfferFirst(NumbersOfferFirstHolder holder)
    {
        holder.offer_value.setText(getString(Tables.GameTemp.Columns.offer_value));
        holder.offer_bulls.setText(getString(Tables.GameTemp.Columns.offer_bulls));
        holder.offer_cows.setText(getString(Tables.GameTemp.Columns.offer_cows));
    }
    private void initOffer(NumbersOfferHolder holder)
    {
//        holder.time_spend.setVisibility(View.VISIBLE);
//        String time_spend = getString(Tables.GameTemp.Columns.time_spend);
        int time_spend = mCursor.getInt(mCursor.getColumnIndex(Tables.GameTemp.Columns.time_spend));
        holder.clock_frame.setVisibility(View.VISIBLE);
        if(time_spend == 0)
        {
            holder.clock_frame.setVisibility(View.INVISIBLE);
        }
        else if(time_spend == 1)
        {
            holder.clock_frame.setBackgroundColor(mContext.getResources().getColor(R.color.green));
        }
        else if(time_spend == 2)
        {
            holder.clock_frame.setBackgroundColor(mContext.getResources().getColor(R.color.orange));
        }
        else if(time_spend == 3)
        {
            holder.clock_frame.setBackgroundColor(mContext.getResources().getColor(R.color.red));
        }
        if(mCursor.getInt(mCursor.getColumnIndex(Tables.GameTemp.Columns.quality)) == 1)
        {
            holder.quality_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.correct_white));
            holder.quality_frame.setBackgroundColor(mContext.getResources().getColor(R.color.green));
        }
        else
        {
            holder.quality_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.quality_white));
            holder.quality_frame.setBackgroundColor(mContext.getResources().getColor(R.color.red));
        }
        holder.offer_value.setText(getString(Tables.GameTemp.Columns.offer_value));
        holder.offer_bulls.setText(getString(Tables.GameTemp.Columns.offer_bulls));
        holder.offer_cows.setText(getString(Tables.GameTemp.Columns.offer_cows));
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