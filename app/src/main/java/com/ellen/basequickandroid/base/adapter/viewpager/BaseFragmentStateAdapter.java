package com.ellen.basequickandroid.base.adapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public abstract class BaseFragmentStateAdapter extends FragmentStatePagerAdapter {

    public BaseFragmentStateAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    @Override
    public int getCount() {
        return getFragmentPagerSize();
    }

    protected abstract int getFragmentPagerSize();
    protected abstract Fragment getFragment(int position);
}
