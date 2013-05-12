package com.mlakhia.draw;

import com.mlakhia.api.ListPicturesAsyncTask;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	public static final String USER_PREFS = "UserPrefs";
	public static final String USER_NAME_FIELD = "userName";
	
	private Context context;
	
	// Values for email and password at the time of the login attempt.
	private String sUsername;
	// private String mPassword;

	private UserLoginTask mAuthTask = null;

	// UI references.
	private EditText mUsername;
	// private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle(R.string.title_activity_login);
		context = this;
		
		// Restore preferences
       SharedPreferences settings = getSharedPreferences(USER_PREFS, 0);
       String prefUserName = settings.getString(USER_NAME_FIELD, "");
		
		mUsername = (EditText) findViewById(R.id.username);
		
		if(!prefUserName.equalsIgnoreCase("")){
			mUsername.setText(prefUserName);
		}
		
		// mEmailView.setText(mEmail);

		/*
		 * mPasswordView = (EditText) findViewById(R.id.password); mPasswordView
		 * .setOnEditorActionListener(new TextView.OnEditorActionListener() {
		 * 
		 * @Override public boolean onEditorAction(TextView textView, int id,
		 * KeyEvent keyEvent) { if (id == R.id.login || id ==
		 * EditorInfo.IME_NULL) { attemptLogin(); return true; } return false; }
		 * });
		 */

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.skip_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						// reset prefs
					      SharedPreferences settings = getSharedPreferences(USER_PREFS, 0);
					      SharedPreferences.Editor editor = settings.edit();
					      editor.putString(USER_NAME_FIELD, "");
					      // Commit the edits!
					      editor.commit();
					      
						// start paint activity
						Intent intent = new Intent(context, PaintActivity.class);
						startActivity(intent);
					}
				});
		
		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_help:{				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(R.string.help_text)
				       .setTitle(R.string.help_title)
				       .setPositiveButton(R.string.help_ok, new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				              dialog.dismiss();
				           }
				       });
				builder.create();
				builder.show();
				break;
			}
			default:
				break;
		}

		return true;
	}

	public void attemptLogin() {
		// Reset errors.
		mUsername.setError(null);
		// mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		sUsername = mUsername.getText().toString();
		// mPassword = mPasswordView.getText().toString();
		
		// We need an Editor object to make preference changes.
	      // All objects are from android.context.Context
	      SharedPreferences settings = getSharedPreferences(USER_PREFS, 0);
	      SharedPreferences.Editor editor = settings.edit();
	      editor.putString(USER_NAME_FIELD, sUsername);
	      // Commit the edits!
	      editor.commit();

		boolean cancel = false;
		View focusView = null;

		/*
		 * // Check for a valid password. if (TextUtils.isEmpty(mPassword)) {
		 * mPasswordView.setError(getString(R.string.error_field_required));
		 * focusView = mPasswordView; cancel = true; } else if
		 * (mPassword.length() < 4) {
		 * mPasswordView.setError(getString(R.string.error_invalid_password));
		 * focusView = mPasswordView; cancel = true; }
		 */

		// Check for a valid email address.
		if (TextUtils.isEmpty(sUsername)) {
			mUsername.setError(getString(R.string.error_field_required));
			focusView = mUsername;
			cancel = true;
		}/*
		 * else if (!mEmail.contains("@")) {
		 * mEmailView.setError(getString(R.string.error_invalid_email));
		 * focusView = mEmailView; cancel = true; }
		 */

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}			
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				// Start Dialog Activity for Saved Selection
				showProgress(false);
				
				// Show List of Pictures/Options to Start New Drawing
				new ListPicturesAsyncTask(context).execute(sUsername);
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}
