package bg.uni_sofia.fmi_si2011_fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import bg.uni_sofia.fmi_si2011.R;
import bg.uni_sofia.fmi_si2011_fragments.IBANFragment.IibanFragmentListener;

public class IBANFragmentV2 extends Fragment {
	private Spinner mOwnIBAN;
	private Spinner mOtherIBAN;
	private Button mDoneBtn;
	private List<String> mOwnIbans;
	public IBANFragmentV2(List<String> ownIBANs) {
		mOwnIbans = ownIBANs;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.iban_v2_fragment, container, false);
		init(rootView);
		return rootView;
	}
	private void init(View rootView) {
		mOwnIBAN = (Spinner) rootView.findViewById(R.id.ownIBAN);
		mOtherIBAN = (Spinner) rootView.findViewById(R.id.otherIBANspinner);
		mDoneBtn = (Button) rootView.findViewById(R.id.doneBtn);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, mOwnIbans);
		mOwnIBAN.setAdapter(adapter);
		mOtherIBAN.setAdapter(adapter);
		mDoneBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String ownIBAN = (String) mOwnIBAN.getSelectedItem();
				String otherIBAN = (String) mOtherIBAN.getSelectedItem();
				if (ownIBAN.equals(otherIBAN))
					Toast.makeText(getActivity(), getString(R.string.equal_ibans),
							Toast.LENGTH_LONG).show();
				else {
					if (getActivity() instanceof IibanFragmentListener)
						((IibanFragmentListener) getActivity()).setIBANsAndProceed(ownIBAN,
								otherIBAN);
				}
			}
		});
	}
}
