# COMMANDES #
JAVAC = javac
# note $$ to get a single shell $
JAVAC_OPTIONS = -d build -cp build -sourcepath src   -encoding UTF-8 -implicit:none
JAVA = java
JAVA_OPTIONS_W = -cp "build;res/mariadb-client.jar" 
JAVA_OPTIONS = -cp "build:res/mariadb-client.jar" 
JAR = jar
EXEC_JAR = ${JAVA} -jar
SUP = rm -rf

# CHEMINS RELATIFS
SRC = src/fr/iutfbleau/projetIHM2022FI2
BUILD = build/fr/iutfbleau/projetIHM2022FI2
DOC = doc/fr/iutfbleau/projetIHM2022FI2
BUILD_WINDOWS = "build/fr/iutfbleau/projetIHM2022FI2/"
# CHOIX NOMS
JAR_FILE =  gestionnaire_groupe.jar

# BUTS FACTICES #
.PHONY : run clean doc

# BUT PAR DEFAUT #
run : ${BUILD}/VIEW/ChoixProfil.class
	${JAVA} ${JAVA_OPTIONS} fr.iutfbleau.projetIHM2022FI2.VIEW.ChoixProfil

run_W : ${BUILD}/VIEW/ChoixProfil.class
	${JAVA} ${JAVA_OPTIONS_W} fr.iutfbleau.projetIHM2022FI2.VIEW.ChoixProfil

jar: ${JAR_FILE}
	${EXEC_JAR} ${JAR_FILE}

# AUTRE BUTS
doc :
	javadoc -d doc -encoding UTF-8 src/fr/iutfbleau/projetIHM2022FI2/API/*.java  src/fr/iutfbleau/projetIHM2022FI2/MODEL/*.java src/fr/iutfbleau/projetIHM2022FI2/CONTROLLER/*.java src/fr/iutfbleau/projetIHM2022FI2/VIEW/*.java src/fr/iutfbleau/projetIHM2022FI2/VIEW/Admin/*.java src/fr/iutfbleau/projetIHM2022FI2/VIEW/Prof/*.java src/fr/iutfbleau/projetIHM2022FI2/VIEW/Etudiant/*.java src/fr/iutfbleau/projetIHM2022FI2/Utils/*.java  

# POUR WINDOWS#
clean_W : 
	rd /s /q ${BUILD_WINDOWS}

clean_jar_W:
	del gestionnaire_groupe.jar

#POUR LINUX 
clean : 
	rm -r build/*

clean_jar:
	rm gestionnaire_groupe.jar
	

##clean : 



# REGLES DE DEPENDANCE #

## API ##
${BUILD}/API/MonPrint.class : ${SRC}/API/MonPrint.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/MonPrint.java

${BUILD}/API/TypeGroupe.class : ${SRC}/API/TypeGroupe.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/TypeGroupe.java

${BUILD}/API/Groupe.class : ${SRC}/API/Groupe.java \
	  		     ${BUILD}/API/TypeGroupe.class\
			     ${BUILD}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Groupe.java


${BUILD}/API/Etudiant.class : ${SRC}/API/Etudiant.java \
                            ${BUILD}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Etudiant.java

${BUILD}/API/Changement.class : ${SRC}/API/Changement.java \
	  		            ${BUILD}/API/Etudiant.class \
	  		     	    ${BUILD}/API/Groupe.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Changement.java


${BUILD}/API/AbstractGroupeFactory.class : ${SRC}/API/AbstractGroupeFactory.java \
				${BUILD}/API/Groupe.class \
	  		    ${BUILD}/API/Etudiant.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/AbstractGroupeFactory.java

${BUILD}/API/AbstractChangementFactory.class : ${SRC}/API/AbstractChangementFactory.java \
	  		            ${BUILD}/API/AbstractGroupeFactory.class \
				    ${BUILD}/API/Changement.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/AbstractChangementFactory.java



## TEST ##

 ${BUILD}/Test/TestTexteMNP.class : ${SRC}/Test/TestTexteMNP.java \
			 ${BUILD}/MNP/EtudiantNP.class \
			 ${BUILD}/MNP/GroupeNP.class \
			 ${BUILD}/MNP/ChangementNP.class \
                         ${BUILD}/MNP/AbstractGroupeFactoryNP.class \
                         ${BUILD}/MNP/AbstractChangementFactoryNP.class	
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/Test/TestTexteMNP.java



##UTILS#

${BUILD}/UTILS/Utils.class: ${SRC}/UTILS/Utils.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/UTILS/Utils.java

##MODEL##


${BUILD}/MODEL/MyGroupe.class: ${SRC}/MODEL/MyGroupe.java \
				${BUILD}/API/Groupe.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODEL/MyGroupe.java




${BUILD}/MODEL/MyEtudiant.class: ${SRC}/MODEL/MyEtudiant.java \
                            ${BUILD}/API/Etudiant.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODEL/MyEtudiant.java

${BUILD}/MODEL/MyChangement.class: ${SRC}/MODEL/MyChangement.java \
				${BUILD}/API/Changement.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODEL/MyChangement.java

${BUILD}/MODEL/MyChangementEtudiant.class: ${SRC}/MODEL/MyChangementEtudiant.java \
				${BUILD}/API/Changement.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODEL/MyChangementEtudiant.java

${BUILD}/MODEL/MyAbstractGroupeFactory.class: ${SRC}/MODEL/MyAbstractGroupeFactory.java \
				${BUILD}/API/AbstractGroupeFactory.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODEL/MyAbstractGroupeFactory.java




${BUILD}/MODEL/MyAbstractChangementFactory.class: ${SRC}/MODEL/MyAbstractChangementFactory.java \
				${BUILD}/API/AbstractChangementFactory.class \
				${BUILD}/MODEL/MyChangement.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MODEL/MyAbstractChangementFactory.java



## CONTROLLER ##

${BUILD}/CONTROLLER/Observateur_creation_partition.class: ${SRC}/CONTROLLER/Observateur_creation_partition.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_creation_partition.java


${BUILD}/CONTROLLER/Observateur_fenetre.class: ${SRC}/CONTROLLER/Observateur_fenetre.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_fenetre.java

${BUILD}/CONTROLLER/ObservateurChoixProfil.class: ${SRC}/CONTROLLER/ObservateurChoixProfil.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/ObservateurChoixProfil.java


${BUILD}/CONTROLLER/Observateur_ajout_etudiant.class: ${SRC}/CONTROLLER/Observateur_ajout_etudiant.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_ajout_etudiant.java


${BUILD}/CONTROLLER/ObservateurCreation.class: ${SRC}/CONTROLLER/ObservateurCreation.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/ObservateurCreation.java

${BUILD}/CONTROLLER/Observateur_arborescence.class: ${SRC}/CONTROLLER/Observateur_arborescence.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_arborescence.java


${BUILD}/CONTROLLER/Observateur_MEG.class: ${SRC}/CONTROLLER/Observateur_MEG.java \
		${BUILD}/VIEW/Admin/CreationGroupe.class \
		${BUILD}/VIEW/Admin/EditGroupe.class \
		${BUILD}/VIEW/Admin/Creation_Etudiant.class \
		${BUILD}/VIEW/Etudiant/Changement_Groupe.class \
		${BUILD}/VIEW/Admin/Menu_Changement.class \
		${BUILD}/VIEW/Etudiant/Menu_ChangementEtudiant.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_MEG.java

${BUILD}/CONTROLLER/Observateur_EG.class: ${SRC}/CONTROLLER/Observateur_EG.java \
		${BUILD}/VIEW/Admin/Ajout_Etudiant.class \
		${BUILD}/VIEW/Admin/Creation_Partition.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_EG.java

${BUILD}/CONTROLLER/Observateur_menu_etudiant.class: ${SRC}/CONTROLLER/Observateur_menu_etudiant.java \
		${BUILD}/VIEW/Admin/Choix_deplacement.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_menu_etudiant.java

${BUILD}/CONTROLLER/Observateur_demande.class: ${SRC}/CONTROLLER/Observateur_demande.java \
		${BUILD}/VIEW/Admin/explication_demande.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_demande.java

${BUILD}/CONTROLLER/Observateur_creation_demande.class: ${SRC}/CONTROLLER/Observateur_creation_demande.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_creation_demande.java

${BUILD}/CONTROLLER/Observateur_ChoixDeplacement.class: ${SRC}/CONTROLLER/Observateur_ChoixDeplacement.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_ChoixDeplacement.java

${BUILD}/CONTROLLER/Observateur_recherche.class: ${SRC}/CONTROLLER/Observateur_recherche.java \
		${BUILD}/VIEW/Admin/panneau_recherche.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/CONTROLLER/Observateur_recherche.java



## VIEW ##

${BUILD}/VIEW/Admin/panneau_recherche.class: ${SRC}/VIEW/Admin/panneau_recherche.java 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/panneau_recherche.java

${BUILD}/VIEW/Admin/Choix_deplacement.class: ${SRC}/VIEW/Admin/Choix_deplacement.java \
			${BUILD}/CONTROLLER/Observateur_ChoixDeplacement.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/Choix_deplacement.java


${BUILD}/VIEW/Admin/Menu_Changement.class: ${SRC}/VIEW/Admin/Menu_Changement.java \
			${BUILD}/CONTROLLER/Observateur_demande.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/Menu_Changement.java

${BUILD}/VIEW/Etudiant/Menu_ChangementEtudiant.class: ${SRC}/VIEW/Etudiant/Menu_ChangementEtudiant.java \
			${BUILD}/CONTROLLER/Observateur_demande.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Etudiant/Menu_ChangementEtudiant.java

${BUILD}/VIEW/Admin/explication_demande.class: ${SRC}/VIEW/Admin/explication_demande.java 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/explication_demande.java

${BUILD}/VIEW/Admin/Creation_Partition.class: ${SRC}/VIEW/Admin/Creation_Partition.java \
		${BUILD}/CONTROLLER/Observateur_creation_partition.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/Creation_Partition.java

${BUILD}/VIEW/Admin/pan_info_groupe.class: ${SRC}/VIEW/Admin/pan_info_groupe.java 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/pan_info_groupe.java	

${BUILD}/VIEW/ChoixProfil.class: ${SRC}/VIEW/ChoixProfil.java \
			${BUILD}/VIEW/Admin/DashboardGroupe.class \
			${BUILD}/VIEW/Etudiant/ViewEtudiant.class \
			${BUILD}/VIEW/Prof/ViewProf.class \
			${BUILD}/CONTROLLER/ObservateurChoixProfil.class 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/ChoixProfil.java	

${BUILD}/VIEW/Etudiant/ViewEtudiant.class: ${SRC}/VIEW/Etudiant/ViewEtudiant.java 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Etudiant/ViewEtudiant.java	

${BUILD}/VIEW/Prof/ViewProf.class: ${SRC}/VIEW/Prof/ViewProf.java \
			${BUILD}/CONTROLLER/Observateur_recherche.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Prof/ViewProf.java

${BUILD}/VIEW/Admin/EditGroupe.class: ${SRC}/VIEW/Admin/EditGroupe.java \
			${BUILD}/CONTROLLER/Observateur_EG.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/EditGroupe.java	

${BUILD}/VIEW/Admin/Creation_Etudiant.class: ${SRC}/VIEW/Admin/Creation_Etudiant.java 
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/Creation_Etudiant.java

${BUILD}/VIEW/Etudiant/Changement_Groupe.class: ${SRC}/VIEW/Etudiant/Changement_Groupe.java \
			${BUILD}/CONTROLLER/Observateur_creation_demande.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Etudiant/Changement_Groupe.java


${BUILD}/VIEW/Admin/CreationGroupe.class: ${SRC}/VIEW/Admin/CreationGroupe.java \
				${BUILD}/CONTROLLER/ObservateurCreation.class  
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/CreationGroupe.java


${BUILD}/VIEW/Admin/DashboardGroupe.class: ${SRC}/VIEW/Admin/DashboardGroupe.java \
				${BUILD}/UTILS/Utils.class \
				${BUILD}/CONTROLLER/Observateur_arborescence.class \
				${BUILD}/CONTROLLER/Observateur_MEG.class \
				${BUILD}/MODEL/MyGroupe.class \
				${BUILD}/MODEL/MyEtudiant.class \
				${BUILD}/MODEL/MyAbstractGroupeFactory.class \
				${BUILD}/MODEL/MyAbstractChangementFactory.class \
				${BUILD}/CONTROLLER/Observateur_menu_etudiant.class \
				${BUILD}/VIEW/Admin/pan_info_groupe.class \
				${BUILD}/CONTROLLER/Observateur_fenetre.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/DashboardGroupe.java
	
${BUILD}/VIEW/Admin/Ajout_Etudiant.class: ${SRC}/VIEW/Admin/Ajout_Etudiant.java \
				${BUILD}/CONTROLLER/Observateur_ajout_etudiant.class
		${JAVAC} ${JAVAC_OPTIONS} ${SRC}/VIEW/Admin/Ajout_Etudiant.java

# ## JARS ##

 ${JAR_FILE} : ${BUILD}/VIEW/ChoixProfil.class
	${JAR} cvfe ${JAR_FILE}  fr.iutfbleau.projetIHM2022FI2.VIEW.ChoixProfil -C build .  -C res .

	

