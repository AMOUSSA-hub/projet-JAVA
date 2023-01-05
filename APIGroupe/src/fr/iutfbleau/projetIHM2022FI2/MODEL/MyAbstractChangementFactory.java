package fr.iutfbleau.projetIHM2022FI2.MODEL;
import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;

import java.util.*;

import javax.swing.*;

import java.sql.*;

/**
 * Usine abstraite gérant l'ensemble des changements pour l'Admin.
 */
public class MyAbstractChangementFactory implements AbstractChangementFactory {
  
    
    // l'usine à groupe travaillant en tandem avec cette usine.
    private AbstractGroupeFactory agf;

    // On utilise une table de hachage pour retrouver facilement un changement (à partir de son id).
    // Si il y a beaucoup de changements c'est plus rapide que de parcourir toute une liste.
    private HashMap<Integer,Changement> brain;

    public MyAbstractChangementFactory(MyAbstractGroupeFactory agf){
        Objects.requireNonNull(agf,"On ne peut pas créer une usine à changement dont l'usine à groupe parternaire est null");
        this.agf=agf;
        this.brain=new HashMap<Integer,Changement>();

        try{
            PreparedStatement req = Utils.con.prepareStatement("Select id from Changement ");
                ResultSet res = req.executeQuery();
                
                while(res.next()){
                    brain.put(res.getInt(1), new MyChangement(res.getInt(1),agf));
                }
                } catch (SQLException  se) {
                    System.err.println("errreur Sql at MyAbstractChangementFactory()"+se);
    
                }
        
        
    }
                                        
    
    
    public AbstractGroupeFactory getGroupeFactory(){
        return this.agf;
    }
    
   
    public Set<Changement> getAllChangements(){
        // la méthode value() d'un hashmap retourne la collection des valeurs.
        // Il faut transformer la collection en Set.
        // Un constructeur de HashSet permet de faire cette opération.
        Set<Changement> out = new HashSet<Changement>(this.brain.values());
        return out;
    }


    public void applyChangement(Changement c){
        Objects.requireNonNull(c,"On ne peut pas appliquer un changement qui est null");
        Etudiant e = c.getEtu();
        Groupe a = c.getA();
        Groupe b = c.getB();

        if (!agf.knows(a)) throw new IllegalStateException("Le groupe de départ du changement est inconnu. Impossible à mettre en oeuvre.");
        
        if (!agf.knows(b)) throw new IllegalStateException("Le groupe d'arrivée du changement est inconnu. Impossible à mettre en oeuvre.");

        if(b.getSize()== b.getMax()){
            JOptionPane.showMessageDialog(new JDialog(), "Le groupe d'arrivée est trop plein."); 
            throw new IllegalStateException("Le groupe d'arrivée est trop plein.");
            
        } 
            
        agf.dropFromGroupe(a,e);
        agf.addToGroupe(b,e);
        // En cas de succès, on enlève le changement du cerveau
        this.brain.remove(Integer.valueOf(c.getId()));
        deleteChangement(c);


        
    }


    public void deleteChangement(Changement c){
        Objects.requireNonNull(c,"On ne peut pas demander la suppression d'un changement qui est null");

        this.brain.remove(Integer.valueOf(c.getId()));

        try{

            PreparedStatement req = Utils.con.prepareStatement("Delete from Changement where id = ? ");
            req.setInt(1,c.getId());
            req.executeUpdate();


        } catch (SQLException  se) {
            System.err.println("errreur Sql at deleteChangement()"+se);

    }


    }

   
    public void createChangement(Groupe A, Etudiant e, Groupe B, String expl){
        Objects.requireNonNull(A,"Le groupe d'origine ne peut pas être null");
        Objects.requireNonNull(B,"Le groupe d'arrivée ne peut pas être null");
        Objects.requireNonNull(e,"L'étudiant ne peut pas être null");

        Changement c = new MyChangement(A,e,B,expl);

        for (Map.Entry<Integer,Changement> changement_stocké :  brain.entrySet()) {

            if(changement_stocké.getValue().getA().getId() == c .getA().getId() && changement_stocké.getValue().getB().getId() == c.getB().getId() && changement_stocké.getValue().getEtu().getId() == c.getEtu().getId()){

                JOptionPane.showMessageDialog(new JDialog(), "Cette demande à déjà été effectuée, attendez qu'un admin la traite.");
                throw new IllegalStateException("Demande déjà présente dans la bd.");
            }


        }
        


        this.brain.put(Integer.valueOf(c.getId()),c);   

        try{

            PreparedStatement req = Utils.con.prepareStatement("Insert into Changement (IdEtudiant,idGroupeDepart,idGroupeArrive,Explications) VALUES (?,?,?,?) ");
            req.setInt(1, e.getId());
            req.setInt(2, A.getId());
            req.setInt(3, B.getId());
            req.setString(4, expl);
            req.executeUpdate();


        } catch (SQLException  se) {
            System.err.println("errreur Sql at deleteChangement()"+se);

        }
        
    }

}
