package com.corock.mysite.action.user;

import com.corock.mvc.action.AbstractActionFactory;
import com.corock.mvc.action.Action;
import com.corock.mysite.action.main.IndexAction;

public class UserActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if ("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if ("join".equals(actionName)) {
			action = new JoinAction();
		} else if ("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if ("loginform".equals(actionName)) {
			action = new LoginFormAction();
		} else if ("login".equals(actionName)) {
			action = new LoginAction();
		} else if ("logout".equals(actionName)) {
			action = new LogoutAction();
		} else if ("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if ("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if ("modifysuccess".equals(actionName)) {
			action = new ModifySuccessAction();
		} else {
			action = new IndexAction();
		}
		
		return action;
	}

}
