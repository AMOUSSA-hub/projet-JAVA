package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;

/**
 * permet le lancement de la création d'une partition.
 */

public class Observateur_creation_partition implements ActionListener {

    private JTextField form_nom;
    private JSpinner form_qte;
    private JDialog fen;
    


    public Observateur_creation_partition(JTextField nom , JSpinner qte,JDialog fen){

        this.form_nom = nom;
        this.form_qte = qte;
        this.fen = fen;
        

    }
    
    
    
    public void actionPerformed(ActionEvent e){


        if(form_nom.getText().replaceAll("\\s", "").length() != 0){
            this.fen.dispose();
            DashboardGroupe.bd.createPartition(Observateur_arborescence.group_selected, form_nom.getText(), ((Number)form_qte.getValue()).intValue());
            DashboardGroupe.refresh_pan_group();
        }



    }


}
    

