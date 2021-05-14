package main;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import menu.InsertPage;
import menu.MenuDAO;

public class MenuMain extends JFrame implements ActionListener {
		JTextField txt;
		JPanel pan, South; 
		JPanel p1,p2;
		JTable table;
		JButton select, update, del,insert;
		JRadioButton c,d,a;
		
		MenuDAO dao = new MenuDAO();
		
		String data[][] = new String[0][3];
		String[] title = {"코드", "상품명", "가격"};
		DefaultTableModel modelTable = new DefaultTableModel(data,title) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			
};
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == select) {
			String sur = txt.getText().trim();
			dao.SearchCname(modelTable,sur);
		} else if(e.getSource() == update) {
			
		} else if(e.getSource() == del) {
			String sur = txt.getText().trim();
			dao.DeleteMyInfo(sur);
			dao.selectAll(modelTable);
		} else if(e.getSource() == insert) {
			setVisible(false);
			new InsertPage();
		} else if(e.getSource() == c) {
			
		}
		else if(e.getSource() == d) {
			
		} else {
		
		}
	}
	public static void main(String[] args) {
		new MenuMain();
	}
}