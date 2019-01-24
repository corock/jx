package com.corock.mvc.action.main;

import com.corock.mvc.action.AbstractActionFactory;
import com.corock.mvc.action.Action;

public class MainActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new IndexAction();
	}

}
