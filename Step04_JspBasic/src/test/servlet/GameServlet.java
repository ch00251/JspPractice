package test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//3
@WebServlet("/test1/game")
public class GameServlet extends HttpServlet{//1.
	//2
	@Override
	protected void service(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//���ؽ�Ʈ ��� �о����
		String cPath=req.getContextPath();
		//���ϴ� ��η� �����Ϸ�Ʈ �̵� ��Ű��
		resp.sendRedirect(cPath+"/test1/study.jsp");
	}
}
