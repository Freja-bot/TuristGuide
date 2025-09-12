
##GitHub flow (i detalje)##

Clone GitHub-repository (gøres én gang)
Opdater den lokale main-branch
Opret en lokal feature-branch
Lav commits til feature-branchen
Opdater den lokale main-branch
Merge main-branchen ind i feature-branchen
Push den lokale feature-branch til remote
Opret en pull request på GitHub og tildel en reviewer
Revieweren gennemgår ændringerne
Merge feature-branchen ind i main-branchen (på remote)
Slet feature-branchen (på remote og lokalt):
 - git branch -d feature/add-description
 - git fetch origin --prune
Repeat from step 2 for the next feature or bugfix
