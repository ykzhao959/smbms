package dao.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.StringUtils;

import dao.BaseDao;
import pojo.Provider;
import pojo.User;

@Repository
public class ProviderDaoImpl implements ProviderDao {

	@Override
	public List<Provider> getProviderList(Connection connection) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Provider> providerList = new ArrayList<Provider>();
		if (connection != null) {
			String sql = "select * from smbms_provider";
			Object[] params = {};
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			while (rs.next()) {
				Provider _provider = new Provider();
				_provider.setId(rs.getInt("id"));
				_provider.setProCode(rs.getString("proCode"));
				_provider.setProName(rs.getString("proName"));
				_provider.setProDesc(rs.getString("proDesc"));
				_provider.setProContact(rs.getString("proContact"));
				_provider.setProPhone(rs.getString("proPhone"));
				_provider.setProAddress(rs.getString("proAddress"));
				_provider.setProFax(rs.getString("proFax"));
				_provider.setCreatedBy(rs.getInt("createdBy"));
				_provider.setCreationDate(rs.getTimestamp("creationDate"));
				_provider.setModifyDate(rs.getTimestamp("modifyDate"));
				_provider.setModifyBy(rs.getInt("modifyBy"));
			}
			BaseDao.closeResource(null, pstm, rs);
		}

		return providerList;
	}

	@Override
	public List<Provider> getProviderList(Connection connection, String proName, int currentPageNo, int pageSize)
			throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Provider> providerList = new ArrayList<Provider>();
		if (connection != null) {
			StringBuffer sql = new StringBuffer();
			sql.append("select * from smbms_provider where 1=1");
			List<Object> list = new ArrayList<Object>();
			if (!StringUtils.isNullOrEmpty(proName)) {
				sql.append(" and proName like ?");
				list.add("%" + proName + "%");
			}
			sql.append(" order by creationDate DESC limit ?,?");
			currentPageNo = (currentPageNo - 1) * pageSize;
			list.add(currentPageNo);
			list.add(pageSize);

			Object[] params = list.toArray();
			System.out.println("sql ----> " + sql.toString());
			rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
			while (rs.next()) {
				Provider _provider = new Provider();
				_provider.setId(rs.getInt("id"));
				_provider.setProCode(rs.getString("proCode"));
				_provider.setProName(rs.getString("proName"));
				_provider.setProDesc(rs.getString("proDesc"));
				_provider.setProContact(rs.getString("proContact"));
				_provider.setProPhone(rs.getString("proPhone"));
				_provider.setProAddress(rs.getString("proAddress"));
				_provider.setProFax(rs.getString("proFax"));
				_provider.setCreatedBy(rs.getInt("createdBy"));
				_provider.setCreationDate(rs.getTimestamp("creationDate"));
				_provider.setModifyDate(rs.getTimestamp("modifyDate"));
				_provider.setModifyBy(rs.getInt("modifyBy"));
				providerList.add(_provider);
			}
			BaseDao.closeResource(null, pstm, rs);
		}
		return providerList;
	}

	@Override
	public int getProviderCount(Connection connection, String proName) throws Exception {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		if (connection != null) {
			StringBuffer sql = new StringBuffer();
			sql.append("select count(1) as count from smbms_provider where 1=1");
			List<Object> list = new ArrayList<Object>();
			if (!StringUtils.isNullOrEmpty(proName)) {
				sql.append(" and proName like ?");
				list.add("%" + proName + "%");
			}
			Object[] params = list.toArray();
			System.out.println("sql ----> " + sql.toString());
			rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
			if (rs.next()) {
				count = rs.getInt("count");
			}
			BaseDao.closeResource(null, pstm, rs);
		}
		return count;
	}
	@Override
	public int add(Connection connection, Provider provider) throws Exception {
		PreparedStatement pstm = null;
		int updateRows = 0;
		if (null != connection) {
			String sql = "insert into smbms_provider (proCode,proName,proDesc,"
					+ "proContact,proPhone,proAddress,proFax,createdBy,creationDate,modifyDate,modifyBy) " + "values(?,?,?,?,?,?,?,?,?,?,?)";
			Object[] params = { provider.getProCode(), provider.getProName(),provider.getProDesc(),provider.getProContact(),provider.getProPhone()
					,provider.getProAddress(),provider.getProFax(),provider.getCreatedBy(),provider.getCreationDate(),provider.getModifyDate(),provider.getModifyBy()};
			updateRows = BaseDao.execute(connection, pstm, sql, params);
			BaseDao.closeResource(null, pstm, null);
		}
		return updateRows;
	}

	@Override
	public Provider getProviderById(Connection connection, String id) throws Exception {
		Provider provider = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		if (null != connection) {
			String sql = "select * from smbms_provider where id=?";
			Object[] params = { id };
			rs = BaseDao.execute(connection, pstm, rs, sql, params);
			if (rs.next()) {
				provider = new Provider();
				provider.setId(rs.getInt("id"));
				provider.setProCode(rs.getString("proCode"));
				provider.setProName(rs.getString("proName"));
				provider.setProDesc(rs.getString("proDesc"));
				provider.setProContact(rs.getString("proContact"));
				provider.setProPhone(rs.getString("proPhone"));
				provider.setProAddress(rs.getString("proAddress"));
				provider.setProFax(rs.getString("proFax"));
				provider.setCreatedBy(rs.getInt("createdBy"));
				provider.setCreationDate(rs.getTimestamp("creationDate"));
				provider.setModifyDate(rs.getTimestamp("modifyDate"));
				provider.setModifyBy(rs.getInt("modifyBy"));
			}
			BaseDao.closeResource(null, pstm, rs);
		}
		return provider;
	}

	@Override
	public int modify(Connection connection, Provider provider) throws Exception {
		int flag = 0;
		PreparedStatement pstm = null;
		if (null != connection) {
			String sql = "update smbms_provider set proCode=?,"
					+ "proName=?,proDesc=?,proContact=?,proPhone=?,proAddress=?,proFax=?,createdBy=?,"
					+ "creationDate=?,modifyDate=?,modifyBy=? where id = ? ";
			Object[] params = { provider.getProCode(), provider.getProName(),provider.getProDesc(),provider.getProContact(),provider.getProPhone()
					,provider.getProAddress(),provider.getProFax(),provider.getCreatedBy(),provider.getCreationDate(),provider.getModifyDate(),provider.getModifyBy(),provider.getId()};
			flag = BaseDao.execute(connection, pstm, sql, params);
			BaseDao.closeResource(null, pstm, null);
		}
		return flag;
	}
}
