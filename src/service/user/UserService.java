package service.user;

import java.util.List;

import pojo.User;

public interface UserService {
	public User login(String userCode,String userPassword);	
	//����������ѯ�û��б�
	public List<User> getUserList(String queryUserName,int queryUserRole,int currentPageNo, int pageSize);

	//����������ѯ�û����¼��
	public int getUserCount(String queryUserName,int queryUserRole);
	//����û�
	public boolean add(User user);
	//�����û�ID��ȡ�û���Ϣ
	public User getUserById(String id);

	//�޸��û���Ϣ
	public boolean modify(User user);

}
