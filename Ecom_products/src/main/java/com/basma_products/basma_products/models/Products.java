package com.basma_products.basma_products.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter("monFilterDynamique")
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
//    private String discription;
    @ManyToOne
    @JoinColumn(referencedColumnName = "category", name = "categorie")
    private Category categorie;
    private String sousCategorie;
    private double prix;

    private double prixAchat;

    
    
   

	public Products(long id, String nom, Category categorie, String sousCategorie, double prix, double prixAchat) {
		super();
		this.id = id;
		this.nom = nom;
		this.categorie = categorie;
		this.sousCategorie = sousCategorie;
		this.prix = prix;
		this.prixAchat = prixAchat;
	}

	public Products(String nom, Category categorie, String sousCategorie, double prix, double prixAchat) {
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

	public Category getCategorie() {
		return categorie;
	}

	public void setCategorie(Category categorie) {
		this.categorie = categorie;
	}

	public String getSousCategorie() {
		return sousCategorie;
	}

	public void setSousCategorie(String sousCategorie) {
		this.sousCategorie = sousCategorie;
	}
	
    
    

}
