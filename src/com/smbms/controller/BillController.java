package com.smbms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smbms.pojo.Bill;
import com.smbms.pojo.Provider;
import com.smbms.service.bill.BillService;
import com.smbms.service.provider.ProviderService;

@Controller
public class BillController {
	@Autowired
	private ProviderService providerService;
	@Autowired
	private BillService billService;
	@RequestMapping("/billList.html")
	public String BillList(String queryProductName,Integer queryProviderId,Integer queryIsPayment,
			HttpServletRequest request,
			@RequestParam(defaultValue="1")Integer pageIndex) {
		List<Provider> pList = providerService.getProviderByList(null, null);
		request.setAttribute("providerList", pList);
		Bill bill = new Bill();
		bill.setProductName(queryProductName);
		bill.setProviderId(queryProviderId);
		bill.setIsPayment(queryIsPayment);
		PageHelper.startPage(pageIndex,8);	//·ÖÒ³
		List<Bill> bList = billService.getBillByList(bill);
		request.setAttribute("billList", bList);
		PageInfo<Bill> pageInfo = new PageInfo<Bill>(bList);
		request.setAttribute("totalCount", pageInfo.getTotal());
		request.setAttribute("currentPageNo", pageInfo.getPageNum());
		request.setAttribute("totalPageCount", pageInfo.getPages());
		request.setAttribute("queryProductName",queryProductName);
		request.setAttribute("queryProviderId", queryProviderId);
		request.setAttribute("queryIsPayment", queryIsPayment);
		return "billlist";
	}
}
