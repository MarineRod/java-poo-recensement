package fr.diginamic.recensement.services;


import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import exceptions.SaisieException;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/** Recherche et affichage de la population d'un département
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationDepartementService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws SaisieException {
		
		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		
		if (!NumberUtils.isDigits(choix)) {
			throw new SaisieException("Le département doit être un entier.");
		}
		
		List<Ville> villes = rec.getVilles();
		int somme = 0;
		boolean rechercheVille = false;
		for (Ville ville: villes){
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)){
				somme+=ville.getPopulation();
				rechercheVille = true;	
			}
			
		}
		
		if (rechercheVille==false) {

			throw new SaisieException("Le département que vous avez écrit n'existe pas");

		}

		if (somme>0){
			System.out.println("Population du département "+choix+" : "+ somme);
		}
		else {
			System.out.println("Département "+choix+ " non trouvé.");
		}
	}

}
