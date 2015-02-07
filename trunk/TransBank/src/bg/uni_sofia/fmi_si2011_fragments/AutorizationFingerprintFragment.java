package bg.uni_sofia.fmi_si2011_fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import bg.uni_sofia.fmi_si2011.R;

public class AutorizationFingerprintFragment extends Fragment {
	private ImageView _fingerprintView;
	private int counter;
	private Runnable longPressed;
	private Handler _handler;
	private TextView _hintView;
	public interface AutorizationListener {
		void authorize();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.authorization_fingerprint_fragment, container,
				false);
		init(rootView);
		return rootView;
	}
	private void init(View rootView) {
		_handler = new Handler();
		_hintView = (TextView) rootView.findViewById(R.id.hintTextView);
		longPressed = new Runnable() {

			@Override
			public void run() {
				if (counter == 3)
					authorize();
				else {
					counter++;
					_hintView.setText("" + counter);
					if (counter == 1)
						_hintView.setTextSize(24);
					_handler.postDelayed(longPressed, 1000);
				}
			}
		};
		_fingerprintView = (ImageView) rootView.findViewById(R.id.fingerPrintView);
		_fingerprintView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					counter = 0;
					_handler.post(longPressed);
				}
				if ((event.getAction() == MotionEvent.ACTION_MOVE)
						|| (event.getAction() == MotionEvent.ACTION_UP)) {
					_handler.removeCallbacks(longPressed);
					_hintView.setTextSize(12);
					_hintView.setText(getString(R.string.autorization_fingerprint_hint));
				}

				return true;
			}
		});
	}
	protected void authorize() {
		if (getActivity() instanceof AutorizationListener) {
			((AutorizationListener) getActivity()).authorize();
		}
	}
}
