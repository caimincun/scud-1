package com.team.dream.runlegwork.dialog;

import java.util.List;

import com.team.dream.runlegwork.MyBaseAdapter;
import com.team.dream.runlegwork.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 性别Dialog适配器
 * @author Administrator
 *
 */
public class DialogSingleChoiceGenderAdapter extends MyBaseAdapter{
	List<DialogSingleChoiceMenuItem> items = null;
	Context mContext;
	/**当前选中的菜单索引**/
	int curMenuIndex = -1;
	public DialogSingleChoiceGenderAdapter(Context context, List<DialogSingleChoiceMenuItem> items,int curMenuIndex){
		mContext = context;
		this.items = items;
		this.curMenuIndex = curMenuIndex;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final DialogSingleChoiceMenuItem dialogSingleChoiceMenuItem = items.get(position);
		ViewHolder holder = null;
		if (null == convertView) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.dialog_single_choice_gender_item, null);
			holder = new ViewHolder();
			holder.ivChecked = (ImageView)convertView.findViewById(R.id.ivChecked);
			holder.tvMenuItem = (TextView) convertView.findViewById(R.id.tvMenuItem);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		convertView.setTag(R.string.tag_dialog_single_choice_menuitem, dialogSingleChoiceMenuItem);
		if(curMenuIndex==dialogSingleChoiceMenuItem.menuIndex){
			holder.ivChecked.setVisibility(View.VISIBLE);
		}else{
			holder.ivChecked.setVisibility(View.INVISIBLE);
		}
		holder.tvMenuItem.setText(dialogSingleChoiceMenuItem.menuText);
		return convertView;
	}

	class ViewHolder {
		ImageView ivChecked;
		TextView tvMenuItem;
	}
}
