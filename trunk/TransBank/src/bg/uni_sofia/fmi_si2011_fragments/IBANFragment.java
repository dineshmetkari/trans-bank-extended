package bg.uni_sofia.fmi_si2011_fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import bg.uni_sofia.fmi_si2011.R;

public class IBANFragment extends Fragment {
	public interface IibanFragmentListener {
		void setIBANsAndProceed(String ownIban, String otherIban);
	}
	private Spinner mOwnIBAN;
	private EditText mOtherIBAN;
	private Button mDoneBtn;
	private List<String> mOwnIbans;
	public IBANFragment(List<String> ownIBANs) {
		mOwnIbans = ownIBANs;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.iban_fragment, container, false);
		init(rootView);
		return rootView;
	}
	private void init(View rootView) {
		mOwnIBAN = (Spinner) rootView.findViewById(R.id.ownIBAN);
		mOtherIBAN = (EditText) rootView.findViewById(R.id.otherIBAN);
		mDoneBtn = (Button) rootView.findViewById(R.id.doneBtn);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, mOwnIbans);
		mOwnIBAN.setAdapter(adapter);
		mDoneBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String ownIBAN = (String) mOwnIBAN.getSelectedItem();
				String otherIBAN = mOtherIBAN.getText().toString();
				if (ownIBAN.equals(otherIBAN))
					Toast.makeText(getActivity(), getString(R.string.equal_ibans),
							Toast.LENGTH_LONG).show();
				else if (otherIBAN.equals(""))
					Toast.makeText(getActivity(), getString(R.string.please_fill),
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
