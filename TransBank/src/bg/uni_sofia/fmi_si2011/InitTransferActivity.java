package bg.uni_sofia.fmi_si2011;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import bg.uni_sofia.fmi_si2011_fragments.DescriptionFragment;
import bg.uni_sofia.fmi_si2011_fragments.DescriptionFragment.DescriptionFragmentListener;
import bg.uni_sofia.fmi_si2011_fragments.IBANFragment;
import bg.uni_sofia.fmi_si2011_fragments.IBANFragment.IibanFragmentListener;
import bg.uni_sofia.fmi_si2011_fragments.IBANFragmentV2;
import bg.uni_sofia.fmi_si2011_fragments.SumFragment;
import bg.uni_sofia.fmi_si2011_fragments.SumFragment.SumFragmentListener;
import bg.uni_sofia.fmi_si2011_fragments.TransactionFragment;
import bg.uni_sofia.fmi_si2011_fragments.TransactionFragment.FragmentListener;
import bg.uni_sofia.fmi_si2011_fragments.WaitFragment;
import bg.uni_sofia.fmi_si2011_model.Transfer;
import bg.uni_sofia.fmi_si2011_model.User;
import bg_uni_sofia.fmi_si2011_dummy_server.DummyServer;
import bg_uni_sofia.fmi_si2011_dummy_server.DummyServer.DoneListener;

public class InitTransferActivity extends Activity
		implements
			IibanFragmentListener,
			SumFragmentListener,
			FragmentListener,
			DescriptionFragmentListener {
	private Transfer mTransfer;
	public static final String TRANSFER_EXTRA = "Transfer";
	private User mUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mTransfer = new Transfer();
		mUser = User.getInstance();
		getFragmentManager().beginTransaction().add(R.id.container, new TransactionFragment())
				.commit();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onBackPressed() {
		new AlertDialog.Builder(this).setTitle(R.string.warning).setMessage(R.string.warning_msg)
				.setNegativeButton(android.R.string.no, null)
				.setPositiveButton(android.R.string.ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(InitTransferActivity.this,
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
	protected void goToSumFragment() {
		getFragmentManager().beginTransaction().replace(R.id.container, new SumFragment()).commit();
	}
	@Override
	public void setIBANsAndProceed(String ownIban, String otherIban) {
		mTransfer.setmOrdererIBAN(ownIban);
		mTransfer.setmReceiverIBAN(otherIban);
		goToSumFragment();
	}
	@Override
	public void startOwnTypeFragment() {
		InitTransferActivity.this.getFragmentManager().beginTransaction()
				.replace(R.id.container, new IBANFragment(mUser.getmIBANlist())).commit();
	}

	@Override
	public void startOtherTypeFragment() {
		InitTransferActivity.this.getFragmentManager().beginTransaction()
				.replace(R.id.container, new IBANFragmentV2(mUser.getmIBANlist())).commit();
	}
	@Override
	public void setSumAndProceed(float sum) {
		mTransfer.setSum((double) sum);
		InitTransferActivity.this.getFragmentManager().beginTransaction()
				.replace(R.id.container, new DescriptionFragment()).commit();
	}
	@Override
	public void setDescriptionAndProceed(String desc) {
		mTransfer.setmDescription(desc);
		getFragmentManager().beginTransaction().replace(R.id.container, new WaitFragment())
				.commit();
		DummyServer.initExec(mTransfer, new DoneListener() {

			@Override
			public void done(boolean status) {
				Intent intent = new Intent(InitTransferActivity.this, AutorizationActivity.class);
				intent.putExtra(TRANSFER_EXTRA, mTransfer);
				startActivity(intent);
			}
		});

	}
}
