package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;
import javax.swing.*;


import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;

/**gère les interactions entre l'utilisateur et l'interface dans la Vue Edit_Groupe.
*/ 
public class Observateur_EG implements ActionListener {

    private MyGroupe group_selected;
    private JTextField form_new_name;
    private JDialog fen_edit;

    public Observateur_EG(MyGroupe group, JTextField name, JDialog edit_fen ){
        this.group_selected = group;
        this.form_new_name = name;
        this.fen_edit = edit_fen;

        

    }

    public Observateur_EG (JDialog edit_fen,MyGroupe group ){

        this.fen_edit = edit_fen;
        this.group_selected = group;

    }

    @Override

    public void actionPerformed(ActionEvent e){

       

            
        if(e.getActionCommand().equals("renommer")){

            String new_name = form_new_name.getText();


            
                    if (new_name.replaceAll("\\s", "").length() != 0 && new_name.equals("nouveau nom") == false && !new_name.equals(group_selected.getName())){
                          
                        DashboardGroupe.bd.editNameGroupe(group_selected, new_name);
                        this.fen_edit.dispose(); 
                        DashboardGroupe.refresh_pan_group();
                       
                    }
                
        }

        if(e.getActionCommand().equals("ajouter  des étudiants")){


            System.out.println("ajouter un élève");

            this.fen_edit.dispose();

            new Ajout_Etudiant(DashboardGroupe.menu_fen,this.group_selected);



        }

        if(e.getActionCommand().equals("Partitioner")){

            this.fen_edit.dispose();
            new Creation_Partition(DashboardGroupe.menu_fen);



        }




            

            



        


    }
    
}
