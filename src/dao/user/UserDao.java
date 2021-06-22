package dao.user;

import pojo.User;
import java.sql.Connection;
import java.util.List;


public interface UserDao {
	//ͨ��userCode��ȡUser	 
	public User getLoginUser(Connection connection,String userCode)throws Exception;
	//����������ѯ�û��б�
	public List<User> getUserList(Connection connection,String userName,int userRole,int currentPageNo, int pageSize)throws Exception;
		
	//����������ѯ�û����¼��
	public int getUserCount(Connection connection,String userName,int userRole)throws Exception;
	//����û�
	public int add(Connection connection,User user)throws Exception;
	//�����û�ID��ȡ�û���Ϣ
	public User getUserById(Connection connection,String id)throws Exception;
	//�޸��û���Ϣ
	public int modify(Connection connection,User user)throws Exception;

}
