package info.kyorohiro.helloworld.stressv2.menuparts;

import java.lang.ref.WeakReference;

import info.kyorohiro.helloworld.stressv2.KyoroSetting;
//import info.kyorohiro.helloworld.stressv2.KyoroStressActivity;
import android.app.Activity;
import android.app.Dialog;
import android.os.PowerManager.WakeLock;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MemoryFileOfBigEaterDialog extends Dialog {

	private RadioGroup mRetry = null;
	private RadioButton mOn = null;
	private RadioButton mOff = null;

	private Button mOK = null;
	private LinearLayout mLayout = null;
	private ViewGroup.LayoutParams mParams = 
		new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	private WeakReference<Activity> mParent = null;

	public MemoryFileOfBigEaterDialog(Activity owner) {
		super(owner);
		mParent = new WeakReference<Activity>(owner);
		mLayout = new LinearLayout(getContext());
		TextView label = new TextView(getContext());
		label.setText("--show notifcation--");
		mRetry = new RadioGroup(getContext());
		mOn = new RadioButton(getContext());
		mOn.setText(KyoroSetting.MEMORYFILE_ON);
		mOn.setId(1);
		mOff = new RadioButton(getContext());
		mOff.setText(KyoroSetting.MEMORYFILE_OFF);
		mOff.setId(2);
		mRetry.addView(mOn);
		mRetry.addView(mOff);

		if (KyoroSetting.MEMORYFILE_ON.equals(KyoroSetting.getIsMomoryFile())) {
			mRetry.check(1);
		} else {
			mRetry.check(2);
		}

		mLayout.setOrientation(LinearLayout.VERTICAL);
		mLayout.addView(mRetry);
		mLayout.addView(label, mParams);
		TextView memo = new TextView(getContext());
		memo.setText(
				"if on, use memoryfile"+"\n" +
				" "+"\n"
				);
		mOK = new Button(getContext());
		mOK.setText("Update");
		mOK.setOnClickListener(new android.view.View.OnClickListener(){
			public void onClick(View v) {
			    MemoryFileOfBigEaterDialog.this.dismiss();
			    Activity a = mParent.get();
			    if (mOn.isChecked()) {
			    	KyoroSetting.setIsMemoryFile(KyoroSetting.MEMORYFILE_ON);
			    } else {
			    	KyoroSetting.setIsMemoryFile(KyoroSetting.MEMORYFILE_OFF);		    	
			    }
			    dismiss();
			}
		});
		mLayout.addView(mOK, mParams);
		mLayout.addView(memo);
		setContentView(mLayout, mParams);
	}

	public static MemoryFileOfBigEaterDialog createDialog(Activity owner) {
		return new MemoryFileOfBigEaterDialog(owner);		
	}


}

