package service.provider;

import pojo.Provider;

import java.util.List;

public interface ProviderService {
	public List<Provider> getProviderList();

	public List<Provider> getProviderList(String queryProviderName, int currentPageNo, int pageSize);

	public int getProviderCount(String queryProviderName);
	
	public boolean add(Provider provider);
	
	public Provider getProviderById(String id);

	public boolean modify(Provider provider);
}
