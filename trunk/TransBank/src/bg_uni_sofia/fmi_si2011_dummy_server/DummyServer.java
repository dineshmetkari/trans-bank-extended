package bg_uni_sofia.fmi_si2011_dummy_server;

import android.os.Handler;
import bg.uni_sofia.fmi_si2011_model.Transfer;

public class DummyServer {
	public interface DoneListener {
		void done(boolean status);
	}
	public static void initExec(Transfer transfer, final DoneListener listener) {
		//transfer manipulation
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				listener.done(true);
			}
		}, 2000);
	}
	public static void authorizeExec(final DoneListener listener) {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				listener.done(true);
			}
		}, 2000);
	}
}
