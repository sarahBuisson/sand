Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: l'effet de l'action bouge, en fonction du terrain
Given Un terrain de dimention(10,10)
Given Un grain g1 de type sable
Given Un grain g1 a la position (4,5)
When Run action on g1
Then Le grain g1 est a (4,6)

Given Un terrain de dimention(10,10)
Given Un grain g1 de type sable
Given Un grain g1 a la position (4,5)
Given Un grain g2 de type sable
Given Un grain g2 a la position (4,6) // la position souhaitée est occupée
When Run action on g1
Then Le grain g1 est a (?,6)