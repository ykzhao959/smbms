package service.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import dao.BaseDao;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import pojo.User;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public User login(String userCode, String userPassword) {
		Connection connection = null;
		User user = null;
		try {
			connection = BaseDao.getConnection();
			user = userDao.getLoginUser(connection, userCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}

		// 匹配密码
		if (null != user) {
			if (!user.getUserPassword().equals(userPassword))
				user = null;
		}

		return user;
	}

	@Override
	public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
		Connection connection = null;
		List<User> userList = null;
		System.out.println("queryUserName ---- > " + queryUserName);
		System.out.println("queryUserRole ---- > " + queryUserRole);
		System.out.println("currentPageNo ---- > " + currentPageNo);
		System.out.println("pageSize ---- > " + pageSize);
		try {
			connection = BaseDao.getConnection();
			userList = userDao.getUserList(connection, queryUserName, queryUserRole, currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return userList;
	}

	@Override
	public int getUserCount(String queryUserName, int queryUserRole) {
		Connection connection = null;
		int count = 0;
		System.out.println("queryUserName ---- > " + queryUserName);
		System.out.println("queryUserRole ---- > " + queryUserRole);
		try {
			connection = BaseDao.getConnection();
			count = userDao.getUserCount(connection, queryUserName, queryUserRole);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return count;
	}

	@Override
	public boolean add(User user) {
		// TODO Auto-generated method stub

		boolean flag = false;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);// 开启JDBC事务管理
			int updateRows = userDao.add(connection, user);
			connection.commit();
			if (updateRows > 0) {
				flag = true;
				System.out.println("add success!");
			} else {
				System.out.println("add failed!");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				System.out.println("rollback==================");
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			// 在service层进行connection连接的关闭
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}
	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		User user = null;
		Connection connection = null;
		try{
			connection = BaseDao.getConnection();
			user = userDao.getUserById(connection,id);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			user = null;
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return user;
	}
	@Override
	public boolean modify(User user) {
		// TODO Auto-generated method stub
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			if(userDao.modify(connection,user) > 0)
				flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}


}
