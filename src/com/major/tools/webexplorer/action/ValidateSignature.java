package com.major.tools.webexplorer.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * User: Minjie
 * Date: 13-11-9
 * Time: 下午2:35
 */
public class ValidateSignature extends ActionSupport {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotEmpty(password)) {
            if (userName.equalsIgnoreCase("admin") && password.equals("111")) {
                ActionContext context = ActionContext.getContext();
                //set session
                Map<String, Object> map = context.getSession();
                map.put("user", "admin");
                return SUCCESS;
            }
        }
        return LOGIN;
    }
}
