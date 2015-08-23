package com.team.dream.runlegwork.singleservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.app.Activity;

import com.team.dream.runlegwork.utils.StringUtils;

public class ActivityProcessHandler {
	
	public static final String CREATE_ORDRER_HANDLER="create_order_handler";
	public static final String CREATE_SKILL_HANDLER="create_skill_handler";
	
	private static ActivityProcessHandler instatnce;
	

	private static HashMap<String, List<Activity>> ACTIVIIY_PROCESS = new HashMap<String, List<Activity>>();

	public static ActivityProcessHandler getInstance() {
		if (null == instatnce) {
			instatnce = new ActivityProcessHandler();
		}
		return instatnce;
	}

	public void putActivity(String key, Activity act) {
		if (StringUtils.isEmpty(key))
			return;
		List<Activity> tmp = ACTIVIIY_PROCESS.get(key);
		if (null == tmp) {
			tmp = new ArrayList<Activity>();
			ACTIVIIY_PROCESS.put(key, tmp);
		}
		tmp.add(act);
	}

	public void exit(String key) {
		if (StringUtils.isEmpty(key))
			return;

		List<Activity> tmp = ACTIVIIY_PROCESS.get(key);
		if (null == tmp)
			return;
		for (int i = 0; i < ACTIVIIY_PROCESS.size(); i++) {
			Activity activity = tmp.get(i);
			if (activity != null) {
				activity.finish();
			}
		}
		tmp.clear();
	}

	public void exitAllProcess() {
		Set<String> actKeySet = ACTIVIIY_PROCESS.keySet();

		if (actKeySet == null)
			return;

		for (String key : actKeySet) {

			exit(key);

		}

	}

}
