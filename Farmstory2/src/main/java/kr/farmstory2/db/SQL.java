package kr.farmstory2.db;

public class SQL {
	// User
	public static final String INSERT_USER = "INSERT INTO `User` SET "
											+ "`uid`=?,"
											+ "`pass`=SHA2(?, 256),"
											+ "`name`=?,"											
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`regip`=?,"
											+ "`regDate`=NOW()";
	
	public static final String SELECT_USER_FOR_LOGIN        = "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	public static final String SELECT_COUNT_UID   = "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK  = "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	public static final String SELECT_COUNT_HP    = "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	public static final String SELECT_TERMS       = "SELECT * FROM `Terms`";
	
	public static final String SELECT_USERS = "SELECT * "
												+ "FROM `User` "
												+ "WHERE `name` IS NOT NULL "
												+ "ORDER BY `regDate` DESC "
												+ "LIMIT ?, 10";
	public static final String SELECT_COUNT_USERS = "SELECT COUNT(*) FROM `User`";
	
	public final static String DELETE_USER = "DELETE FROM `User` WHERE `uid`=?";
	public static final String UPDATE_USER_FOR_WITHDRAW = "UPDATE `User` SET "
												+ "`pass`=null,"
												+ "`name`=null,"
												+ "`nick`=null,"
												+ "`email`=null,"
												+ "`hp`=null,"
												+ "`role`=null,"
												+ "`zip`=null,"
												+ "`addr1`=null,"
												+ "`addr2`=null,"
												+ "`leaveDate` = NOW() "
												+ " WHERE `uid` = ?";
	public static final String UPDATE_USER_FOR_ROLE = "UPDATE `User` SET "
												+ "`role`=? "
												+ " WHERE `uid` = ?";

	
	// Article
	public final static String INSERT_ARTICLE = "INSERT INTO `Article` SET "
												+ "`cate`=?, "
												+ "`title`=?, "
												+ "`content`=?,"
												+ "`file`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public final static String INSERT_COMMENT = "INSERT INTO `Article` SET "
												+ "`cate`=?, "
												+ "`parent`=?, "
												+ "`content`=?,"
												+ "`writer`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";

	public final static String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`";
	public final static String SELECT_COMMENT_MAX_NO = "SELECT MAX(`no`) FROM `Article`";
	public final static String SELECT_LATESTS = "SELECT `no`, `title`, `rdate` FROM `Article` "
												+ "WHERE `parent`=0 AND `cate`=? "
												+ "Order BY `no` DESC LIMIT ?";
	
	public static final String SELECT_ARTICLE = "SELECT "
													+ "a.*, b.`nick`, c.* "
													+ "FROM `Article` AS a "
													+ "JOIN `User` AS b ON a.writer=b.uid "
													+ "LEFT JOIN `File` AS c ON a.`no` = c.`ano`"
													+ "WHERE `no`=?";
	public final static String SELECT_ARTICLES = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid "
												+ "WHERE `parent`=0 AND `cate`=? "
												+ "ORDER BY `no` DESC "
												+ "LIMIT ?, 10";
	
	public final static String SELECT_COMMENTS = "SELECT "
												+ "a.*, "
												+ "b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer = b.uid "
												+ "WHERE `parent`=?";
	
	public final static String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0 AND `cate`=?";

	public final static String SELECT_ARTICLE_FOR_CHECK_WRITER = "SELECT * FROM `Article` WHERE `no`=? AND `writer`=?";
	
	
	public final static String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=?, `file`=?, `regip`=? WHERE `no`=?";
	public final static String UPDATE_ARTICLE_FOR_COMMENT_PLUS = "UPDATE `Article` SET `comment` = `comment` + 1 WHERE `no`=?";
	public final static String UPDATE_ARTICLE_FOR_COMMENT_MINUS = "UPDATE `Article` SET `comment` = `comment` - 1 WHERE `no`=?";
	public final static String UPDATE_ARTICLE_HIT = "UPDATE `Article` SET `hit` = `hit` + 1 WHERE `no` = ?";
	public final static String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=?";

	public final static String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? OR `parent`=?";
	public final static String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?";
	
	
	// Product
	public final static String INSERT_PRODUCT = "INSERT INTO `Product` SET "
												+ "`type`=?,"
												+ "`pName`=?,"
												+ "`price`=?,"
												+ "`delivery`=?,"
												+ "`stock`=?,"
												+ "`thumb1`=?,"
												+ "`thumb2`=?,"
												+ "`thumb3`=?,"
												+ "`seller`=?,"
												+ "`etc`=?,"
												+ "`rdate`=NOW()";
	
	
	public final static String SELECT_PRODUCT = "SELECT * FROM `Product` WHERE `pNo`=?  AND `ldate` IS NULL ORDER BY `pNo` DESC ";
	public final static String SELECT_PRODUCTS_ALL = "SELECT * FROM `Product` WHERE `stock` > 0 AND `ldate` IS NULL ORDER BY `pNo` DESC LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_TYPE = "SELECT * FROM `Product` WHERE `stock` > 0 AND `type`=? AND `ldate` IS NULL ORDER BY `pNo` DESC LIMIT ?, 10";
	public final static String SELECT_COUNT_PRODUCTS_ALL = "SELECT COUNT(*) FROM `Product` WHERE `stock` > 0 AND `ldate` IS NULL";
	public final static String SELECT_COUNT_PRODUCTS_TYPE = "SELECT COUNT(*) FROM `Product` WHERE `stock` > 0 AND `type`=? AND `ldate` IS NULL";

	public static final String UPDATE_PRODUCT_FOR_WITHDRAW = "UPDATE `Product` SET "
													+ "`type`=null,"
													+ "`pName`=CONCAT(`pName`,'(판매중단)'),"
													+ "`price`=null,"
													+ "`delivery`=null,"
													+ "`stock`=null,"
													+ "`sold`=null,"
													+ "`thumb1`='soldOut',"
													+ "`thumb2`='soldOut',"
													+ "`thumb3`='soldOut',"
													+ "`etc` = null, "
													+ "`rdate` = NOW() "
													+ " WHERE `pNo` = ?";
	// Order
	public static final String INSERT_ORDER = "INSERT INTO `Order` SET "
											+ "`orderProduct`=?,"
											+ "`orderCount`=?,"
											+ "`orderDelivery`=?,"
											+ "`orderPrice`=?,"
											+ "`orderTotal`=?,"
											+ "`receiver`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`orderEtc`=?,"
											+ "`orderUser`=?,"
											+ "`orderDate`=NOW()";
	
	public static final String SELECT_ORDERS = "SELECT "
												+ "a.*,"
												+ "b.`pName`,"
												+ "b.`thumb1`, "
												+ "c.`name` "
												+ "FROM `Order` AS a "
												+ "LEFT JOIN `Product` AS b "
												+ "ON a.orderProduct = b.pNo "
												+ "LEFT JOIN `User` AS c "
												+ "ON a.orderUser = c.uid "
												+ "ORDER BY `orderNo` DESC "
												+ "LIMIT ?, 10";
	
	public static final String SELECT_COUNT_ORDERS = "SELECT COUNT(*) FROM `Order`";
	public static final String DELETE_ORDER = "DELETE FROM `Order` WHERE `orderNo`=?";

	//파일 
	public final static String INSERT_FILE = "INSERT INTO `File` SET "
											+ "`ano`=?,"
											+ "`oriName`=?,"
											+ "`newName`=?,"
											+ "`rdate`=NOW()";

	public final static String SELECT_FILE = "SELECT * FROM `File` WHERE `fno`=?";
	public final static String SELECT_FILES_NEW_NAME = "SELECT newName FROM `File` WHERE `ano` = ?";
	public final static String DELETE_FILE = "DELETE FROM `File` WHERE `ano`=?";

	public final static String UPDATE_FILE_DOWNLOAD = "UPDATE `File` SET `download` = `download` + 1 WHERE `ano` = ?";


	//admin - index
	public static final String SELECT_LATEST_PRODUCTS = "SELECT * "
										+ "FROM `Product` WHERE `ldate` IS NULL ORDER BY `pNo` DESC LIMIT ?";
			
	public static final String SELECT_LATEST_ORDERS = "SELECT "
										+ "a.*,"
										+ "b.`pName`,"
										+ "c.`name` "
										+ "FROM `Order` AS a "
										+ "LEFT JOIN `Product` AS b "
										+ "ON a.orderProduct = b.pNo "
										+ "LEFT JOIN `User` AS c "
										+ "ON a.orderUser = c.uid "
										+ "ORDER BY `orderNo` DESC "
										+ "LIMIT ?";
	public static final String SELECT_LATEST_USERS = "SELECT * "
										+ "FROM `User` "
										+ "WHERE `name` IS NOT NULL "
										+ "ORDER BY `regDate` DESC "
										+ "LIMIT ?";


	
}