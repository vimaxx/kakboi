/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakboilivedataupdater;

import Model.DBAdmin;
import Model.Dummy;
import Model.Notification;
import Model.User;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Deni Barasena
 */
public class KakboiLiveDataUpdater {

    public static final String ICONS = "icon-bubbles3 icon-bubbles4 icon-bubble-notification icon-bubbles5 icon-bubbles6 icon-bubble6 icon-bubbles7 icon-bubble7 icon-bubbles8 icon-bubble8 icon-bubble-dots3 icon-bubble-lines3 icon-bubble9 icon-bubble-dots4 icon-bubble-lines4 icon-bubbles9 icon-bubbles10 icon-user icon-users icon-user-plus icon-user-minus icon-user-cancel icon-user-block icon-user-lock icon-user-check icon-users2 icon-users4 icon-user-tie icon-collaboration icon-vcard icon-hat icon-bowtie icon-quotes-left icon-quotes-right icon-quotes-left2 icon-quotes-right2 icon-hour-glass icon-hour-glass2 icon-hour-glass3 icon-spinner icon-spinner2 icon-spinner3 icon-spinner4 icon-spinner6 icon-spinner9 icon-spinner10 icon-spinner11 icon-microscope icon-enlarge icon-shrink icon-enlarge3 icon-shrink3 icon-enlarge5 icon-shrink5 icon-enlarge6 icon-shrink6 icon-enlarge7 icon-shrink7 icon-key icon-lock icon-lock2 icon-lock4 icon-unlocked icon-lock5 icon-unlocked2 icon-safe icon-wrench icon-wrench2 icon-wrench3 icon-equalizer icon-equalizer2 icon-equalizer3 icon-equalizer4 icon-cog icon-cogs icon-cog2 icon-cog3 icon-cog4 icon-cog52 icon-cog6 icon-cog7 icon-hammer icon-hammer-wrench icon-magic-wand icon-magic-wand2 icon-pulse2 icon-aid-kit icon-bug2 icon-construction icon-traffic-cone icon-traffic-lights icon-pie-chart icon-pie-chart2";
    public static final String NOTIF_CONTENT
            = "Thisi s a mistake\n"
            + "Saya mau bunuh diri xdd\n"
            + "Pls kil mi :DDD:DD";

    public static Dummy dummy;
    public static Scanner scanner;
    public static Scanner contentScanner;

    public static void main(String[] args) {
        init();

        while (true) {
            update();

            try {
                Thread.sleep((int) (5 * (1000 * (1 + Math.random()))));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void init() {
        dummy = new Dummy();
        scanner = new Scanner(ICONS);

        contentScanner = new Scanner(NOTIF_CONTENT);
    }

    public static void update() {
        Notification n = new Notification();
        n.setTime(new Timestamp(new Date().getTime()));
        if (!scanner.hasNext()) {
            scanner = new Scanner(ICONS);

        }
        if (!contentScanner.hasNextLine()) {
            contentScanner = new Scanner(NOTIF_CONTENT);
        }
        n.setNotificationIcon(scanner.next());
        n.setNotificationContent(contentScanner.nextLine());
        DBAdmin.addNotification(n);
    }
}
