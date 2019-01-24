package com.smbms.service.bill;

import java.util.List;

import com.smbms.pojo.Bill;

public interface BillService {
	List<Bill> getBillByList(Bill bill);
}
