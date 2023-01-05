package fr.iutfbleau.projetIHM2022FI2.VIEW;

import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.API.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * fenêtre permettant de choisir le profil que l'on veut : Admin, Prof ou Etudiant.
 */
public class ChoixProfil extends JFrame {
	public static JFrame fenetre;
	public static JComboBox<String> choix = new JComboBox<String>();
	public static JComboBox<String> choixEtudiant = new JComboBox<String>();
	public static JTextField mdp = new JTextField("mot de passe");
	public static JPanel main_pan =new JPanel();
	public   MyAbstractGroupeFactory bd;
 
	public ChoixProfil() {
		fenetre = this;
		Utils.open_connection();
		ObservateurChoixProfil listener = new ObservateurChoixProfil();
		bd = new MyAbstractGroupeFactory();

		JPanel pan_profil = new JPanel();
	  	JPanel pan_etu = new JPanel();
		JPanel pan_log_admin = new JPanel();
		CardLayout gestionnaire = new CardLayout();
		
		JButton valider = new JButton("Valider");
		JButton valider_etu = new JButton("Valider");
		JButton valider_admin = new JButton("Valider");
		JButton retour_admin = new JButton("retour");
		JButton retour_etudiant = new JButton("retour");

		

		
		mdp.setPreferredSize(new Dimension(100,25));
		setLocation(650,350);
        setSize(new Dimension(300,100));

		main_pan.setLayout(gestionnaire);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		

		choix.addItem("Etudiant");
		choix.addItem("Professeur");
		choix.addItem("Administrateur");
		pan_profil.add(choix,BorderLayout.CENTER);
		pan_profil.add(valider,BorderLayout.SOUTH);

		main_pan.add(pan_profil,"profil");

		

		Iterator<Etudiant> it = bd.getPromotion().getEtudiants().iterator();

		while(it.hasNext()){

			Etudiant e = it.next();

			choixEtudiant.addItem(e.getNom()+" "+e.getPrenom());

		}


		pan_etu.add(choixEtudiant);
		pan_etu.add(valider_etu);
		pan_etu.add(retour_etudiant);

		main_pan.add(pan_etu,"etudiant");

		pan_log_admin.add(mdp);
		pan_log_admin.add(valider_admin);
		pan_log_admin.add(retour_admin);
		pan_log_admin.add(new JLabel("mdp : \"root\""));

		main_pan.add(pan_log_admin,"admin");


		valider.addActionListener(new ObservateurChoixProfil(this.bd,gestionnaire));
		valider_etu.addActionListener(new ObservateurChoixProfil(this.bd,gestionnaire));
		valider_admin.addActionListener(new ObservateurChoixProfil(this.bd,gestionnaire));
		retour_admin.addActionListener(listener);
		retour_etudiant.addActionListener(listener);


		add(main_pan);

		setVisible(true);
	}

	public static void main(String[] args) {
		 new ChoixProfil();
	}
}
