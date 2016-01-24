package com.team.dream.runlegwork.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.SingletonServiceManager;
import com.team.dream.runlegwork.activity.SelectOrderOrSkillActvity;
import com.team.dream.runlegwork.adapter.PushSkillAdapter;
import com.team.dream.runlegwork.adapter.PushSkillAdapter.OnSetDataListener;
import com.team.dream.runlegwork.entity.ShowTimeLine;
import com.team.dream.runlegwork.entity.Skill;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.EntityResponse;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.SelectPicShowHelper;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class PushSkillFragment extends BaseFragment implements
		OnSetDataListener, OnCheckedChangeListener {

	private static final String KEY_IS_UPDATE = "update";

	@InjectView(R.id.mb_topbar)
	MainTitileBar mbTopbar;
	@InjectView(R.id.lv_push_order)
	ListView lvHomePage;
	@InjectView(R.id.tv_push_order)
	TextView tvPushOrder;
	@InjectView(R.id.rl_root_view)
	RelativeLayout rlRootView;
	private PushSkillAdapter mAdapter;
	private ViewHoler holer;

	private TextView tvType;
	private boolean isOnline;

	private SelectPicShowHelper helper;
	private String isUpdate;
	private PicUrlAdapter mlAapter;
	private String[] urls;
	private List<TempPic> mPicList = new ArrayList<PushSkillFragment.TempPic>();

	@Override
	protected void initializePresenter() {
		isUpdate = getArguments().getString(KEY_IS_UPDATE);

		helper = new SelectPicShowHelper(getActivity(), true);

	}

	public static PushSkillFragment newInstance(String isUpdate) {
		PushSkillFragment fragment = new PushSkillFragment();
		Bundle bundle = new Bundle();
		bundle.putString(KEY_IS_UPDATE, isUpdate);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_push_order, container,
				false);
		ButterKnife.inject(this, view);
		mbTopbar.setTitle("技能发布 ");
		mbTopbar.hideTitleRight();

		View foot = inflater.inflate(R.layout.adapter_push_skill_select,
				lvHomePage, false);
		lvHomePage.addFooterView(foot);
		holer = new ViewHoler(foot);
		if (!StringUtils.isEmpty(isUpdate)) {
			obtainDetailSkill();
		} else {
			setAdapter();
		}

		holer.rgOnlineSelect.check(holer.rbOnline.getId());
		holer.etSkillRemark.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				// 在TOUCH的UP事件中，要保存当前的行下标，因为弹出软键盘后，整个画面会被重画
				// 在getView方法的最后，要根据index和当前的行下标手动为EditText设置焦点
				if (event.getAction() == MotionEvent.ACTION_UP) {
					holer.etSkillRemark.requestFocus();
				}
				return false;
			}
		});

		holer.rgOnlineSelect.setOnCheckedChangeListener(this);
		holer.ivSelectPic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mPicList.size() >= 3) {
					ToastUtils.show(getActivity(), "上传图片不能超过3张");
					return;
				}
				helper.showWindow();
			}
		});
		rlRootView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						int screenHeight = rlRootView.getRootView().getHeight();
						int myHeight = rlRootView.getHeight();
						int heightDiff = screenHeight - myHeight;
						Log.e("onGlobalLayout", "screenHeight=" + screenHeight);
						Log.e("onGlobalLayout", "myHeight=" + myHeight);
						int stateBarHeight = AppUtils.dip2px(getActivity(),
								AppUtils.getStatusBarHeight(getActivity()));

						if (heightDiff > stateBarHeight) {
							tvPushOrder.setVisibility(View.GONE);
						} else {
							tvPushOrder.setVisibility(View.VISIBLE);
						}

					}
				});

		return view;
	}

	private void setAdapter() {
		mAdapter = new PushSkillAdapter(getActivity());
		lvHomePage.setAdapter(mAdapter);
		mAdapter.setOnSetDataListener(this);
		mlAapter = new PicUrlAdapter();
		holer.gvSelectPic.setAdapter(mlAapter);
	}

	private void setListener(Skill skill) {
		mAdapter.setOnSetDataListener(this);
		tvPushOrder.setText("更新");
		urls = skill.getSkillPicture().split(";");
		if (urls.length <= 0) {
			return;
		}
		for (String url : urls) {
			TempPic temp = new TempPic();
			temp.picUrl = url;
			mPicList.add(temp);
		}
		mlAapter = new PicUrlAdapter();
		holer.etSkillRemark.setText(skill.getSkillRemark());
		holer.gvSelectPic.setAdapter(mlAapter);

	}

	private void obtainDetailSkill() {
		api.getSkilldetail(isUpdate,
				new JsonObjectResponseHandler<EntityResponse<Skill>>() {

					@Override
					public void onSuccess(EntityResponse<Skill> response) {
						mAdapter = new PushSkillAdapter(getActivity(), response
								.getData());
						lvHomePage.setAdapter(mAdapter);
						setListener(response.getData());
					}

					@Override
					public void onFailure(String errMsg) {
						ToastUtils.show(getActivity(), "获取失败");
						setAdapter();

					}
				});

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (group.getId()) {
		case R.id.rg_online_select:
			if (checkedId == R.id.rb_online) {
				isOnline = true;
			} else {
				isOnline = false;
			}
			break;
		}
	}

	class ViewHoler {
		@InjectView(R.id.iv_select_pic)
		ImageView ivSelectPic;
		@InjectView(R.id.gv_select_pic)
		GridView gvSelectPic;
		@InjectView(R.id.rg_online_select)
		RadioGroup rgOnlineSelect;
		@InjectView(R.id.rb_online)
		RadioButton rbOnline;
		@InjectView(R.id.et_skill_remark)
		EditText etSkillRemark;

		public ViewHoler(View view) {
			ButterKnife.inject(this, view);
		}
	}

	@OnClick(R.id.tv_push_order)
	public void pushSkill() {
		String[] checkString = getCheckStringFor();

		String msg = AppUtils
				.CheckViewEmpty(
						getResources().getStringArray(R.array.check_skill),
						checkString);
		if (msg.equals(getString(R.string.success))) {
			pushSkill(checkString);
		} else {
			ToastUtils.show(getActivity(), msg);
		}

	}

	private String[] getCheckStringFor() {
		String[] mDataString = new String[5];
		for (int i = 0; i < mAdapter.getCheckData().length; i++) {
			ShowTimeLine line = mAdapter.getDataMap().get(
					mAdapter.getCheckData()[i]);
			if (i == 4) {
				mDataString[i] = holer.etSkillRemark.getText().toString()
						.trim();
				break;
			}
			if (line == null)
				mDataString[i] = null;
			else {
				mDataString[i] = line.getInputData();
			}
		}
		return mDataString;
	}

	private void pushSkill(String[] checkString) {
		Skill skill = new Skill();
		skill.setSkillTitle(checkString[0]);
		skill.setSkillMoney(checkString[2]);
		skill.setSkillContent(checkString[3]);
		skill.setSkillSort(checkString[1]);
		skill.setSkillRemark(checkString[4]);
		skill.setTradeFlag(isOnline ? 1 : 2);
		skill.setSkillUnit(mAdapter.getDataMap()
				.get(mAdapter.getCheckData()[4]).getInputData());
		if (!TextUtils.isEmpty(isUpdate)) {
			skill.setSkillToken(isUpdate);
		}
		if (!TextUtils.isEmpty(isUpdate)) {
			update(skill);
		} else {
			if (mPicList.size() == 0)
				ToastUtils.show(getActivity(), "上传图片不能小于1张");
			create(skill);
		}

	}

	private void create(Skill skill) {
		api.createSkill(skill, mPicList, new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				getActivity().finish();
				ToastUtils.show(getActivity(), R.string.push_skill_success);
			}

			@Override
			public void onFailure(String errMsg) {
				ToastUtils.show(getActivity(), R.string.push_skill_failed);
			}
		});
	}

	private void update(Skill skill) {
		api.updateUserSkill(skill, mPicList, new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				ToastUtils.show(getActivity(), "修改成功");
				getActivity().finish();

			}

			@Override
			public void onFailure(String errMsg) {
				ToastUtils.show(getActivity(), "修改失败");
			}
		});
	}

	private ShowTimeLine line;

	@Override
	public void ChoiceNeed(View v, ShowTimeLine line) {
		tvType = (TextView) v;
		this.line = line;
		Navigator.NavigatorToSelectOrderOrSkillActivity(getActivity());
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		Bundle mBundle;
		switch (requestCode) {
		case SelectOrderOrSkillActvity.REQUEST_TYPE:
			tvType.setText(data.getStringExtra("data"));
			line.setInputData(data.getStringExtra("data"));
			mAdapter.getDataMap().put(mAdapter.getCheckData()[1], line);
			break;
		case SelectPicShowHelper.REQUESTCODE_CAMERA:
			helper.getImgRequsetAndCorp(300);
			break;
		case SelectPicShowHelper.REQUESTCODE_PICTURE:
			mBundle = data.getExtras();
			if (mBundle == null)
				break;
			initBitmap(mBundle);
			break;
		case SelectPicShowHelper.PHOTO_REQUEST_GALLERY:
			if (data != null)
				helper.startPhotoZoom(data.getData(), 300);
			break;
		case SelectPicShowHelper.PHOTO_REQUEST_CUT:
			mBundle = data.getExtras();
			if (mBundle == null)
				break;
			initBitmap(mBundle);
			break;
		}
	}

	private void initBitmap(Bundle mBundle) {
		Bitmap bitmap = (Bitmap) mBundle.get("data");
		TempPic temp = new TempPic();
		temp.bitmap = bitmap;
		mPicList.add(temp);
		mlAapter.notifyDataSetChanged();

	}

	class PicUrlAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mPicList.size();
		}

		@Override
		public Object getItem(int arg0) {
			return mPicList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ViewHoler holer;
			TempPic item = mPicList.get(arg0);
			if (null == arg1) {
				arg1 = LayoutInflater.from(getActivity()).inflate(
						R.layout.adapter_skill_pic, arg2, false);
				holer = new ViewHoler(arg1);
				arg1.setTag(holer);
			} else {
				holer = (ViewHoler) arg1.getTag();
			}
			if (!StringUtils.isEmpty(item.picUrl)) {
				String url = "http://scud-skills.bj.bcebos.com" + urls[arg0];
				SingletonServiceManager.getInstance().display(url, holer.ivPic,
						0, null);
			} else if (item.bitmap != null) {
				holer.ivPic.setImageBitmap(item.bitmap);
			}

			return arg1;
		}

		class ViewHoler {
			@InjectView(R.id.iv_pic)
			ImageView ivPic;

			public ViewHoler(View view) {
				ButterKnife.inject(this, view);
			}
		}

	}

	public class TempPic {
		public String picUrl;
		public Bitmap bitmap;
	}
}
