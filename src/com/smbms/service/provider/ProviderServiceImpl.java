package com.smbms.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.dao.provider.ProviderMapper;
import com.smbms.pojo.Provider;
@Service
public class ProviderServiceImpl implements ProviderService{
	@Autowired
	private ProviderMapper providerMapper;
	@Override
	public List<Provider> getProviderByList(String proCode, String proName) {
		return providerMapper.getProviderByList(proCode, proName);
	}
	@Override
	public int addProvider(Provider provider) {
		return providerMapper.addProvider(provider);
	}
}
