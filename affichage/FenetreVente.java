package affichage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import metier.Catalogue;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private Catalogue catalogue;


	public FenetreVente(Catalogue catalogue) {
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
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
		contentPane.add(new JLabel("Quantit� vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		int vendu = Integer.parseInt(txtQuantite.getText());
		String toRemove = (String)combo.getSelectedItem();
		catalogue.vendreStock(toRemove, vendu);
		this.dispose();
	}

}
