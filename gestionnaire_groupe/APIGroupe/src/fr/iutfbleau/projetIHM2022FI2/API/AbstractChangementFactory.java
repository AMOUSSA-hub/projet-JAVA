package fr.iutfbleau.projetIHM2022FI2.API;
import java.util.*;
/**
 * Usine abstraite gérant l'ensemble des changements.
 * 
 * 
 */

public interface AbstractChangementFactory {

    /**
     * permet de récupérer une usine abstraite pour les groupes qui fonctionne en tandem avec cette usine abstraite
     * @return cette usine abstraite pour les groupes
     */
    public AbstractGroupeFactory getGroupeFactory();
    
    /**
     * permet de récupérer les changements 
     * @return l'ensemble de tous les changements en attente
     *
     * NB. Attention. C'était Iterator<Changement> dans la version beta.
     */
    public Set<Changement> getAllChangements();

    /**
     * permet de mettre en oeuvre un changement connu de l'usine abstraite.
     * En cas de succès, le changement est oublié (détruit).
     *
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalArgumentException si inconnu de l'usine abstraite
     */
    public void applyChangement(Changement c);
    
    /**
     * permet de supprimer un changement connu de l'usine abstraite.
     *
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalArgumentException si inconnu de l'usine abstraite
     */
    public void deleteChangement(Changement c);

    /**
     * permet d'ajouter un nouveau changement.
     *
     * @param A groupe actuel
     * @param B groupe demandé
     * @param e étudiant concerné par le changement
     *
     * @throws java.lang.NullPointerException si un argument est null
     * @throws java.lang.IllegalArgumentException si les groupes ou l'étudiant ne sont pas connus de la factory partenaire, ou e n'appartient pas à A ou A et B ne sont pas frères dans l'arbre des groupes.
     *        
     */
    public void createChangement(Groupe A, Etudiant e, Groupe B, String explication);
    
}
