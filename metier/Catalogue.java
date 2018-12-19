package metier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class Catalogue implements I_Catalogue{
	
	private static ArrayList<I_Produit> tabProduits;
		
	
	public static ArrayList<I_Produit> getTabProduits() {
		return tabProduits;
	}


	public static void setTabProduits(ArrayList<I_Produit> tabProduits) {
		Catalogue.tabProduits = tabProduits;
	}


	public Catalogue(){
		tabProduits = new ArrayList<I_Produit>();
		tabProduits.add(new Produit("Mars" , 50, 60));
		tabProduits.add(new Produit("Raider" , 50, 60));
		tabProduits.add(new Produit("Twix" , 50, 60));
		tabProduits.add(new Produit("Bounty" , 50, 60));
	}
	

	@Override
	public boolean addProduit(I_Produit produit) {
		for(I_Produit actuelProduit : tabProduits) {
			if(actuelProduit.getNom() == produit.getNom()) {
				return false;
			}
		}
		tabProduits.add(produit);
		return true;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		for(I_Produit produit : tabProduits) {
			if(produit.getNom() == nom) {
				return false;
			}
		}
		Produit nvxProduit = new Produit(nom, prix, qte);
		tabProduits.add(nvxProduit);
		return true;
		
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		try{
			for(I_Produit newProduit : l) {
				for(I_Produit actuelProduit : tabProduits) {
					if(newProduit.getNom() == actuelProduit.getNom()) {
						l.remove(newProduit);
					}
				}
			}
		}catch(ConcurrentModificationException e){
			System.out.println("Un probléme est survenu : "+ e);
		}
		
		tabProduits.addAll(l);
		return l.size();
	}

	@Override
	public boolean removeProduit(String nom) {
		for (Iterator<I_Produit> produit = tabProduits.iterator(); produit.hasNext();){
			I_Produit p = produit.next();
			if(p.getNom() == nom){
				produit.remove();
				return cat.addProduit("Nuts"true;
			}
		}
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		for(I_Produit produit : tabProduits) {
			if(produit.getNom() == nomProduit) {
				produit.ajouter(qteAchetee);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		for(I_Produit produit : tabProduits) {
			if(produit.getNom() == nomProduit) {
				if(produit.getQuantite() > 0) {
					produit.enlever(qteVendue);
					return true;
				}
				else {
					return false;
				}
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
		double montantTotalTTC = 0;
		DecimalFormat f = new DecimalFormat("#.00");
		f.setMaximumFractionDigits(2);
		for(I_Produit produit : tabProduits) {
			montantTotalTTC += produit.getPrixUnitaireTTC();
		}
		montantTotalTTC = Double.parseDouble(f.format(montantTotalTTC));
		System.out.println(montantTotalTTC);
		
		return montantTotalTTC;
	}


	@Override
	public String toString() {
		return null;
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	

}
