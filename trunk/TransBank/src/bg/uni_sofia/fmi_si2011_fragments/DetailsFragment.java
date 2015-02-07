package bg.uni_sofia.fmi_si2011_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import bg.uni_sofia.fmi_si2011.R;
import bg.uni_sofia.fmi_si2011_model.Transfer;

public class DetailsFragment extends Fragment {
	private Button _finalizeBnt;
	private TextView _orderersView;
	private TextView _receiversView;
	private TextView _descrView;
	private TextView _sumView;
	private Transfer _trans;
	public interface DetailsListener {
		void finalize();
	}

	public DetailsFragment(Transfer transfer) {
		_trans = transfer;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.details_and_finish_fragment, container, false);
		init(rootView);
		return rootView;
	}
	private void init(View rootView) {
		_orderersView = (TextView) rootView.findViewById(R.id.myIBANdetailsView);
		_receiversView = (TextView) rootView.findViewById(R.id.otherIBANdetailsView);
		_sumView = (TextView) rootView.findViewById(R.id.sumDetailsView);
		_descrView = (TextView) rootView.findViewById(R.id.descriptionDetailsView);

		_orderersView.setText(getString(R.string.orderers_iban) + "\n" + _trans.getmOrdererIBAN());
		_receiversView
				.setText(getString(R.string.receivers_iban) + "\n" + _trans.getmReceiverIBAN());
		_sumView.setText(getString(R.string.sum) + "\n" + _trans.getSum());
		_descrView.setText(getString(R.string.description_label) + "\n" + _trans.getmDescription());

		_finalizeBnt = (Button) rootView.findViewById(R.id.finalizeBtn);
		_finalizeBnt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (getActivity() instanceof DetailsListener) {
					((DetailsListener) getActivity()).finalize();
				}
			}
		});
	}
}
