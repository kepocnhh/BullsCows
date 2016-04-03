package stan.bulls.cows.ui.adapters.game;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stan.bulls.cows.R;
import stan.bulls.cows.db.Tables;
import stan.bulls.cows.ui.adapters.StanRecyclerAdapter;
import stan.bulls.cows.ui.holders.adapters.game.OfferHolder;
import stan.bulls.cows.ui.holders.adapters.game.OffersFooterHolder;

public abstract class OffersAdapter
        extends StanRecyclerAdapter
{
    static public final int FOOTER_ID = 1603311710;
    static public final int FOOTER_TYPE = 0;
    static public final int OFFER_TYPE = 1;

    public OffersAdapter(Context context, int l)
    {
        super(context, l);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(viewType == FOOTER_TYPE)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View v = inflater.inflate(R.layout.offers_list_footer, parent, false);
            return initFooterHolder(v);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    private RecyclerView.ViewHolder initFooterHolder(View v)
    {
        return new OffersFooterHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i)
    {
        if(mCursor.moveToPosition(i))
        {
            initView(holder, i);
        }
    }

    @Override
    public long getItemId(int position)
    {
        if(getItemCount()>1 && position == getItemCount()-1)
        {
            return FOOTER_ID;
        }
        else
        {
            return super.getItemId(position);
        }
    }

    @Override
    public int getItemCount()
    {
        if(mCursor == null)
        {
            return 0;
        }
        else if(mCursor.getCount() == 1)
        {
            return mCursor.getCount();
        }
        else
        {
            return mCursor.getCount() + 1;
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        if(getItemCount()>1 && position == getItemCount()-1)
        {
            return FOOTER_TYPE;
        }
        return OFFER_TYPE;
    }
}