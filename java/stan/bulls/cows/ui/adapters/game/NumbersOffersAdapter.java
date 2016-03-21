package stan.bulls.cows.ui.adapters.game;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import stan.bulls.cows.R;
import stan.bulls.cows.db.Tables;
import stan.bulls.cows.ui.adapters.StanRecyclerAdapter;
import stan.bulls.cows.ui.holders.adapters.game.NumbersOfferHolder;

public class NumbersOffersAdapter
        extends StanRecyclerAdapter
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
        getHolder(h).offer_value.setText(getString(Tables.GameTemp.offer_value_COLUMN));
        getHolder(h).offer_bulls.setText(getString(Tables.GameTemp.offer_bulls_COLUMN));
        getHolder(h).offer_cows.setText(getString(Tables.GameTemp.offer_cows_COLUMN));
    }

    NumbersOfferHolder getHolder(RecyclerView.ViewHolder holder)
    {
        return (NumbersOfferHolder) holder;
    }
}