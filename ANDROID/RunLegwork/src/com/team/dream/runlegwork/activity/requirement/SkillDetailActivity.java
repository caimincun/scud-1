package com.team.dream.runlegwork.activity.requirement;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.adapter.PushOrderAdapter.OnSetDataListener;
import com.team.dream.runlegwork.dialog.DataPickDialogFragment;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.entity.ShowTimeLine;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.entity.SkillAndUser;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.interfaces.OnMyDialogClickListener;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class SkillDetailActivity extends BaseActivity implements
		OnSetDataListener, OnMyDialogClickListener {
	@InjectView(R.id.skilldetail_mtb)
	MainTitileBar mtb;
	@InjectView(R.id.skilldetail_tvSend)
	TextView tvSend;
	@InjectView(R.id.skilldetail_edtSimplePrice)
	EditText edtSimplePrice;
	@InjectView(R.id.skilldetail_edtTitle)
	EditText edtTitle;
	@InjectView(R.id.skilldetail_edtMoney)
	EditText edtMoney;
	@InjectView(R.id.skilldetail_tvTime)
	TextView tvTime;
	@InjectView(R.id.skilldetail_edtAddress)
	EditText edtAddress;
	@InjectView(R.id.skilldetail_tvAge)
	TextView tvAge;
	@InjectView(R.id.skilldetail_tvName)
	TextView tvName;
	@InjectView(R.id.skilldetail_ivSex)
	ImageView ivSex;
	@InjectView(R.id.skilldetail_ivHead)
	ImageView ivHead;
	@InjectView(R.id.skilldetail_tvDistance)
	TextView tvDistance;

	private TextView tvDate;

	private String selectDate;

	private Skill skill;
	private NearUserInfo userInfo;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_skilldetail);
		ButterKnife.inject(this);
		getExtras();
		initData();
	}

	private void getExtras() {
		if(getIntent().getExtras().containsKey("skillanduser")){
			SkillAndUser skillUser = (SkillAndUser) getIntent().getExtras().getSerializable("skillanduser");
			skill = new Skill();
			skill.setSkillContent(skillUser.getSkillContent());
			skill.setSkillTitle(skillUser.getSkillTitle());
			skill.setTradeFlag(skillUser.getTradeFlag());
			skill.setSkillMoney(skillUser.getSkillMoney());
			skill.setSkillUnit(skillUser.getSkillUnit());
			
			userInfo = new NearUserInfo();
			userInfo.setUserRealName(skillUser.getUserName());
			userInfo.setAge(skillUser.getAge()+"");
			userInfo.setUserToken(skillUser.getUserToken());
			userInfo.setUserInfoPicture(skillUser.getUserPicture());
			userInfo.setUserInfoSex(skillUser.getUserInfoSex());
			userInfo.setDistance(skillUser.getDistance()+"");
		}
		else{
			skill = (Skill) getIntent().getExtras().getSerializable("skill");
			userInfo = (NearUserInfo) getIntent().getExtras().getSerializable(
				"userinfo");
		}
		
	}

	private void initData() {
		mtb.setTitle("发起订单");
		edtSimplePrice.setText(skill.getSkillMoney()+skill.getSkillUnit());
		edtTitle.setText(skill.getSkillTitle());
		tvAge.setText(userInfo.getAge() + "");
		tvName.setText(userInfo.getUserRealName() + "");
		if (userInfo.getUserInfoSex() == 1) {
			SingletonServiceManager.getInstance().display(
					"drawable://" + R.drawable.icon_boy, ivSex,
					R.drawable.icon_boy, null);
		} else {
			SingletonServiceManager.getInstance().display(
					"drawable://" + R.drawable.icon_gril, ivSex,
					R.drawable.icon_boy, null);
		}
		tvDistance.setText(userInfo.getUserDantce());
		SingletonServiceManager.getInstance().display(userInfo.getUserInfoPicture(), ivHead, R.drawable.user_default_head, null);
	}

	@OnClick(R.id.skilldetail_tvTime)
	public void getDate() {
		SetDate(tvTime,null);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}

	@Override
	public void onDialogDone(String tag, boolean cancelled, CharSequence message) {
		if (!cancelled) {
			selectDate = message.toString();
			tvTime.setText(selectDate);
		}
	}

	@Override
	public void ChoiceNeed(View v,ShowTimeLine item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SetDate(View v,ShowTimeLine item) {
		tvDate = (TextView) v;
		showDataPickerDialog();
	}

	private void showDataPickerDialog() {
		DataPickDialogFragment dataPickDialogFragment = DataPickDialogFragment
				.newInstance(selectDate);
		dataPickDialogFragment.show(getSupportFragmentManager(), "select time");
		dataPickDialogFragment.setListener(this);
	}
	@OnClick(R.id.skilldetail_tvSend)
	public void send(){
		String money = edtMoney.getText().toString();
		if(StringUtils.isEmpty(money)){
			Tool.showToast(this, "请输入价格");
			return;
		}
		showProgressDialog();
		UserOrder userOrder = new UserOrder();
		
		userOrder.setOrderComplteFlag(1);
		userOrder.setOrderTitle(skill.getSkillTitle());
		userOrder.setOrderContent(skill.getSkillContent());
		userOrder.setOrderMoney(Double.parseDouble(money));
		userOrder.setOrderServiceAddress(skill.getTradeFlag() == 1?"线上交易":"线下交易");	
		userOrder.setOrderAcptUsken(userInfo.getUserToken());
		
		api.sendOrderWithSkill(userOrder, new JsonBooleanResponseHandler() {
			
			@Override
			public void onSuccess() {
				removeProgressDialog();
				Tool.showToast(SkillDetailActivity.this, "发起订单成功，请等待对方验证");
			}
			
			@Override
			public void onFailure(String errMsg) {
				removeProgressDialog();
				Tool.showToast(SkillDetailActivity.this, errMsg);
			}
		});
	}
}
