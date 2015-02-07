package bg.uni_sofia.fmi_si2011_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import bg.uni_sofia.fmi_si2011.R;
import bg.uni_sofia.fmi_si2011_fragments.AutorizationFingerprintFragment.AutorizationListener;

public class AutorizationPincodeFragment extends Fragment {
	private EditText _pinView;
	private Button _doneBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.authorization_pin_fragment, container, false);
		init(rootView);
		return rootView;
	}
	private void init(View rootView) {
		_pinView = (EditText) rootView.findViewById(R.id.pinEditText);
		_doneBtn = (Button) rootView.findViewById(R.id.waitButton);
		_doneBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (_pinView.getText().toString().equals(""))
					Toast.makeText(getActivity(), R.string.please_fill, Toast.LENGTH_LONG).show();
				else if (getActivity() instanceof AutorizationListener) {
					((AutorizationListener) getActivity()).authorize();
				}
			}
		});
	}
}
