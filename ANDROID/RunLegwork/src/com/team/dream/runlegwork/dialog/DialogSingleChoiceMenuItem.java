package com.team.dream.runlegwork.dialog;

/**
 * 单项选择菜单项对象
 * 
 * @author Administrator
 * 
 */
public class DialogSingleChoiceMenuItem {
	/** 菜单索引 **/
	public int menuIndex;
	/** 菜单显示的文字 **/
	public String menuText;
	/** 菜单执行的命令 **/
	public int menuCommand;
	/** 菜单项对象 **/
	public Object obj;

	public DialogSingleChoiceMenuItem(int menuIndex, String menuText,
			int menuCommand, Object obj) {
		super();
		this.menuIndex = menuIndex;
		this.menuText = menuText;
		this.menuCommand = menuCommand;
		this.obj = obj;
	}

	public DialogSingleChoiceMenuItem(int menuIndex, String menuText,
			int menuCommand) {
		super();
		this.menuIndex = menuIndex;
		this.menuText = menuText;
		this.menuCommand = menuCommand;
	}

}