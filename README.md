# Contexte

Projet scolaire de 1ère année d'école d'ingénieur (IMT Atlantique).

C'est un projet d'initiation à la POO avec Java et à la gestion d'un projet de dev.

Projet réalisé en Avril/Mai 2020.

Creation d'un dépôt public le 14/02/2021.


# Résumé du sujet

Il s'agit d'une version de démonstration d'un produit futur consistant en un service web de type « réseau social ». Son principe est de permettre à ses utilisateurs de donner des notes et des avis sur des produits divers (culturels, gastronomiques, etc.) et de consulter ceux des autres utilisateurs. Dans une version avancée, la notation des avis permettra de moduler l'impact de ceux-ci en fonction des notes qu'accumulent leurs auteurs (notion de « karma »).

# Contact

:email: clement.le-gruiec@imt-atlantique.net
:email: elouan.le-duc@imt-atlantique.net

# Fichiers

### Etat

  - Le logiciel fonctionne sans erreurs de compilation.
  - Le lot 1 est terminé.
  - Les tests sont terminés egalement. (91 Tests, 0 erreurs - validé avec tests profs également)
  - Le lot 2 est terminé. 
  - Les tests sont terminés egalement. (24 Tests, 0 erreurs)
  - Les Tests du lot 1 passent encore sans problèmes.
  - Le lot 3 est terminé. 
  - La méthode s'appelle searchItemPremium().
  -  Elle retourne la liste de tout les titre qui match avec la recherche.
  - Les diags de séquences du lot1 et du lot2 sont faits.
  - La JavaDoc a été générée.
 
 
IDE utilisé: Eclipse

JAVA: JSE11

### Les fichiers présents dans le dossier (258 au total)

- cLEGRUIEC-eLEDUC-Diagrammes.pdf  --> Tous les digrammes mergés dans un fichier
- CLEGRUIEC-ELEDUC-feuilleHeures.pdf
- CLEGRUIEC-ELEDUC-Explication_LOT-2-3.pdf --> comment fonctionne notre lot 2

_Tests  (6 fichiers)_
- ficheValidationAddItemFilm.pdf
- ficheValidationAddMember.pdf
- ficheValidationReviewItemFIlm.pdf
- ficheValidationReviewOpinion.pdf
- Trace-ResultatsFULL.txt   --> traces d'exécution du code en version pleine (avec le debug)
- Trace-ResultatsLIGHT.txt  --> traces d'exécution du code en version raccourcie

_Diagrammes (8 fichiers)_
- LEGRUIEC-LEDUC-DC_FINAL.pdf
- LEGRUIEC-LEDUC-diagSequenceAddItemBook.pdf
- LEGRUIEC-LEDUC-diagSequenceAddItemFilm.pdf
- LEGRUIEC-LEDUC-diagSequenceAddMember.pdf
- LEGRUIEC-LEDUC-diagSequenceConsultItem.pdf
- LEGRUIEC-LEDUC-diagSequenceReviewItemBook.pdf
- LEGRUIEC-LEDUC-diagSequenceReviewItemFilm.pdf
- LEGRUIEC-LEDUC-diagSequenceReviewOpinion.pdf

_CodeLOT (27 fichiers)_
- tests
  - addItemBookTest.java
  - addItemFilmTest.java
  - AddMemberTest.java
  - ConsultItemTest.java
  - InitTest.java
  - package-info.java
  - reviewItemBookTest.java
  - reviewItemFilmTest.java
  - SocialNetworkTest.java
  - TestReport.java
- test2
  - ConsultItemTestPremium.java
  - ReviewOfReviewTest.java
  - SocialNetworkTest.java
  - TestReport.java
- Opinions
  - Book.java
  - Film.java
  - HMI.java
  - ISocialNetwork.java
  - ISocialNetworkPremium.java
  - Item.java
  - Member.java
  - Opinion.java
  - package-info.java
  - Review.java
  - SocialNetwork.java
  - TestReport.java
  - TestSocialNetwork.java
