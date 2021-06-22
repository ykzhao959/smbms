package service.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.BaseDao;
import dao.provider.ProviderDao;
import dao.role.RoleDao;
import pojo.Provider;
import pojo.Role;
import pojo.User;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Resource
	private ProviderDao providerDao;

	@Override
	public List<Provider> getProviderList() {
		Connection connection = null;
		List<Provider> providerList = null;
		try {
			connection = BaseDao.getConnection();
			providerList = providerDao.getProviderList(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return providerList;
	}

	@Override
	public List<Provider> getProviderList(String queryProviderName, int currentPageNo, int pageSize) {
		Connection connection = null;
		List<Provider> providerList = null;
		System.out.println("queryUserName ---- > " + queryProviderName);
		System.out.println("currentPageNo ---- > " + currentPageNo);
		System.out.println("pageSize ---- > " + pageSize);
		try {
			connection = BaseDao.getConnection();
			providerList = providerDao.getProviderList(connection, queryProviderName, currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return providerList;
	}

	@Override
	public int getProviderCount(String queryProviderName) {
		Connection connection = null;
		int count = 0;
		System.out.println("queryProviderName ---- > " + queryProviderName);
		try {
			connection = BaseDao.getConnection();
			count = providerDao.getProviderCount(connection, queryProviderName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return count;
	}

	@Override
	public boolean add(Provider provider) {

		boolean flag = false;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			connection.setAutoCommit(false);// 开启JDBC事务管理
			int updateRows = providerDao.add(connection, provider);
			connection.commit();
			if (updateRows > 0) {
				flag = true;
				System.out.println("add success!");
			} else {
				System.out.println("add failed!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				System.out.println("rollback==================");
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// 在service层进行connection连接的关闭
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}

	@Override
	public Provider getProviderById(String id) {
		Provider provider = null;
		Connection connection = null;
		try {
			connection = BaseDao.getConnection();
			provider = providerDao.getProviderById(connection, id);
		} catch (Exception e) {
			e.printStackTrace();
			provider = null;
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return provider;
	}

	@Override
	public boolean modify(Provider provider) {
		Connection connection = null;
		boolean flag = false;
		try {
			connection = BaseDao.getConnection();
			if (providerDao.modify(connection, provider) > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeResource(connection, null, null);
		}
		return flag;
	}

}
