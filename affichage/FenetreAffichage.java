package affichage;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import metier.Catalogue;

public class FenetreAffichage extends JFrame implements ActionListener {

	private JButton btOK;
	
	public FenetreAffichage(Catalogue catalogue) {

		setTitle("Affichage");
		setBounds(500, 500, 450, 250);
		JPanel panHaut = new JPanel();
		JPanel panBas = new JPanel();
		panHaut.setLayout(new BorderLayout());
		panBas.setLayout(new FlowLayout());
		
		String[] texte = catalogue.getNomProduits();
		
		JTextArea jtaSortie = new JTextArea(10,5);
		btOK = new JButton("Quitter");
		
		for(String nom : texte) {
			jtaSortie.append(nom);
					
		}
				
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		panHaut.add(jtaSortie);
		panBas.add(btOK);

		contentPane.add(panHaut,"North");
		contentPane.add(panBas, "South");
		btOK.addActionListener(this);

		this.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

}
