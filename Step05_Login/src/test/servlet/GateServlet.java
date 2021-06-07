package test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.CafeListController;

// .nhn���� ������ ��� ��û�� ó���� ���� �����ϱ�
@WebServlet("*.nhn")
public class GateServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		//���ؽ�Ʈ ����� ����
		String cPath=request.getContextPath();
		int cLength=cPath.length();
		// .�� �ε���
		int index=uri.indexOf(".");
		//���� ��û ���(���ؽ�Ʈ ��� ����, .nhn ����)
		String path=uri.substring(cLength, index);
		
		if(path.equals("/cafe/list")) {//ī�� �� ��Ϻ��� ��û�̶��
			new CafeListController().execute(request, response);
		}else if(path.equals("/cafe/insertform")) {
			
		}
	}
}
