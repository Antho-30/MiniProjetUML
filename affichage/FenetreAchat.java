package affichage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import metier.Catalogue;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private Catalogue catalogue;

	public FenetreAchat(Catalogue catalogue) {

		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");
		this.catalogue = catalogue;
		combo = new JComboBox();
		for(String nom : catalogue.getNomProduits()) {
			combo.addItem(nom);
		}
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� achet�e"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		int achete = Integer.parseInt(txtQuantite.getText());
		String toRemove = (String)combo.getSelectedItem();
		catalogue.acheterStock(toRemove, achete);
		this.dispose();
	}

}
