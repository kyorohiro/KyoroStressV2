package info.kyorohiro.helloworld.stressv2.menuparts;

import java.lang.ref.WeakReference;

import info.kyorohiro.helloworld.stressv2.KyoroSetting;
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
import android.widget.TextView;

public class NumOfBigEaterDialog extends Dialog {

	private AutoCompleteTextView mEdit = null;
	private Button mOK = null;
	private LinearLayout mLayout = null;
	private ViewGroup.LayoutParams mParams = 
		new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
	private WeakReference<Activity> mParent = null;

	public NumOfBigEaterDialog(Activity owner) {
		super(owner);
		mParent = new WeakReference<Activity>(owner);
		mLayout = new LinearLayout(getContext());
		setEditText();
		TextView label = new TextView(getContext());
		label.setText("--num of bigeater--");
		mLayout.setOrientation(LinearLayout.VERTICAL);
		mLayout.addView(label, mParams);
		mLayout.addView(mEdit, mParams);
		mEdit.setText(""+KyoroSetting.getNumOfBigEater());
		TextView memo = new TextView(getContext());
		memo.setText(
				" 3-32"+"\n" +
				" "+"\n"
				);
		mOK = new Button(getContext());
		mOK.setText("Update");
		mOK.setOnClickListener(new android.view.View.OnClickListener(){
			public void onClick(View v) {
				if(mEdit == null){
					return;
				}
				String text = mEdit.getText().toString();
				if(text == null){
					KyoroSetting.setNumOfBigEater(""+KyoroSetting.NUM_OF_BIGEATER_DEFAULT_VALUE);
				}
				KyoroSetting.setNumOfBigEater(text);
			    NumOfBigEaterDialog.this.dismiss();
			    Activity a = mParent.get();
//			    if(a != null&& a instanceof KyoroStressActivity) {
//			    	((KyoroStressActivity)a).resetBigEater(KyoroSetting.getNumOfBigEater());
//			    }
			}
		});
		mLayout.addView(mOK, mParams);
		mLayout.addView(memo);
		setContentView(mLayout, mParams);
	}

	public void setEditText(){
		mEdit = new AutoCompleteTextView(getContext());
		mEdit.setSelected(false);
		mEdit.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		mEdit.setHint("kbyte");
		mEdit.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
		mEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        ArrayAdapter<String> automatedStrage = 
        	new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,
        		new String[]{
        		"4",
        		"8",
        		"16",
        		"24",
        		"32" 
        		});
        mEdit.setAdapter(automatedStrage);
        mEdit.setThreshold(1);
	}

	public static NumOfBigEaterDialog createDialog(Activity owner) {
		return new NumOfBigEaterDialog(owner);		
	}


}

