package com.ascii.phoenix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class PhoenixActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phoenix);
	}

	public void onDemoButtonClicked(View view) {
		startActivity(new Intent(this, PhoenixDemoActivity.class));
	}

	public void onPracticeButtonClicked(View view) {
		startActivity(new Intent(this, PhoenixPracticeActivity.class));
	}
}
