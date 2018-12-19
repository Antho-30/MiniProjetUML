package affichage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import metier.Catalogue;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	private Catalogue catalogue;
	public FenetreSuppressionProduit(Catalogue catalogue) {
		
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");
		this.catalogue = catalogue;
		combo = new JComboBox();
		for(String nom : catalogue.getNomProduits()) {
			combo.addItem(nom);
		}
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String toRemove = (String)combo.getSelectedItem();
		catalogue.removeProduit(toRemove);
		this.dispose();
	}

}
