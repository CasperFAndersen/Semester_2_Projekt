package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.InsertFailedException;
import exception.NotImplementedException;
import exception.PhoneAlreadyExistException;
import exception.ZipAlreadyExistException;
import model.Product;
import model.ProductPrice;
import model.ProductType;
import model.Property;
import model.Supplier;

/**
 * SmartOrder
 * ProductDB.java
 * Purpose: Handle Product object interaction from and to the database
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductDB implements ProductDBIF {

	private MakePreparedStatement mps = new MakePreparedStatement();

	/**
	 * PreparedStatements for Products in the database. 
	 */
	private static final String insertProductQ = "INSERT INTO product VALUES (?, ?, ?, ?, ?, ?)";
	private static final String insertProductPriceQ = "INSERT INTO productPrice VALUES (?, ?, ?)";
	private static final String insertProductTypeQ = "INSERT INTO producttype VALUES (?, ?, ?)";
	private static final String insertPartOfProductQ = "INSERT INTO partofproduct VALUES (?, ?)";

	private static final String findAllQ = "SELECT * FROM product, productprice, producttype WHERE producttype.id = product.product_type_id AND product.product_price_id = productprice.id ";
	private static final String findByModelQ = findAllQ + "AND model = ? and product.product_price_id = productprice.id";
	private static final String findByIdQ = findAllQ + "AND product.id = ? and product.product_price_id = productprice.id";
	private static final String findModuleQ = "SELECT * FROM PartOfProduct WHERE product_id = ?";
	private static final String findAllProductTypesQ = "SELECT * FROM producttype";
	private static final String findProductTypeByNameQ = "SELECT * FROM producttype WHERE category_name = ?";
	private static final String findProductTypeByIdQ = "SELECT * FROM producttype WHERE id = ?";
	private static final String findTemplateByIdQ = "SELECT * FROM product WHERE id = ?";

	private static final String updateProductQ = ""; //TODO To be implemented
	private static final String updateProductsProdTypeQ = "UPDATE product SET product_type_id = ? WHERE id = ?";
	private static final String removeProductQ = "DELETE FROM product where id = ?";

	private PreparedStatement insertProduct, insertProductPrice, insertProductType, insertPartOfProduct,
	findAll, findProductByModel, findById, findTemplateById, findModule, findAllProductTypes, findProductTypeByName, findProductTypeById,
	updateProduct, updateProductsProdType, removeProduct;

	public ProductDB() {
		try {
			insertProduct = mps.makePreparedStatement(insertProductQ, Statement.RETURN_GENERATED_KEYS);
			insertProductPrice = mps.makePreparedStatement(insertProductPriceQ, Statement.RETURN_GENERATED_KEYS);
			insertProductType = mps.makePreparedStatement(insertProductTypeQ, Statement.RETURN_GENERATED_KEYS);
			insertPartOfProduct = mps.makePreparedStatement(insertPartOfProductQ);
			findAll = mps.makePreparedStatement(findAllQ);
			findProductByModel = mps.makePreparedStatement(findByModelQ);
			findById = mps.makePreparedStatement(findByIdQ);
			findTemplateById = mps.makePreparedStatement(findTemplateByIdQ);
			findModule = mps.makePreparedStatement(findModuleQ);
			findProductTypeByName = mps.makePreparedStatement(findProductTypeByNameQ);
			findProductTypeById = mps.makePreparedStatement(findProductTypeByIdQ);
			findAllProductTypes = mps.makePreparedStatement(findAllProductTypesQ);

			updateProduct = mps.makePreparedStatement(updateProductQ);
			updateProductsProdType = mps.makePreparedStatement(updateProductsProdTypeQ);
			removeProduct = mps.makePreparedStatement(removeProductQ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserts informaton into the ProductPrice table in the database
	 * @param productPrice as ProductPrice
	 * @return id of the inserted ProductPrice
	 */
	@Override
	public int insertProductPrice(ProductPrice productPrice) throws SQLException, InsertFailedException {
		insertProductPrice.setDouble(1, productPrice.getPurchasePrice());
		insertProductPrice.setDouble(2, productPrice.getSalesPrice());
		insertProductPrice.setDate(3, java.sql.Date.valueOf(productPrice.getFromDate()));
		int id = -1;
		insertProductPrice.executeUpdate();
		try (ResultSet generatedKey = insertProductPrice.getGeneratedKeys()) {
			if (generatedKey.next()) {
				id = generatedKey.getInt(1);
			} else {
				throw new InsertFailedException(insertProductPrice.getClass().getName());
			}
		}
		return id;
	}

	/**
	 * Inserts information into the ProductType table in the database.
	 * @param productType as ProductType
	 * @return id of the inserted ProductType
	 */
	@Override
	public int insertProductType(ProductType productType) throws SQLException, InsertFailedException {
		insertProductType.setString(1, productType.getCategoryName());
		insertProductType.setString(2, productType.getType());
		insertProductType.setInt(3, productType.getTemplateId());
		int id = -1;
		insertProductType.execute();
		try (ResultSet generatedKey = insertProductType.getGeneratedKeys()) {
			if (generatedKey.next()) {
				id = generatedKey.getInt(1);
			} else {
				throw new InsertFailedException(insertProductType.getClass().getName());
			}
		}
		return id;
	}

	/**
	 * Inserts a template product, which is a template for a given product type.
	 * @param product as Product
	 * @return id of the inserted product template.
	 */
	@Override
	public int insertTemplateProduct(Product product) throws SQLException, InsertFailedException {
		insertProduct.setString(1, product.getModel());
		insertProduct.setString(2, null);
		insertProduct.setString(3, null);
		insertProduct.setNull(4, java.sql.Types.INTEGER);
		insertProduct.setNull(5, java.sql.Types.INTEGER);
		insertProduct.setNull(6, java.sql.Types.INTEGER);
		int id = -1;
		insertProduct.executeUpdate();
		try (ResultSet generatedKey = insertProduct.getGeneratedKeys()) {
			if (generatedKey.next()) {
				id = generatedKey.getInt(1);
			} else {
				throw new InsertFailedException(insertProduct.getClass().getName());
			}
		}
		return id;
	}

	/**
	 * Inserts information into the Product table in the database
	 * @param product as Product
	 * @param productType as ProductType
	 * @return id of the inserted product.
	 */
	@Override
	public int insertProduct(Product product, ProductType productType) throws SQLException, InsertFailedException {
		int productPriceId = insertProductPrice(product.getProductPrice());
		int productTypeId;
		ProductType tempProductType = findProductTypeByName(productType.getCategoryName());
		if (tempProductType != null) {
			productTypeId = tempProductType.getId();
		} else { 
			productTypeId = insertProductType(productType);
		}
		insertProduct.setString(1, product.getModel());
		insertProduct.setString(2, product.getDescription());
		insertProduct.setString(3, product.getDimensions());
		insertProduct.setInt(4, productPriceId);
		insertProduct.setInt(5, productTypeId);
		insertProduct.setInt(6, product.getSupplier().getId());
		int id = -1;
		insertProduct.executeUpdate();
		try (ResultSet generatedKey = insertProduct.getGeneratedKeys()) {
			if (generatedKey.next()) {
				id = generatedKey.getInt(1);
			} else {
				throw new InsertFailedException(insertProduct.getClass().getName());
			}
		}
		return id;
	}

	/**
	 * Inserts information about a module added to the PartOfProduct table.
	 * @param moduleId as int
	 * @param productId as int
	 */
	@Override
	public void insertPartOfProduct(int moduleId, int productId) throws SQLException {
		insertPartOfProduct.setInt(1, moduleId);
		insertPartOfProduct.setInt(2, productId);
		insertPartOfProduct.execute();
	}

	/**
	 * Update the product type of an Product
	 * @param productId as int
	 * @param productTypeId as int
	 */
	@Override
	public void updateProductsProductType(int prodId, int prodTypeId) throws SQLException {
		updateProductsProdType.setInt(1, prodTypeId);
		updateProductsProdType.setInt(2, prodId);
		updateProductsProdType.execute();
	}

	/**
	 * Creates a Product object from an ResultSet, which is filled with the information retrieved from the database using a find method.
	 * @param rs as ResultSet
	 * @return a Product Object.
	 */
	private Product buildProductObject(ResultSet rs) throws SQLException { 
		int id = rs.getInt("id");
		String model = rs.getString("model");
		String description = rs.getString("description");
		String dimensions = rs.getString("dimensions");
		ProductPrice productprice = new ProductPrice(
				rs.getDouble("purchase_price"), 
				rs.getDouble("sales_price"), 
				rs.getDate("from_date").toLocalDate());
		Supplier supplier = new PersonDB().findSupplierById(rs.getInt("supplier_id")); 
		ArrayList<Property> properties = new ArrayList<>(new PropertyDB().findPropertiesByProductId(id));
		Product res = new Product(id, model, description, dimensions, productprice, supplier, properties);
		res.setProductType(buildProductTypeObject(rs));
		if (res.getProductType().getType().equalsIgnoreCase("customizable")) {
			loadModules(res);
		}
		return res;
	}

	/**
	 * Creates a ProductType object from an ResultSet, which is filled with the information retrieved from the database using a find method.
	 * @param rs as ResultSet
	 * @return a ProductType Object.
	 */
	private ProductType buildProductTypeObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String category = rs.getString("category_name");
		int templateId = rs.getInt("template_id");
		String type = rs.getString("type");
		return new ProductType(id, category, templateId, type);
	}

	/**
	 * loads all the modules which is for a given product
	 * @param product
	 */
	private void loadModules(Product product) {
		ArrayList<Product> modules = new ArrayList<>();
		try{
			modules = (ArrayList<Product>) findModulesByProductId(product.getId());
			for(Product p : modules){
				product.addModuleToProduct(p);
			}
		}
		catch(Exception e){
			e.getMessage();
		}
	}

	/**
	 * Creates an ArrayList Products from an ResultSet, which is filled with information retrieved from the database with a find method.
	 * @param rs as ResultSet
	 * @return An ArrayList of Product objects.
	 */
	private List<Product> buildProductObjects(ResultSet rs) throws SQLException { 
		ArrayList<Product> res = new ArrayList<>();
		while (rs.next()) {
			Product p = buildProductObject(rs);
			res.add(p);
		}
		return res;
	}

	/**
	 * Finds all products in the database
	 * @return a list of all products
	 */
	@Override
	public List<Product> findAll() {
		ResultSet rs;
		try{
			rs = findAll.executeQuery();
			List<Product> res = buildProductObjects(rs);
			return res;
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Finds a Product in the database based on a model name.
	 * @param model as String
	 * @return Product object
	 */
	@Override
	public Product findProductByModel(String model) {
		ResultSet rs;
		Product res = null;
		try{
			findProductByModel.setString(1, model);
			rs = findProductByModel.executeQuery();
			if(rs.next()){
				res = buildProductObject(rs);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Finds all modules for a Product, by a given Product id
	 * @param id as int
	 * @return a list of modules for a given Product.
	 */
	@Override
	public List<Product> findModulesByProductId(int id) {
		ResultSet rs;
		List<Product> res = new ArrayList<>();
		try {
			findModule.setInt(1, id);
			rs = findModule.executeQuery();
			while(rs.next()){
				int moduleId = rs.getInt("product_part_id");
				res.add(findProductById(moduleId));
			}

			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Finds a Product in the database based on an id.
	 * @param id as int
	 * @return Product object.
	 */
	@Override
	public Product findProductById(int id) {
		ResultSet rs;
		Product res = null;
		try {
			findById.setInt(1, id);
			rs = findById.executeQuery();
			if (rs.next()) {
				res = buildProductObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Finds all existing ProductType's.
	 * @return an ArrayList of all ProductTypes
	 */
	@Override
	public ArrayList<ProductType> findAllProductTypes() {
		ResultSet rs;
		ArrayList<ProductType> res = null;
		try {
			rs = findAllProductTypes.executeQuery();
			res = buildProductTypeObjects(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Creates an ArrayList of ProductType objects based on a ResultSet
	 * @param rs as ResultSet
	 * @return an ArrayList of ProductType objects
	 */
	public ArrayList<ProductType> buildProductTypeObjects(ResultSet rs) throws SQLException {
		ArrayList<ProductType> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildProductTypeObject(rs));
		}
		return res;
	}

	/**
	 * Finds a ProductType in the database based on a name.
	 * @param productType as String
	 * @return ProductType object
	 */
	public ProductType findProductTypeByName(String productType) {
		ResultSet rs;
		ProductType res = null;
		try {
			findProductTypeByName.setString(1, productType);
			rs = findProductTypeByName.executeQuery();
			if (rs.next()) {
				res = buildProductTypeObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Updates a product by id.
	 */
	@Override
	public void updateProductById() throws NotImplementedException {
		throw new NotImplementedException();
	}

	/**
	 * Removes a Product by id
	 * @param id as int
	 */
	public void removeProductById(int id) throws SQLException {
		removeProduct.setInt(1, id);
		removeProduct.executeUpdate();
	}

	/**
	 * Finds a Product template in the database using an id.
	 * @param id as int
	 * @return Product template
	 */
	@Override
	public Product findTemplateByID(int id) {
		ResultSet rs;
		Product res = null;
		try {
			findTemplateById.setInt(1, id);
			rs = findTemplateById.executeQuery();
			if (rs.next()) {
				res = buildTemplateObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Finds a ProductType in the database based on an id.
	 * @param id as int
	 * @return ProductType object.
	 */
	public ProductType findProductTypeById(int id) {
		ResultSet rs;
		ProductType res = null;
		try {
			findProductTypeById.setInt(1, id);
			rs = findProductTypeById.executeQuery();
			if (rs.next()) {
				res = buildProductTypeObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Creates a Template object from an ResultSet, which is filled with the information retrieved from the database using a find method.
	 * @param rs as ResultSet
	 * @return a Product Object.
	 */
	private Product buildTemplateObject(ResultSet rs) throws SQLException {
		String model = rs.getString("model");
		ProductType productType = findProductTypeById(rs.getInt("product_type_id"));
		int id = rs.getInt("id");
		ArrayList<Property> properties = new ArrayList<>(new PropertyDB().findPropertiesByProductId(id));
		Product res = new Product();
		res.setModel(model);
		res.setProductType(productType);
		res.setProperties(properties);
		return res;
	}

}
