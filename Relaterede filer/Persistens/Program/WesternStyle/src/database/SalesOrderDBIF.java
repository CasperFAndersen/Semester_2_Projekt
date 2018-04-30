package database;

import java.util.List;

import model.SalesOrder;

public interface SalesOrderDBIF {
	List<SalesOrder> getSalesOrders();
	int addSalesOrder(SalesOrder salesOrder);
}
