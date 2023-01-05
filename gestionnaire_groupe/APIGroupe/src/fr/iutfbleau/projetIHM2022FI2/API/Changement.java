package fr.iutfbleau.projetIHM2022FI2.API;

/**
 * Une demande de changement de groupe
 * concerne un étudiant, qui est dans un groupe A et veut aller dans un groupe B.
 * 
 */

public interface Changement extends MonPrint {

    /**
     * permet de récupérer l'identifiant du changement (référence interne sans intérêt irl).
     * @return l'identifiant.
     */
    public int getId();

    /**
     * permet de récupérer le groupe de depart
     * @return ce groupe.
     */
    public Groupe getA();

    /**
     * permet de récupérer le groupe d'arrivée
     * @return ce groupe.
     */
    public Groupe getB();

    /**
     * permet de récupérer l'étudiant demandant le changement
     * @return cet étudiant
     */
    public Etudiant getEtu();

    /**
     * permet de récupérer la justification de chaque demande
     * @return l'explication
     */
    public String getExplication();

    
    /**
     * @see MonPrint
     * NB. On n'utilise le mécanisme des méthodes par défaut pour donner du code dans une interface. C'est un petit peu laid et à contre-emploi mais pratique ici.
     *
     * NB2. On ne peut pas utiliser le toString de Objects
     * https://stackoverflow.com/questions/24016962/java8-why-is-it-forbidden-to-define-a-default-method-for-a-method-from-java-lan
     */ 
    public default String monPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("Changement de " + this.getEtu().monPrint() + " depuis le groupe " + this.getA().getName() + " vers le groupe " + this.getB().getName());
        return sb.toString();
    }

}
