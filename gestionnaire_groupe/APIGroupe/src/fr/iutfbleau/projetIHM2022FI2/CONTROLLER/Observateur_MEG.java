package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.Etudiant;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant.*;

/**
 * gère les interactions entre l'utilisateur et l'interface dans la vue DashboardGroupe.
*/
 
public class Observateur_MEG implements ActionListener {

    private JFrame fen_menu_edit;
    private Etudiant etudiant;
    
    public Observateur_MEG(JFrame fen){
        fen_menu_edit = fen;
    }

    public Observateur_MEG(){
    }

    public Observateur_MEG(JFrame fen, Etudiant etu){
        fen_menu_edit = fen;
        this.etudiant = etu;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("créer un sous-groupe libre")){
            System.out.println("demande de création de groupe de Type free ");
            new CreationGroupe(fen_menu_edit);
        }

        if(e.getActionCommand().equals("supprimer le groupe")){
            int reply = JOptionPane.showConfirmDialog(DashboardGroupe.menu_fen, "Voulez vous vraiment supprimer le sous groupe "+Observateur_arborescence.group_selected.getPath(),"supprimer étudiant", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(DashboardGroupe.menu_fen, "Suppresion");
                DashboardGroupe.bd.deleteGroupe(Observateur_arborescence.group_selected);
                DashboardGroupe.refresh_pan_group();
                }
        }

        if(e.getActionCommand().equals("modifier")){
            new EditGroupe(Observateur_arborescence.group_selected,fen_menu_edit);
            
        }

        if(e.getActionCommand().equals("créer un etudiant")){
            new Creation_Etudiant(fen_menu_edit);
        }

        if(e.getActionCommand().equals("Changer de groupe")){
            new Changement_Groupe(fen_menu_edit, this.etudiant);
        }

        if(e.getActionCommand().equals("voir les demandes")){
            new Menu_Changement(fen_menu_edit,DashboardGroupe.demandes,1);
        }

        if(e.getActionCommand().equals("Voir les demandes")){
            new Menu_Changement(fen_menu_edit,ViewEtudiant.demandes,2);
        }
    }
    
}
