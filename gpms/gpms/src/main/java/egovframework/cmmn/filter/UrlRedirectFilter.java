package egovframework.cmmn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UrlRedirectFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest) request;
        
        // HTTP 응답코드 301을 사용하도록 설정하여 리아이렉트합니다.
        //String uri = req.getRequestURI();
        StringBuffer urlbuf = req.getRequestURL();
        String url = urlbuf.toString();
        String contextPath = req.getContextPath();
        int idx = url.indexOf(contextPath);
        
        //if ( !uri.contains("change-main") ) {
        
            res.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            //res.setHeader("Location", "http://localhost:8081/gpms/change-main.do");
            
            res.setHeader("Location", url.substring(0,  idx) + "/gpms/change-main.jsp"); 
        
        //}

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
    
}
