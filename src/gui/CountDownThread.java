package gui;

import java.awt.Color;
import java.sql.SQLException;

import ConnectDB.KetNoiSQL;

public class CountDownThread extends Thread {
	public void run() {
		int count = 3;
		KetNoiSQL.getInstance().connect();
		FrmCountDown frmGioiThieu = new FrmCountDown();
		frmGioiThieu.setVisible(true);
		frmGioiThieu.setLocationRelativeTo(null);
		for(int i=0;i<count;i++) {
			try {
				Thread.sleep(1000);
				frmGioiThieu.progressBar.setValue(10);
				if(i==1)
					frmGioiThieu.progressBar.setValue(66);
				if(i==2)
					frmGioiThieu.progressBar.setValue(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		frmGioiThieu.setVisible(false);
		Login tabLogin = null;
		try {
			tabLogin = new Login();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabLogin.setVisible(true);
		tabLogin.setLocationRelativeTo(null);
	}
}
