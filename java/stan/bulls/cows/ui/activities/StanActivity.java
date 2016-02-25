package stan.bulls.cows.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import stan.bulls.cows.ui.fragments.FragmentTransactionPattern;


public abstract class StanActivity
        extends AppCompatActivity
{
    //__________FRAGMENTS
    private FragmentTransactionPattern fTP;
    private int contentView;
    private int frameView;

    public StanActivity(int content, int frame)
    {
        initActivity(content, frame);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(contentView);
        fTP = new FragmentTransactionPattern(this, frameView);
        //
        initViews();
        init();
    }

    private void initActivity(int content, int frame)
    {
        this.contentView = content;
        this.frameView = frame;
    }

    public void addFragment(Fragment f)
    {
        fTP.add(f);
    }
    public void replaceFragment(Fragment f)
    {
        fTP.replace(f);
    }

    protected abstract void initViews();

    protected abstract void init();
}