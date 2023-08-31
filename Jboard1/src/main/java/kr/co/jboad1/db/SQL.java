package kr.co.jboad1.db;

public class SQL {
	//user
	public static final String INSERT_USER = "INSERT INTO `User` SET "
											+ "`uid`=?, "
											+ "`pass`=SHA2(?,256), "
											+ "`name`=?, "
											+ "`nick`=?, "
											+ "`email`=?, "
											+ "`hp`=?,"
											+ "`zip`=?, "
											+ "`addr1`=?, "
											+ "`addr2`=?, "
											+ "`regip`=?, "
											+ "`regDate`=NOW()";

	public static final String SELECT_USER 			= "SELECT * FROM `User` WHERE `uid`=? AND PASS =SHA2(?,256)";
	public static final String SELECT_COUNT_UID 	= "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK 	= "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	public static final String SELECT_COUNT_EMAIL 	= "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	public static final String SELECT_COUNT_HP	 	= "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	public static final String SELECT_TERMS	 		= "SELECT * FROM `Terms`";
	
	//Article
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` SET "
											  + "`title`=? ,"
											  + "`content`=? ,"
											  + "`writer`=? ,"
											  + "`regip`=? ,"
											  + "`rdate`=NOW()";

	public static String INSERT_COMMENT =  "INSERT INTO `Article` SET "
			  								+ "`parent`=? ,"
											  + "`content`=? ,"
											  + "`writer`=? ,"
											  + "`regip`=? ,"
											  + "`rdate`=NOW()";
	//public static final String SELECT_ARTICLES = "SELECT * FROM `article` ORDER BY `rdate` DESC";
	//public static final String SELECT_USER_NICK = "SELECT nick FROM `User` WHERE `uid`=?"; 

	public static final String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=?";

	public static final String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? OR `parent`=?";

	public static final String SELECT_ARTICLE = "SELECT "
										+ "a.*, b.`nick` "
										+ "FROM `Article` AS a "
										+ "JOIN `User` AS b ON a.writer=b.uid "
										+ "WHERE `no`=?";

	public static final String UPDATE_ARTICLE = "UPDATE `Article` SET"
												+"`title`=?,"
												+"`content`=?,"
												+"`regip`=? "
												+"WHERE `no`=?";
	public static final String SELECT_ARTICLES = "SELECT "
												+ "a.*, b.`nick` "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b ON a.writer=b.uid "
												+ "WHERE `parent`=0 "
												+ "ORDER BY a.`no` DESC "
												+ "LIMIT ?,10";
	public static final String SELECT_COMMENTS = "SELECT *, b.`nick` FROM `Article` AS a "
												+"JOIN `User` AS b ON a.writer=b.uid "
												+"WHERE `parent`=?";
	public static final String SELECT_COUNT_TOTAL= "SELECT COUNT(*) FROM `Article` WHERE `parent`=0";

	public static final String UPDATE_ARTICLE_FOR_COMMENT_PLUS = "UPDATE `Article` SET "
													+ "`comment` = `comment` + 1 "
													+ "WHERE `no` = ?";
	public static final String UPDATE_ARTICLE_FOR_COMMENT_MINUS = "UPDATE `Article` SET "
			+ "`comment` = `comment` - 1 "
			+ "WHERE `no` = ?";

	public static final String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?";



	}
