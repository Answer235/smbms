package com.smbms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smbms.pojo.Provider;
import com.smbms.service.provider.ProviderService;

@Controller
public class ProviderController {
	@Autowired
	private ProviderService providerService;
	@RequestMapping("/providerList.html")
	public String providerList(String queryProCode,String queryProName,
				HttpServletRequest request,
				@RequestParam(defaultValue="1")Integer pageIndex) {
		PageHelper.startPage(pageIndex,8);	//·ÖÒ³
		List<Provider> proList = providerService.getProviderByList(queryProCode, queryProName);
		request.setAttribute("providerList", proList);
		PageInfo<Provider> pageInfo = new PageInfo<Provider>(proList);
		request.setAttribute("totalCount", pageInfo.getTotal());
		request.setAttribute("currentPageNo", pageInfo.getPageNum());
		request.setAttribute("totalPageCount", pageInfo.getPages());
		request.setAttribute("queryProCode", queryProCode);
		request.setAttribute("queryProName", queryProName);
		return "providerlist";
	}
	@RequestMapping("/provideradd.html")
	public String proAdd() {
		return "provideradd";
	}
	@RequestMapping("/providersave.html")
	public String providerSave(Provider provider) {
		if(providerService.addProvider(provider)>0) {
			
			return "redirect:providerlist.jsp";
		}
		return "provideradd";
	}
}
