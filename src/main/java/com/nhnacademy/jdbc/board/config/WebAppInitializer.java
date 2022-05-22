package com.nhnacademy.jdbc.board.config;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static int MAX_FILE_SIZE = 10 * 1024 * 1024;


    @Override
    public void onStartup (ServletContext servletContext) throws ServletException
    {
        super.onStartup(servletContext);
        servletContext.addFilter("name", XssEscapeServletFilter.class)
            .addMappingForUrlPatterns(null, false, "/*");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{com.nhnacademy.jdbc.board.config.RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        MultipartFilter multipartFilter =new MultipartFilter();
        XssEscapeServletFilter xssEscapeServletFilter = new XssEscapeServletFilter();

        return new Filter[]{characterEncodingFilter, hiddenHttpMethodFilter, multipartFilter, xssEscapeServletFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfig = new MultipartConfigElement("", MAX_FILE_SIZE, MAX_FILE_SIZE, 0);
        registration.setMultipartConfig(multipartConfig);
    }
}
