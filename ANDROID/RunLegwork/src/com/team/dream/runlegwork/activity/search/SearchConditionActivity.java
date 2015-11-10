package com.team.dream.runlegwork.activity.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.search.SearchconditionAdapter;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class SearchConditionActivity extends BaseActivity {
	private Context ctx;
	@InjectView(R.id.searchcondition_gv)
	GridView gvSubscribe;
	@InjectView(R.id.searchcondition_gv1)
	GridView gvNoSubscribe;
	@InjectView(R.id.searchcondition_tvHidden)
	TextView tvHidden;
	@InjectView(R.id.searchcondition_mtb)
	MainTitileBar mtb;
	@InjectView(R.id.searchcondition_tvConfirm)
	TextView tvConfirm;

	int statusBarHeight;
	ArrayList<String> list;
	List<String> list1;
	SearchconditionAdapter mainAdapter, mainAdapter2;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_searchcondition);
		ButterKnife.inject(this);
		ctx = this;
		mtb.setTitle("条件");
		getStatusBarHeight();
		initListener();
		getExtrasData();
		initData();
	}
	private void getExtrasData() {
		list = new ArrayList<String>();
		String[] conditionStr  = (String[]) getIntent().getExtras().get("condition");
		list.addAll(Arrays.asList(conditionStr));
	}
	private void initListener() {
		gvSubscribe.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				if (arg2 != 0) {
					final String str = list.get(arg2);

					float x = arg1.getLeft();
					float y = arg1.getTop();
					LayoutParams params = new LayoutParams(
							arg1.getWidth()-dip2px(ctx, 4),
							arg1.getHeight());
					params.leftMargin = (int) x;
					params.topMargin = (int) y + statusBarHeight+dip2px(ctx, 10)+gvSubscribe.getTop();

					tvHidden.setLayoutParams(params);
					tvHidden.setVisibility(View.VISIBLE);
					tvHidden.setBackgroundResource(R.drawable.shape_condition);
					// getSpaceing();
					String temp = list.get(arg2);
					Log.d("TAG", temp);
					tvHidden.setText(temp);
					int size = list1.size();
					int tempSize = size;
					if (size != 0) {
						tempSize = list1.size() - 1;
					}

					float endwidth = 0;
					float endheitht = 0;
					View v = gvNoSubscribe.getChildAt(tempSize);
					if (v != null) {
						if(list1.size()%4==0){
							endwidth =   gvNoSubscribe.getLeft()-arg1.getLeft();
							endheitht = v.getTop() - arg1.getTop()
									+ (gvNoSubscribe.getTop() - gvSubscribe.getTop())+v.getHeight();
						}
						else{
							endwidth = v.getLeft() - arg1.getLeft()
								+ arg1.getWidth() + dip2px(ctx, 5);
							endheitht = v.getTop() - arg1.getTop()
								+ (gvNoSubscribe.getTop() - gvSubscribe.getTop());
						}
						
					}
					else{
						endwidth =   gvNoSubscribe.getLeft()-arg1.getLeft();
						endheitht = gvNoSubscribe.getTop() - gvSubscribe.getTop() - arg1.getTop();
					}
					

					TranslateAnimation ta = new TranslateAnimation(0, endwidth,
							0, endheitht);
					ta.setDuration(500);
					tvHidden.setAnimation(ta);

					ta.setAnimationListener(new AnimationListener() {

						@Override
						public void onAnimationStart(Animation arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationRepeat(Animation arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationEnd(Animation arg0) {
							list.remove(arg2);
							list1.add(str);
							tvHidden.setVisibility(View.GONE);
							dataChanged();

						}

					});
				}

			}
		});
		gvNoSubscribe.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
					long arg3) {

				final String str1 = list1.get(arg2);

				float x = arg1.getLeft();
				float y = arg1.getTop();
				LayoutParams params = new LayoutParams(
						arg1.getWidth()-dip2px(ctx, 4),
						arg1.getHeight());
				params.leftMargin = (int) x;
				params.topMargin = (int)( y + statusBarHeight+gvNoSubscribe.getTop());

				tvHidden.setLayoutParams(params);
				tvHidden.setVisibility(View.VISIBLE);
				tvHidden.setBackgroundResource(R.drawable.shape_condition2);
				String temp = list1.get(arg2);
				tvHidden.setText(temp);
				
				int size = list.size();
				int tempSize = size;
				if (size != 0) {
					tempSize = list.size() - 1;
				}

				float endwidth = 0;
				float endheitht = 0;
				View v = gvSubscribe.getChildAt(tempSize);
				if (v != null) {
					if(list.size()%4==0){
						endwidth =   gvSubscribe.getLeft()-arg1.getLeft();
						endheitht = v.getTop() - arg1.getTop()
								+ (gvSubscribe.getTop() - gvNoSubscribe.getTop())+v.getHeight();
					}
					else{
						endwidth = v.getLeft() - arg1.getLeft()
							+ arg1.getWidth() + dip2px(ctx, 5);
						endheitht = v.getTop() - arg1.getTop()
							+ (gvSubscribe.getTop() - gvNoSubscribe.getTop());
					}
					
				}
//				else{
//					endwidth =   gvNoSubscribe.getLeft()-arg1.getLeft();
//					endheitht = gvNoSubscribe.getTop() - gvSubscribe.getTop() - arg1.getTop();
//				}
				
				
				TranslateAnimation ta = new TranslateAnimation(0, endwidth,
						0, endheitht);
				ta.setDuration(500);
				tvHidden.setAnimation(ta);

				ta.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animation arg0) {
						list1.remove(arg2);
						list.add(str1);
						tvHidden.setVisibility(View.GONE);
						dataChanged();

					}

				});
			
				
				dataChanged();
			}
		});
	}
	private void initData() {
		list1 = new ArrayList<String>();


		list1.add("aa");
		list1.add("ss");
		list1.add("dd");
		list1.add("ff");
		list1.add("gg");
		list1.add("hh");
		list1.add("11qq");
		list1.add("aa");
		list1.add("ss");
		list1.add("dd");
		list1.add("ff");
		list1.add("gg");
		list1.add("hh");
		list1.add("11qq");
		dataChanged();
	}

	public void dataChanged() {
		if (mainAdapter == null) {
			mainAdapter = new SearchconditionAdapter(list, ctx,1);
			gvSubscribe.setAdapter(mainAdapter);
		} else {
			mainAdapter.notifyDataSetChanged();
		}
		if (mainAdapter2 == null) {
			mainAdapter2 = new SearchconditionAdapter(list1, ctx,2);
			gvNoSubscribe.setAdapter(mainAdapter2);
		} else {
			mainAdapter2.notifyDataSetChanged();
		}

	}
	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height",
				"dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	@OnClick(R.id.searchcondition_tvConfirm)
	public void confirm(){
		Intent intent = new Intent();
		intent.putStringArrayListExtra("listCondition", list);
		setResult(RESULT_OK, intent);
		finish();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.reset(this);
	}
}
