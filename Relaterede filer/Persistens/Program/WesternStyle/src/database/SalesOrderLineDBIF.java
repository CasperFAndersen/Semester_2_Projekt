package database;

import java.util.ArrayList;

import model.SalesOrderLine;

public interface SalesOrderLineDBIF {
	ArrayList<SalesOrderLine> findAllWithSalesOrderId(int prodId);
}
