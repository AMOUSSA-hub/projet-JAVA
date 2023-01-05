package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import fr.iutfbleau.projetIHM2022FI2.MODEL.*;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;


/**
 * Permet de récupérer des Informations sur l'arborescence des groupes: quel groupe est sélectionné(vue admin).
 */

public class Observateur_arborescence implements TreeSelectionListener {

private MyAbstractGroupeFactory magf;
private Map<String,Integer> map;
private JButton modif;
private JButton crea;
private JButton supp;
private CardLayout ca_etu;
private CardLayout ca_info;
private JPanel pan_etu;
private JPanel pan_info;


    public Observateur_arborescence( MyAbstractGroupeFactory magf, Map<String,Integer> m){
        this.magf = magf;
        this.map = m ;

    }

    public Observateur_arborescence( MyAbstractGroupeFactory magf, Map<String,Integer> m,JButton mo,JButton c, JButton s,CardLayout ca_etu,CardLayout ca_info, JPanel m_etu, JPanel p_info ){
        this.magf = magf;
        this.map = m ;
        this.modif = mo;
        this.crea = c;
        this.supp = s;
        this.ca_etu = ca_etu;
        this.ca_info = ca_info;
        this.pan_etu = m_etu;
        this.pan_info = p_info;

    }

    public Observateur_arborescence( MyAbstractGroupeFactory magf, Map<String,Integer> m,CardLayout ca_etu, JPanel m_etu){
        this.magf = magf;
        this.map = m ;
        this.ca_etu = ca_etu;
        this.pan_etu = m_etu;

    }



    public static MyGroupe  group_selected = null;
  
    @Override
    public void valueChanged(TreeSelectionEvent e) {
    determined_group(e.getPath().toString());
        if(group_selected != null){
            if(crea != null){
                    if(e.getPath().getLastPathComponent().toString().equals("Promotion")){
                        modif.setEnabled(false);
                        supp.setEnabled(false);
                    }
                    else{   
                        modif.setEnabled(true);
                        if(group_selected.getSousGroupes().isEmpty()){
                        supp.setEnabled(true);
                        }
                        else{
                           supp.setEnabled(false);
                        }
                    }
                    crea.setEnabled(true);
            }    
        } 
      
            ca_etu.show(pan_etu,group_selected.getId()+"");
            if(ca_info != null)
            ca_info.show(pan_info,group_selected.getId()+"");
        
    }

    
    /**
     * permet de déterminer l'id d'un groupe grâce à son chemin en Utilisant la Map : group_map.
     * @param p chemin de la fonction
     * @return 
     */
    public int determined_group( String p){
       group_selected = magf.brain.get(this.map.get(p));
        return group_selected.getId();
    }


    
    
}
