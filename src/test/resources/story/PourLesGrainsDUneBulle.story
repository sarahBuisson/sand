Narrative: Pour un grain d'une bulle
Scenario: Pour un grain d'une bulle
Scenario: Comportement d'un grain d'un ensemble en fonction de la zone de l'ensemble auquel il appartient
Given Un grain g1
Given Le grain g1 est dans la zone interne
When Run action on g1
Then Le grain g1 est lissé

Given Un grain g1
Given Le grain g1 est dans la zone intermediaire
When Run action on g1
Then Le grain g1 se rapproche

Given Un grain g1
Given Le grain g1 est dans la zone externe
When Run action on g1
Then Le grain g1 quitte l'ensemble

Scenario: A partir de la position d'un grain dans un ensemble, determinon la zone de cet ensemble auquel il appartient
Given Un ensemble e1 de centre (1,1) de taille 10
Given Le grain g1 a la position (3,3)
Given g1 dans l'ensemble e1
Then Le grain g1 est dans la zone interne

Given Un ensemble e1 de centre (1,1) de taille 5
Given Le grain g1 a la position (3,3)
Given g1 dans l'ensemble e1
Then Le grain g1 est dans la zone intermediaire

Given Un ensemble e1 de centre (1,1) de taille 5
Given Le grain g1 a la position (10,10)
Given g1 dans l'ensemble e1
Then Le grain g1 est dans la zone externe

Scenario: En fonction de l'action auquel un grain est soumis, teste quelle effet cela  aura sur le grain

Given Un terrain " aa
 aa
aaa "
Given Aux coordonées (1,0) le grain g1
When Run ActionCreation sur g1
When Le grain g1 est lissé
Then Le grain g est a (0,1)


Given Un terrain " aa
 aa
aaa "
Given Aux coordonées (1,0) le grain g1
When Run ActionCreation sur g1
When Le grain g1 est lissé
Then Le grain g est a (0,1)


Given Un terrain "aaa
 aa
aaa "
aaa "
aaa "
aaa "
aaaa"
Given Aux coordonées (0,0) le grain g1
When Run ActionCreation sur g1
When Le grain g1 se rapproche
Then Le grain g1 a pour vitesse (0,4)
Given Aux coordonées (3,6) le grain g2
When Le grain g2 se rapproche
!-- a verifier
Then Le grain g2 a pour vitesse (0,-4)



Given Un terrain "aaa
 aa
aaa "
aaa "
aaa "
aaa "
aaaa"
Given Aux coordonées (0,0) le grain g1
When Run ActionCreation sur g1
When Le grain g1 quitte l'ensemble
Then Le grain g1 n'est pas dans un ensemble

Scenario:fusion

Given Un ensemble e1 de centre (1,1) de taille 15
Given Un ensemble e2 de centre (1,2) de taille 10
Given Le grain g1 a la position (1,2)
Given g1 dans l'ensemble e1
Given Le grain g1 est dans la zone interne
Given Le grain g1 est, par rapport à e2, dans la zone interne
When Run action on $g1
Then fusion de e1 et e2
Then Le grain g1 est dans l'ensemble e1

Given Un ensemble e1 de centre (1,1) de taille 10
Given Un ensemble e2 de centre (1,2) de taille 15
Given Le grain g1 a la position (1,2)
Given g1 dans l'ensemble e1
Given Le grain g1 est dans la zone interne
Given Le grain g1 est, par rapport à e2, dans la zone interne
When Run action on g1
Then fusion de e2 et e1
Then Le grain g1 est dans l'ensemble e2