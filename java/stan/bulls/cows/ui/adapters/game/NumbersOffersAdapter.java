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
        holder.time_spend.setVisibility(View.VISIBLE);
        String time_spend = getString(Tables.GameTemp.Columns.time_spend);
        long milliseconds = Long.parseLong(time_spend);
        holder.time_spend.setText(TimeHelper.getSecondsStringWithSec(mContext, milliseconds));
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