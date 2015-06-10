package com.daimajia.numberprogressbar.example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;

import java.util.Timer;
import java.util.TimerTask;

public class NumberProgressBarActivity extends ActionBarActivity implements OnProgressBarListener {
	private Timer timer;

	private NumberProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numberprogressbar);

		progressBar = (NumberProgressBar) findViewById(R.id.numberbar1);
		progressBar.setOnProgressBarListener(this);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						progressBar.incrementProgressBy(1);
					}
				});
			}
		}, 1000, 100);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		timer.cancel();
	}

	@Override
	public void onProgressChange(int current, int max) {
		if (current == max) {
			Toast.makeText(getApplicationContext(), getString(R.string.finish), Toast.LENGTH_SHORT).show();
			progressBar.setProgress(0);
		}
	}
}
