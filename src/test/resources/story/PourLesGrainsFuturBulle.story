Scenario: Decrit le comportement de grains n'appartenant pas encore a une bulle


Given Le grain g1 a la position (1,1)
Given Le grain g2 a la position (1,2)
When Run ActionCreation sur g1
Then Le grain g1 est dans un ensemble
Then Le grain g2 est dans un ensemble
Then Le grain g1 est dans le meme ensemble que g2


Given Le grain g1 a la position (1,1)
Given Le grain g2 a la position (1,3)
When Run ActionCreation sur g1
Then Le grain g1 n'est pas dans un ensemble
Then Le grain g2 n'est pas dans un ensemble


Scenario: Decrit le comportement de grains en contacte avec une autre bulle
Given Le grain g1 a la position (1,1)
Given Le grain g2 a la position (1,3)
Given g2 dans l'ensemble e2
When Run ActionCreation sur g1
Then Le grain g1 est dans le meme ensemble que g2
Then Le grain g1 est dans l'ensemble e2