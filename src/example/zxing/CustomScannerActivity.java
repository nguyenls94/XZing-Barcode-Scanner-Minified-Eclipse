package example.zxing;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.zxing.client.android.R;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

/**
 * Custom Scannner Activity extending from Activity to display a custom layout
 * form scanner view.
 */
public class CustomScannerActivity extends Fragment implements
		CompoundBarcodeView.TorchListener {

	private CaptureManager capture;
	private CompoundBarcodeView barcodeScannerView;
	private Button switchFlashlightButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_custom_scanner,
				container, false);

		barcodeScannerView = (CompoundBarcodeView) view
				.findViewById(R.id.zxing_barcode_scanner);
		
		barcodeScannerView.setTorchListener(this);

		switchFlashlightButton = (Button) view
				.findViewById(R.id.switch_flashlight);

		// if the device does not have flashlight in its camera,
		// then remove the switch flashlight button...
		if (!hasFlash()) {
			switchFlashlightButton.setVisibility(View.GONE);
		}

		capture = new CaptureManager(getActivity(), barcodeScannerView);
		capture.initializeFromIntent(getActivity().getIntent(),
				savedInstanceState);
		capture.decode();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		capture.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		capture.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		capture.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		capture.onSaveInstanceState(outState);
	}

	// @Override
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// return barcodeScannerView.onKeyDown(keyCode, event)
	// || super.onKeyDown(keyCode, event);
	// }

	/**
	 * Check if the device's camera has a Flashlight.
	 * 
	 * @return true if there is Flashlight, otherwise false.
	 */
	private boolean hasFlash() {
		return getActivity().getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA_FLASH);
	}

	public void switchFlashlight(View view) {
		if (getString(R.string.turn_on_flashlight).equals(
				switchFlashlightButton.getText())) {
			barcodeScannerView.setTorchOn();
		} else {
			barcodeScannerView.setTorchOff();
		}
	}

	@Override
	public void onTorchOn() {
		switchFlashlightButton.setText(R.string.turn_off_flashlight);
	}

	@Override
	public void onTorchOff() {
		switchFlashlightButton.setText(R.string.turn_on_flashlight);
	}

}
