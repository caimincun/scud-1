package com.team.dream.runlegwork.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseLocationActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class OpenShopActivity extends BaseLocationActivity {

	private static final int PHOTO_REQUEST_GALLERY = 10;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 11;// 结果

	@InjectView(R.id.mt_title)
	MainTitileBar mtTitle;
	@InjectView(R.id.iv_shop_logo)
	ImageView ivShopLogo;
	@InjectView(R.id.iv_camera)
	ImageView ivCamera;
	@InjectView(R.id.et_shop_name)
	EditText etShopName;
	@InjectView(R.id.et_shop_detail)
	EditText etShopDetail;
	@InjectView(R.id.tv_open_shop)
	TextView tvOpenShop;

	public boolean isLocationSuccess;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle onSaveInstate) {
		super.onCreate(onSaveInstate);
		setContentView(R.layout.activity_open_shop);
		ButterKnife.inject(this);
		mtTitle.hideTitleRight();
		mtTitle.setTitle("免费开店");
		// 开启定位
		startPosition();
	}

	@OnClick({ R.id.iv_camera, R.id.iv_shop_logo })
	public void choiceImage() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, PHOTO_REQUEST_GALLERY);

	}

	@OnClick(R.id.tv_open_shop)
	public void openShop() {
		String string[] = { etShopName.getText().toString().trim(),
				etShopDetail.getText().toString().toString() };
		String msg = AppUtils.CheckViewEmpty(
				getResources().getStringArray(R.array.create_shop), string);
		if (!msg.equals(getString(R.string.success))) {
			ToastUtils.show(this, msg);
		} else {
			createShop(string);
		}
	}

	private void createShop(String[] string) {
		if (!isLocationSuccess) {
			ToastUtils.show(this, "定位失败");
			return;
		}
		if (bitmap == null) {
			ToastUtils.show(this, "店铺图片不能为空");
			return;
		}
		String name = etShopName.getText().toString().trim();
		String detail = etShopDetail.getText().toString().trim();

		if (StringUtils.isEmpty(name)) {
			ToastUtils.show(this, "店铺名不能为空");
			return;
		}
		if (StringUtils.isEmpty(detail)) {
			ToastUtils.show(this, "店铺详情不能为空");
			return;
		}
		api.createShop(name, detail, bitmap, new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				ToastUtils.show(OpenShopActivity.this, "开店成功");
				Navigator.NavigatorToShopDetailActivity(OpenShopActivity.this);
			}

			@Override
			public void onFailure(String errMsg) {
				ToastUtils.show(OpenShopActivity.this, errMsg);
			}
		});
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
			ivShopLogo.setImageBitmap(bitmap);
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
		return new Intent(context, OpenShopActivity.class);
	}

	@Override
	public void OnCompleteLocation(boolean isLocationSuccess) {
		this.isLocationSuccess = isLocationSuccess;
	}

}
