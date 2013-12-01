package com.major.tools.webexplorer.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * User: Minjie
 * Date: 13-12-1
 * Time: 下午1:00
 */
public class GlobalInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        if (session.containsKey("user")) {
            return invokeWithException(actionInvocation);
        } else {
            if (actionInvocation.getAction().getClass().equals(ValidateSignature.class))
                return invokeWithException(actionInvocation);
            else
                return Action.LOGIN;
        }
    }

    private String invokeWithException(ActionInvocation actionInvocation) {
        try {
            return actionInvocation.invoke();
        } catch (Exception e) {
            return Action.ERROR;
        }
    }
}
