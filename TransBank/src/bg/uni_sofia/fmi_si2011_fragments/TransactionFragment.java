package bg.uni_sofia.fmi_si2011_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import bg.uni_sofia.fmi_si2011.R;

public class TransactionFragment extends Fragment {
	public interface FragmentListener {
		void startOwnTypeFragment();
		void startOtherTypeFragment();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.transfer_type_fragment, container, false);
		ImageView ownType = (ImageView) rootView.findViewById(R.id.ownTransaction);
		ImageView otherType = (ImageView) rootView.findViewById(R.id.otherTransaction);
		ownType.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getActivity() instanceof FragmentListener)
					((FragmentListener) getActivity()).startOwnTypeFragment();
			}
		});
		otherType.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getActivity() instanceof FragmentListener)
					((FragmentListener) getActivity()).startOtherTypeFragment();
			}
		});
		return rootView;
	}
}
