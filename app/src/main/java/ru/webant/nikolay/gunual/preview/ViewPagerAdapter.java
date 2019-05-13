package ru.webant.nikolay.gunual.preview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.webant.gunual.R;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String[] mInformationAboutApplication = {
            "Viewing weapons and their specifications",
//            "Purchasing weapons in the application",
            "Purchasing cartridges to selected weapons"};

    private Integer[] mPageImages = {R.drawable.preview_weapon, R.drawable.preview_buy_ammo};

    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        DemoFragment demoFragment = new DemoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", mInformationAboutApplication[i]);
        bundle.putInt("image", mPageImages[i]);
        i++;
        demoFragment.setArguments(bundle);
        return demoFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}