package example.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.client.android.IntentIntegrator;
import com.google.zxing.client.android.IntentResult;
import com.google.zxing.client.android.R;

public class ScannerActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);

		Fragment fragmentToReplace = new CustomScannerActivity();

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		transaction.replace(R.id.frame_root, fragmentToReplace, null);
		transaction.commit();
	}

	public void scanBarcode(View view) {
		new IntentIntegrator(this).initiateScan();

	}

	// public void scanBarcodeCustomLayout(View view) {
	// IntentIntegrator integrator = new IntentIntegrator(this);
	// integrator.setCaptureActivity(AnyOrientationCaptureActivity.class);
	// integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
	// integrator.setPrompt("Scan something");
	// integrator.setOrientationLocked(false);
	// integrator.setBeepEnabled(false);
	// integrator.initiateScan();
	// }
	//
	// public void scanBarcodeFrontCamera(View view) {
	// IntentIntegrator integrator = new IntentIntegrator(this);
	// integrator.setCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT);
	// integrator.initiateScan();
	// }
	//
	// public void scanContinuous(View view) {
	// Intent intent = new Intent(this, ContinuousCaptureActivity.class);
	// startActivity(intent);
	// }
	//
	// public void scanToolbar(View view) {
	// new
	// IntentIntegrator(this).setCaptureActivity(ToolbarCaptureActivity.class).initiateScan();
	// }
	//
	// public void scanCustomScanner(View view) {
	// new
	// IntentIntegrator(this).setCaptureActivity(CustomScannerActivity.class).initiateScan();
	// }
	//
	// public void scanMarginScanner(View view) {
	// IntentIntegrator integrator = new IntentIntegrator(this);
	// integrator.setOrientationLocked(false);
	// integrator.setCaptureActivity(SmallCaptureActivity.class);
	// integrator.initiateScan();
	// }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode,
				resultCode, data);
		if (result != null) {
			if (result.getContents() == null) {
				Log.d("MainActivity", "Cancelled scan");
				Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
			} else {
				Log.d("MainActivity", "Scanned");
				Toast.makeText(this, "Scanned: " + result.getContents(),
						Toast.LENGTH_LONG).show();
			}
		} else {
			// This is important, otherwise the result will not be passed to the
			// fragment
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

}