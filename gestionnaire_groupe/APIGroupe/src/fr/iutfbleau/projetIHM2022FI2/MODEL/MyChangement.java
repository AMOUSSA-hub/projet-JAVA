package fr.iutfbleau.projetIHM2022FI2.MODEL;
import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;


import java.util.*;
import java.sql.*;

import fr.iutfbleau.projetIHM2022FI2.API.Changement;

/**
 * Une demande de changement de groupe concerne un étudiant, qui est dans un groupe A et veut aller dans un groupe B (pour l'admin).
 */

public class MyChangement implements Changement {

    private int id;
    private Groupe a,b;
    private Etudiant e;
    private int id_etu;
    private String expli;




//génère les changements déjà pésent dans la base de données

   public MyChangement(int id ,MyAbstractGroupeFactory magf){

    this.id = id;
    try{
    PreparedStatement req = Utils.con.prepareStatement("Select  IdEtudiant,idGroupeDepart,idGroupeArrive,Explications  from Changement where id = ? ");
    req.setInt(1,id);
    ResultSet res = req.executeQuery();
    while (res.next()){

        id_etu = res.getInt(1);
        a = magf.brain.get(res.getInt(2));
        b = magf.brain.get(res.getInt(3));
        expli = res.getString(4);



    }

} catch (SQLException  se) {
    System.err.println("errreur Sql at MyChangement()"+se);

}



   } 




    public MyChangement(Groupe a, Etudiant e, Groupe b){
        Objects.requireNonNull(a,"On ne peut pas créer un changement avec un groupe à quitter null");
        Objects.requireNonNull(b,"On ne peut pas créer un changement avec un groupe à rejoindre null");
        Objects.requireNonNull(e,"On ne peut pas créer un changement concernant un étudiant null");
  
        this.a=a;
        this.b=b;
        this.e=e;
    }

    public MyChangement(Groupe a, Etudiant e, Groupe b,String expl){
        Objects.requireNonNull(a,"On ne peut pas créer un changement avec un groupe à quitter null");
        Objects.requireNonNull(b,"On ne peut pas créer un changement avec un groupe à rejoindre null");
        Objects.requireNonNull(e,"On ne peut pas créer un changement concernant un étudiant null");
        Objects.requireNonNull(expl,"On ne peut pas créer un changement sans renseigner une explication");

       this.expli = expl;
        this.a=a;
        this.b=b;
        this.e=e;
    }


    
    /**
     * permet de récupérer l'identifiant du changement (référence interne sans intérêt irl).
     * @return l'identifiant.
     */
    public int getId(){
        return this.id;
    }

    /**
     * permet de récupérer le groupe de depart
     * @return ce groupe.
     */
    public Groupe getA(){
        return this.a;
    }

    /**
     * permet de récupérer le groupe d'arrivée
     * @return ce groupe.
     */
    public Groupe getB(){
        return this.b;
    }

    /**
     * permet de récupérer l'étudiant demandant le changement
     * @return cet étudiant
     */
    public Etudiant getEtu(){

        if(e== null){
        for(Etudiant i : a.getEtudiants()){

            if(i.getId()==id_etu){
                System.out.println(i.getId());
                e=i;
                return this.e; 
            }

        }
        if(e == null){
            throw new IllegalStateException("Impossible qu'un élève demande à partir d'un groupe auquel il n'appartient pas"); 
        }
    }
    return e;
    }

    public String getExplication(){


        return expli;
    };

   
    
}
