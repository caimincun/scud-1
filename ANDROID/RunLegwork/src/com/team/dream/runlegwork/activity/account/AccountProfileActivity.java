package com.team.dream.runlegwork.activity.account;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.DataApplication;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.dialog.DialogSingleChoice;
import com.team.dream.runlegwork.dialog.DialogSingleChoiceGenderAdapter;
import com.team.dream.runlegwork.dialog.DialogSingleChoiceMenuItem;
import com.team.dream.runlegwork.dialog.DialogTextEdit;
import com.team.dream.runlegwork.entity.Account;
import com.team.dream.runlegwork.tool.RegexUtil;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.view.MenuItem1;
import com.team.dream.runlegwork.widget.MainTitileBar;

/**
 * 用户详情界面
 * @author Administrator
 *
 */
public class AccountProfileActivity extends BaseActivity{
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
	@InjectView(R.id.activity_account_profile_btn)
	Button btnsave;
	@InjectView(R.id.ctivity_accountprofile_topbar)
	MainTitileBar mtb;
	String name,sex,signer,email,idcard,label,imageurl;
	Context ctx;

	ProgressDialog pdg;
	Account account;
	private List<DialogSingleChoiceMenuItem> genderItems = new ArrayList<DialogSingleChoiceMenuItem>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
		genderItems.add(new DialogSingleChoiceMenuItem(0, "男",Account.Sex.MALE));
		genderItems.add(new DialogSingleChoiceMenuItem(1, "女",Account.Sex.FEMALE));
		genderItems.add(new DialogSingleChoiceMenuItem(2, "保密",Account.Sex.SECRET));
		account = DataApplication.mAccount;
		
		if(account!=null){
			
		
		name = account.getUserRealName();
		email = account.getUserInfoEmail();
		signer = account.getUserInfoSignature();
		int sex1 = account.getUserInfoSex();
		idcard = account.getUserIdCardNum();
		label = account.getUserInfoLabel();
		
		miname.setRightText(name);
		miemail.setRightText(email);
		miIdcard.setRightText(idcard);
		miLabel.setRightText(label);
		if(sex1==0){sex="男";}
		else if(sex1==1){sex="女";}
		else{sex="保密";}
		misex.setTag(sex1);
		misex.setRightText(sex);
		misigner.setRightText(signer);
		}
	}
	
	private void goBack(){
		finish();
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
					Toast.makeText(AccountProfileActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
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
				case Account.Sex.MALE:
					sex = "男";
					misex.setRightText(sex);
					misex.setTag(0);
					break;
				case Account.Sex.FEMALE:
					sex = "女";
					misex.setRightText(sex);
					misex.setTag(1);
					break;
				case Account.Sex.SECRET:
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
				// TODO Auto-generated method stub
				final String content = dialogTextEdit.getEditContent().trim();
				email = content;
				if(content.length()>0){
					Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
					Log.d(tag, "修改邮箱:"+content);
					if(email!=null && !"".equals(email) && !RegexUtil.isEmail(email)){
						Toast.makeText(ctx, "邮箱格式不正确", 1).show();
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				final String content = dialogTextEdit.getEditContent().trim();
				name = content;
				if(content.length()>0){
					Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
					Log.d(tag, "修改姓名:"+content);
					miname.setRightText(content);
					Tool.cancelAlertDialog();
				}else{
					Toast.makeText(AccountProfileActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
				}
			}
		})
		.setRightBtnOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				final String content = dialogTextEdit.getEditContent().trim();
				label = content;
				if(content.length()>0){
					Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
					Log.d(tag, "修改地址:"+content);
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				Tool.cancelAlertDialog();Tool.hiddenSoftKeyboard(AccountProfileActivity.this,dialogTextEdit.getFocusView());
			}
		});
		Tool.showAlertDialog(AccountProfileActivity.this,dialogTextEdit, true, true);
	}
	
	@OnClick(R.id.activity_account_profile_btn)
	public void save(){
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ButterKnife.reset(this);
	}
	
	
}
