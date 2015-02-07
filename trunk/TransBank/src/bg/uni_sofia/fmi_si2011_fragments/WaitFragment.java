package bg.uni_sofia.fmi_si2011_fragments;

import bg.uni_sofia.fmi_si2011.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WaitFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.wait_fragment, container, false);
		return rootView;
	};
}
