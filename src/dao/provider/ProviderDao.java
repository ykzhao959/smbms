package dao.provider;

import pojo.Provider;
import pojo.User;

import java.sql.Connection;
import java.util.List;

public interface ProviderDao {
	public List<Provider> getProviderList(Connection connection) throws Exception;

	// ����������ѯprovider�б�
	public List<Provider> getProviderList(Connection connection, String proName, int currentPageNo, int pageSize)
			throws Exception;

	public int getProviderCount(Connection connection, String proName) throws Exception;

	public int add(Connection connection, Provider provider) throws Exception;

	public Provider getProviderById(Connection connection, String id) throws Exception;

	public int modify(Connection connection, Provider provider) throws Exception;
}
