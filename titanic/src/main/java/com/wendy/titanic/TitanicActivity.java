package com.wendy.titanic;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.romainpiel.titanic.library.Titanic;
import com.romainpiel.titanic.library.TitanicTextView;

public class TitanicActivity extends ActionBarActivity {
	Titanic titanic = new Titanic();
	TitanicTextView lableLoading;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_titanic);
		lableLoading = (TitanicTextView) findViewById(R.id.my_text_view);

		// set fancy typeface
		lableLoading.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

		// start animation
		titanic.start(lableLoading);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_titanic, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.demo_mode) {
			titanic.cancel();
			lableLoading.setStyle(TitanicTextView.DrawableStyle.WAVE);
			titanic.start(lableLoading);
			return true;
		} else if (id == R.id.practice_mode) {
			titanic.cancel();
			lableLoading.setStyle(TitanicTextView.DrawableStyle.PRACTICE);
			titanic.start(lableLoading);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
