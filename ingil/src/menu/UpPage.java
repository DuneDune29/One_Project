package menu;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpPage extends JFrame implements ActionListener {
	JButton checkUpdate;
	JPanel panel,panel1;
	JPanel n1,n2;
	JTextField cno,cname,cprice,Ncno,Ncname,Ncprice;
	
	
	PreparedStatement pstmt;
	private String sqlUpdate = "update from menu set cno = ?, cname = ?, cprice = ? "
								+ "where cno = ?, cname = ?, cprice = ? ";
	
	public UpPage() {
		panel = new JPanel(new GridLayout(0,2));
		n1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		n1.add(new JLabel("상품 코드"));
		n1.add(cno = new JTextField(10));
		n1.add(new JLabel("상품명"));
		n1.add(cname = new JTextField(10));
		n1.add(new JLabel("가 격"));
		n1.add(cprice = new JTextField(10));
		n2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		n2.add(new JLabel("뉴상품 코드"));
		n2.add(Ncno = new JTextField(10));
		n2.add(new JLabel("뉴상품명"));
		n2.add(Ncname = new JTextField(10));
		n2.add(new JLabel("뉴가 격"));
		n2.add(Ncprice = new JTextField(10));
		panel.add(n1);
		panel.add(n2);
		
		add(panel);
		
		panel1 = new JPanel();
		panel1.add(checkUpdate = new JButton("수정"));
		add(panel1, "South");
		
		checkUpdate.addActionListener(this);
		
		
		setSize(250,280);
		setVisible(true);
		
	}
	public void Update() {
		String sur1 = cno.getText();
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == checkUpdate) {
			Update();
		}
	}

}