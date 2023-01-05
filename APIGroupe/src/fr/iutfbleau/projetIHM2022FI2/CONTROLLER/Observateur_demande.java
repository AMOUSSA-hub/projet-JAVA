package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;

import java.awt.event.*;


import javax.swing.JDialog;
/**
 * gère les interactions entre l'utilisateur et l'interface dans la vue Menu_Changement.
*/
public class Observateur_demande implements ActionListener {

    private Changement c;
    private JDialog fen;

    public Observateur_demande (Changement ch, JDialog f){
        this.c =ch;
        this.fen=f;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("voir explication")){

            new explication_demande(fen, c);
        }

        if(e.getActionCommand().equals("accepter")){
            fen.dispose();
            DashboardGroupe.demandes.applyChangement(c);
            DashboardGroupe.refresh_pan_group();
        }

        if(e.getActionCommand().equals("refuser")){
            DashboardGroupe.demandes.deleteChangement(c);
            fen.dispose();
            new Menu_Changement(DashboardGroupe.menu_fen,DashboardGroupe.demandes,1);
        }

    }

    
}
