package metier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Catalogue implements I_Catalogue{
	
	private static ArrayList<I_Produit> tabProduits;
		

	public Catalogue(){
		tabProduits = new ArrayList<I_Produit>();
	}
	

	@Override
	public boolean addProduit(I_Produit produit) {
		try{
			if(!verificationNomPasPresent(produit) || !verificationPrixDifferentZeroEtPositif(produit) || !verificationStockSuperieurOuEgaleAZero(produit)) {
				return false;
			}
		}catch(NullPointerException e){
			System.out.println("Impossible de passer null en paramétre : Objet de type I_Produit attendu " + e);
			return false;
		}
		tabProduits.add(produit);
		return true;
	}


	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		I_Produit nvxProduit = new Produit(nom.trim(), prix, qte);
		System.out.println(nvxProduit.getNom());
		try {
			if(!verificationNomPasPresent(nvxProduit) || !verificationPrixDifferentZeroEtPositif(nvxProduit) || !verificationStockSuperieurOuEgaleAZero(nvxProduit)) {
				return false;
			}
		}catch(NullPointerException e){
			System.out.println("Impossible de passer null en paramétre : String attendu " + e);
			return false;
		}
		tabProduits.add(nvxProduit);
		return true;	
	}

	@Override
	public int addProduits(List<I_Produit> listProduits) {
		List<I_Produit> produitVerifie = new ArrayList();
		try{
			for(I_Produit newProduit : listProduits) 
			{
				if(verificationPrixDifferentZeroEtPositif(newProduit) && verificationStockSuperieurOuEgaleAZero(newProduit) && verificationNomPasPresent(newProduit)) 
				{
					produitVerifie.add(newProduit);
				}
				
			}
		}catch(NullPointerException e){
			System.out.println("Impossible de passer null en paramétre : String attendu " + e);
			return 0;
		}
		tabProduits.addAll(produitVerifie);
		return produitVerifie.size();
	}

	@Override
	public boolean removeProduit(String nom) {
		try {
			for(I_Produit actuelProduit : tabProduits) {
				if(nom.equals(actuelProduit.getNom())) {
					return true;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Impossible de passer null en paramétre : String attendu " + e);
			return false;
		}
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		for(I_Produit produit : tabProduits) {
			if(!verificationNomPasPresent(nomProduit) && qteAchetee > 0) {
				produit.ajouter(qteAchetee);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		for(I_Produit produit : tabProduits) {
			if(produit.getNom().equals(nomProduit) && produit.getQuantite() > 0 && produit.getQuantite() >= qteVendue ) {
				System.out.println(produit.getQuantite());
				produit.enlever(qteVendue);
				return true;
			}
		}
		return false;
	}

	@Override
	public String[] getNomProduits() {
		int i = 0;
		String[] nomP = new String[tabProduits.size()];
		for(I_Produit p : tabProduits){
			nomP[i] = p.getNom();
			i++;
		}
		return nomP;
	}

	@Override
	public double getMontantTotalTTC() {
		float montantTotal = 0; 
		for(I_Produit produit : tabProduits) {
			montantTotal += produit.getPrixStockTTC();
		}

		DecimalFormat decimalFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US));
        decimalFormat.setRoundingMode(RoundingMode.UP);
        decimalFormat.setMinimumIntegerDigits(2);
        return Double.parseDouble(decimalFormat.format(montantTotal));
	}


	@Override
	public String toString() {
		String listeP = "";
		for(I_Produit produit : tabProduits) {
			listeP += produit.toString() + "\n";
		}
		listeP += "\nMontant total TTC du stock : "+ getMontantTotalTTC() +" €";
		return listeP;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean verificationPrixDifferentZeroEtPositif(I_Produit produit) {
		if(produit.getPrixUnitaireHT() > 0) {
			return true;
		}
		return false;
	}
	
	private boolean verificationStockSuperieurOuEgaleAZero(I_Produit produit) {
		if (produit.getQuantite() >= 0) {
			return true;
		}
		return false;
	}
	
	private boolean verificationNomPasPresent(String nom) {
		String nomNouveauProduit = nom.trim();
		for(I_Produit actuelProduit : tabProduits) {
			if(nomNouveauProduit.equals(actuelProduit.getNom())) {
				return false;
			}
		}
		return true;
	}
	
	private boolean verificationNomPasPresent(I_Produit produit) {
		String nomNouveauProduit = produit.getNom().trim();
		for(I_Produit actuelProduit : tabProduits) {
			if(nomNouveauProduit.equals(actuelProduit.getNom())) {
				return false;
			}
		}
		return true;
	}
	
}
