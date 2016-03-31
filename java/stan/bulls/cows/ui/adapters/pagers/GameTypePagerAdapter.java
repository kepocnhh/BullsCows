package stan.bulls.cows.ui.adapters.pagers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class GameTypePagerAdapter
        extends FragmentPagerAdapter
{
    private List<Fragment> fragments;

    public GameTypePagerAdapter(FragmentManager fm, List<Fragment> fs)
    {
        super(fm);
        this.fragments = fs;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }
}