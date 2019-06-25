package comjonathanvanwin.github.wizoprojecthailey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new Fragment[]{new WelcomeFragment(),new DemoFragment(), new DemoFragment()};
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0) return fragments[position++];
        Fragment demoFragment = fragments[position++];
        Bundle bundle = new Bundle();
        bundle.putString("message", "Fragment :" + position);
        demoFragment.setArguments(bundle);
        return demoFragment;
    }

    // Num of tabs
    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        position++;
        return "Fragment " + position;
    }
}
