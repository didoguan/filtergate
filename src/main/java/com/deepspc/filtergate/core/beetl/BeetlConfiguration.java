package com.deepspc.filtergate.core.beetl;

import com.deepspc.filtergate.config.properties.AppNameProperties;
import com.deepspc.filtergate.core.shiro.ShiroExt;
import org.beetl.core.Context;
import org.beetl.core.Function;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * beetl拓展配置,绑定一些工具类,方便在模板中直接调用
 *
 */
public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    @Autowired
    Environment env;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    AppNameProperties appNameProperties;

    @Override
    public void initOther() {

        //全局共享变量
        Map<String, Object> shared = new HashMap<>();
        shared.put("systemName", appNameProperties.getName());
        groupTemplate.setSharedVars(shared);

        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());

        groupTemplate.registerFunction("env", new Function() {
            @Override
            public String call(Object[] paras, Context ctx) {
                String key = (String)paras[0];
                String value =  env.getProperty(key);
                if(value!=null) {
                    return getStr(value);
                }
                if(paras.length==2) {
                    return (String)paras[1];
                }
                return null;
            }

            protected String getStr(String str) {
                try {
                    return new String(str.getBytes("iso8859-1"),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
