package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  /showtime  ��û�� ���ؼ� ������ ���� �����ϱ�
 *  
 *  1. HttpServlet Ŭ������ ��� �޴´�.
 *  2. service() �޼ҵ带 �������̵� �Ѵ�.
 *  3. @WebSerlvet("/showtime") ��û ��θ� ������̼����� ����Ѵ�. 
 */
@WebServlet("/showtime")
public class ShowTimeServlet extends HttpServlet{
	/*
	 *  service() �޼ҵ忡�� HttpServletRequest ��ü�� ��������
	 *  HttpServletResponse  ��ü�� �������� ���޵ȴ�.
	 *  HttpServletRequest ��ü�� ��û�� Ŭ���̾�Ʈ�� ������ Ȯ���Ҽ� �ִ±��
	 *  �� ��� �ְ� 
	 *  HttpServletResponse ��ü���� ���信 �ʿ��� ��ɵ��� ��� �ִ�. 
	 */
	@Override
	protected void service(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//���� �ѱ� ���ڵ� ���� 
		resp.setCharacterEncoding("utf-8");
		//���� content type ���� (html �������� ���� �ϰڴٰ� �˸���)
		resp.setContentType("text/html;charset=utf-8");

		//��û�� Ŭ���̾�Ʈ���� ���ڿ��� ����Ҽ� �ִ� ��ü
		PrintWriter pw=resp.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'/>");
		pw.println("<title>���� �ð� ������</title>");
		pw.println("</head>");
		pw.println("<body>");
		//��¥ ��ü�� �����Ѵ�. 
		Date d=new Date();
		//���� ��¥ �ð� ������ ���ڿ��� ������ ����
		String info=d.toString();
		pw.println("<p>���� �ð� : <strong>"+info+"</strong></p>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}