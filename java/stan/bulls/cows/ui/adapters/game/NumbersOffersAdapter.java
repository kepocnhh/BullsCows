package stan.bulls.cows.ui.adapters.game;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import stan.bulls.cows.R;
import stan.bulls.cows.db.Tables;
import stan.bulls.cows.helpers.TimeHelper;
import stan.bulls.cows.ui.holders.adapters.game.NumbersOfferHolder;

public class NumbersOffersAdapter
        extends OffersAdapter
{
    public NumbersOffersAdapter(Context context)
    {
        super(context, R.layout.numbers_offer_list_item);
    }

    @Override
    protected RecyclerView.ViewHolder initHolder(View v)
    {
        return new NumbersOfferHolder(v);
    }

    @Override
    protected void initView(RecyclerView.ViewHolder h, int i)
    {
        if (i == 0)
        {
            getHolder(h).time_spend.setVisibility(View.INVISIBLE);
        }
        else
        {
            getHolder(h).time_spend.setVisibility(View.VISIBLE);
            String time_spend = getString(Tables.GameTemp.Columns.time_spend);
            long milliseconds = Long.parseLong(time_spend);
            getHolder(h).time_spend.setText(TimeHelper.getSecondsStringWithSec(mContext, milliseconds));
        }
        getHolder(h).offer_value.setText(getString(Tables.GameTemp.Columns.offer_value));
        getHolder(h).offer_bulls.setText(getString(Tables.GameTemp.Columns.offer_bulls));
        getHolder(h).offer_cows.setText(getString(Tables.GameTemp.Columns.offer_cows));
    }

    NumbersOfferHolder getHolder(RecyclerView.ViewHolder holder)
    {
        return (NumbersOfferHolder) holder;
    }
}