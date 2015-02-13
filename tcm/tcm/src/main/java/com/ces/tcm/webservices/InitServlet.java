package com.ces.tcm.webservices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitServlet extends HttpServlet {
  /**
   * webservices 可以使用spring注解
   */
  private static final long serialVersionUID = -3962535683227715257L;

  @Override
  public void init() throws ServletException {
    WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    ServicesSingleton.getInstance().setServletContext(ctx);
  }
}
