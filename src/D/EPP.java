package D;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.swing.ButtonGroup;

public class EPP {
	static int cpt = 0;
	static int cpt3 = 0;
	static int cpt4 = 0;
	static int tirage = 10000;

	public static void main(String[] args) {
		BigInteger n = new BigInteger("170141183460469231731687303715884105727", 10);

		// System.out.println("**********Question1**********");
		// System.out.print("Le nombre " + n);
		// if (est_probablement_premier(n))
		// System.out.println(" est très probablement premier!");
		// else
		// System.out.println(" n'est absolument pas premier!");

		// System.out.println("**********Question2**********");
		// long startTime = System.currentTimeMillis();
		// System.out.println("le nbr trouvé en decimale \n"+generateNbr());
		// long endTime = System.currentTimeMillis();
		// long duration = (endTime - startTime);
		// System.out.println("le nbr de tentative aleatoire est "+cpt);
		// System.out.println("le temps de calcule est "+duration+" Ms");

		// System.out.println("**********Question3**********");
		// long startTime3 = System.currentTimeMillis();
		// BigInteger nbrPremierDeSophieGermain = germain();
		// long endTime3 = System.currentTimeMillis();
		// long duration3 = (endTime3 - startTime3);
		// System.out.println("le nbr de tentative aleatoire est " + cpt3);// il
		// faut voir si le calcule a l'interrieur de Genre rate nvr rdt inclu
		// System.out.println("le temps de calcule est " + duration3 +
		// "Millis");
		// System.out.println("p = " + nbrPremierDeSophieGermain);
		// BigInteger NbrPremierSur =
		// nbrPremierDeSophieGermain.multiply(BigInteger.valueOf(2)).add(BigInteger.valueOf(1));
		// System.out.println("2p+1 = " + NbrPremierSur);
		//

		System.out.println("**********Question4**********");
		long startTime4 = System.currentTimeMillis();
		int p = monteCarlo();
		long endTime4 = System.currentTimeMillis();
		long duration4 = (endTime4 - startTime4);
		System.out.println("le temps de calcule est " + duration4 + "Millis");
		System.out.println("le probabilité est " + p / tirage + "," + p % tirage + " %");
		System.out.println("le nbr des impaier trouvé est "+cpt4);
		
	}

	static boolean est_probablement_premier(BigInteger n) {
		/*
		 * Modifiez cette fonction afin qu'elle retourne si oui ou non l'entier
		 * n est un nombre premier, avec un taux d'erreur inférieur à 1/1000 000
		 * 000 000 000 000.
		 */
		return n.isProbablePrime(1000);// la probabalité de presistion est
										// 1/pow(2^100)
	}

	static BigInteger generateNbr() {

		SecureRandom random = new SecureRandom();
		BigInteger n = new BigInteger(512, random);

		while (!est_probablement_premier(n)) {
			cpt++;
			n = new BigInteger(512, random);
		}

		return n;
	}

	static BigInteger germain() {

		BigInteger nbrPremierDeSophieGermain;
		BigInteger nbrPremierSur;
		do {
			nbrPremierDeSophieGermain = generateNbr();
			nbrPremierSur = nbrPremierDeSophieGermain.multiply(BigInteger.valueOf(2)).add(BigInteger.valueOf(1));
			cpt3++;
		} while (!est_probablement_premier(nbrPremierSur));
		return nbrPremierDeSophieGermain;
	}

	static BigInteger generateNbrImpaire() {

		SecureRandom random = new SecureRandom();
		BigInteger nbrImpaire = new BigInteger(1024, random);
		while (!nbrImpaire.mod(BigInteger.ONE).equals(BigInteger.ZERO)) {

			nbrImpaire = new BigInteger(1024, random);
		}
		return nbrImpaire;
	}

	static int monteCarlo() {

		for (int i = 0; i < tirage; i++) {

			if (est_probablement_premier(generateNbrImpaire()) == true) {
				cpt4++;
			}
		}

		return cpt4;
	}
	

}

/*
 * $ make javac *.java $ java EPP Le nombre
 * 170141183460469231731687303715884105727 ...
 */
