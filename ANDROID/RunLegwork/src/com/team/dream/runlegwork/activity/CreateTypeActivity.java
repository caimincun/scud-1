package com.team.dream.runlegwork.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import butterknife.InjectView;
import butterknife.OnClick;

import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.ShopTypeAdapter;
import com.team.dream.runlegwork.entity.Producttype;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.ArrayEntityResponse;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;

public class CreateTypeActivity extends BaseActivity {

	@InjectView(R.id.mt_bar)
	MainTitileBar mtTitle;

	@InjectView(R.id.lv_type)
	ListView lvType;
	@InjectView(R.id.ll_push_goods)
	LinearLayout llPushGoods;

	private ShopTypeAdapter mAdapter;
	private List<Producttype> mTypes = new ArrayList<Producttype>();

	private String typeName;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_create_type);

		mtTitle.hideTitleRight();
		mtTitle.setTitle("分类管理");

		initList();
	}

	private void initList() {
		mAdapter = new ShopTypeAdapter(mTypes, this);
		api.querylistproductTypes(new JsonObjectResponseHandler<ArrayEntityResponse<Producttype>>() {

			@Override
			public void onSuccess(ArrayEntityResponse<Producttype> response) {
				mTypes.clear();
				mTypes.addAll(response.getData());
				lvType.setAdapter(mAdapter);

			}

			@Override
			public void onFailure(String errMsg) {
				ToastUtils.show(CreateTypeActivity.this, errMsg);
			}
		});
	}

	@OnClick(R.id.ll_push_goods)
	public void add() {

		final EditText editText = new EditText(this);
		editText.setHint("请输入分类");
		new AlertDialog.Builder(this).setTitle("添加分类")
				.setIcon(android.R.drawable.ic_dialog_info).setView(editText)
				.setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						typeName = editText.getText().toString();
						addType();

					}
				}).setNegativeButton("取消", null).show();

	}

	private void addType() {
		if (StringUtils.isEmpty(typeName)) {
			ToastUtils.show(this, "请输入类型名称");
		}
		api.saveGoodsType(typeName, new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				initList();
			}

			@Override
			public void onFailure(String errMsg) {
				ToastUtils.show(CreateTypeActivity.this, errMsg);

			}
		});
	}

}
