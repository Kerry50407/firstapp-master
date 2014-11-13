package com.example.firstapp;

import com.example.firstapp.fragment.InputFragment;
import com.example.firstapp.fragment.MessageFragment;
import com.example.firstapp.fragment.PhotoFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new InputFragment();
		case 1:
			return new MessageFragment();
		case 2:
			return new PhotoFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
