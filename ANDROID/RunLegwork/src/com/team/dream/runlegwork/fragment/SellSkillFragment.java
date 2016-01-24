package com.team.dream.runlegwork.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.SinglePicAdapter;
import com.team.dream.runlegwork.dialog.XgHeadDialogUtil;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.PathUtil;
import com.team.dream.runlegwork.utils.StreamUtil;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.TopBar;

public class SellSkillFragment extends BaseFragment implements
		OnCheckedChangeListener, OnClickListener {
	private static final String ORDER_KEY = "order_key";

	@InjectView(R.id.topbar)
	TopBar topbar;
	@InjectView(R.id.rg_online_select)
	RadioGroup rgOnlineSelect;
	@InjectView(R.id.rg_price_select)
	RadioGroup rgPriceSelect;
	@InjectView(R.id.rb_hours)
	RadioButton rbHours;
	@InjectView(R.id.rb_online)
	RadioButton rbOnline;
	@InjectView(R.id.tv_show_type)
	TextView tvShowType;
	@InjectView(R.id.et_skill_titile)
	EditText etSkillTitle;
	@InjectView(R.id.et_skill_price)
	EditText etSkillPrice;
	@InjectView(R.id.et_skill_detail)
	EditText etSkillDetail;
	@InjectView(R.id.et_skill_remark)
	EditText etSkillRemark;
	@InjectView(R.id.tv_skill_confirm)
	TextView tvSkillConfirm;
	@InjectView(R.id.tv_type)
	TextView tvTpye;
	@InjectView(R.id.sell_skill_ivAddpic)
	ImageView ivAddpic;
	@InjectView(R.id.sell_skill_hListv)
	GridView listv;

	private boolean isOnline = true;
	private String type;

	private XgHeadDialogUtil xgHeadDialog;
	
	List<String> listImageurl = new ArrayList<String>();
	List<Bitmap> listBitmap = new ArrayList<Bitmap>();
	
	private int flag =1;
	private String filename;
	
	private SinglePicAdapter singPicAdapter;

	public static SellSkillFragment newInstance(String selectNeed) {
		SellSkillFragment fragment = new SellSkillFragment();
		Bundle bundle = new Bundle();
		bundle.putString(ORDER_KEY, selectNeed);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_sell_skill, container,
				false);
		ButterKnife.inject(this, view);
		topbar.initialze("发布技能");
		rgOnlineSelect.setOnCheckedChangeListener(this);
		rgPriceSelect.setOnCheckedChangeListener(this);
		rgPriceSelect.check(rbHours.getId());
		rgOnlineSelect.check(rbOnline.getId());
		tvTpye.setText(type);
		return view;
	}

	@Override
	protected void initializePresenter() {
		type = getArguments().getString(ORDER_KEY);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@OnClick(R.id.tv_skill_confirm)
	public void pushSkill() {
		String[] checkSkillDetail = getResources().getStringArray(
				R.array.check_skill);
		View[] views = { etSkillTitle, etSkillPrice, etSkillDetail,
				etSkillRemark };
		String showType = tvShowType.getText().toString();
		String msg = AppUtils.CheckViewEmpty(checkSkillDetail, views);
		if (msg.equals(getString(R.string.success))) {
			pushSkill(showType);
		} else {
			ToastUtils.show(getActivity(), msg);
		}
	}

	private void pushSkill(String showType) {
		Skill skill = new Skill();
		skill.setSkillTitle(etSkillTitle.getText().toString().trim());
		skill.setSkillMoney(etSkillPrice.getText().toString().trim());
		skill.setSkillContent(etSkillDetail.getText().toString().trim());
		skill.setSkillSort(type);
		skill.setSkillRemark(etSkillRemark.getText().toString().trim());
		skill.setTradeFlag(isOnline ? 1 : 2);
		skill.setSkillUnit(showType);
		api.createSkills(skill,listBitmap,new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				ToastUtils.show(getActivity(), R.string.push_skill_success);
			}

			@Override
			public void onFailure(String errMsg) {
				ToastUtils.show(getActivity(), R.string.push_skill_failed);
			}
		});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
		switch (group.getId()) {
		case R.id.rg_online_select:
			if (checkedId == R.id.rb_online) {
				isOnline = true;
			} else {
				isOnline = false;
			}
			break;
		case R.id.rg_price_select:
			tvShowType.setText(radioButton.getText());
			break;
		}
	}
	@OnClick(R.id.sell_skill_ivAddpic)
	public void updateHead() {
		if(listBitmap.size()==3){
			Tool.showToast(getActivity(), "最多上传3张图片");
		}
		else{
		filename ="/sdcard/skillpic"+flag++ +".png";
		xgHeadDialog = new XgHeadDialogUtil(getActivity());
		xgHeadDialog.setOnClickListeners(this);
		xgHeadDialog.show();
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.take_pics_layout:
			if (android.os.Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED)) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 101);
			} else {
				Toast.makeText(getActivity(), "内存卡不可用，请检测内存卡",
						Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.select_pics_layout:
			if (android.os.Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED)) {
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 100);
			} else {
				Toast.makeText(getActivity(), "内存卡不可用，请检测内存卡",
						Toast.LENGTH_LONG).show();
			}
			break;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        xgHeadDialog.dismiss();
        if ((requestCode == 101 || requestCode == 100) && data != null) {
			File file = new File(filename);

			String mPath = null;
			if (requestCode == 101) {
				mPath = PathUtil.getPath(getActivity(), data.getData());
				if(mPath == null) {
					File file2 = new File(filename);

					Bitmap bm = data.getParcelableExtra("data");
					StreamUtil.saveBitmap(file2.getAbsolutePath(), bm, 100);

					mPath = file2.getAbsolutePath();
				}
			} else if (requestCode == 100) {
				Uri originalUri = data.getData();
				String[] proj = {MediaStore.Images.Media.DATA};
	            //android多媒体数据库的封装接口，具体的看Android文档
	            
				Cursor cursor = getActivity().managedQuery(originalUri, proj, null, null, null); 
	            if(cursor!=null){
	            	int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		            //将光标移至开头 ，这个很重要，不小心很容易引起越界
		            cursor.moveToFirst();
		            //最后根据索引值获取图片路径
		            mPath = cursor.getString(column_index);
	            }
	            
			}

			if(mPath == null) {
				return;
		    }

			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(Uri.fromFile(new File(mPath)), "image/*");
			intent.putExtra("output", Uri.fromFile(file));
			intent.putExtra("crop", "true");
			intent.putExtra("scale", true);
			intent.putExtra("scaleUpIfNeeded", true);
			intent.putExtra("aspectX", 1);// 裁剪框比例
			intent.putExtra("aspectY", 1);
			intent.putExtra("outputX", 200);// 输出图片大小
			intent.putExtra("outputY", 200);
			startActivityForResult(intent, 102);
		} else if(requestCode == 102){
			saveCropAvator();
		}
    }
	
    /**
     * 保存裁剪的头像
     * 
     * @param data
     */
    private void saveCropAvator() {
    	File file = new File(filename);
    	
    	if(file.exists()){
//			uploadHead(file.getAbsolutePath());
    		Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
    		listImageurl.add(filename);
    		listBitmap.add(bitmap);
    		dataChangedPic();
//    		api.uploadUserhead(bitmap, new JsonBooleanResponseHandler() {
//				
//				@Override
//				public void onSuccess() {
////					Tool.showToast(get, "头像上传成功");
//				}
//				
//				@Override
//				public void onFailure(String errMsg) {
////					Log.d(tag, "头像上传失败"+errMsg);
//				}
//			});
//    		loadhead1();
		}
		else{
			Log.d("SellSkillFragment", "文件不存在");
		}	
    }
    
    public void dataChangedPic(){
    	if(singPicAdapter == null){
    		singPicAdapter = new SinglePicAdapter(listImageurl, getActivity());
    		listv.setAdapter(singPicAdapter);
    	}
    	else{
    		singPicAdapter.notifyDataSetChanged();
    	}
    }
}
