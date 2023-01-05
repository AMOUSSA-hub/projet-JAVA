package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant.ViewEtudiant;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * met en place la création d'une demande de changement.
 */

public class Observateur_creation_demande implements ActionListener {

    private JDialog fen;
    private Etudiant etu;
    private JComboBox<String> form_groupe_depart;
    private JComboBox<String> form_groupe_arrivee;
    private JTextField form_explication;

    public Observateur_creation_demande (JDialog f, Etudiant e,JComboBox<String> form_depart,JComboBox<String> form_arrivee,JTextField expl ) {
        this.etu = e;
        this.fen = f;
        this.form_groupe_depart = form_depart;
        this.form_groupe_arrivee = form_arrivee;
        this.form_explication = expl;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand().equals("Faire la demande")) {
            String pereDepart = form_groupe_depart.getSelectedItem().toString();
            String pereArrivee = form_groupe_arrivee.getSelectedItem().toString();
            String explication  = form_explication.getText();

            if(pereDepart== null || pereArrivee == null || explication == null){
                
                JOptionPane.showMessageDialog(new JDialog(),"Veuillez remplir le formulaire au complet.");
                Objects.requireNonNull(pereArrivee,"On ne peut pas demander la suppression d'un changement qui est null");
                Objects.requireNonNull(pereDepart,"On ne peut pas demander la suppression d'un changement qui est null");
                Objects.requireNonNull(explication,"On ne peut pas demander la suppression d'un changement qui est null");
            }

            Groupe groupe_depart = determined_group_from_name(pereDepart);
            Groupe groupe_arrive = determined_group_from_name(pereArrivee);


                if(groupe_arrive.getPointPoint().getId() == groupe_depart.getPointPoint().getId()){

                ViewEtudiant.demandes.createChangement(groupe_depart, etu, groupe_arrive,explication);
                this.fen.dispose();
                }else{

                    JOptionPane.showMessageDialog(new JDialog(), "choississsez de 2 groupes se situant au même niveau.");
                }
        }
    }

    public Groupe determined_group_from_name( String nom_groupe){
        
        for (Map.Entry<Integer,MyGroupe> group_stocké :  ViewEtudiant.bd.brain.entrySet()) {
            if(group_stocké.getValue().getName() == nom_groupe ){

                return  group_stocké.getValue();
            }
        }
        throw new IllegalStateException("nom de groupe introuvable");
  

}

}
