package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 *   /detail?num=1
 *   /detail?num=2
 *   /detail?num=3
 *   
 *   - ���� ���� ��û�� �� �ö� �Ʒ��� ������ �ִ� service()  �޼ҵ尡 ȣ��ȴ�.
 *   - ?num=x �������� ���޵Ǵ� ���� GET ��� ��û �Ķ���� ��� �Ѵ�.
 *   - ��û �Ķ���ʹ� HttpServletRequest ��ü�� �̿��ؼ� �����Ҽ� �ִ�.
 */
@WebServlet("/detail")
public class DetailServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// "num" �̶�� �Ķ���� ������ ���޵� ���ڿ� �о���� 
		String strNum=req.getParameter("num");
		// ���ڿ��� ���� ������ �ٲٰ� ������?
		int num=Integer.parseInt(strNum);
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
		pw.println("<title>�ڼ��� ���� ������</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p>"+num+" �� �ڼ��� ���� ok!</p>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();				

	}
}
