package dao.user;

import pojo.User;
import java.sql.Connection;
import java.util.List;


public interface UserDao {
	//通过userCode获取User	 
	public User getLoginUser(Connection connection,String userCode)throws Exception;
	//根据条件查询用户列表
	public List<User> getUserList(Connection connection,String userName,int userRole,int currentPageNo, int pageSize)throws Exception;
		
	//根据条件查询用户表记录数
	public int getUserCount(Connection connection,String userName,int userRole)throws Exception;
	//添加用户
	public int add(Connection connection,User user)throws Exception;
	//根据用户ID获取用户信息
	public User getUserById(Connection connection,String id)throws Exception;
	//修改用户信息
	public int modify(Connection connection,User user)throws Exception;

}
