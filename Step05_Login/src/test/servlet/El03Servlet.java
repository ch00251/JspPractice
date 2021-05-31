package test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/el03")
public class El03Servlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//���� ������ String type ���
		HttpSession session=req.getSession();
		session.setAttribute("myNick", "ȣ����");
		//jsp �������� �����Ϸ�Ʈ �̵��ϱ�
		String cPath=req.getContextPath();
		resp.sendRedirect(cPath+"/el/test03.jsp");
	}
}
