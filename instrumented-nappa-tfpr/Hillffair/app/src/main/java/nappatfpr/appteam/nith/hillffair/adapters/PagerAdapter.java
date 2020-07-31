package nappatfpr.appteam.nith.hillffair.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Aditya on 9/13/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    private ArrayList<String>   titleArrayList=new ArrayList<>();
    int tabCount;

    public PagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList, ArrayList<String> titleArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
        this.titleArrayList = titleArrayList;
    }

    public PagerAdapter(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position){
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount(){
        return fragmentArrayList==null?tabCount:fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleArrayList.get(position);
    }
}
