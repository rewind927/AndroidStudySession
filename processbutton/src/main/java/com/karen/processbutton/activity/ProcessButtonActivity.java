package com.karen.processbutton.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karen.processbutton.ActionProcessButton;
import com.karen.processbutton.PracticeProcessButton;
import com.karen.processbutton.ProcessButton;
import com.karen.processbutton.R;
import com.karen.processbutton.util.ProgressGenerator;

public class ProcessButtonActivity extends ActionBarActivity implements ProgressGenerator.OnCompleteListener {

	private TextView labelMode;
	private ActionProcessButton buttonSignIn;
	private PracticeProcessButton buttonPractice;
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
	private View.OnClickListener buttonLoginClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			progressGenerator.start(buttonPractice);
			buttonPractice.setEnabled(false);
			textEmail.setEnabled(false);
			textPassword.setEnabled(false);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process_button);
		labelMode = (TextView) findViewById(R.id.label_mode);
		textEmail = (EditText) findViewById(R.id.text_email);
		textPassword = (EditText) findViewById(R.id.text_password);

		buttonSignIn = (ActionProcessButton) findViewById(R.id.button_sign_in);
		buttonSignIn.setMode(ActionProcessButton.Mode.PROGRESS);
		progressGenerator = new ProgressGenerator(this);
		buttonSignIn.setOnClickListener(buttonSignInClickListener);

		buttonPractice = (PracticeProcessButton) findViewById(R.id.button_practice);
		buttonPractice.setOnClickListener(buttonLoginClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_process_button, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.progress_mode) {
			labelMode.setText(getResources().getString(R.string.progress_mode));
			buttonSignIn.setVisibility(View.VISIBLE);
			buttonSignIn.setMode(ActionProcessButton.Mode.PROGRESS);
			buttonPractice.setVisibility(View.GONE);
			refreshButtonUI(buttonSignIn);
			return true;
		} else if (id == R.id.endless_mode) {
			labelMode.setText(getResources().getString(R.string.endless_mode));
			buttonSignIn.setVisibility(View.VISIBLE);
			buttonSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
			buttonPractice.setVisibility(View.GONE);
			refreshButtonUI(buttonSignIn);
			return true;
		} else if (id == R.id.practice_mode) {
			labelMode.setText(getResources().getString(R.string.practice_mode));
			buttonSignIn.setVisibility(View.GONE);
			buttonPractice.setVisibility(View.VISIBLE);
			refreshButtonUI(buttonPractice);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onComplete() {
		Toast.makeText(this, R.string.loading_Complete, Toast.LENGTH_LONG).show();
	}

	private void refreshButtonUI(ProcessButton button) {
		button.setProgress(0);
		button.setEnabled(true);
		textEmail.setEnabled(true);
		textPassword.setEnabled(true);
		progressGenerator = new ProgressGenerator(this);
	}
}
