package com.smbms.service.bill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.dao.bill.BillMapper;
import com.smbms.pojo.Bill;

@Service
public class BillServiceImpl implements BillService{
	@Autowired
	private BillMapper billMapper;
	@Override
	public List<Bill> getBillByList(Bill bill) {
		return billMapper.getBillByList(bill);
	}
	
}
