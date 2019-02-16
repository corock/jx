package com.corock.mvc.action.board;

import com.corock.mvc.action.AbstractActionFactory;
import com.corock.mvc.action.Action;

public class BoardActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if ("writeform".equals(actionName)) {
			action = new BoardWriteFormAction();
		} else if ("write".equals(actionName)) {
			action = new BoardWriteAction();
		} else if ("writesuccess".equals(actionName)) {
			action = new BoardWriteSuccessAction();
		} else if ("view".equals(actionName)) {
			action = new BoardViewAction();
		} else if ("modifyform".equals(actionName)) {
			action = new BoardModifyFormAction();
		} else if ("modify".equals(actionName)) {
			action = new BoardModifyAction();
		} else if ("delete".equals(actionName)) {
			action = new BoardDeleteAction();
		} else if ("replyform".equals(actionName)) {
			action = new BoardReplyFormAction();
		} else if ("reply".equals(actionName)) {
			action = new BoardReplyAction();
		} else {
			action = new BoardListAction();
		}
		
		return action;
	}

}
