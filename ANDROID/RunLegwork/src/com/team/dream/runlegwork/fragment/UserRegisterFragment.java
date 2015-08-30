package com.team.dream.runlegwork.fragment;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.http.Header;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.team.dream.runlegwork.BaseFragment;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.UserInfo;
import com.team.dream.runlegwork.jpush.ExampleUtil;
import com.team.dream.runlegwork.navigator.Navigator;
import com.team.dream.runlegwork.net.JsonBooleanResponseHandler;
import com.team.dream.runlegwork.net.JsonObjectResponseHandler;
import com.team.dream.runlegwork.net.response.UserInfoResponse;
import com.team.dream.runlegwork.singleservice.AccountManager;
import com.team.dream.runlegwork.tool.Tool;
import com.team.dream.runlegwork.utils.AppUtils;
import com.team.dream.runlegwork.utils.StringUtils;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.TopBar;

public class UserRegisterFragment extends BaseFragment {
	private final String tag = UserRegisterFragment.class.getSimpleName();
	@InjectView(R.id.topbar)
	TopBar topBar;
	@InjectView(R.id.user_register)
	TextView tvRegister;
	@InjectView(R.id.user_name)
	EditText etUserName;
	@InjectView(R.id.user_password)
	EditText etUserPassword;
	@InjectView(R.id.user_confirmpwd)
	EditText etUserConPwd;
	private String username, password, conPassword;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user_register, container, false);
		ButterKnife.inject(this, view);
		topBar.initialze(getResources().getString(R.string.register));

		return view;
	}

	@OnClick(R.id.user_register)
	public void register() {
		if (!check()) {
			return;
		}
		api.register(username, password, new JsonBooleanResponseHandler() {

			@Override
			public void onSuccess() {
				AccountManager.getInstance().initUser(username);
				getUserinfoByToken();
			}

			@Override
			public void onSuccess(Header[] headers) {
				AppUtils.setHeader(headers);
			}

			@Override
			public void onFailure(String errMsg) {
				Log.d(tag, "注册失败" + errMsg);
			}
		});
	}

	private void getUserinfoByToken() {
		Log.d(tag, "开始");
		api.getUserinfoByToken(new JsonObjectResponseHandler<UserInfoResponse>() {

			@Override
			public void onFailure(String errMsg) {
				Log.d(tag, "错误" + errMsg);
			}

			@Override
			public void onSuccess(UserInfoResponse response) {
				UserInfo userInfo = response.getData();
				//设置tag
				setTag(userInfo.getUserToken());
				AccountManager.getInstance().setUserinfo(userInfo);
				Log.d(tag, userInfo.getUserInfoEmail() + "asdfs");
//				startActivity(new Intent(getActivity(), AccountProfileActivity.class));
				Navigator.NavigatorToMainActivity(getActivity());
			}
		});
	}
	public void setTag(String tag){
		
        // 检查 tag 的有效性
		if (TextUtils.isEmpty(tag)) {
			Toast.makeText(getActivity(),"tag不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// ","隔开的多个 转换成 Set
		String[] sArray = tag.split(",");
		Set<String> tagSet = new LinkedHashSet<String>();
		for (String sTagItme : sArray) {
			if (!ExampleUtil.isValidTagAndAlias(sTagItme)) {
				Toast.makeText(getActivity(),"tag只能为数字和英文字母", Toast.LENGTH_SHORT).show();
				return;
			}
			tagSet.add(sTagItme);
		}
		
		//调用JPush API设置Tag
		JPushInterface.setAliasAndTags(getActivity().getApplicationContext(), null, (Set<String>) tagSet, mTagsCallback);
	}
	
	private final TagAliasCallback mTagsCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
            case 0:
                logs = "设置成功tag";
                break;
                
            case 6002:
                logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                if (ExampleUtil.isConnected(getActivity().getApplicationContext())) {
                	JPushInterface.setAliasAndTags(getActivity().getApplicationContext(), null, tags, mTagsCallback);
                } else {
                	Tool.showToast(getActivity(), "网络不可用");
                }
                break;
            
            default:
                logs = "Failed with errorCode = " + code;
            }
            Tool.showToast(getActivity(), logs);
        }
        
    };

	private boolean check() {
		username = etUserName.getText().toString().trim();
		password = etUserPassword.getText().toString().trim();
		conPassword = etUserConPwd.getText().toString().trim();
		if (StringUtils.isEmpty(username)) {
			ToastUtils.show(getActivity(), "用户名不能为空");
			return false;
		}
		if (StringUtils.isEmpty(password)) {
			ToastUtils.show(getActivity(), "密码不能为空");
			return false;
		}
		if (StringUtils.isEmpty(conPassword)) {
			ToastUtils.show(getActivity(), "确认密码不能为空");
			return false;
		}
		if (!password.equals(conPassword)) {
			ToastUtils.show(getActivity(), "两次密码输入不一致");
			return false;
		}
		return true;
	}

	@Override
	protected void initializePresenter() {

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
