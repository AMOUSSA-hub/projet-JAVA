package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;
import java.util.Set;
import java.awt.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.MODEL.MyAbstractGroupeFactory;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.panneau_recherche;

public class Observateur_recherche implements ActionListener {

    private JTextField form_search;
    private MyAbstractGroupeFactory magf;
    private static JPanel pan_home;
    private static CardLayout card;

    public Observateur_recherche(JTextField f_search,MyAbstractGroupeFactory magf,JPanel p_home,CardLayout ca){

        this.form_search = f_search;
        this.magf = magf;
        pan_home = p_home;
        card = ca;


    }

    public Observateur_recherche(){}


    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("rechercher")){

            Set<Etudiant> result=magf.getEtudiants(form_search.getText());
            panneau_recherche pan_result = new panneau_recherche(result);


            pan_home.add(pan_result,"recherche");
            card.show(pan_home,"recherche");




        }

        if(e.getActionCommand().equals("Accueil")){


            card.show(pan_home, "home");


        }



    }
    
}
