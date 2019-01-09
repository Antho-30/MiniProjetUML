package metier;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Produit implements I_Produit{
	
	private int quantiteStock;
	private String nom;
	private double prixUnitaire;
	private double tauxTVA = 0.2;
	
	public Produit(String nom, double prixUnitaire, int qte){
		this.nom = nom;
		this.prixUnitaire = prixUnitaire;
		this.quantiteStock = qte;	
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		this.quantiteStock = quantiteStock + qteAchetee;
		return true;
	}

	@Override
	public boolean enlever(int qteVendue) {
		if(this.quantiteStock > 0){
			this.quantiteStock = quantiteStock - qteVendue;
			return true;
		}
		else{
			return false;
		}	
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public int getQuantite() {
		return quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
        return prixUnitaire;
	}

	@Override
	public double getPrixUnitaireTTC() {
        return prixUnitaire * (1 + tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
        return quantiteStock * getPrixUnitaireTTC();
	}

	@Override
	public String toString() {
		return this.nom + " - prix HT : "+ this.getPrixUnitaireHT() +" € - prix TTC : "+ this.getPrixUnitaireTTC() +" € - quantité en stock : " + this.quantiteStock;
	}
}
