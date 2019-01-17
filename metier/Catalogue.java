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
			boolean doublenom = false;
			for (int i = 0; i < this.tabProduits.size(); i++) {
				
			if(!verificationNomPasPresent(produit) || !verificationNomPasPresent(produit))
				doublenom = true;
			
			}
			
			if(verificationPrixDifferentZeroEtPositif(produit) && verificationStockSuperieurOuEgaleAZero(produit) &&  !doublenom) {
				this.tabProduits.add(produit);
				return true;
			}
			return false;	
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
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
			System.out.println("Impossible de passer null en param�tre : String attendu " + e);
			return false;
		}
		tabProduits.add(nvxProduit);
		return true;	
	} 

	@Override
	public int addProduits(List<I_Produit> listProduits) {
		// TODO Auto-generated method stub
		try {
			
			boolean doublenom = false;
			int nbproduitajouter = 0;
			for(int i = 0; i<listProduits.size(); i++) {
				String tabnom = listProduits.get(i).getNom().trim() ;
				for(int j = 0; j< this.tabProduits.size(); j++) {
					if(this.tabProduits.get(j).getNom().equals(tabnom)){
						doublenom = true;
					}
				}
				if(listProduits.get(i).getPrixUnitaireHT() > 0 && listProduits.get(i).getQuantite() >= 0 && doublenom == false){
					this.tabProduits.add(listProduits.get(i));
					nbproduitajouter ++;
				}
				doublenom = false;
			}
			return nbproduitajouter;
		}
		catch(Exception e) {
			System.out.println(e);
			return 0;
		}
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
			System.out.println("Impossible de passer null en param�tre : String attendu " + e);
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
		// TODO Auto-generated method stub
		try {
			if(qteVendue > 0)
			{
				int i = 0;
				while(!this.tabProduits.get(i).getNom().equals(nomProduit)) {
					i++;
				}
				if(this.tabProduits.get(i).getNom().equals(nomProduit) && this.tabProduits.get(i).getQuantite() >= qteVendue){
					this.tabProduits.get(i).enlever(qteVendue);
					return true;
				}
			}
			return false;
		}
		catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	
	@Override
	public String[] getNomProduits() {
		// TODO Auto-generated method stub
		try {
			String [] tabProduits = new String [this.tabProduits.size()];
			for(int i = 0; i<this.tabProduits.size(); i++) {
				
				tabProduits[i] = this.tabProduits.get(i).getNom().replaceAll("	", " ");
			}
			Arrays.sort(tabProduits);
			return tabProduits;
		}
		catch(Exception e) {
			System.out.println(e);
			
			return null;
		}
	}

	@Override
	public double getMontantTotalTTC() {
		// TODO Auto-generated method stub
		try {
			double prixMontantTotalTTC = 0;
			for(int i = 0; i<this.tabProduits.size(); i++) {
				prixMontantTotalTTC = this.tabProduits.get(i).getPrixStockTTC() + prixMontantTotalTTC;
			}
			return (double)Math.round(prixMontantTotalTTC * 100) / 100 ;
		}
		catch(Exception e) {
			System.out.println(e);
			return 0;
		}
	}


	public String toString() {
		final NumberFormat instance = NumberFormat.getNumberInstance();
		instance.setMinimumFractionDigits(2);
		instance.setMaximumFractionDigits(2);
		instance.setGroupingUsed(false);
		String Produit="";
		for( int  i = 0 ; i < this.tabProduits.size(); i++) {
			Produit = Produit+this.tabProduits.get(i).toString();
		}
		Produit = Produit + "\nMontant total TTC du stock : " + instance.format(this.getMontantTotalTTC()) + " �";
		return Produit;
	}
	
	@Override
	public void clear() {
		System.out.println("La liste a bien été vidé ");
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
