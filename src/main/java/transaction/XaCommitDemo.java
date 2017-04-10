package transaction;

import java.sql.Connection;
import java.sql.Statement;

/**
 * @author ash
 */
public class XaCommitDemo extends BaseDemo {

    public static void main(String[] args) throws Exception {
        init();

        tm.begin(); // 开启xa事务

        Connection c1 = ds1.getConnection();
        Statement stmt = c1.createStatement();
        stmt.execute("insert into t_order(uid, price, status) values(1, 2, 1)");
        stmt.close();
        c1.close();

        Connection c2 = ds2.getConnection();
        Statement stmt2 = c2.createStatement();
        stmt2.execute("insert into t_log(uid, price, status) values(1, 2, 1)");
        stmt2.close();
        c2.close();

        tm.commit(); // 提交xa事务

        shutdown();
    }

}
