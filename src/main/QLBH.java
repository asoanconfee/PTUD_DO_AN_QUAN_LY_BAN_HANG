
package main;

import java.sql.SQLException;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import ConnectDB.KetNoiSQL;
import gui.CountDownThread;
import gui.Login;

public class QLBH {

    public static void main(String[] args) throws SQLException {
    	FlatAnimatedLafChange.showSnapshot();
        FlatMacLightLaf.setup();
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
        new Login().setVisible(true);
//        try {
//			KetNoiSQL.getInstance().connect();
//			CountDownThread countDownThread = new CountDownThread();
//			countDownThread.start();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
    }
}