package test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.cafe.dao.CafeDao;
import test.cafe.dto.CafeDto;

public class CafeListController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�� �������� ��Ÿ�� row�� ����
		final int PAGE_ROW_COUNT=5;
		//�ϴ� ���÷��� ������ ����
		final int PAGE_DISPLAY_COUNT=5;
		
		//������ �������� ��ȣ
		int pageNum=1;
		//������ �������� ��ȣ�� �Ķ���ͷ� ���޵Ǵ��� �о�� ����
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum!=null) {//������ ��ȣ�� �Ķ���ͷ� �Ѿ�´ٸ�
			//������ ��ȣ�� �����Ѵ�
			pageNum=Integer.parseInt(strPageNum);
		}
		//������ ������ �������� ���� ResultSet row��ȣ
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//������ ������ �������� �� ResultSet row ��ȣ
		int endRowNum=pageNum*PAGE_ROW_COUNT;

		//��ü row �� ������ �о�´�.
		int totalRow=CafeDao.getInstance().getCount();
		//��ü �������� ���� ���ϱ�
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//���� ������ ��ȣ
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//�� ������ ��ȣ
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//�� ������ ��ȣ�� �߸��� ���̶�� 
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //�������ش�. 
		}		
		// CafeDto ��ü�� ������ ���� startRowNum �� endRowNum �� ��´�.
		CafeDto dto=new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		//1. DB ���� �� ����� ���´�.
		List<CafeDto> list=CafeDao.getInstance().getList(dto);
		//2. �� ����� �����Ѵ�.

		//EL, JSTL �� Ȱ���ϱ� ���� �ʿ��� ���� request �� ��´�.
		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("totalPageCount", totalPageCount);

		//3. view page (jsp ������) �� forward  �̵��ؼ� ����
		RequestDispatcher rd=
				request.getRequestDispatcher("/views/cafe/list.jsp");
		rd.forward(request, response);		
	}

}
