package com.team.dream.runlegwork.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

import com.team.dream.runlegwork.BaseLocationActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.adapter.SelectOrderOrSkillAdapter;
import com.team.dream.runlegwork.dialog.DialogTextEdit;
import com.team.dream.runlegwork.entity.Store;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.PathUtil;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.view.RoundImageView;
import com.team.dream.runlegwork.widget.MainTitileBar;

@SuppressLint("NewApi")
public class UpdateShopActivity extends BaseLocationActivity {
	private static final int PHOTO_REQUEST_GALLERY = 10;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 11;// 结果

	@InjectView(R.id.mt_title)
	MainTitileBar mtTitle;
	@InjectView(R.id.gv_type)
	GridView gvType;
	@InjectView(R.id.ll_shop_loc)
	LinearLayout llShopLoc;
	@InjectView(R.id.iv_shop_head)
	RoundImageView ivShopHead;
	@InjectView(R.id.ll_shop_name)
	LinearLayout llShopName;
	@InjectView(R.id.ll_shop_ad)
	LinearLayout llShopAd;
	@InjectView(R.id.ll_shop_type)
	LinearLayout llShopType;
	@InjectView(R.id.tv_shop_type)
	TextView tvShopType;
	@InjectView(R.id.ll_shop_address)
	LinearLayout llShopAddress;
	@InjectView(R.id.ll_phone_num)
	LinearLayout llPhoneNum;
	@InjectView(R.id.ll_price)
	LinearLayout llPrice;
	@InjectView(R.id.tv_shop_name)
	TextView tvShopName;
	@InjectView(R.id.tv_ad)
	TextView tvShopAd;
	@InjectView(R.id.tv_shop_address)
	TextView tvShopAddress;
	@InjectView(R.id.tv_phone_num)
	TextView tvPhoneNum;
	@InjectView(R.id.tv_price)
	TextView tvPrice;
	@InjectView(R.id.iv_arrow)
	ImageView ivArrow;
	@InjectView(R.id.tv_push_order)
	TextView tvPushOrder;

	private String[] mData;
	private SelectOrderOrSkillAdapter hotAdapter;
	public static final String STORE_KEY = "store";
	private Store mStore;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle onSaveInstate) {
		super.onCreate(onSaveInstate);
		setContentView(R.layout.activity_shop_setting);
		ButterKnife.inject(this);
		initData();
		startPosition();
	}

	private void initData() {
		mtTitle.hideTitleRight();
		mtTitle.setTitle("店铺设置");
		gvType.setVisibility(View.GONE);
		mData = getResources().getStringArray(R.array.shop_type);
		hotAdapter = new SelectOrderOrSkillAdapter(this, mData);
		gvType.setAdapter(hotAdapter);
		setListViewHeightBasedOnChildren(gvType);
		mStore = (Store) getIntent().getSerializableExtra(STORE_KEY);
		if (!StringUtils.isEmpty(mStore.getStorePicture())) {
			SingletonServiceManager.getInstance().display(
					PathUtil.getShopStorePicUrl(mStore.getStorePicture()),
					ivShopHead, R.drawable.shop_df_pic, null);
		}
		if (!StringUtils.isEmpty(mStore.getStoreName())) {
			tvShopName.setText(mStore.getStoreName());
		}
		if (!StringUtils.isEmpty(mStore.getSlogan())) {
			tvShopAd.setText(mStore.getSlogan());
		}
		if (!StringUtils.isEmpty(mStore.getStorePhone())) {
			tvPhoneNum.setText(mStore.getStorePhone());
		}
		if (!StringUtils.isEmpty(mStore.getStoreType())) {
			tvShopType.setText(mStore.getStoreType());
		}
			tvPrice.setText(mStore.getStartPrice()+"");
	}

	@OnClick(R.id.tv_push_order)
	public void pushUpdate() {
		String[] checkString = { tvShopName.getText().toString().trim(),
				tvShopAd.getText().toString().trim(),
				tvShopType.getText().toString().trim(),
				tvShopAddress.getText().toString().trim(),
				tvPhoneNum.getText().toString().trim(),
				tvPrice.getText().toString().trim() };
		String[] toastString = getResources().getStringArray(
				R.array.update_shop_msg);
		String msg = AppUtils.CheckViewEmpty(toastString, checkString);
		if (msg.equals(getString(R.string.success))) {
			pushUpdateShop(checkString);
		} else {
			ToastUtils.show(this, msg);
		}
	}

	private void pushUpdateShop(String[] checkString) {
		Store store = new Store();
		store.setAddress(checkString[3]);
		store.setStoreName(checkString[0]);
		store.setSlogan(checkString[1]);
		store.setStoreType(checkString[2]);
		store.setStorePhone(checkString[4]);
		store.setStartPrice(Integer.parseInt(checkString[5]));

		api.updateShop(store, bitmap, new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				ToastUtils.show(UpdateShopActivity.this, "修改成功");
			}

			@Override
			public void onFailure(String errMsg) {
				// TODO Auto-generated method stub

			}
		});
	}

	@OnClick(R.id.ll_shop_loc)
	public void locaitonShop() {
		startPosition();
	}

	@OnClick(R.id.ll_shop_type)
	public void onSelctType() {
		if (gvType.getVisibility() == View.VISIBLE) {
			gvType.setVisibility(View.GONE);
			ivArrow.setImageResource(R.drawable.ic_arrow_right);
		} else {
			gvType.setVisibility(View.VISIBLE);
			ivArrow.setImageResource(R.drawable.ic_arrow_bottom);
		}

	}

	@OnItemClick(R.id.gv_type)
	public void onGvSelect(int postion) {
		gvType.setVisibility(View.GONE);
		tvShopType.setText(mData[postion]);
	}

	@OnClick({ R.id.ll_shop_name, R.id.ll_shop_ad, R.id.ll_phone_num,
			R.id.ll_price })
	public void showChange(View v) {
		switch (v.getId()) {
		case R.id.ll_shop_name:
			showDiaglogMsg(tvShopName, "修改店名", "请输入店名");
			break;
		case R.id.ll_shop_ad:
			showDiaglogMsg(tvShopAd, "修改广告语", "请输入广告语");
			break;
		case R.id.ll_phone_num:
			showDiaglogMsg(tvPhoneNum, "修改联系电话", "请输联系电话");
			break;
		case R.id.ll_price:
			showDiaglogMsg(tvPrice, "修改起送价", "请输入起送价");
			break;
		}

	}

	private void showDiaglogMsg(final TextView tvMesg, String titile,
			String hint) {
		boolean isInt = false;
		if (tvMesg.getId() == R.id.tv_phone_num
				|| tvMesg.getId() == R.id.tv_price) {
			isInt = true;
		}
		String oldNickName = tvMesg.getText().toString().trim();
		final DialogTextEdit dialogTextEdit = new DialogTextEdit(this);
		dialogTextEdit
				.setTitleText(titile)
				.setEditTextContent(oldNickName)
				.setEditTextHint(hint)
				.setInputType(
						isInt ? InputType.TYPE_CLASS_NUMBER
								: InputType.TYPE_CLASS_TEXT)
				.setSingleLine(true).setLeftBtnContent("保存")
				.setRightBtnContent("取消")
				.setLeftBtnOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						final String content = dialogTextEdit.getEditContent()
								.trim();
						if (content.length() > 0) {
							Tool.hiddenSoftKeyboard(UpdateShopActivity.this,
									dialogTextEdit.getFocusView());
							tvMesg.setText(content);
							Tool.cancelAlertDialog();
						} else {
							// Toast.makeText(UpdateShopActivity.this,
							// "身份证号码不能为空", Toast.LENGTH_SHORT).show();
						}
					}
				}).setRightBtnOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Tool.cancelAlertDialog();
						Tool.hiddenSoftKeyboard(UpdateShopActivity.this,
								dialogTextEdit.getFocusView());
					}
				});
		Tool.showAlertDialog(UpdateShopActivity.this, dialogTextEdit, true,
				true);

	}

	public static void setListViewHeightBasedOnChildren(GridView listView) {

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			return;
		}

		// int totalHeight = 0;
		int itemHeight = 0;

		for (int i = 0; i < listAdapter.getCount(); i++) {

			View listItem = listAdapter.getView(i, null, listView);

			listItem.measure(0, 0);

			// totalHeight += listItem.getMeasuredHeight();
			itemHeight = listItem.getMeasuredHeight();

		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();

		int dividerHeight = listView.getVerticalSpacing();

		// totalHeight += (dividerHeight * (listAdapter.getCount() - 1));

		params.height = itemHeight * 2 + dividerHeight * 2;

		listView.setLayoutParams(params);

	}

	@OnClick(R.id.iv_shop_head)
	public void selectPic() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		Bundle mBundle = data.getExtras();
		switch (requestCode) {

		case PHOTO_REQUEST_GALLERY:
			if (data != null)
				startPhotoZoom(data.getData(), 200);

			break;
		case PHOTO_REQUEST_CUT:
			mBundle = data.getExtras();
			if (mBundle == null)
				break;
			bitmap = (Bitmap) mBundle.get("data");
			ivShopHead.setImageBitmap(bitmap);
			break;
		}
	}

	private void startPhotoZoom(Uri uri, int size) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", size);
		intent.putExtra("outputY", size);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, UpdateShopActivity.class);
	}

	@Override
	public void OnCompleteLocation(boolean isLocationSuccess) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnCompleteLocation(String address) {
		super.OnCompleteLocation(address);
		tvShopAddress.setText(address);
	}

}
