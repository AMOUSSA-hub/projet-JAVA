package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;
import java.awt.event.*;



import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;

import javax.swing.*;
import java.util.*;

/**
 * lance le déplacement d'un élève.
 */
public class Observateur_ChoixDeplacement implements ActionListener {

   

    private JComboBox<String> selection_group;
    private Etudiant etu;
    private JDialog fen ;
    public Observateur_ChoixDeplacement(JComboBox<String> choix, Etudiant e,JDialog f){

        this.selection_group = choix;
        this.etu = e;
        this.fen = f;
    }


    public void actionPerformed(ActionEvent e) {


        if(e.getActionCommand().equals("valider")){

           Groupe group_arr =  determine_group(selection_group.getSelectedItem().toString());

            DashboardGroupe.demandes.applyChangement(new MyChangement(Observateur_arborescence.group_selected,this.etu,group_arr));
            DashboardGroupe.refresh_pan_etudiant();
            this.fen.dispose();





        }
    }

/** permet de déterminer un groupe grâce à son nom
 * 
 * @param name Nom du groupe
 * @return Objet de type  Groupe()
 */
    public Groupe determine_group(String name){


        Set<Groupe> list_group = Observateur_arborescence.group_selected.getPointPoint().getSousGroupes();


        for(Groupe g : list_group){

            if(g.getName()== name){
                return g;
            }
        }

        throw new NullPointerException();

    }



    
}
