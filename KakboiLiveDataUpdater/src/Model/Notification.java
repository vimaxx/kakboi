/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Deni Barasena
 */
public class Notification extends Model {

    private int notificationID;
    private String notificationContent;
    private String notificationIcon;
    private Timestamp time;

    public Notification() {
    }

    public Notification(int notificationID, String notificationContent, String notificationIcon, Timestamp time) {
        this.notificationID = notificationID;
        this.notificationContent = notificationContent;
        this.notificationIcon = notificationIcon;
        this.time = time;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationIcon() {
        return notificationIcon;
    }

    public void setNotificationIcon(String notificationIcon) {
        this.notificationIcon = notificationIcon;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    void setFrom(ResultSet rs) throws SQLException {
        notificationID = rs.getInt("notification_id");
        notificationContent = rs.getString("notification_content");
        notificationIcon = rs.getString("notification_icon");
        time = rs.getTimestamp("time");
    }

    public String getFormattedTime() {
        if (time != null) {
            return time.toString();
        } else {
            return "";
        }
    }

}
