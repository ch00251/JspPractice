package test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/*
 * 	[Filter Ŭ���� ����� ��]
 * 
 * 	1. Filter�������̽��� implements �Ѵ�.
 * 	2. � ��û�� ���ؼ� Filter�� �����ϰ� ����
 * 	   @WebFilter(��û��� ����) ������̼��� �����Ѵ�
 * 	3. doFilter() �޼ҵ忡�� ���ϴ� ������ �Ѵ�
 */
@WebFilter("/*")
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	//���Ͱ� ���� �Ǹ� ȣ��Ǵ� �޼ҵ�
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1.���ڵ��� �������� �ʾҴٸ�
		if(request.getCharacterEncoding()==null) {
			//post��� ��û ���� �� �ѱ� ���ڵ� ������ ���ش�
			request.setCharacterEncoding("utf-8");
		}
		//��û�� �帧 ��� �̾��
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
