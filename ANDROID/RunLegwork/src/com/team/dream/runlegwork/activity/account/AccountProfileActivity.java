package com.team.dream.runlegwork.activity.account;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.imageloader.core.assist.FailReason;
import com.team.dream.imageloader.core.listener.ImageLoadingListener;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.DataApplication;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.dialog.DialogSingleChoice;
import com.team.dream.runlegwork.dialog.DialogSingleChoiceGenderAdapter;
import com.team.dream.runlegwork.dialog.DialogSingleChoiceMenuItem;
import com.team.dream.runlegwork.dialog.DialogTextEdit;
import com.team.dream.runlegwork.dialog.DialogTextEdit1;
import com.team.dream.runlegwork.dialog.XgHeadDialogUtil;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.tool.RegexUtil;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.PathUtil;
import com.team.dream.runlegwork.utils.StreamUtil;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.view.MenuItem1;
import com.team.dream.runlegwork.widget.MainTitileBar;

/**
 * 用户详情界面
 * @author Administrator
 *
 */
public class AccountProfileActivity extends BaseActivity implements OnClickListener{
	private static final String tag = AccountProfileActivity.class.getSimpleName();
	
	@InjectView(R.id.activity_account_profile_miname)
	MenuItem1 miname;
	@InjectView(R.id.activity_account_profile_miIdcard)
	MenuItem1 miIdcard;
	@InjectView(R.id.activity_account_profile_misex)
	MenuItem1 misex;
	@InjectView(R.id.activity_account_profile_miemail)
	MenuItem1 miemail;
	@InjectView(R.id.activity_account_profile_miLabel)
	MenuItem1 miLabel;
	@InjectView(R.id.activity_account_profile_misigner)
	MenuItem1 misigner;
	@InjectView(R.id.activity_account_profile_miIntriduce)
	MenuItem1 miIntriduce;
	@InjectView(R.id.activity_account_profile_btn)
	Button btnsave;
	@InjectView(R.id.ctivity_accountprofile_topbar)
	MainTitileBar mtb;
	@InjectView(R.id.activity_account_profile_ivhead)
	ImageView ivHead;
	String name,sex,signer,email,idcard,userJob,imageurl,peoIntriduce;
	Context ctx;

	ProgressDialog pdg;
	UserInfo account;
	private XgHeadDialogUtil xgHeadDialog;
	private List<DialogSingleChoiceMenuItem> genderItems = new ArrayList<DialogSingleChoiceMenuItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_profile);
		ButterKnife.inject(this);
		initView();
	}

	

	private void initView(){
		ctx = this;
		//设置标题
		mtb.setTitle(R.string.accountprofile_title);
		mtb.hideTitleRight();
		genderItems.clear();
		genderItems.add(new DialogSingleChoiceMenuItem(0, "男",UserInfo.Sex.MALE));
		genderItems.add(new DialogSingleChoiceMenuItem(1, "女",UserInfo.Sex.FEMALE));
		genderItems.add(new DialogSingleChoiceMenuItem(2, "保密",UserInfo.Sex.SECRET));
//		account = DataApplication.mAccount;
		account = AccountManager.getInstance().getUserinfo();
		
		if(account!=null){
			
		
		name = account.getUserRealName();
		email = account.getUserInfoEmail();
		signer = account.getUserInfoSignature();
		int sex1 = account.getUserInfoSex();
		idcard = account.getUserIdCardNum();
		userJob = account.getUserInfoJob();
		
		miname.setRightText(name);
		miemail.setRightText(email);
		miIdcard.setRightText(idcard);
		miLabel.setRightText(userJob);
		miIntriduce.setRightText(account.getUserInfoIntroduction());
		if(sex1==0){sex="男";}
		else if(sex1==1){sex="女";}
		else{sex="保密";}
		misex.setTag(sex1);
		misex.setRightText(sex);
		misigner.setRightText(signer);
		}
	}
	/**
	 * 修改身份证号码
	 * @param oldNickName
	 */
	@OnClick(R.id.activity_account_profile_miIdcard)
	public void modifyIdcard(){
		String oldNickName =miIdcard.getRightText().toString();
		final DialogTextEdit dialogTextEdit = new DialogTextEdit(this);
		dialogTextEdit.setTitleText("修改身份证号码")
		.setEditTextContent(oldNickName)
		.setEditTextHint("请输入身份证号码")
		.setSingleLine(true)
		.setLeftBtnContent("保存")
		.setRightBtnContent("取消")
		.setLeftBtnOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String content = dialogTextEdit.getEditContent().trim();
				idcard = content;
				if(content.length()>0){
					Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
					Log.d(tag, "修改身份证号码:"+content);
					miIdcard.setRightText(content);
					Tool.cancelAlertDialog();
				}else{
					Toast.makeText(AccountProfileActivity.this, "身份证号码不能为空", Toast.LENGTH_SHORT).show();
				}
			}
		}).setRightBtnOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {Tool.cancelAlertDialog();Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());}
		});
		Tool.showAlertDialog(AccountProfileActivity.this,dialogTextEdit, true, true);
	}
	/**
	 * 选择性别
	 */
	@OnClick(R.id.activity_account_profile_misex)
	public void choiceSex(){
		DialogSingleChoice dialogSingleChoice = new DialogSingleChoice(this);
		dialogSingleChoice.setTitleText("设置性别")
		.setAdapter(new DialogSingleChoiceGenderAdapter(this ,genderItems,Integer.parseInt(misex.getTag().toString())))
		.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,View view, int position, long id) {
				Tool.cancelAlertDialog();
				dialogListItemClick(position);
			}
			
			private void dialogListItemClick(int position){
				DialogSingleChoiceMenuItem dscmSex = genderItems.get(position);
				switch(dscmSex.menuCommand){
				case UserInfo.Sex.MALE:
					sex = "男";
					misex.setRightText(sex);
					misex.setTag(0);
					break;
				case UserInfo.Sex.FEMALE:
					sex = "女";
					misex.setRightText(sex);
					misex.setTag(1);
					break;
				case UserInfo.Sex.SECRET:
					sex = "保密";
					misex.setRightText(sex);
					misex.setTag(2);
					break;
				}
			}
		});
		Tool.showAlertDialog(this, dialogSingleChoice, true, true);
	}
	/**
	 * 修改邮箱
	 * @param oldName
	 */
	@OnClick(R.id.activity_account_profile_miemail)
	public void modifyEmail(){
		String oldEmail = miemail.getRightText().toString();
		final DialogTextEdit dialogTextEdit = new DialogTextEdit(this);
		dialogTextEdit.setTitleText("修改邮箱")
		.setEditTextContent(oldEmail)
		.setEditTextHint("请输入邮箱")
		.setSingleLine(true)
		.setLeftBtnContent("保存")
		.setRightBtnContent("取消")
		.setLeftBtnOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final String content = dialogTextEdit.getEditContent().trim();
				email = content;
				if(content.length()>0){
					Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
					Log.d(tag, "修改邮箱:"+content);
					if(email!=null && !"".equals(email) && !RegexUtil.isEmail(email)){
						Toast.makeText(ctx, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
					}
					else{
						miemail.setRightText(content);
					}
					Tool.cancelAlertDialog();
				}else{
					Toast.makeText(AccountProfileActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
				}
			}
		})
		.setRightBtnOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Tool.cancelAlertDialog();Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
			}
		});
		Tool.showAlertDialog(AccountProfileActivity.this,dialogTextEdit, true, true);
	}
	/**
	 * 修改姓名
	 * @param oldName
	 */
	@OnClick(R.id.activity_account_profile_miname)
	public void modifyName(){
		String oldName = miname.getRightText().toString();
		final DialogTextEdit dialogTextEdit = new DialogTextEdit(this);
		dialogTextEdit.setTitleText("修改姓名")
		.setEditTextContent(oldName)
		.setEditTextHint("请输入姓名")
		.setSingleLine(true)
		.setLeftBtnContent("保存")
		.setRightBtnContent("取消")
		.setLeftBtnOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final String content = dialogTextEdit.getEditContent().trim();
				name = content;
				if(content.length()>0){
					Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
					Log.d(tag, "修改姓名:"+content);
					miname.setRightText(content);
					Tool.cancelAlertDialog();
				}else{
					Toast.makeText(AccountProfileActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
				}
			}
		})
		.setRightBtnOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Tool.cancelAlertDialog();Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
			}
		});
		Tool.showAlertDialog(AccountProfileActivity.this,dialogTextEdit, true, true);
	}
	/**
	 * 修改地址
	 * @param oldName
	 */
	@OnClick(R.id.activity_account_profile_miLabel)
	public void modifyAddress(){
		String oldAddress = miLabel.getRightText().toString();
		final DialogTextEdit dialogTextEdit = new DialogTextEdit(this);
		dialogTextEdit.setTitleText("修改职位")
		.setEditTextContent(oldAddress)
		.setEditTextHint("请输入职位")
		.setSingleLine(true)
		.setLeftBtnContent("保存")
		.setRightBtnContent("取消")
		.setLeftBtnOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final String content = dialogTextEdit.getEditContent().trim();
				userJob = content;
				if(content.length()>0){
					Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
					Log.d(tag, "修改职位:"+content);
					miLabel.setRightText(content);
					Tool.cancelAlertDialog();
				}else{
					Toast.makeText(AccountProfileActivity.this, "地址不能为空", Toast.LENGTH_SHORT).show();
				}
			}
		})
		.setRightBtnOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Tool.cancelAlertDialog();Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
			}
		});
		Tool.showAlertDialog(AccountProfileActivity.this,dialogTextEdit, true, true);
	}
	/**
	 * 修改签名
	 * @param oldSigner
	 */
	@OnClick(R.id.activity_account_profile_misigner)
	public void modifySigner(){
		String oldSigner = misigner.getRightText().toString();
		final DialogTextEdit dialogTextEdit = new DialogTextEdit(this);
		dialogTextEdit.setTitleText("修改签名")
		.setEditTextContent(oldSigner)
		.setEditTextHint("请输入签名")
		.setSingleLine(true)
		.setLeftBtnContent("保存")
		.setRightBtnContent("取消")
		.setLeftBtnOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final String content = dialogTextEdit.getEditContent().trim();
				signer = content;
				if(content.length()>0){
					Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
					Log.d(tag, "修改签名:"+content);
					misigner.setRightText(content);
					Tool.cancelAlertDialog();
				}else{
					Toast.makeText(AccountProfileActivity.this, "签名不能为空", Toast.LENGTH_SHORT).show();
				}
			}
		})
		.setRightBtnOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Tool.cancelAlertDialog();Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
			}
		});
		Tool.showAlertDialog(AccountProfileActivity.this,dialogTextEdit, true, true);
	}
	@OnClick(R.id.activity_account_profile_miIntriduce)
	public void modifyIntriduce(){
		String oldIntriduce =miIntriduce.getRightText().toString();
		final DialogTextEdit1 dialogTextEdit = new DialogTextEdit1(this);
		dialogTextEdit.setTitleText("修改个人简介")
		.setEditTextContent(oldIntriduce)
		.setEditTextHint("请输入您的简介")
		.setLeftBtnContent("保存")
		.setRightBtnContent("取消")
		.setLeftBtnOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String content = dialogTextEdit.getEditContent().trim();
				peoIntriduce = content;
				if(content.length()>0){
					Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
					Log.d(tag, "修改个人简介:"+content);
					miIntriduce.setRightText(content);
					Tool.cancelAlertDialog();
				}else{
					Toast.makeText(AccountProfileActivity.this, "个人简介不能为空", Toast.LENGTH_SHORT).show();
				}
			}
		}).setRightBtnOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {Tool.cancelAlertDialog();Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());}
		});
		Tool.showAlertDialog(AccountProfileActivity.this,dialogTextEdit, true, true);
	}
	@OnClick(R.id.activity_account_profile_ivhead)
	public void updateHead(){
		xgHeadDialog = new XgHeadDialogUtil(this);
		xgHeadDialog.setOnClickListeners(this);
		xgHeadDialog.show();
	}
	@OnClick(R.id.activity_account_profile_btn)
	public void save(){
		int mSex;
		if(StringUtils.isEmpty(sex)){
			mSex=0;
		}
		else{
			mSex = (Integer) misex.getTag();
		}
		UserInfo userInfo = new UserInfo(name, idcard, email, mSex, userJob, signer, userJob, AccountManager.getInstance().getUserToken(),peoIntriduce);
		Log.d(tag, userInfo.toString());
		api.updateUserInfo(userInfo, new JsonBooleanResponseHandler() {
			
			@Override
			public void onSuccess() {
				Log.d(tag, "成功");
				ToastUtils.show(AccountProfileActivity.this, "修改成功");
			}
			
			@Override
			public void onFailure(String errMsg) {
				Log.d(tag,  "修改失败"+errMsg);
				ToastUtils.show(AccountProfileActivity.this, "修改失败"+errMsg);
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
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
			File file = new File("/sdcard/temp.png");

			String mPath = null;
			if (requestCode == 101) {
				mPath = PathUtil.getPath(this, data.getData());
				if(mPath == null) {
					File file2 = new File("/sdcard/temp.png");

					Bitmap bm = data.getParcelableExtra("data");
					StreamUtil.saveBitmap(file2.getAbsolutePath(), bm, 100);

					mPath = file2.getAbsolutePath();
				}
			} else if (requestCode == 100) {
				Uri originalUri = data.getData();
				String[] proj = {MediaStore.Images.Media.DATA};
	            //android多媒体数据库的封装接口，具体的看Android文档
	            
				Cursor cursor = managedQuery(originalUri, proj, null, null, null); 
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
	
    /**
     * 保存裁剪的头像
     * 
     * @param data
     */
    private void saveCropAvator() {
    	File file = new File("/sdcard/temp.png");
    	if(file.exists()){
//			uploadHead(file.getAbsolutePath());
    		Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
    		api.uploadUserhead(bitmap, new JsonBooleanResponseHandler() {
				
				@Override
				public void onSuccess() {
					Log.d(tag, "");
					Tool.showToast(ctx, "头像上传成功");
				}
				
				@Override
				public void onFailure(String errMsg) {
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
		File filehead = new File("/sdcard/temp.png");
		ivHead.setImageResource(R.drawable.ic_launcher);
		if (filehead.exists()) {
			SingletonServiceManager.getInstance().imageLoader.clearMemoryCache();
			SingletonServiceManager.getInstance().imageLoader.clearDiskCache();
			SingletonServiceManager.getInstance().display(
					"file://" + filehead.getAbsolutePath(), ivHead,
					R.drawable.ic_launcher, new ImageLoadingListener() {
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
