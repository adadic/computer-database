package persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {
	
	private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
 
    static {
        config.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" );
        config.setUsername( "admincdb" );
        config.setPassword( "qwerty1234" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }
 
    private DataSource() {
    }
 
    public static Connection getConnection() throws SQLException {
    	
        return ds.getConnection();
    }
    
//    private static HikariConfig config = new HikariConfig("src/main/java/res/hikari.properties");
//    private static HikariDataSource ds;
// 
//    static {
//        ds = new HikariDataSource(config);
//    }
// 
//    private DataSource() {
//    	
//    }
// 
//    public static Connection getConnection() throws SQLException {
//        return ds.getConnection();
//    }
//    
}
