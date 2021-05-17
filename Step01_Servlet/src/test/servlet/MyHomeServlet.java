package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
//3.
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/home/myhome")
public class MyHomeServlet extends HttpServlet {
	//2.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//���� �ѱ� ���ڵ� ����
		resp.setCharacterEncoding("utf-8");
		//���� content type ����(html �������� �����ϰڴٰ� �˸���)
		resp.setContentType("text/html;charset=utf-8");
		
		//��û�� Ŭ���̾�Ʈ���� ���ڿ��� ����� �� �ִ� ��ü(console�� �ƴ϶�)
		PrintWriter pw=resp.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='usf-8'/>");
		pw.println("<title>���� ���� ������</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p>���̴�~~!!</p>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
		}
}
