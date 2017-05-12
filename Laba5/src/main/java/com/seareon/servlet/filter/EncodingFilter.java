package com.seareon.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Класс - языковой фильтр.
 *
 * @author Misha Ro
 * @version 1.0
 */
@WebFilter(urlPatterns = { "/*" },
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param") })
public class EncodingFilter implements Filter {
    private String code;

    /**
     * Метод инициализации экземпляра класса фильтра
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
    }

    /**
     * Метод устанавливает заданную в {@link EncodingFilter#init(FilterConfig)} в
     * {@link javax.servlet.http.HttpServletRequest}
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String codeRequest = servletRequest.getCharacterEncoding();

        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() { }
}

