package com.team.dream.runlegwork.adapter;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.NearUserInfo;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.StringUtils;

@SuppressLint("ResourceAsColor")
public class AnserOrderPersonAdapter extends BaseAdapter {

	private List<NearUserInfo> mData;
	private Context mConext;
	private Drawable ivMan;
	private Drawable ivWoMan;
	private boolean isHave = false;
	private onConfirmChangeListener onConfirmChangeListener;
	private boolean isCompelete;

	public AnserOrderPersonAdapter(List<NearUserInfo> mData, Context mContext,
			boolean isCompelete) {
		this.mConext = mContext;
		this.mData = mData;
		this.isCompelete = isCompelete;

		// get res
		ivMan = mConext.getResources().getDrawable(R.drawable.man);
		// 确认宽高 位置
		ivMan.setBounds(0, 0, ivMan.getMinimumWidth(), ivMan.getMinimumHeight());
		// get res
		ivWoMan = mConext.getResources().getDrawable(R.drawable.woman);
		// 确认宽高 位置
		ivWoMan.setBounds(0, 0, ivWoMan.getMinimumWidth(),
				ivWoMan.getMinimumHeight());
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int p, View convertView, ViewGroup parent) {

		int position = (int) getItemId(p);
		final NearUserInfo userInfo = mData.get(position);
		ViewHoler holer = null;
		if (null == convertView) {
			convertView = LayoutInflater.from(mConext).inflate(
					R.layout.adapter_answer_order_item, parent, false);
			holer = new ViewHoler(convertView);
			convertView.setTag(holer);
		} else {
			holer = (ViewHoler) convertView.getTag();
		}
		if (!isHave) {
			isHave = (userInfo.getIsAccess() == 1) ? true : false;
		}
		holer.tvOrderItemTitle.setVisibility(View.GONE);
		holer.rlOperate.setVisibility(View.GONE);
		holer.tvConmfirmOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (onConfirmChangeListener != null) {
					onConfirmChangeListener.onComfirmOrder(userInfo);
				}
			}
		});
		if (isHave) {
			holer.tvConmfirmOrder.setVisibility(View.GONE);
		}

		if (isHave && position == 0) {
			holer.rlOperate.setVisibility(View.VISIBLE);
			holer.tvOrderItemTitle.setVisibility(View.VISIBLE);
			holer.tvOrderItemTitle.setText("接单人");

			holer.tvTalk.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onConfirmChangeListener != null) {
						onConfirmChangeListener.onSelectTalk(userInfo);
					}

				}
			});
			holer.tvPhone.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (onConfirmChangeListener != null) {
						onConfirmChangeListener.onSelectCallPhone(userInfo);
					}
				}
			});

			if (!isCompelete) {

				holer.tvConfirm.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (onConfirmChangeListener != null) {
							onConfirmChangeListener.onConfirm();
						}

					}
				});

			}
		} else if (isHave && position == 1) {
			holer.tvOrderItemTitle.setVisibility(View.VISIBLE);
			holer.tvOrderItemTitle.setText("还有" + (mData.size() - 1)
					+ "个人(已应邀)");
		} else {
			if (position == 0) {
				holer.tvOrderItemTitle.setVisibility(View.VISIBLE);
			}
		}
		holer.tvName.setText(userInfo.getUserRealName());
		holer.tvDance.setText(userInfo.getDistance());
		String sign = String.valueOf(userInfo.getSkillMoney());

		if (StringUtils.isEmpty(sign)) {
			sign = "暂无";
		}
		holer.tvServerPrice.setText("Ta的报价： " + sign);
		if (userInfo.getUserInfoSex() == 1) {
			holer.tvAge.setCompoundDrawables(null, null, ivMan, null);
			holer.tvAge.setText("男");
		} else {
			holer.tvAge.setCompoundDrawables(null, null, ivWoMan, null);
			holer.tvAge.setText("女");
		}

		return convertView;
	}

	class ViewHoler {
		@InjectView(R.id.tv_name)
		TextView tvName;
		@InjectView(R.id.iv_head_img)
		ImageView ivHeadImg;
		@InjectView(R.id.tv_dantce)
		TextView tvDance;
		@InjectView(R.id.tv_server_price)
		TextView tvServerPrice;
		@InjectView(R.id.tv_age)
		TextView tvAge;
		@InjectView(R.id.tv_confirm_order)
		TextView tvConmfirmOrder;
		@InjectView(R.id.tv_talk)
		TextView tvTalk;
		@InjectView(R.id.tv_phone)
		TextView tvPhone;
		@InjectView(R.id.rl_operate)
		RelativeLayout rlOperate;
		@InjectView(R.id.tv_order_item_title)
		TextView tvOrderItemTitle;
		@InjectView(R.id.tv_confirm)
		TextView tvConfirm;

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}
	}

	public onConfirmChangeListener getOnConfirmChangeListener() {
		return onConfirmChangeListener;
	}

	public void setOnConfirmChangeListener(
			onConfirmChangeListener onConfirmChangeListener) {
		this.onConfirmChangeListener = onConfirmChangeListener;
	}

	public interface onConfirmChangeListener {
		void onComfirmOrder(NearUserInfo userInfo);

		void onSelectTalk(NearUserInfo userInfo);

		void onSelectCallPhone(NearUserInfo userInfo);

		void onConfirm();
	}
}
