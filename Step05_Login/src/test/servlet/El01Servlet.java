package test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/el01")
public class El01Servlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req,
					HttpServletResponse resp) throws ServletException, IOException{
		//request������ "myName"�̶�� Ű������ "�豸��" �����ϱ�
		req.setAttribute("myName", "�豸��");
		//jsp�������� forward �̵� (������ �����ϱ�)
		RequestDispatcher rd=req.getRequestDispatcher("/el/test01.jsp");
		rd.forward(req, resp);
	}
}
