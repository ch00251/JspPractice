package test.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(value = {"/shop/*","/users/private/*", "/cafe/private/*","/file/private/*"})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		/*
		 * 	�α��� ���θ� Ȯ�� �Ϸ��� HttpSession ��ü�� �ʿ��ϴ�
		 * 	HttpSession��ü�� HttpServletRequest��ü�� �޼ҵ带 �̿��ؼ�
		 *  �������� �� �� �����Ƿ� ServletRequest��ü��
		 *  ���� type�� HttpServletRequest type���� ĳ�����Ѵ�.
		 */
		//1. HttpServletRequest type ����
		HttpServletRequest request=(HttpServletRequest)req;
		//2. HttpSession type ����
		HttpSession session=request.getSession();
		//3. ���ǿ� "id" ��� Ű������ ����� ���ڿ��� �ִ��� �о�� ����.
		String id=(String)session.getAttribute("id");
		if(id != null) {//�α����� �� ��� 
			//���� ���� �ʰ� ��û�� �帧 ��� ���� ��Ű��
			chain.doFilter(req, res);
		}else {//�α����� �ȵ� ���
			/*
			 *  �α��� �Ŀ� ���� ������ �������� �ٽ� ������ �ϰ� 
			 *  ���۵Ǵ� �Ķ���Ͱ� �ִٸ� �Ķ���� ������ ���� �Ѱ� ��� �Ѵ�. 
			 */
			//���� ������ url ���� �о���� 
			String url=request.getRequestURI();
			//GET ��� �Ķ���� ���ڿ��� ������ �о���� (num=1&name=coffee ..)
			String query=request.getQueryString();
			// ����������?num=1&name=coffee ������ ���ڵ��� ���ڿ� �����ϱ�
			String encodedUrl=null;
			if(query==null) {
				encodedUrl=URLEncoder.encode(url);
			}else {
				encodedUrl=URLEncoder.encode(url+"?"+query);
			}

			HttpServletResponse response=(HttpServletResponse)res;
			String cPath=request.getContextPath();
			//�α��� ������ �����Ϸ�Ʈ �����ش�.
			response.sendRedirect(cPath+"/users/loginform.jsp?url="+encodedUrl);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
