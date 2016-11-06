package Util;

import model.Center_Activity;
import model.Record;
import model.Visits;

import java.sql.*;
import java.util.LinkedList;

public enum DBUtil {
    INSTANCE;

    private String driver;
    private String url;
    private String user;
    private String password;

    DBUtil() {
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://ec2-52-211-157-152.eu-west-1.compute.amazonaws.com:3306/maggie_center";
        this.user = "codeforgood";
        this.password = "codeforgood";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    private void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public String user_register(String username, String password) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        String insert_sql = "insert into user_login(username, password) values(?,?)";
        try {
            conn = getConnection();
            pStmt = conn.prepareStatement(insert_sql);
            pStmt.setString(1, username);
            pStmt.setString(2, password);
            int result = pStmt.executeUpdate();
            if(result > 0)
                return "register successfully";
        } catch (SQLException e) {
            return "the username has already been used";
        } finally {
            free(null, pStmt, conn);
        }
        return "failure";
    }

    public Boolean user_login(String username, String password) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String select_sql = "select * from user_login";
        try {
            conn = getConnection();
            pStmt = conn.prepareStatement(select_sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                String username_in_db = rs.getString("username");
                String password_in_db = rs.getString("password");
                System.out.println(username_in_db);
                System.out.println(password_in_db);
                if (username_in_db.equals(username) && password_in_db.equals(password))
                    return true;
            }
            System.out.println("--not found---");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            free(rs, pStmt, conn);
        }
        return false;
    }

    public void truncate_users(){
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String select_sql = "truncate input_j";
        try {
            conn = getConnection();
            pStmt = conn.prepareStatement(select_sql);
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            free(rs, pStmt, conn);
        }
    }


    public LinkedList<Record> list_patients() {
        LinkedList list = new LinkedList();
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String select_sql = "select day_id, remark, seen_by, person, visit_type, gender, cancer_site, journey_stage, " +
                "nature_of_visit, activity from input_draft_j order by day_id desc limit 20";
        try {
            conn = getConnection();
            pStmt = conn.prepareStatement(select_sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                int day_id = rs.getInt("day_id");
                String remark = rs.getString("remark");
                String seen_by = rs.getString("seen_by");
                String person = rs.getString("person");
                String visit_type = rs.getString("visit_type");
                String gender = rs.getString("gender");
                String cancer_site = rs.getString("cancer_site");
                String journey_stage = rs.getString("journey_stage");
                String nature_of_visit = rs.getString("nature_of_visit");
                String activity = rs.getString("activity");
                Record record = new Record(remark, day_id, seen_by, person, visit_type, gender, cancer_site, journey_stage, nature_of_visit, activity);
                list.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            free(rs, pStmt, conn);
        }
        return list;
    }


    public void insert_record(Record record) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String insert_sql = "insert into input_draft_j(day_id, remark, seen_by, person, visit_type, gender, cancer_site, journey_stage, nature_of_visit, activity, center, record_timestamp) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = getConnection();
            pStmt = conn.prepareStatement(insert_sql);
            pStmt.setInt(1, record.getDay_id());
            pStmt.setString(2, record.getRemark());
            pStmt.setString(3, record.getSeen_by());
            pStmt.setString(4, record.getPerson());
            pStmt.setString(5, record.getVisit_type());
            pStmt.setString(6, record.getGender());
            pStmt.setString(7, record.getCancer_site());
            pStmt.setString(8, record.getJourney_stage());
            pStmt.setString(9, record.getNature_of_visit());
            pStmt.setString(10, record.getActivity());
            pStmt.setInt(11, record.getCenter());
            pStmt.setTimestamp(12, new Timestamp(System.currentTimeMillis()));
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            free(rs, pStmt, conn);
        }
    }


    public Record duplicate_patient_record(int day_id) {
        Record record = null;
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String select_sql = "select * from input_draft_j where day_id = ?";
        String insert_sql = "insert into input_j(day_id, seen_by, person, visit_type, gender, cancer_site, journey_stage, nature_of_visit, activity, center, record_timestamp) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = getConnection();
            pStmt = conn.prepareStatement(select_sql);
            pStmt.setInt(1, day_id);
            rs = pStmt.executeQuery();

            String seen_by = null;
            String person = null;
            String visit_type = null;
            String gender = null;
            String cancer_site = null;
            String journey_stage = null;
            String nature_of_visit = null;
            String activity = null;
            String other_activity = null;
            int center = 0;
            Timestamp record_timestamp = null;

            if (rs.next()) {
                seen_by = rs.getString("seen_by");
                person = rs.getString("person");
                visit_type = rs.getString("visit_type");
                gender = rs.getString("gender");
                cancer_site = rs.getString("cancer_site");
                journey_stage = rs.getString("journey_stage");
                nature_of_visit = rs.getString("nature_of_visit");
                activity = rs.getString("activity").trim();
                center = rs.getInt("center");
                record_timestamp = new Timestamp(System.currentTimeMillis());
            }

            while (rs.next()) {
                other_activity = rs.getString("activity").trim();
                activity = activity + ", " + other_activity;
            }
            activity = activity.substring(0, activity.length());
            System.out.println(activity);

            record = new Record(day_id, seen_by, person, visit_type, gender, cancer_site, journey_stage, nature_of_visit, activity, center, record_timestamp);
            pStmt = conn.prepareStatement(insert_sql);
            pStmt.setInt(1, day_id);
            pStmt.setString(2, seen_by);
            pStmt.setString(3, person);
            pStmt.setString(4, visit_type);
            pStmt.setString(5, gender);
            pStmt.setString(6, cancer_site);
            pStmt.setString(7, journey_stage);
            pStmt.setString(8, nature_of_visit);
            pStmt.setString(9, activity);
            pStmt.setInt(10, center);
            pStmt.setTimestamp(11, record_timestamp);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            free(rs, pStmt, conn);
        }
        return record;
    }


    public Record unique_patient_record(int day_id) {
        Record record = null;
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String select_sql = "select * from input_draft_j where day_id = ?";
        String insert_sql = "insert into input_j(day_id, seen_by, person, visit_type, gender, cancer_site, journey_stage, nature_of_visit, activity, center, record_timestamp) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = getConnection();
            pStmt = conn.prepareStatement(select_sql);
            pStmt.setInt(1, day_id);
            rs = pStmt.executeQuery();

            String seen_by = null;
            String person = null;
            String visit_type = null;
            String gender = null;
            String cancer_site = null;
            String journey_stage = null;
            String nature_of_visit = null;
            String activity = null;
            int center = 0;
            Timestamp record_timestamp = null;

            if (rs.next()) {
                seen_by = rs.getString("seen_by");
                person = rs.getString("person");
                visit_type = rs.getString("visit_type");
                gender = rs.getString("gender");
                cancer_site = rs.getString("cancer_site");
                journey_stage = rs.getString("journey_stage");
                nature_of_visit = rs.getString("nature_of_visit");
                activity = rs.getString("activity");
                center = rs.getInt("center");
                record_timestamp = new Timestamp(System.currentTimeMillis());
            }

            record = new Record(day_id, seen_by, person, visit_type, gender, cancer_site, journey_stage, nature_of_visit, activity, center, record_timestamp);
            pStmt = conn.prepareStatement(insert_sql);
            pStmt.setInt(1, day_id);
            pStmt.setString(2, seen_by);
            pStmt.setString(3, person);
            pStmt.setString(4, visit_type);
            pStmt.setString(5, gender);
            pStmt.setString(6, cancer_site);
            pStmt.setString(7, journey_stage);
            pStmt.setString(8, nature_of_visit);
            pStmt.setString(9, activity);
            pStmt.setInt(10, center);
            pStmt.setTimestamp(11, record_timestamp);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            free(rs, pStmt, conn);
        }
        return record;
    }


    public void update_day_id(int original_day_id, int day_id) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String update_sql = "update input_draft_j set day_id = ? where day_id = ?;";
        try {
            conn = getConnection();
            pStmt = conn.prepareStatement(update_sql);
            pStmt.setInt(1, day_id);
            pStmt.setInt(2, original_day_id);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            free(rs, pStmt, conn);
        }
    }


    public LinkedList<Visits> list_visitors() {
        LinkedList list = new LinkedList();
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String select_sql = "select month, center, count from visitor_counting";
        try {
            conn = getConnection();
            pStmt = conn.prepareStatement(select_sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("month");
                int center = rs.getInt("center");
                int count = rs.getInt("count");
                Visits visits = new Visits(month, center, count);
                list.add(visits);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            free(rs, pStmt, conn);
        }
        return list;
    }

    public LinkedList<Center_Activity> list_center_activities() {
        LinkedList list = new LinkedList();
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        String select_sql = "select center, activity, count from center_activity";
        try {
            conn = getConnection();
            pStmt = conn.prepareStatement(select_sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                int center = rs.getInt("center");
                String activity = rs.getString("activity");
                int count = rs.getInt("count");
                Center_Activity center_activity = new Center_Activity(center, activity, count);
                list.add(center_activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            free(rs, pStmt, conn);
        }
        return list;
    }


    public static void main(String[] args) {
        DBUtil.INSTANCE.truncate_users();
    }
}
