package com.smbms.dao.bill;

import java.util.List;

import com.smbms.pojo.Bill;

public interface BillMapper {
	List<Bill> getBillByList(Bill bill);
}
