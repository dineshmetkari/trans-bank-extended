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

public class DescriptionFragment extends Fragment {
	public interface DescriptionFragmentListener {
		void setDescriptionAndProceed(String desc);
	}
	private EditText mDescription;
	private Button mDoneBtn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.description_fragment, container, false);
		mDescription = (EditText) rootView.findViewById(R.id.descriptionEditText);
		mDoneBtn = (Button) rootView.findViewById(R.id.doneButton);
		mDoneBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if (mDescription.getText().toString().equals(""))
						Toast.makeText(getActivity(), R.string.please_fill, Toast.LENGTH_LONG)
								.show();
					else if (getActivity() instanceof DescriptionFragmentListener)
						((DescriptionFragmentListener) getActivity())
								.setDescriptionAndProceed((mDescription.getText().toString()));
				} catch (Exception e) {
					Toast.makeText(getActivity(), getString(R.string.please_fill),
							Toast.LENGTH_LONG).show();
				}
			}
		});
		return rootView;
	}
}
