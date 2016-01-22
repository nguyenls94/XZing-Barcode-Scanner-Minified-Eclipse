package example.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.client.android.IntentIntegrator;
import com.google.zxing.client.android.IntentResult;
import com.google.zxing.client.android.R;

/**
 * Sample of scanning from a Fragment
 */
public class SimpleScanFragment extends Fragment  {
	private String toast;

	public SimpleScanFragment() {
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		displayToast();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_custom_scanner, container, false);
//		Button scan = (Button) view.findViewById(R.id.scan_from_fragment);
//		scan.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
				scanFromFragment();
//			}
//		});
	return view;
	}

	public void scanFromFragment() {
		IntentIntegrator.forSupportFragment(this).initiateScan();
	}

	private void displayToast() {
		if (getActivity() != null && toast != null) {
			Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
			toast = null;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode,
				resultCode, data);
		if (result != null) {
			if (result.getContents() == null) {
				toast = "Cancelled from fragment";
			} else {
				toast = "Scanned from fragment: " + result.getContents();
			}

			// At this point we may or may not have a reference to the activity
			displayToast();
		}
	}
}