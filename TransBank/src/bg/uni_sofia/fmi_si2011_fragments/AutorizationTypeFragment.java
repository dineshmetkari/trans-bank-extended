package bg.uni_sofia.fmi_si2011_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import bg.uni_sofia.fmi_si2011.R;

public class AutorizationTypeFragment extends Fragment {
	private ImageView _pinView;
	private ImageView _fingerprintView;
	public interface AutorizationTypeListener {
		void goToPincodeFragment();
		void goToFingerprintFragment();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.authorization_type_fragment, container, false);
		init(rootView);
		return rootView;
	}
	private void init(View rootView) {
		_pinView = (ImageView) rootView.findViewById(R.id.pincodeView);
		_fingerprintView = (ImageView) rootView.findViewById(R.id.fingerprintView);
		_pinView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getActivity() instanceof AutorizationTypeListener)
					((AutorizationTypeListener) getActivity()).goToPincodeFragment();
			}
		});
		_fingerprintView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getActivity() instanceof AutorizationTypeListener)
					((AutorizationTypeListener) getActivity()).goToFingerprintFragment();
			}
		});
	}
}
