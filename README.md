# Bavard

## Contexte du projet
Le but de ce projet est de mettre en place une application style messagerie instantanée que nous allons appeler ePapotage. Cette application doit permettre aux 
bavards d’une part de poster des petits messages, et d’autre part de recevoir les messages 
poster par d’autres bavards.
Les utilisateurs de ce système sont donc un gestionnaire (concierge), et plusieurs bavards. 
Chaque bavard peut s’enregistrer auprès du gestionnaire afin de poster de messages et de
recevoir les messages d’autres bavards.
La communication entre ces utilisateurs se fera en utilisant le mécanisme d’événements. Les 
bavards et le concierge échangeront des PapotagesEvent. 

## Installation
$ git clone https://github.com/RICHARD-90/Bavard.git

# Environnement de travail
Il n'y a pas de contrainte par rapport à l'environnement de travail. Il faut bien penser a importer les packages.

## UML
![image](https://user-images.githubusercontent.com/72502866/119502886-b3767500-bd6a-11eb-8e4f-2ac3800a46ec.png)
