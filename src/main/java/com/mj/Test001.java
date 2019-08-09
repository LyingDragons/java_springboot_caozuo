package com.mj;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;


public class Test001 {

    private static String PATH="/mj";

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat=new Tomcat();
        tomcat.setPort(8888);
        tomcat.getHost().setAutoDeploy(false);

        StandardContext standardContext = new StandardContext();
        standardContext.setPath(PATH);
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());

        tomcat.getHost().addChild(standardContext);

        tomcat.addServlet(PATH,"IndexServlet",new IndexServlet());

        standardContext.addServletMappingDecoded("/index","IndexServlet");
        tomcat.start();
        System.out.println("tomcat 已经启动...");
        tomcat.getServer().await();
    }
}
