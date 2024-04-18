package fr.diginamic.recensement.services;

import exceptions.SaisieException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws SaisieException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		
		if (!NumberUtils.isDigits(choix)) {
			throw new SaisieException("Le département doit être un entier.");
		}

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();

		int min = 0;

		try {
			min = Integer.parseInt(saisieMin) * 1000;
		} catch (Exception e)

		{ // mettre en place la SaisieException "Vous n'avez pas le droit d'écrire une
			// lettre" si la saisie n'est pas un nombre
			throw new SaisieException("Vous n'avez pas le droit d'écrire une lettre");
		}

		if (min < 0) {
			// mettre en place la SaisieException "la valeur ne doit pas être négative" si
			// la saisie n'est pas un nombre
			throw new SaisieException("la valeur ne doit pas être négative");
		}

		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		int max = 0;
		try {
			max = Integer.parseInt(saisieMax) * 1000;
			
		} catch (Exception e) {
			
			throw new SaisieException("Erreur lors de la conversion en entier");
		}
			//dans le bloc catch, la variable max n'a pas encore été mise à jour avec la valeur convertie en entier. 
		    //Par conséquent, la vérification if (max < 0) ne détectera jamais si la valeur rentrée est négative si on la place
		    // après le catch, elle doit etre placée après l'exception jetée Erreur lors de la conversion en entier
		    
		
			if (max < 0) {
				throw new SaisieException("la valeur ne doit pas être négative");
			}
			  

			if (min > max) {
				throw new SaisieException("Vous n'avez pas le droit de saisir une valeur maximale < à la valeur minimale");
			}

			List<Ville> villes = rec.getVilles();
			for (Ville ville : villes) {
				if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
					if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
						System.out.println(ville);
					}
				}
			}
		}

	}

