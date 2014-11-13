package com.example.firstapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;

public class MainActivity extends FragmentActivity {

	private ViewPager viewPager1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	    Parse.initialize(this, "pTm5TEfLsTKhtn0mxEly1oIpa5lZgDczNmum8PY3", "9W4L6TXeaSHXuMZnHqwYKiAW987ieCPsRPPlidxl");
	     
	    viewPager1 = (ViewPager) findViewById(R.id.viewPager1);
	    viewPager1.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
	    
//	    InputFragment inputFragment = new InputFragment();
//	    FragmentTransaction ft = getFragmentManager().beginTransaction();
//	    ft.add(R.id.container, inputFragment);
//	    ft.commit();


	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
