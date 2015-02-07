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

public class SumFragment extends Fragment {
	public interface SumFragmentListener {
		void setSumAndProceed(float sum);
	}
	private EditText mSumText;
	private Button mDoneBtn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.sum_fragment, container, false);
		mSumText = (EditText) rootView.findViewById(R.id.sumEditText);
		mDoneBtn = (Button) rootView.findViewById(R.id.doneButton);
		mDoneBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if (getActivity() instanceof SumFragmentListener)
						((SumFragmentListener) getActivity()).setSumAndProceed(Float
								.parseFloat(mSumText.getText().toString()));
				} catch (Exception e) {
					Toast.makeText(getActivity(), getString(R.string.please_fill),
							Toast.LENGTH_LONG).show();
				}
			}
		});
		return rootView;
	}
}
