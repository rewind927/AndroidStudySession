package com.karen.processbutton.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.karen.processbutton.ActionProcessButton;
import com.karen.processbutton.R;
import com.karen.processbutton.util.ProgressGenerator;

public class ProcessButtonActivity extends ActionBarActivity implements ProgressGenerator.OnCompleteListener {

	private ActionProcessButton buttonSignIn;
	private EditText textEmail;
	private EditText textPassword;
	private ProgressGenerator progressGenerator;
	private View.OnClickListener buttonSignInClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			progressGenerator.start(buttonSignIn);
			buttonSignIn.setEnabled(false);
			textEmail.setEnabled(false);
			textPassword.setEnabled(false);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process_button);
		textEmail = (EditText) findViewById(R.id.text_email);
		textPassword = (EditText) findViewById(R.id.text_password);
		buttonSignIn = (ActionProcessButton) findViewById(R.id.button_sign_in);
		buttonSignIn.setMode(ActionProcessButton.Mode.PROGRESS);
		progressGenerator = new ProgressGenerator(this);
		buttonSignIn.setOnClickListener(buttonSignInClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_process_button, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.progress_mode) {
			refreshUI(false);
			return true;
		} else if (id == R.id.endless_mode) {
			refreshUI(true);
			return true;
		} else if (id == R.id.practice_mode) {
			refreshUI(true);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onComplete() {
		Toast.makeText(this, R.string.loading_Complete, Toast.LENGTH_LONG).show();
	}

	private void refreshUI(Boolean isEndlessMode) {
		if (!isEndlessMode) {
			buttonSignIn.setMode(ActionProcessButton.Mode.PROGRESS);
		} else {
			buttonSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
		}
		buttonSignIn.setProgress(0);
		buttonSignIn.setEnabled(true);
		textEmail.setEnabled(true);
		textPassword.setEnabled(true);
		progressGenerator = new ProgressGenerator(this);
	}
}
