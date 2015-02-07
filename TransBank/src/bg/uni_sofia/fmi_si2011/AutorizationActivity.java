package bg.uni_sofia.fmi_si2011;

import java.io.Serializable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import bg.uni_sofia.fmi_si2011_fragments.AutorizationFingerprintFragment;
import bg.uni_sofia.fmi_si2011_fragments.AutorizationFingerprintFragment.AutorizationListener;
import bg.uni_sofia.fmi_si2011_fragments.AutorizationPincodeFragment;
import bg.uni_sofia.fmi_si2011_fragments.AutorizationTypeFragment;
import bg.uni_sofia.fmi_si2011_fragments.WaitFragment;
import bg.uni_sofia.fmi_si2011_fragments.AutorizationTypeFragment.AutorizationTypeListener;
import bg.uni_sofia.fmi_si2011_model.Transfer;
import bg_uni_sofia.fmi_si2011_dummy_server.DummyServer;
import bg_uni_sofia.fmi_si2011_dummy_server.DummyServer.DoneListener;

public class AutorizationActivity extends Activity
		implements
			AutorizationTypeListener,
			AutorizationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		getFragmentManager().beginTransaction().add(R.id.container, new AutorizationTypeFragment())
				.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.autorization, menu);

		return true;
	}
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this).setTitle(R.string.warning).setMessage(R.string.warning_msg)
				.setNegativeButton(android.R.string.no, null)
				.setPositiveButton(android.R.string.ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(AutorizationActivity.this,
								InitTransferActivity.class);
						startActivity(intent);
					}
				}).show();
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

	@Override
	public void goToPincodeFragment() {
		getFragmentManager().beginTransaction()
				.replace(R.id.container, new AutorizationPincodeFragment()).commit();
	}

	@Override
	public void goToFingerprintFragment() {
		getFragmentManager().beginTransaction()
				.replace(R.id.container, new AutorizationFingerprintFragment()).commit();
	}

	@Override
	public void authorize() {
		getFragmentManager().beginTransaction().replace(R.id.container, new WaitFragment())
				.commit();
		DummyServer.authorizeExec(new DoneListener() {

			@Override
			public void done(boolean status) {
				Intent intent = new Intent(AutorizationActivity.this, FinalizeActivity.class);
				Serializable extra = getIntent().getSerializableExtra(
						InitTransferActivity.TRANSFER_EXTRA);
				intent.putExtra(InitTransferActivity.TRANSFER_EXTRA, extra);
				startActivity(intent);
			}
		});

	}
}
