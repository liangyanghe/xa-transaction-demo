package transaction;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.util.Properties;

/**
 * @author ash
 */
public class BaseDemo {

    // Atomikos相关
    private static UserTransactionManager utm;
    private static AtomikosDataSourceBean adsb1;
    private static AtomikosDataSourceBean adsb2;

    // 标准接口
    protected static TransactionManager tm;
    protected static DataSource ds1;
    protected static DataSource ds2;

    // 初始化资源
    protected static void init() throws Exception {
        utm = new UserTransactionManager();
        utm.init();
        tm = utm;

        adsb1 = new AtomikosDataSourceBean();
        adsb1.setUniqueResourceName("mysql-db1");
        adsb1.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties p1 = new Properties();
        p1.setProperty("user", "root");
        p1.setProperty("password", "root");
        p1.setProperty("URL", "jdbc:mysql://localhost:3306/db1");
        adsb1.setXaProperties(p1);
        ds1 = adsb1;

        adsb2 = new AtomikosDataSourceBean();
        adsb2.setUniqueResourceName("mysql-db2");
        adsb2.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties p2 = new Properties();
        p2.setProperty("user", "root");
        p2.setProperty("password", "root");
        p2.setProperty("URL", "jdbc:mysql://localhost:3306/db2");
        adsb2.setXaProperties(p2);
        ds2 = adsb2;
    }

    // 释放资源
    protected static void shutdown() {
        adsb1.close();
        adsb2.close();
        utm.close();
    }

}
