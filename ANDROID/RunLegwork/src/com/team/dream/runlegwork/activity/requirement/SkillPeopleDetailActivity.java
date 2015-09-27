package com.team.dream.runlegwork.activity.requirement;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.entity.SkillAndUser;
import com.team.dream.runlegwork.entity.UserOrder;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.tool.Tool;

public class SkillPeopleDetailActivity extends BaseActivity {
	@InjectView(R.id.skillpeopledetail_ivSkillone)
	ImageView ivPic1;
	@InjectView(R.id.skillpeopledetail_ivSkilltwo)
	ImageView ivPic2;
	@InjectView(R.id.skillpeopledetail_ivSkillthree)
	ImageView ivPic3;
	@InjectView(R.id.skillpeopledetail_ivHead)
	ImageView ivHead;
	@InjectView(R.id.skillpeopledetail_ivSex)
	ImageView ivSex;
	@InjectView(R.id.skillpeopledetail_tvName)
	TextView tvName;
	@InjectView(R.id.skillpeopledetail_tvAge)
	TextView tvAge;
	@InjectView(R.id.skillpeopledetail_tvMoney)
	TextView tvMoney;
	@InjectView(R.id.skillpeopledetail_tvSkillname)
	TextView tvSkillname;
	@InjectView(R.id.skillpeopledetail_tvSkilldetail)
	TextView tvSkillDetail;
	@InjectView(R.id.skillpeopledetail_tvRemark)
	TextView tvRemark;
	@InjectView(R.id.skillpeopledetail_tvOrder)
	TextView tvOrder;
	
	private SkillAndUser skillUser;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_skillpeopledetail);
		ButterKnife.inject(this);
		initExtras();
		initData();
	}
	private void initExtras() {
		skillUser = (SkillAndUser) getIntent().getExtras().getSerializable("skillpeople");
	}
	public void initData(){
		String picString = skillUser.getSkillPicture();
		String[] pictures = picString.split("\\;");
		switch (pictures.length) {
		case 1:
			SingletonServiceManager.getInstance().display(getpicUrl(pictures[0]), ivPic1, R.drawable.photo_1, null);
			break;
		case 2:
			SingletonServiceManager.getInstance().display(getpicUrl(pictures[0]), ivPic1, R.drawable.photo_1, null);
			SingletonServiceManager.getInstance().display(getpicUrl(pictures[1]), ivPic2, R.drawable.photo_1, null);
			break;
		case 3:
			SingletonServiceManager.getInstance().display(getpicUrl(pictures[0]), ivPic1, R.drawable.photo_1, null);
			SingletonServiceManager.getInstance().display(getpicUrl(pictures[1]), ivPic2, R.drawable.photo_1, null);
			SingletonServiceManager.getInstance().display(getpicUrl(pictures[2]), ivPic3, R.drawable.photo_1, null);
			break;

		default:
			break;
		}
		SingletonServiceManager.getInstance().display(getpicUrl(skillUser.getUserPicture()), ivHead, R.drawable.photo_1, null);
		tvName.setText(skillUser.getUserName()+"");
		tvMoney.setText(skillUser.getSkillMoney()+skillUser.getSkillUnit()+"");
		tvRemark.setText(skillUser.getSkillRemark()+"");
		tvSkillname.setText(skillUser.getSkillTitle()+"");
		tvSkillDetail.setText(skillUser.getSkillContent()+"");
		tvAge.setText(skillUser.getAge()+"");
		int sex = skillUser.getUserInfoSex();
		if(sex == 0){
			ivSex.setImageResource(R.drawable.icon_gril);
		}
		else{
			ivSex.setImageResource(R.drawable.icon_boy);
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
	
	public String getpicUrl(String url){
		return "http://scud-skills.bj.bcebos.com"+url;
	}
	@OnClick(R.id.skillpeopledetail_tvOrder)
	public void order(){
		UserOrder userOrder = new UserOrder();
		
		userOrder.setOrderComplteFlag(1);
		userOrder.setOrderTitle(skillUser.getSkillTitle());
		userOrder.setOrderContent(skillUser.getSkillContent());
		userOrder.setOrderMoney(100);
		userOrder.setOrderServiceAddress(skillUser.getTradeFlag() == 1?"线上交易":"线下交易");
		userOrder.setOrderAcptUsken(skillUser.getUserToken());
		
		api.sendOrderWithSkill(userOrder, new JsonBooleanResponseHandler() {
			
			@Override
			public void onSuccess() {
				Tool.showToast(SkillPeopleDetailActivity.this, "发起订单成功，请等待对方验证");
			}
			
			@Override
			public void onFailure(String errMsg) {
				Tool.showToast(SkillPeopleDetailActivity.this, errMsg);
			}
		});
	}
}