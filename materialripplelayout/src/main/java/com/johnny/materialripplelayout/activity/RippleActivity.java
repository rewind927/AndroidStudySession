package com.johnny.materialripplelayout.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.johnny.materialripplelayout.LockableScrollView;
import com.johnny.materialripplelayout.R;

public class RippleActivity extends ActionBarActivity {

	private LockableScrollView scrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ripple);
		getSupportActionBar().setTitle("Material Ripple Layout");

		scrollView = (LockableScrollView) findViewById(R.id.scrollView);
		scrollView.setScrollingEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_ripple, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		if (scrollView.isScrollable()) {
			menu.removeItem(R.id.action_unlock);
		} else {
			menu.removeItem(R.id.action_lock);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_lock) {
			scrollView.setScrollingEnabled(false); // disable scrolling
			invalidateOptionsMenu();
			return true;
		} else if (id == R.id.action_unlock) {
			scrollView.setScrollingEnabled(true); // enable scrolling
			invalidateOptionsMenu();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
