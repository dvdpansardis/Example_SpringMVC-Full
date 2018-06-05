package org.Example_SpringMVC.Full.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Class que representa o Dispatcher do Spring Obs.: Esta classe que é
 * configurada geralmente em xml.
 * 
 * @author David
 *
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class, JPAProductionConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("Get Servlet Config Classes");
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("Get Servlet Mappings");
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[] { characterEncodingFilter, new OpenEntityManagerInViewFilter()};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		super.onStartup(servletContext);
//		servletContext.addListener(RequestContextListener.class);
//		servletContext.setInitParameter("spring.profiles.active","dev");
//	}
}
