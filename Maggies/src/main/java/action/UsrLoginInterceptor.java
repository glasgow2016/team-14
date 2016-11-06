package action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;


public class UsrLoginInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation arg0) throws Exception {

        if (UserAction.class == arg0.getAction().getClass()) {
            return arg0.invoke();
        } else {
            Map map = arg0.getInvocationContext().getSession();
            if (null == map.get("USERNAME")){
                System.out.println("nologin");
                return "noLogin";
            } else {
                return arg0.invoke();
            }
        }
    }
}