package com.fakechat.practice.service.message.practice.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fakechat.practice.service.message.practice.fragment.BaseFragment;

import java.util.List;

/**
 * Created by FungWah on 2018/2/28.
 */

public class FrameworkMainAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> list;

    public FrameworkMainAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
