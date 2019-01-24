package com.smbms.service.provider;

import java.util.List;
import com.smbms.pojo.Provider;

public interface ProviderService {
	List<Provider> getProviderByList(String proCode,String proName);
	int addProvider(Provider provider);
}
