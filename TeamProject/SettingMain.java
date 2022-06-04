package TeamProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingMain extends JFrame implements ActionListener{
	public static final JPanel currentPanel = new JPanel();
	public static final Color BGCOLOR = new Color(0xededf9);
	
	private JButton submitBtn;
	private JButton mainBtn;
	private JButton addmediBtn;
	private JButton checkborrowBtn;
	private JButton backBtn;
	
	private Setting_adminPage adminPage;
	private Setting_villainPage villainPage;
	private Setting_selectPage selectPage;
	
	public static void main(String[] args) {
		SettingMain main = new SettingMain();
		main.setVisible(true);
		return;
	}
	
	public SettingMain() {
		super("Setting Main");
		setSize(660, 990);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(600, 10);
		setResizable(false);
		setLayout(new BorderLayout());
		
		currentPanel.setBackground(BGCOLOR);
		
		Container test = this.getContentPane();
		test.setBackground(BGCOLOR);
		JPanel viewPanel = new JPanel();
		viewPanel.setBackground(BGCOLOR);
		viewPanel.add(currentPanel);
		test.add(viewPanel, BorderLayout.CENTER);
		
		adminPage(0,"","","");
		
		setVisible(true);
	}
	
	private void adminPage(int errorCode, String name, String ID, String phone) {
		String errorStr = "";

		if (errorCode == 1) {
			errorStr += "등록되지 않은 이용자입니다";
		} else if (errorCode == 0) {
			errorStr += "";
		} else if (errorCode == -1) {
			errorStr += "정보를 모두 입력해주세요";
		} else if (errorCode == -2) {
			errorStr += "학번을 다시 입력해주세요";
		} else if (errorCode == -3) {
			errorStr += "전화번호를 다시 입력해주세요";
		} else {
			errorStr += "알 수 없는 오류입니다";
		}

		adminPage = new Setting_adminPage(errorStr, name, ID, phone);

		submitBtn = new ButtonForm("./image/mailSubmitButton");
		submitBtn.addActionListener(this);

		adminPage.add(submitBtn);

		currentPanel.add(adminPage, BorderLayout.CENTER);
	}
	
	private void villainPage() {
		villainPage = new Setting_villainPage();
		mainBtn = new ButtonForm("./image/gotoMainButton");
		mainBtn.addActionListener(this);
		
		villainPage.add(mainBtn);
		
		currentPanel.add(villainPage, BorderLayout.CENTER);
	}
	
	private void selectPage() {
		selectPage = new Setting_selectPage();
		
		addmediBtn = new ButtonForm2("./image/addmedi");
		addmediBtn.addActionListener(this);
		
		checkborrowBtn = new ButtonForm2("./image/checkborrow");
		checkborrowBtn.addActionListener(this);
		
		//back 버튼 맨 밑에 넣을 수 있는가??? 
		backBtn = new ButtonForm_back("./image/back");
		backBtn.addActionListener(this);
		
		selectPage.add(addmediBtn);
		selectPage.add(checkborrowBtn);
		selectPage.add(backBtn);
		
		currentPanel.add(selectPage, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		currentPanel.removeAll();
		
		if (e.getSource() == submitBtn) {
			String name = adminPage.getName();
			String id = adminPage.getID();
			String phone = adminPage.getPhone();
			
			if (!name.isEmpty() && !phone.isEmpty()) {
				//관리자가 맞다면 
				//다음 페이지로
				selectPage();
			/*} else if (name.length() == 0 || id.length() == 0
					|| phone.length() == 0) {
				adminPage(-1, name, id, phone);
			} else if (id.length() != 10) {
				adminPage(-2, name, id, phone);
			} else if (phone.length() != 11) {
				adminPage(-3, name, id, phone);
			} else {
				adminPage(1, name, id, phone);
				*/
			}
			
			//관리자가 아니면
			else {
				villainPage();
			}
		}
		
		if(e.getSource() == mainBtn) {
			//메인 화면 연결 ㅎㅎ
		}
		
		
		
		currentPanel.updateUI();
	}

}
