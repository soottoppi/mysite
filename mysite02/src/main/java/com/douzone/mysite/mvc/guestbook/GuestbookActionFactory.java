package com.douzone.mysite.mvc.guestbook;

import com.douzone.mysite.mvc.user.UpdateAction;
import com.douzone.mysite.mvc.user.UpdateFormAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("add".equals(actionName)) {
			action = new AddAction();
		} else {
			action = new ListAction();
		}
		return action;
	}

}
