//信管182 徐学印 201802104067
package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filter 1",urlPatterns = {"/*"})
public class Filter10_encoding implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("Filter 1 - encoding begins");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String path= request.getRequestURI();
        String method=request.getMethod();
        if (!path.contains("/login") && !path.contains("/myapp")){
            response.setContentType("text/html;charset=UTF-8");
            if (method.equals("POST")||method.equals("PUT")){
                request.setCharacterEncoding("UTF-8");
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Filter 1 - encoding ends");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
