package com.douzone.mysite.mvc.main;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class MainUserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}
