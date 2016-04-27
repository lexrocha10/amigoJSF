package filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	private final static String FILTER_APPLIED = "_security_filter_applied";
	public LoginFilter() {
	}
        @Override
	public void init(FilterConfig arg0) throws ServletException {
	}
        @Override
	public void destroy() {
	}
        @Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hresp = (HttpServletResponse) response;
		HttpSession session = hreq.getSession();
		hreq.getPathInfo();
		String paginaAtual = new String(hreq.getRequestURL());
		// dont filter login.jsp because otherwise an endless loop.
		// & only filter .jsp otherwise it will filter all images etc as well.
		if ((request.getAttribute(FILTER_APPLIED) == null) && paginaAtual != null
				&& (!paginaAtual.endsWith("login.xhtml"))
				&& (paginaAtual.endsWith(".xhtml"))) {
			request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
			// If the session bean is not null get the session bean property
			// username.
			Integer idLogin = null;
			if ( session.getAttribute("ID_LOGIN") != null) {
				 idLogin = (Integer) session.getAttribute("ID_LOGIN");
			}
			if (idLogin == null) {
				hresp.sendRedirect("faces/login.xhtml");
				return;
			}
		}
		// deliver request to next filter
		chain.doFilter(request, response);
	}
}