package kr.co.jboard2.db;

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

	public static final String SELECT_USER 			= "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?,256)";
	public static final String SELECT_USER_BY_NAME_AND_EMAIL 			= "SELECT * FROM `User` WHERE `name`=? AND `email` = ?";
	public static final String SELECT_COUNT_UID 	= "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK 	= "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	public static final String SELECT_COUNT_EMAIL 	= "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	public static final String SELECT_COUNT_NAME_EMAIL 	= "SELECT COUNT(*) FROM `User` WHERE `name`=? AND `email`=?";
	public static final String SELECT_COUNT_UID_EMAIL 	= "SELECT COUNT(*) FROM `User` WHERE `uid`=? AND `email`=?";;
	public static final String SELECT_COUNT_HP	 	= "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	public static final String SELECT_TERMS	 		= "SELECT * FROM `Terms`";
	

	public static final String UPDATE_USER= "UPDATE `User` SET "
											+ "`name`=?,"
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=? "
											+ " WHERE `uid` = ?";
	public static final String UPDATE_USER_PASS = "UPDATE `User` SET `pass`=SHA2(?,256) WHERE `uid` = ?";
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
	
	
	//Article
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` SET "
											  + "`title`=? ,"
											  + "`content`=? ,"
											  + "`file` =? ,"
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

	
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`";
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


	//File 
	public final static String INSERT_FILE = "INSERT INTO `File` SET "
											+ "`ano`=?,"
											+ "`oriName`=?,"
											+ "`newName`=?,"
											+ "`rdate`=NOW()";

	}
