package action;

import com.opensymphony.xwork2.ActionSupport;

public class InterceptedAction extends ActionSupport{
    public String intercept(){
        return SUCCESS;
    }
}
