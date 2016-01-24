package com.team.dream.runlegwork.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.easemob.EMCallBack;
import com.easemob.EMConnectionListener;
import com.easemob.EMError;
import com.easemob.EMEventListener;
import com.easemob.EMGroupChangeListener;
import com.easemob.EMNotifierEvent;
import com.easemob.EMValueCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactManager;
import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.easemob.chat.EMMessage;
import com.easemob.chat.EMMessage.ChatType;
import com.easemob.chat.EMMessage.Type;
import com.easemob.chat.TextMessageBody;
import com.easemob.chatuidemo.db.InviteMessgeDao;
import com.easemob.chatuidemo.db.UserDao;
import com.easemob.chatuidemo.domain.InviteMessage;
import com.easemob.chatuidemo.domain.InviteMessage.InviteMesageStatus;
import com.easemob.chatuidemo.domain.User;
import com.easemob.util.EMLog;
import com.easemob.util.HanziToPinyin;
import com.team.dream.runlegwork.BaseActivity;
import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.adapter.MainPagerAdapter;
import com.team.dream.runlegwork.chathelper.Constant;
import com.team.dream.runlegwork.chathelper.DemoHXSDKHelper;
import com.team.dream.runlegwork.chathelper.HXSDKHelper;
import com.team.dream.runlegwork.fragment.HomePageFragment;
import com.team.dream.runlegwork.fragment.MineFragment;
import com.team.dream.runlegwork.fragment.NearbyPeopleFragment;
import com.team.dream.runlegwork.fragment.OrderFragment;
import com.team.dream.runlegwork.listener.MainPageChangerLister;
import com.team.dream.runlegwork.singleservice.Syseting;
import com.team.dream.runlegwork.utils.ToastUtils;
import com.team.dream.runlegwork.widget.MainTitileBar;
import com.team.dream.runlegwork.widget.TabSelectView;
import com.team.dream.runlegwork.widget.TabSelectView.IMenuItemOnClick;

public class MainActivity extends BaseActivity implements IMenuItemOnClick,EMEventListener{

	public static final String KEY_POSTION = "key_postion";
	private List<Fragment> fragments = new ArrayList<Fragment>();
	private int defualtPostion;

	@InjectView(R.id.view_pager)
	ViewPager vp;
	@InjectView(R.id.tab_select_view)
	TabSelectView tsv;
	@InjectView(R.id.topbar)
	MainTitileBar mtb;
	// 账号在别处登录
	public boolean isConflict = false;
	// 账号被移除
	private boolean isCurrentAccountRemoved = false;
	private MyConnectionListener connectionListener = null;
	private MyGroupChangeListener groupChangeListener = null;
	private InviteMessgeDao inviteMessgeDao;
	private UserDao userDao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_tab);
		ButterKnife.inject(this);
		
		fragments.add(new HomePageFragment());
//		fragments.add(HomeFragment.newInstance(1));
		fragments.add(new NearbyPeopleFragment());
//		fragments.add(HomeFragment.newInstance(1));
		
		fragments.add(OrderFragment.newInstance());
		fragments.add(new MineFragment());
		vp.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),
				fragments));
		vp.setOnPageChangeListener(new MainPageChangerLister(tsv));
		vp.setOffscreenPageLimit(4);
		tsv.setOnMenuItemClickListener(this);
		mtb.hideTitleLeft();
		mtb.setTitle(R.string.home);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		defualtPostion = intent.getIntExtra(KEY_POSTION, 0);
		Log.d("TAG", "postion:"+defualtPostion);
		vp.setCurrentItem(defualtPostion);
		tsv.menuChoice(defualtPostion);
		
	}

	@Override
	public void menuItemOnClick(int position) {
		vp.setCurrentItem(position, false);
		switch (position) {
		case 0:
			mtb.setTitle(R.string.home);
			break;
		case 1:
			mtb.setTitle(R.string.discover);
			break;
		case 2:
			mtb.setTitle(R.string.order);
			break;
		case 3:
			mtb.setTitle(R.string.mine);
			break;

		default:
			break;
		}
	}

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, MainActivity.class);
	}

	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				ToastUtils.show(this, R.string.exit_app);
				exitTime = System.currentTimeMillis();
			} else {
				// Intent intent = new Intent();
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// startActivity(intent);
				Syseting.exitApp();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void init() {     
		// setContactListener监听联系人的变化等
//		EMContactManager.getInstance().setContactListener(new MyContactListener());
		// 注册一个监听连接状态的listener
		inviteMessgeDao = new InviteMessgeDao(this);
		connectionListener = new MyConnectionListener();
		EMChatManager.getInstance().addConnectionListener(connectionListener);
		
		groupChangeListener = new MyGroupChangeListener();
		// 注册群聊相关的listener
        EMGroupManager.getInstance().addGroupChangeListener(groupChangeListener);
	}
	
	static void asyncFetchGroupsFromServer(){
	    HXSDKHelper.getInstance().asyncFetchGroupsFromServer(new EMCallBack(){

            @Override
            public void onSuccess() {
                HXSDKHelper.getInstance().noitifyGroupSyncListeners(true);
                
                if(HXSDKHelper.getInstance().isContactsSyncedWithServer()){
                    HXSDKHelper.getInstance().notifyForRecevingEvents();
                }
            }

            @Override
            public void onError(int code, String message) {
                HXSDKHelper.getInstance().noitifyGroupSyncListeners(false);                
            }

            @Override
            public void onProgress(int progress, String status) {
                
            }
            
        });
	}
	
	static void asyncFetchContactsFromServer(){
	    HXSDKHelper.getInstance().asyncFetchContactsFromServer(new EMValueCallBack<List<String>>(){

            @Override
            public void onSuccess(List<String> usernames) {
                Context context = HXSDKHelper.getInstance().getAppContext();
                
                System.out.println("----------------"+usernames.toString());
                EMLog.d("roster", "contacts size: " + usernames.size());
                Map<String, User> userlist = new HashMap<String, User>();
                for (String username : usernames) {
                    User user = new User();
                    user.setUsername(username);
                    setUserHearder(username, user);
                    userlist.put(username, user);
                }
                // 添加user"申请与通知"
                User newFriends = new User();
                newFriends.setUsername(Constant.NEW_FRIENDS_USERNAME);
                String strChat = context.getString(R.string.Application_and_notify);
                newFriends.setNick(strChat);
        
                userlist.put(Constant.NEW_FRIENDS_USERNAME, newFriends);
                // 添加"群聊"
                User groupUser = new User();
                String strGroup = context.getString(R.string.group_chat);
                groupUser.setUsername(Constant.GROUP_USERNAME);
                groupUser.setNick(strGroup);
                groupUser.setHeader("");
                userlist.put(Constant.GROUP_USERNAME, groupUser);
                
                 // 添加"聊天室"
                User chatRoomItem = new User();
                String strChatRoom = context.getString(R.string.chat_room);
                chatRoomItem.setUsername(Constant.CHAT_ROOM);
                chatRoomItem.setNick(strChatRoom);
                chatRoomItem.setHeader("");
                userlist.put(Constant.CHAT_ROOM, chatRoomItem);
                
                // 添加"Robot"
        		User robotUser = new User();
        		String strRobot = context.getString(R.string.robot_chat);
        		robotUser.setUsername(Constant.CHAT_ROBOT);
        		robotUser.setNick(strRobot);
        		robotUser.setHeader("");
        		userlist.put(Constant.CHAT_ROBOT, robotUser);
        		
                 // 存入内存
                ((DemoHXSDKHelper)HXSDKHelper.getInstance()).setContactList(userlist);
                 // 存入db
                UserDao dao = new UserDao(context);
                List<User> users = new ArrayList<User>(userlist.values());
                dao.saveContactList(users);

                HXSDKHelper.getInstance().notifyContactsSyncListener(true);
                
                if(HXSDKHelper.getInstance().isGroupsSyncedWithServer()){
                    HXSDKHelper.getInstance().notifyForRecevingEvents();
                }
                
                ((DemoHXSDKHelper)HXSDKHelper.getInstance()).getUserProfileManager().asyncFetchContactInfosFromServer(usernames,new EMValueCallBack<List<User>>() {

					@Override
					public void onSuccess(List<User> uList) {
						((DemoHXSDKHelper)HXSDKHelper.getInstance()).updateContactList(uList);
						((DemoHXSDKHelper)HXSDKHelper.getInstance()).getUserProfileManager().notifyContactInfosSyncListener(true);
					}

					@Override
					public void onError(int error, String errorMsg) {
					}
				});
            }

            @Override
            public void onError(int error, String errorMsg) {
                HXSDKHelper.getInstance().notifyContactsSyncListener(false);
            }
	        
	    });
	}
	static void asyncFetchBlackListFromServer(){
	    HXSDKHelper.getInstance().asyncFetchBlackListFromServer(new EMValueCallBack<List<String>>(){

            @Override
            public void onSuccess(List<String> value) {
                EMContactManager.getInstance().saveBlackList(value);
                HXSDKHelper.getInstance().notifyBlackListSyncListener(true);
            }

            @Override
            public void onError(int error, String errorMsg) {
                HXSDKHelper.getInstance().notifyBlackListSyncListener(false);
            }
	        
	    });
	}
	
	/**
     * 设置hearder属性，方便通讯中对联系人按header分类显示，以及通过右侧ABCD...字母栏快速定位联系人
     * 
     * @param username
     * @param user
     */
    private static void setUserHearder(String username, User user) {
        String headerName = null;
        if (!TextUtils.isEmpty(user.getNick())) {
            headerName = user.getNick();
        } else {
            headerName = user.getUsername();
        }
        if (username.equals(Constant.NEW_FRIENDS_USERNAME)) {
            user.setHeader("");
        } else if (Character.isDigit(headerName.charAt(0))) {
            user.setHeader("#");
        } else {
            user.setHeader(HanziToPinyin.getInstance().get(headerName.substring(0, 1)).get(0).target.substring(0, 1)
                    .toUpperCase());
            char header = user.getHeader().toLowerCase().charAt(0);
            if (header < 'a' || header > 'z') {
                user.setHeader("#");
            }
        }
    }
    

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (conflictBuilder != null) {
			conflictBuilder.create().dismiss();
			conflictBuilder = null;
		}

		if(connectionListener != null){
		    EMChatManager.getInstance().removeConnectionListener(connectionListener);
		}
		
		if(groupChangeListener != null){
		    EMGroupManager.getInstance().removeGroupChangeListener(groupChangeListener);
		}
		
		try {
            unregisterReceiver(internalDebugReceiver);
        } catch (Exception e) {
        }
	}
	
	/**
	 * 连接监听listener
	 * 
	 */
	public class MyConnectionListener implements EMConnectionListener {

		@Override
		public void onConnected() {
            boolean groupSynced = HXSDKHelper.getInstance().isGroupsSyncedWithServer();
            boolean contactSynced = HXSDKHelper.getInstance().isContactsSyncedWithServer();
            
            // in case group and contact were already synced, we supposed to notify sdk we are ready to receive the events
            if(groupSynced && contactSynced){
                new Thread(){
                    @Override
                    public void run(){
                        HXSDKHelper.getInstance().notifyForRecevingEvents();
                    }
                }.start();
            }else{
                if(!groupSynced){
                    asyncFetchGroupsFromServer();
                }
                
                if(!contactSynced){
                    asyncFetchContactsFromServer();
                }
                
                if(!HXSDKHelper.getInstance().isBlackListSyncedWithServer()){
                    asyncFetchBlackListFromServer();
                }
            }
            
		}

		@Override
		public void onDisconnected(final int error) {
			final String st1 = getResources().getString(R.string.can_not_connect_chat_server_connection);
			final String st2 = getResources().getString(R.string.the_current_network);
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					if (error == EMError.USER_REMOVED) {
						// 显示帐号已经被移除
						showAccountRemovedDialog();
					} else if (error == EMError.CONNECTION_CONFLICT) {
						// 显示帐号在其他设备登陆dialog
						showConflictDialog();
					} 
				}

			});
		}
	}
		
		/**
		 * MyGroupChangeListener
		 */
		public class MyGroupChangeListener implements EMGroupChangeListener {

			@Override
			public void onInvitationReceived(String groupId, String groupName, String inviter, String reason) {
				
				boolean hasGroup = false;
				for (EMGroup group : EMGroupManager.getInstance().getAllGroups()) {
					if (group.getGroupId().equals(groupId)) {
						hasGroup = true;
						break;
					}
				}
				if (!hasGroup)
					return;

				// 被邀请
				String st3 = getResources().getString(R.string.Invite_you_to_join_a_group_chat);
				EMMessage msg = EMMessage.createReceiveMessage(Type.TXT);
				msg.setChatType(ChatType.GroupChat);
				msg.setFrom(inviter);
				msg.setTo(groupId);
				msg.setMsgId(UUID.randomUUID().toString());
				msg.addBody(new TextMessageBody(inviter + " " +st3));
				// 保存邀请消息
				EMChatManager.getInstance().saveMessage(msg);
				// 提醒新消息
				HXSDKHelper.getInstance().getNotifier().viberateAndPlayTone(msg);


			}
			
			@Override
			public void onInvitationAccpted(String groupId, String inviter, String reason) {
				
			}

			@Override
			public void onInvitationDeclined(String groupId, String invitee, String reason) {

			}

			@Override
			public void onUserRemoved(String groupId, String groupName) {
							
				// 提示用户被T了，demo省略此步骤
				// 刷新ui
			}

			@Override
			public void onGroupDestroy(String groupId, String groupName) {
				
				// 群被解散
				// 提示用户群被解散,demo省略
				// 刷新ui

			}

			@Override
			public void onApplicationReceived(String groupId, String groupName, String applyer, String reason) {
				
				// 用户申请加入群聊
				InviteMessage msg = new InviteMessage();
				msg.setFrom(applyer);
				msg.setTime(System.currentTimeMillis());
				msg.setGroupId(groupId);
				msg.setGroupName(groupName);
				msg.setReason(reason);
				Log.d("MainActivity", applyer + " 申请加入群聊：" + groupName);
				msg.setStatus(InviteMesageStatus.BEAPPLYED);
				notifyNewIviteMessage(msg);
			}

			@Override
			public void onApplicationAccept(String groupId, String groupName, String accepter) {

				String st4 = getResources().getString(R.string.Agreed_to_your_group_chat_application);
				// 加群申请被同意
				EMMessage msg = EMMessage.createReceiveMessage(Type.TXT);
				msg.setChatType(ChatType.GroupChat);
				msg.setFrom(accepter);
				msg.setTo(groupId);
				msg.setMsgId(UUID.randomUUID().toString());
				msg.addBody(new TextMessageBody(accepter + " " +st4));
				// 保存同意消息
				EMChatManager.getInstance().saveMessage(msg);
				// 提醒新消息
				HXSDKHelper.getInstance().getNotifier().viberateAndPlayTone(msg);

			}

			@Override
			public void onApplicationDeclined(String groupId, String groupName, String decliner, String reason) {
				// 加群申请被拒绝，demo未实现
			}
		}

		/**
		 * 保存提示新消息
		 * 
		 * @param msg
		 */
		private void notifyNewIviteMessage(InviteMessage msg) {
			saveInviteMsg(msg);
			// 提示有新消息
			HXSDKHelper.getInstance().getNotifier().viberateAndPlayTone(null);

			// 刷新bottom bar消息未读数
//			updateUnreadAddressLable();
			// 刷新好友页面ui
//			if (currentTabIndex == 1)
//				contactListFragment.refresh();
		}
		/**
		 * 保存邀请等msg
		 * 
		 * @param msg
		 */
		private void saveInviteMsg(InviteMessage msg) {
			// 保存msg
			inviteMessgeDao.saveMessage(msg);
			// 未读数加1
			User user = ((DemoHXSDKHelper)HXSDKHelper.getInstance()).getContactList().get(Constant.NEW_FRIENDS_USERNAME);
			if (user.getUnreadMsgCount() == 0)
				user.setUnreadMsgCount(user.getUnreadMsgCount() + 1);
		}

		/**
		 * set head
		 * 
		 * @param username
		 * @return
		 */
		User setUserHead(String username) {
			User user = new User();
			user.setUsername(username);
			String headerName = null;
			if (!TextUtils.isEmpty(user.getNick())) {
				headerName = user.getNick();
			} else {
				headerName = user.getUsername();
			}
			if (username.equals(Constant.NEW_FRIENDS_USERNAME)) {
				user.setHeader("");
			} else if (Character.isDigit(headerName.charAt(0))) {
				user.setHeader("#");
			} else {
				user.setHeader(HanziToPinyin.getInstance().get(headerName.substring(0, 1)).get(0).target.substring(0, 1)
						.toUpperCase());
				char header = user.getHeader().toLowerCase().charAt(0);
				if (header < 'a' || header > 'z') {
					user.setHeader("#");
				}
			}
			return user;
		}

		@Override
		protected void onResume() {
			super.onResume();
			if (!isConflict && !isCurrentAccountRemoved) {
				EMChatManager.getInstance().activityResumed();
			}

			// unregister this event listener when this activity enters the
			// background
			DemoHXSDKHelper sdkHelper = (DemoHXSDKHelper) DemoHXSDKHelper.getInstance();
			sdkHelper.pushActivity(this);

			// register the event listener when enter the foreground
			EMChatManager.getInstance().registerEventListener(this,
					new EMNotifierEvent.Event[] { EMNotifierEvent.Event.EventNewMessage ,EMNotifierEvent.Event.EventOfflineMessage, EMNotifierEvent.Event.EventConversationListChanged});
		}

		@Override
		protected void onStop() {
			EMChatManager.getInstance().unregisterEventListener(this);
			DemoHXSDKHelper sdkHelper = (DemoHXSDKHelper) DemoHXSDKHelper.getInstance();
			sdkHelper.popActivity(this);

			super.onStop();
		}

		@Override
		protected void onSaveInstanceState(Bundle outState) {
			outState.putBoolean("isConflict", isConflict);
			outState.putBoolean(Constant.ACCOUNT_REMOVED, isCurrentAccountRemoved);
			super.onSaveInstanceState(outState);
		}


		private android.app.AlertDialog.Builder conflictBuilder;
		private android.app.AlertDialog.Builder accountRemovedBuilder;
		private boolean isConflictDialogShow;
		private boolean isAccountRemovedDialogShow;
	    private BroadcastReceiver internalDebugReceiver;

		/**
		 * 显示帐号在别处登录dialog
		 */
		private void showConflictDialog() {
			isConflictDialogShow = true;
			DemoHXSDKHelper.getInstance().logout(false,null);
			String st = getResources().getString(R.string.Logoff_notification);
			if (!MainActivity.this.isFinishing()) {
				// clear up global variables
				try {
					if (conflictBuilder == null)
						conflictBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
					conflictBuilder.setTitle(st);
					conflictBuilder.setMessage(R.string.connect_conflict);
					conflictBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							conflictBuilder = null;
							finish();
							startActivity(new Intent(MainActivity.this, UserLoginActivity.class));
						}
					});
					conflictBuilder.setCancelable(false);
					conflictBuilder.create().show();
					isConflict = true;
				} catch (Exception e) {
					EMLog.e("MainActivity", "---------color conflictBuilder error" + e.getMessage());
				}

			}

		}

		/**
		 * 帐号被移除的dialog
		 */
		private void showAccountRemovedDialog() {
			isAccountRemovedDialogShow = true;
			DemoHXSDKHelper.getInstance().logout(true,null);
			String st5 = getResources().getString(R.string.Remove_the_notification);
			if (!MainActivity.this.isFinishing()) {
				// clear up global variables
				try {
					if (accountRemovedBuilder == null)
						accountRemovedBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
					accountRemovedBuilder.setTitle(st5);
					accountRemovedBuilder.setMessage(R.string.em_user_remove);
					accountRemovedBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							accountRemovedBuilder = null;
							finish();
							startActivity(new Intent(MainActivity.this, UserLoginActivity.class));
						}
					});
					accountRemovedBuilder.setCancelable(false);
					accountRemovedBuilder.create().show();
					isCurrentAccountRemoved = true;
				} catch (Exception e) {
					EMLog.e("MainActivity", "---------color userRemovedBuilder error" + e.getMessage());
				}

			}

		}
		
		@Override
		public void onEvent(EMNotifierEvent event) {
			switch (event.getEvent()) {
			case EventNewMessage: // 普通消息
			{
				EMMessage message = (EMMessage) event.getData();

				// 提示新消息
				HXSDKHelper.getInstance().getNotifier().onNewMsg(message);

				break;
			}

			case EventOfflineMessage: {
				break;
			}

			case EventConversationListChanged: {
			    break;
			}
			
			default:
				break;
			}
		}

}
