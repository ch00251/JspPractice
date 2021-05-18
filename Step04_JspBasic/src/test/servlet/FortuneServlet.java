package test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test2/fortune")
public class FortuneServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
			//������ JSP ������ Ȥ�� �ٸ� �������� ���� �� �� �ִ�
		
			//1. ������ ������ �̵� ��� (context path�� ���� �ʴ´�)
			String path="/test2/person.jsp";
			//2. RequestDispatcher ��ü�� ������
			RequestDispatcher rd=req.getRequestDispatcher(path);
			//3. ������ �̵��ؼ� �����Ѵ�
			rd.forward(req, resp);
		
	}
}
