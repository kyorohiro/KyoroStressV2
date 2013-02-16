package info.kyorohiro.helloworld.stressv2.menuparts;

import info.kyorohiro.helloworld.stressv2.KyoroSetting;
import android.app.Activity;
import android.app.Dialog;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HeapSizeOfBigEaterDialog extends Dialog {

	private AutoCompleteTextView mEdit = null;
	private Button mOK = null;
	private LinearLayout mLayout = null;
	private ViewGroup.LayoutParams mParams = 
		new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);

	public HeapSizeOfBigEaterDialog(Activity owner) {
		super(owner);
		mLayout = new LinearLayout(getContext());
		setEditText();
		TextView label = new TextView(getContext());
		label.setText("--eatup java heap size(kb) per one BigEater--");
		mLayout.setOrientation(LinearLayout.VERTICAL);
		mLayout.addView(label, mParams);
		mLayout.addView(mEdit, mParams);
		mEdit.setText(""+KyoroSetting.getEatupHeapSize());
		TextView memo = new TextView(getContext());
		memo.setText(
				" 1. set eatup heap size"+"\n" +
				" 2. push update button"+"\n" +
				" 3. push stopall"+"\n" +
				" 4. push BigEater<num> or startall"+"\n" +
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
					KyoroSetting.setEatupHeadSize(""+KyoroSetting.MEMSIZE_DEFAULT_VALUE);
				}
				KyoroSetting.setEatupHeadSize(text);
			    HeapSizeOfBigEaterDialog.this.dismiss();
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
        		"10240",
        		"20560",
        		"40000",
        		"80000",
        		"160000" 
        		});
        mEdit.setAdapter(automatedStrage);
        mEdit.setThreshold(1);
	}

	public static HeapSizeOfBigEaterDialog createDialog(Activity owner) {
		return new HeapSizeOfBigEaterDialog(owner);		
	}


}

