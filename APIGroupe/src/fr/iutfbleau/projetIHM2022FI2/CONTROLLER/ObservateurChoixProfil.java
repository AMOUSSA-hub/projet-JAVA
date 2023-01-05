package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import fr.iutfbleau.projetIHM2022FI2.VIEW.ChoixProfil;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Prof.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.API.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

/**
 * gère les interactions entre l'utilisateur et l'interface dans ChoixProfil().
*/
 
public class ObservateurChoixProfil implements ActionListener {

	private MyAbstractGroupeFactory bd;
	private static CardLayout ca ;
	private static boolean choix_profil = false;
	
	public ObservateurChoixProfil() {
	}

	public ObservateurChoixProfil(MyAbstractGroupeFactory bd, CardLayout gest){
		this.bd = bd;
		ca = gest;
	}

	public void actionPerformed(ActionEvent clique) {
	
	
	if(ChoixProfil.choix.getSelectedItem() != null && choix_profil == false){	
		if (ChoixProfil.choix.getSelectedItem().equals("Etudiant")) {

		ca.show(ChoixProfil.main_pan,"etudiant");
		choix_profil = true;
		


		
		}
		else if (ChoixProfil.choix.getSelectedItem().equals("Administrateur")) {
			ca.show(ChoixProfil.main_pan,"admin");
			choix_profil = true;
	
		}
		else if (ChoixProfil.choix.getSelectedItem().equals("Professeur")) {
			ChoixProfil.fenetre.dispose();
				new ViewProf(this.bd);
		
		}

	}


	else{

		
		if (clique.getActionCommand().equals("Valider")) {
		
			if (ChoixProfil.choix.getSelectedItem().equals("Etudiant")) {
				ChoixProfil.fenetre.dispose();
				String nom = ChoixProfil.choixEtudiant.getSelectedItem().toString();
				int id = 0;

				Iterator<Etudiant> i = bd.getPromotion().getEtudiants().iterator();

				while(i.hasNext()){
					Etudiant e = i.next();
					if(nom.equals(e.getNom()+" "+e.getPrenom())){
						System.out.println("check");
						id = e.getId();
					}
				}

				new ViewEtudiant(nom, id,this.bd);
			} 
			else if  (ChoixProfil.choix.getSelectedItem().equals("Administrateur")){
				if (ChoixProfil.mdp.getText().toString().equals("root")) {
					ChoixProfil.fenetre.dispose();
					new DashboardGroupe(this.bd);
				}
			}

		} else if (clique.getActionCommand().equals("retour")) {
			
			choix_profil = false;
			ca.show(ChoixProfil.main_pan,"profil");
		}

	}
	}
}
