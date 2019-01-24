package com.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.Provider;

public interface ProviderMapper {
	List<Provider> getProviderByList(@Param("proCode") String proCode,
			@Param("proName") String proName);
	int addProvider(Provider provider);
}
