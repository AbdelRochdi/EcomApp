package com.products_Categories.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Products {
	

	@Id
    private long id;
    private String nom;
//    private String discription;
    private String categorie;
    private String sousCategorie;
    private double prix;

    private double prixAchat;

    

    

    public Products(long id, String nom, String categorie, String sousCategorie, double prix, double prixAchat) {
		this.id = id;
		this.nom = nom;
		this.categorie = categorie;
		this.sousCategorie = sousCategorie;
		this.prix = prix;
		this.prixAchat = prixAchat;
	}

	public Products(String nom, String categorie, String sousCategorie, double prix, double prixAchat) {
		super();
		this.nom = nom;
		this.categorie = categorie;
		this.sousCategorie = sousCategorie;
		this.prix = prix;
		this.prixAchat = prixAchat;
	}

	public Products() {
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
//	public String getDiscription() {
//		return discription;
//	}
//	public void setDiscription(String discription) {
//		this.discription = discription;
//	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
	public double getPrixAchat() {
		return prixAchat;
	}
	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}
	@Override
	public String toString() {
		return "Products [id=" + id + ", nom=" + nom +", prix=" + prix + "]";
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getSousCategorie() {
		return sousCategorie;
	}

	public void setSousCategorie(String sousCategorie) {
		this.sousCategorie = sousCategorie;
	}
	
    
    

}
