package stan.bulls.cows.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class StanRecyclerAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    protected Context mContext;
    protected Cursor mCursor;
    private int layout;

    public StanRecyclerAdapter(Context context, int l)
    {
        mContext = context;
        layout = l;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(layout, parent, false);
        return initHolder(v);
    }

    protected abstract RecyclerView.ViewHolder initHolder(View v);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i)
    {
        if(mCursor.moveToPosition(i))
        {
            initView(holder, i);
        }
    }

    protected abstract void initView(RecyclerView.ViewHolder h, int i);

    @Override
    public long getItemId(int position)
    {
        mCursor.moveToPosition(position);
        return mCursor.getInt(mCursor.getColumnIndex(BaseColumns._ID));
    }

    @Override
    public int getItemCount()
    {
        if(mCursor == null)
        {
            return 0;
        } else
        {
            return mCursor.getCount();
        }
    }

    protected String getString(String cn)
    {
        return mCursor.getString(mCursor.getColumnIndex(cn));
    }

    public void swapCursor(Cursor c)
    {
        if(mCursor != null)
        {
            mCursor.close();
        }
        mCursor = c;
        notifyDataSetChanged();
    }
}