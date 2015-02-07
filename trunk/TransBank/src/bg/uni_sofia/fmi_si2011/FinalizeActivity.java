package bg.uni_sofia.fmi_si2011;

import java.io.Serializable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import bg.uni_sofia.fmi_si2011_fragments.DetailsFragment;
import bg.uni_sofia.fmi_si2011_model.Transfer;

public class FinalizeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Serializable transfer = getIntent().getSerializableExtra(
				InitTransferActivity.TRANSFER_EXTRA);
		if (transfer instanceof Transfer)
			getFragmentManager().beginTransaction()
					.add(R.id.container, new DetailsFragment((Transfer) transfer)).commit();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finilaze, menu);
		return true;
	}
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this).setTitle(R.string.warning).setMessage(R.string.warning_msg)
				.setNegativeButton(android.R.string.no, null)
				.setPositiveButton(android.R.string.ok, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(FinalizeActivity.this,
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
}
