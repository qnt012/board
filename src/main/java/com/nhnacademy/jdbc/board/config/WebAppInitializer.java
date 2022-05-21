package com.nhnacademy.jdbc.board.config;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

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
}
