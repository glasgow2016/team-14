package action;

import Util.DBUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import tool.StringTool;

import java.util.Map;

public class UserAction extends ActionSupport{

	private String username;
	private String password;
	private String msg;

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String login() {
		System.out.println("username->" + username);
		System.out.println("password->" + password);
		
		msg = "";
		if (DBUtil.INSTANCE.user_login(username, password)) {
			Map map = ActionContext.getContext().getSession();
			map.put("USERNAME", username);
			return "success";
		} else {
			System.out.println("failure----");
			msg = "login failure, no such username or password";
			return "failure";
		}
	}
	
	public String register() {
		System.out.println("name->" + username);
		System.out.println("password->" + password);
		
		if(!StringTool.checkStr(username) || !StringTool.checkStr(password)){
			msg = "illegal characters, failure!";
			return SUCCESS;
		}
		
		msg = DBUtil.INSTANCE.user_register(username, password);
		return msg;
	}
	
	public String logout() {
		System.out.println("logout action");
		Map map = ActionContext.getContext().getSession();
		map.remove("USERNAME");
		msg = "logout successfully";
		return SUCCESS;
	}

}
