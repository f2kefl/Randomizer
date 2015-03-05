package ru.FLDev.Randomizer;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import by.zti.SerializationManager;

public class Randomizer {
	
	private static List<String> list = new ArrayList<String>();	
	private static Random rand = new Random();
	
		@SuppressWarnings("unchecked")
		public static void main(String[] args) {
		
		JFrame frame = new JFrame("Random movie");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		frame.setSize(310, 180);	
		
		Font font = new Font("COMIC SANS MS", Font.BOLD, 22);				
		
		JLabel label = new JLabel("^_^");
		frame.add(label, new GridBagConstraints(0, 0, 2, 1, 1, 5, GridBagConstraints.CENTER, 
				  GridBagConstraints.NORTH, new Insets (5, 5, 5, 5), 0, 0));
		
		label.setFont(font);
		label.setForeground(Color.BLACK);		
		
		list = (List<String>) SerializationManager.deSerializeData("list", "ser", "");
		
		filmlist fl = new filmlist();		
		
		JButton r2 = new JButton("Show random title");
		frame.add(r2, new GridBagConstraints(0, 1, 2, 1, 1, 5,
				GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
				new Insets (1, 1, 0, 1), 0, 0));
		
		r2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				label.setText(list.get(rand.nextInt(list.size())));				
			}
		});
		
		JButton add = new JButton("Add new title");
		frame.add(add, new GridBagConstraints(0, 2, 1, 1, 1, 1,
				GridBagConstraints.SOUTHWEST, GridBagConstraints.HORIZONTAL,
				new Insets (0, 1, 1, 1), 0, 0));
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				
				fl.setName(JOptionPane.showInputDialog("¬ведите название фильма"));
				if(!(list.contains(fl.name))){
					if(!(fl.name.equals(""))){
						list.add(fl.name);
						SerializationManager.serializeData(list, "list", "ser", "");
					}
				}				
			}
		});
		
		JButton del = new JButton("Delete this title");
		frame.add(del, new GridBagConstraints(1, 2, 1, 1, 1, 1,
				GridBagConstraints.SOUTHEAST, GridBagConstraints.HORIZONTAL,
				new Insets (1, 1, 1, 1), 0, 0));
		del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int reply = JOptionPane.showConfirmDialog(null, "”далить этот фильм?", "", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){	
					list.remove(label.getText());
					label.setText("");
					SerializationManager.serializeData(list, "list", "ser", "");
				}				
			}
		});
		
		frame.setVisible(true);

	}
	
}
