package test.todo.dao;
/*
 *  Application �������� ���� �Ѱ��� ��ü�� �����Ǿ ���Ǵ� ������ 
 *  Dao Ŭ���� �����ϱ�
 *  
 *  1. �ܺο��� ��ü �����Ҽ� ������ �������� ���� �����ڸ� private �� ���� 
 *  2. �ڽ��� �������� ���� static �ʵ� ����
 *  3. �ڽ��� �������� �������ִ� public static �޼ҵ� ����
 */
public class TodoDao {
	//2. 
	private static TodoDao dao;
	//1. 
	private TodoDao() {}
	//3.
	public static TodoDao getInstance() {
		if(dao==null) {// ���� ȣ�� �ɶ��� null �̴�. 
			//null �̸� ��ü�� �����ؼ� static �������� �ʵ忡 �����Ѵ�.
			dao=new TodoDao();
		}
		return dao;
	}
}