package com.team.dream.runlegwork.fragment;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.imageloader.core.assist.FailReason;
import com.team.dream.imageloader.core.listener.ImageLoadingListener;
import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.activity.MessageActivity;
import com.team.dream.runlegwork.activity.account.PeopleSettingActivity;
import com.team.dream.runlegwork.dialog.XgHeadDialogUtil;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.PathUtil;
import com.team.dream.runlegwork.utils.StreamUtil;
import com.team.dream.runlegwork.view.CircleImageView;

public class MineFragment extends BaseFragment implements OnClickListener{
	private String tag = MineFragment.class.getSimpleName();
	private Context ctx;

	@InjectView(R.id.tv_nick_name)
	TextView tvNickName;
	@InjectView(R.id.mine_setting)
	RelativeLayout llSetting;
	@InjectView(R.id.mine_ivHead)
	CircleImageView ivHead;
	@InjectView(R.id.tv_my_shop)
	TextView tvMyShop;
	@InjectView(R.id.mine_llMessage)
	LinearLayout llMessage;
	@InjectView(R.id.mine_llMyshop)
	LinearLayout llMyshop;

	private String userName;
	private boolean isHaveStore;
	private XgHeadDialogUtil xgHeadDialog;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View mainView = inflater.inflate(R.layout.fragment_mine, container,
				false);
		ButterKnife.inject(this, mainView);
		ctx = getActivity();
		tvNickName.setText(userName);

		return mainView;
	}

	@Override
	public void onResume() {
		super.onResume();
		userName = AccountManager.getInstance().getUserinfo().getUserRealName();
		tvNickName.setText(userName);
		loadHead();
		getStoreState();
	}

	@SuppressLint("SdCardPath")
	private void loadHead() {
		// 加载头像
		File filehead = new File("/sdcard/headimg.png");
		SingletonServiceManager.getInstance().imageLoader.clearMemoryCache();
		SingletonServiceManager.getInstance().imageLoader.clearDiskCache();
		SingletonServiceManager.getInstance().display(
				"file://" + filehead.getAbsolutePath(), ivHead,
				R.drawable.user_default_head, null);
	}


	@OnClick(R.id.mine_setting)
	public void setting() {
		startActivity(new Intent(ctx, PeopleSettingActivity.class));
	}

	@OnClick(R.id.mine_llMyshop)
	public void toMyShop() {
		if (isHaveStore) {
			Navigator.NavigatorToShopDetailActivity(getActivity());
		} else {
			Navigator.NavigatorToOpenShopActivity(getActivity());
		}
	}
	@OnClick(R.id.mine_llMessage)
	public void message(){
		startActivity(new Intent(ctx, MessageActivity.class));
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}

	@Override
	protected void initializePresenter() {

		userName = AccountManager.getInstance().getUserinfo().getUserRealName();
		getStoreState();

	}

	private void getStoreState() {
		api.isHavaStore(new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				isHaveStore = true;
			}

			@Override
			public void onFailure(String errMsg) {
				isHaveStore = false;
			}
		});
	}

	
	
	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.take_pics_layout:
			if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            } else {
            	Toast.makeText(ctx, "内存卡不可用，请检测内存卡", Toast.LENGTH_LONG).show();
            }
			break;
		case R.id.select_pics_layout:
			if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
           	 Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
           } else {
        	   Toast.makeText(ctx, "内存卡不可用，请检测内存卡", Toast.LENGTH_LONG).show();
           }
			break;
		}
	}
	@SuppressWarnings("deprecation")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        xgHeadDialog.dismiss();
        if ((requestCode == 101 || requestCode == 100) && data != null) {
			File file = new File("/sdcard/headimg.png");

			String mPath = null;
			if (requestCode == 101) {
				mPath = PathUtil.getPath(getActivity(), data.getData());
				if(mPath == null) {
					File file2 = new File("/sdcard/headimg.png");

					Bitmap bm = data.getParcelableExtra("data");
					StreamUtil.saveBitmap(file2.getAbsolutePath(), bm, 100);

					mPath = file2.getAbsolutePath();
				}
			} else if (requestCode == 100) {
				Uri originalUri = data.getData();
				String[] proj = {MediaStore.Images.Media.DATA};
	            //android多媒体数据库的封装接口，具体的看Android文档
	            
				Cursor cursor = getActivity().managedQuery(originalUri, proj, null, null, null); 
	            //按我个人理解 这个是获得用户选择的图片的索引值
	            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	            //将光标移至开头 ，这个很重要，不小心很容易引起越界
	            cursor.moveToFirst();
	            //最后根据索引值获取图片路径
	            mPath = cursor.getString(column_index);
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
	@OnClick(R.id.mine_ivHead)
	public void updateHead(){
		xgHeadDialog = new XgHeadDialogUtil(getActivity());
		xgHeadDialog.setOnClickListeners(this);
		xgHeadDialog.show();
	}
    /**
     * 保存裁剪的头像
     * 
     * @param data
     */
    private void saveCropAvator() {
    	File file = new File("/sdcard/headimg.png");
    	if(file.exists()){
//			uploadHead(file.getAbsolutePath());
    		showProgressDialog();
    		Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
    		api.uploadUserhead(bitmap, new JsonBooleanResponseHandler() {
				
				@Override
				public void onSuccess() {
					removeProgressDialog();
					Tool.showToast(ctx, "头像上传成功");
				}
				
				@Override
				public void onFailure(String errMsg) {
					removeProgressDialog();
					Log.d(tag, "头像上传失败"+errMsg);
				}
			});
    		loadhead1();
		}
		else{
			Log.d(tag, "文件不存在");
		}	
    }
    
    private void loadhead1() {
		File filehead = new File("/sdcard/headimg.png");
		ivHead.setImageResource(R.drawable.ic_launcher);
		if (filehead.exists()) {
			SingletonServiceManager.getInstance().imageLoader.clearMemoryCache();
			SingletonServiceManager.getInstance().imageLoader.clearDiskCache();
			SingletonServiceManager.getInstance().display(
					"file://" + filehead.getAbsolutePath(), ivHead,
					R.drawable.user_default_head, new ImageLoadingListener() {
						@Override
						public void onLoadingStarted(String imageUri, View view) {
							System.out.println(imageUri);
						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							System.out.println(imageUri);
						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							System.out.println(imageUri);
						}

						@Override
						public void onLoadingCancelled(String imageUri,
								View view) {
							System.out.println(imageUri);
						}

					});
		}
	}
	
}
