/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Query.PQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Deni Barasena
 */
public class DBAdmin {

    static final String DB_URL = "jdbc:mysql://localhost:3306/kakboi";
    static final String DB_USER = "root";
    static final String DB_PASS = ""; // Local machine DB Pass
//    static final String DB_PASS = "uvUqdU9n"; // DENI GCP machine DB Pass

    private static final String LOGIN_USER = "SELECT * FROM `user` WHERE fullname = ? AND password = SHA1(?)";
    
    private static final String ADD_USER = "INSERT INTO `user` (fullname, email, user_type, password) VALUES(?, ?, ?, ?)";
    private static final String ADD_REGION = "INSERT INTO `region` (region_code, region_name, region_population) VALUES(?, ?, ?)";
    private static final String ADD_BRANCH = "INSERT INTO `branch` (branch_id, branch_name, latitude, longitude, branch_manager) VALUES(?, ?, ?)";
    private static final String ADD_EMPLOYEE = "INSERT INTO `region` (region_code, region_name, region_population) VALUES(?, ?, ?, ?, ?)";
    private static final String ADD_APPLICANT = "INSERT INTO `region` (region_code, region_name, region_population) VALUES(?, ?, ?)";
    private static final String ADD_GOAL = "INSERT INTO `region` (region_code, region_name, region_population) VALUES(?, ?, ?)";
    private static final String ADD_STRATEGY = "INSERT INTO `region` (region_code, region_name, region_population) VALUES(?, ?, ?)";
    private static final String ADD_PLAN = "INSERT INTO `region` (region_code, region_name, region_population) VALUES(?, ?, ?)";
    private static final String ADD_CUSTOMER = "INSERT INTO `region` (region_code, region_name, region_population) VALUES(?, ?, ?)";
    private static final String ADD_TRANSACTION = "INSERT INTO `region` (region_code, region_name, region_population) VALUES(?, ?, ?)";
    
    private static final String TRUNCATE_TABLE = "TRUNCATE TABLE ?";

    public static User login(String username, String password) {
        try {
            return Query.create(LOGIN_USER, User.class).setString(username).setString(password).query();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static boolean addUser(User user) {
        try {
            return Query.create(ADD_USER)
                    .setString(user.getFullname())
                    .setString(user.getEmail())
                    .setString(user.getUserType())
                    .setString(user.getPassword())
                    .executeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean addRegion(Region region) {
        try {
            return Query.create(ADD_REGION)
                    .setString(region.getRegionCode())
                    .setString(region.getRegionName())
                    .setInt(region.getRegionPopulation())
                    .executeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean addBranch(Branch branch) {
        try {
            return Query.create(ADD_BRANCH)
                    .setInt(branch.getRegion().getRegionID())
                    .setString(branch.getBranchName())
                    .setFloat(branch.getLatitude())
                    .setFloat(branch.getLongitude())
                    .setInt(branch.getBranchManager().getEmployeeID())
                    .executeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean addEmployee(Employee employee) {
        try {
            return Query.create(ADD_EMPLOYEE)
                    .setInt(employee.get())
                    .setString(branch.getBranchName())
                    .setFloat(branch.getLatitude())
                    .setFloat(branch.getLongitude())
                    .setInt(branch.getBranchManager().getEmployeeID())
                    .executeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void addApplicant(Applicants applicant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void addCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void addTransaction(Transaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void addGoal(Goal goal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void addStrategy(Strategy strat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void addPlan(Plan plan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static boolean clearDatabase() {
        String[] tableNames = {
            "user", "applicant", "branch", "branch_plan", "customer",
            "employee", "goal", "plan", "product", "product_branch", "region",
            "region", "strategy", "strategy_plan", "transaction", "transaction_data"
        };

        try {
            try (Connection con = Query.create("").getConnection()) {
                for (String name : tableNames) {
                    Query.create(con, TRUNCATE_TABLE).setString(name).executeStatement();
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}

class Statement {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet = null;
    private int slotCounter = 1;

    public Statement(String query) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(DBAdmin.DB_URL, DBAdmin.DB_USER, DBAdmin.DB_PASS);

            statement = connection.prepareStatement(query);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Statement(Connection connection, String query) throws SQLException {
        this.connection = connection;
        this.statement = connection.prepareStatement(query);
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement setInt(int i) throws SQLException {
        statement.setInt(slotCounter, i);
        slotCounter++;
        return this;
    }

    public Statement setString(String i) throws SQLException {
        statement.setString(slotCounter, i);
        slotCounter++;
        return this;
    }

    public Statement setFloat(float i) throws SQLException {
        statement.setFloat(slotCounter, i);
        slotCounter++;
        return this;
    }

    public Statement setTimestamp(Timestamp i) throws SQLException {
        statement.setTimestamp(slotCounter, i);
        slotCounter++;
        return this;
    }

    public boolean executeStatement() throws SQLException {
        return statement.execute();
    }

    public boolean executeStatementOpen() throws SQLException {
        boolean b = statement.execute();
        close();
        return b;
    }

    public ResultSet queryStatement() throws SQLException {
        System.out.println(statement.toString());
        resultSet = statement.executeQuery();
        return resultSet;
    }

    public void close() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        statement.close();
        connection.close();
    }
}

class Query<T extends Model> extends Statement {

    private final Class<T> typeClass;

    private Query(Connection connection, String query, Class<T> typeClass) throws SQLException {
        super(connection, query);
        this.typeClass = typeClass;
    }

    private Query(String query, Class<T> typeClass) throws SQLException {
        super(query);
        this.typeClass = typeClass;
    }

    @Override
    public Query<T> setInt(int i) throws SQLException {
        return (Query<T>) super.setInt(i);
    }

    @Override
    public Query<T> setString(String s) throws SQLException {
        return (Query<T>) super.setString(s);
    }

    @Override
    public Query<T> setFloat(float f) throws SQLException {
        return (Query<T>) super.setFloat(f);
    }

    @Override
    public Query<T> setTimestamp(Timestamp t) throws SQLException {
        return (Query<T>) super.setTimestamp(t);
    }

    public T query() throws SQLException {
        ResultSet rs = queryStatement();
        rs.next();
        T t = getFrom(rs);
        close();
        return t;
    }

    public T queryOpen() throws SQLException {
        ResultSet rs = queryStatement();
        rs.next();
        return getFrom(rs);
    }

    public ArrayList<T> queryList() throws SQLException {
        ArrayList<T> res = new ArrayList<>();
        ResultSet rs = queryStatement();

        while (rs.next()) {
            res.add(getFrom(rs));
        }
        close();

        return res;
    }

    public ArrayList<T> queryListOpen() throws SQLException {
        ArrayList<T> res = new ArrayList<>();
        ResultSet rs = queryStatement();

        while (rs.next()) {
            res.add(getFrom(rs));
        }

        return res;
    }

    private T getFrom(ResultSet rs) throws SQLException {
        try {
            T t = typeClass.newInstance();
            t.setFrom(rs);
            return t;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Statement create(String query) throws SQLException {
        return new Statement(query);
    }

    public static Statement create(Connection connection, String query) throws SQLException {
        return new Statement(connection, query);
    }

    public static <E extends Model> Query<E> create(String query, Class<E> typeClass) throws SQLException {
        return new Query<>(query, typeClass);
    }

    public static <E extends Model> Query<E> create(Connection connection, String query, Class<E> typeClass) throws SQLException {
        return new Query<>(connection, query, typeClass);
    }

    public static PQuery<Integer> Integer(String query, String columnName) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getInt(columnName));
    }

    public static PQuery<Integer> Integer(String query, int columnIndex) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getInt(columnIndex));
    }

    public static PQuery<Integer> Integer(String query) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getInt(1));
    }

    public static PQuery<String> String(String query, String columnName) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getString(columnName));
    }

    public static PQuery<String> String(String query, int columnIndex) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getString(columnIndex));
    }

    public static PQuery<String> String(String query) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getString(1));
    }

    public static PQuery<Float> Float(String query, String columnName) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getFloat(columnName));
    }

    public static PQuery<Float> Float(String query, int columnIndex) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getFloat(columnIndex));
    }

    public static PQuery<Float> Float(String query) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getFloat(1));
    }

    public static PQuery<Timestamp> Timestamp(String query, String columnName) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getTimestamp(columnName));
    }

    public static PQuery<Timestamp> Timestamp(String query, int columnIndex) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getTimestamp(columnIndex));
    }

    public static PQuery<Timestamp> Timestamp(String query) throws SQLException {
        return new PQuery<>(query, (ResultSet rs) -> rs.getTimestamp(1));
    }

    public static PQuery<Integer> Integer(Connection con, String query, String columnName) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getInt(columnName));
    }

    public static PQuery<Integer> Integer(Connection con, String query, int columnIndex) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getInt(columnIndex));
    }

    public static PQuery<Integer> Integer(Connection con, String query) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getInt(1));
    }

    public static PQuery<String> String(Connection con, String query, String columnName) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getString(columnName));
    }

    public static PQuery<String> String(Connection con, String query, int columnIndex) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getString(columnIndex));
    }

    public static PQuery<String> String(Connection con, String query) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getString(1));
    }

    public static PQuery<Float> Float(Connection con, String query, String columnName) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getFloat(columnName));
    }

    public static PQuery<Float> Float(Connection con, String query, int columnIndex) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getFloat(columnIndex));
    }

    public static PQuery<Float> Float(Connection con, String query) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getFloat(1));
    }

    public static PQuery<Timestamp> Timestamp(Connection con, String query, String columnName) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getTimestamp(columnName));
    }

    public static PQuery<Timestamp> Timestamp(Connection con, String query, int columnIndex) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getTimestamp(columnIndex));
    }

    public static PQuery<Timestamp> Timestamp(Connection con, String query) throws SQLException {
        return new PQuery<>(con, query, (ResultSet rs) -> rs.getTimestamp(1));
    }

    static class PQuery<T> extends Statement {

        interface PQueryInterface<T> {

            T getFrom(ResultSet rs) throws SQLException;
        }
        private final PQueryInterface<T> pQueryInterface;

        public PQuery(String query, PQueryInterface<T> pQueryInterface) throws SQLException {
            super(query);
            this.pQueryInterface = pQueryInterface;
        }

        public PQuery(Connection con, String query, PQueryInterface<T> pQueryInterface) throws SQLException {
            super(con, query);
            this.pQueryInterface = pQueryInterface;
        }

        public T query() throws SQLException {
            ResultSet rs = queryStatement();
            rs.next();
            T t = pQueryInterface.getFrom(rs);
            close();
            return t;
        }

        public T queryOpen() throws SQLException {
            ResultSet rs = queryStatement();
            rs.next();
            return pQueryInterface.getFrom(rs);
        }

        public ArrayList<T> queryList() throws SQLException {
            ArrayList<T> res = new ArrayList<>();
            ResultSet rs = queryStatement();

            while (rs.next()) {
                res.add(pQueryInterface.getFrom(rs));
            }
            close();

            return res;
        }

        public ArrayList<T> queryListOpen() throws SQLException {
            ArrayList<T> res = new ArrayList<>();
            ResultSet rs = queryStatement();

            while (rs.next()) {
                res.add(pQueryInterface.getFrom(rs));
            }

            return res;
        }

        @Override
        public PQuery<T> setInt(int i) throws SQLException {
            return (PQuery<T>) super.setInt(i);
        }

        @Override
        public PQuery<T> setString(String s) throws SQLException {
            return (PQuery<T>) super.setString(s);
        }

        @Override
        public PQuery<T> setFloat(float f) throws SQLException {
            return (PQuery<T>) super.setFloat(f);
        }

        @Override
        public PQuery<T> setTimestamp(Timestamp t) throws SQLException {
            return (PQuery<T>) super.setTimestamp(t);
        }
    }

}
