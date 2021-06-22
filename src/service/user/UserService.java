package service.user;

import java.util.List;

import pojo.User;

public interface UserService {
	public User login(String userCode,String userPassword);	
	//根据条件查询用户列表
	public List<User> getUserList(String queryUserName,int queryUserRole,int currentPageNo, int pageSize);

	//根据条件查询用户表记录数
	public int getUserCount(String queryUserName,int queryUserRole);
	//添加用户
	public boolean add(User user);
	//根据用户ID获取用户信息
	public User getUserById(String id);

	//修改用户信息
	public boolean modify(User user);

}
