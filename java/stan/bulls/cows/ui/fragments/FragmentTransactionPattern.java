package stan.bulls.cows.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class FragmentTransactionPattern
{
    FragmentManager fragmentManager;
    int ID;

    public FragmentTransactionPattern(AppCompatActivity act, int id)
    {
        fragmentManager = act.getSupportFragmentManager();
        this.ID = id;
    }

    public void add(Fragment f)
    {
        add(f, this.ID);
    }
    public void add(Fragment f, int id)
    {
        if(fragmentManager.findFragmentById(id) == null)
        {
            fragmentManager.beginTransaction().add(id, f).commit();
        }
    }
    public void replace(Fragment f)
    {
        fragmentManager.beginTransaction().replace(ID, f).commit();
    }
}