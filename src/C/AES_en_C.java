package C;

public class AES_en_C {

	/* La clef courte K utilisée est formée de 16 octets nuls */

	static int longueur_de_la_clef = 16;

	static byte K[] = { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00 };

	static byte[] M = { (byte) 0x02, (byte) 0x01, (byte) 0x01, (byte) 0x03, (byte) 0x03, (byte) 0x02, (byte) 0x01,
			(byte) 0x01, (byte) 0x01, (byte) 0x03, (byte) 0x02, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x03,
			(byte) 0x02 };

	static byte[] MInv = { (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x01 };

	// byte M[16] = {
	// (byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};

	/*
	 * Résultat du TP précédent : diversification de la clef courte K en une
	 * clef étendue W
	 */

	static int Nr = 10, Nk = 4;
	static int longueur_de_la_clef_etendue = 176;

	static byte[] W = { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x62, (byte) 0x63, (byte) 0x63, (byte) 0x63, (byte) 0x62, (byte) 0x63, (byte) 0x63,
			(byte) 0x63, (byte) 0x62, (byte) 0x63, (byte) 0x63, (byte) 0x63, (byte) 0x62, (byte) 0x63, (byte) 0x63,
			(byte) 0x63, (byte) 0x9B, (byte) 0x98, (byte) 0x98, (byte) 0xC9, (byte) 0xF9, (byte) 0xFB, (byte) 0xFB,
			(byte) 0xAA, (byte) 0x9B, (byte) 0x98, (byte) 0x98, (byte) 0xC9, (byte) 0xF9, (byte) 0xFB, (byte) 0xFB,
			(byte) 0xAA, (byte) 0x90, (byte) 0x97, (byte) 0x34, (byte) 0x50, (byte) 0x69, (byte) 0x6C, (byte) 0xCF,
			(byte) 0xFA, (byte) 0xF2, (byte) 0xF4, (byte) 0x57, (byte) 0x33, (byte) 0x0B, (byte) 0x0F, (byte) 0xAC,
			(byte) 0x99, (byte) 0xEE, (byte) 0x06, (byte) 0xDA, (byte) 0x7B, (byte) 0x87, (byte) 0x6A, (byte) 0x15,
			(byte) 0x81, (byte) 0x75, (byte) 0x9E, (byte) 0x42, (byte) 0xB2, (byte) 0x7E, (byte) 0x91, (byte) 0xEE,
			(byte) 0x2B, (byte) 0x7F, (byte) 0x2E, (byte) 0x2B, (byte) 0x88, (byte) 0xF8, (byte) 0x44, (byte) 0x3E,
			(byte) 0x09, (byte) 0x8D, (byte) 0xDA, (byte) 0x7C, (byte) 0xBB, (byte) 0xF3, (byte) 0x4B, (byte) 0x92,
			(byte) 0x90, (byte) 0xEC, (byte) 0x61, (byte) 0x4B, (byte) 0x85, (byte) 0x14, (byte) 0x25, (byte) 0x75,
			(byte) 0x8C, (byte) 0x99, (byte) 0xFF, (byte) 0x09, (byte) 0x37, (byte) 0x6A, (byte) 0xB4, (byte) 0x9B,
			(byte) 0xA7, (byte) 0x21, (byte) 0x75, (byte) 0x17, (byte) 0x87, (byte) 0x35, (byte) 0x50, (byte) 0x62,
			(byte) 0x0B, (byte) 0xAC, (byte) 0xAF, (byte) 0x6B, (byte) 0x3C, (byte) 0xC6, (byte) 0x1B, (byte) 0xF0,
			(byte) 0x9B, (byte) 0x0E, (byte) 0xF9, (byte) 0x03, (byte) 0x33, (byte) 0x3B, (byte) 0xA9, (byte) 0x61,
			(byte) 0x38, (byte) 0x97, (byte) 0x06, (byte) 0x0A, (byte) 0x04, (byte) 0x51, (byte) 0x1D, (byte) 0xFA,
			(byte) 0x9F, (byte) 0xB1, (byte) 0xD4, (byte) 0xD8, (byte) 0xE2, (byte) 0x8A, (byte) 0x7D, (byte) 0xB9,
			(byte) 0xDA, (byte) 0x1D, (byte) 0x7B, (byte) 0xB3, (byte) 0xDE, (byte) 0x4C, (byte) 0x66, (byte) 0x49,
			(byte) 0x41, (byte) 0xB4, (byte) 0xEF, (byte) 0x5B, (byte) 0xCB, (byte) 0x3E, (byte) 0x92, (byte) 0xE2,
			(byte) 0x11, (byte) 0x23, (byte) 0xE9, (byte) 0x51, (byte) 0xCF, (byte) 0x6F, (byte) 0x8F, (byte) 0x18,
			(byte) 0x8E };

	/* Le bloc à chiffrer aujourd'hui: 16 octets nuls */

//	static byte[] State = { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
//			(byte) 0x00 };

//	static byte[] State = {
//	 (byte)0x0E, (byte)0x09 , (byte)0x0D , (byte)0x0B , (byte)0x0B ,
//	 (byte)0x0E , (byte)0x09 , (byte)0x0D , (byte)0x0D , (byte)0x0B ,
//	 (byte)0x0E
//	 , (byte)0x09 , (byte)0x09 , (byte)0x0D , (byte)0x0B , (byte)0x0E};

	// byte State[16] = {
	// (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	// (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
	// (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF} ;
	
//	static byte[] State = {
//			 (byte)0x00, (byte)0x01 , (byte)0x02 , (byte)0x03 , 
//			 (byte)0x00, (byte)0x01 , (byte)0x02 , (byte)0x03 ,
//			 (byte)0x00, (byte)0x01 , (byte)0x02 , (byte)0x03 ,
//			 (byte)0x00, (byte)0x01 , (byte)0x02 , (byte)0x03 };
	static byte[] State = {
			 (byte)0xA0, (byte)0xB0 , (byte)0xC0 , (byte)0xD0 , 
			 (byte)0xA1, (byte)0xB1 , (byte)0xC1 , (byte)0xD1 , 
			 (byte)0xA2, (byte)0xB2 , (byte)0xC2 , (byte)0xD2 , 
			 (byte)0xA3, (byte)0xB3 , (byte)0xC3 , (byte)0xD3 };
	


	/* Table de substitution déjà utilisée lors du TP précédent */

	static byte[] SBox = { (byte) 0x63, (byte) 0x7C, (byte) 0x77, (byte) 0x7B, (byte) 0xF2, (byte) 0x6B, (byte) 0x6F,
			(byte) 0xC5, (byte) 0x30, (byte) 0x01, (byte) 0x67, (byte) 0x2B, (byte) 0xFE, (byte) 0xD7, (byte) 0xAB,
			(byte) 0x76, (byte) 0xCA, (byte) 0x82, (byte) 0xC9, (byte) 0x7D, (byte) 0xFA, (byte) 0x59, (byte) 0x47,
			(byte) 0xF0, (byte) 0xAD, (byte) 0xD4, (byte) 0xA2, (byte) 0xAF, (byte) 0x9C, (byte) 0xA4, (byte) 0x72,
			(byte) 0xC0, (byte) 0xB7, (byte) 0xFD, (byte) 0x93, (byte) 0x26, (byte) 0x36, (byte) 0x3F, (byte) 0xF7,
			(byte) 0xCC, (byte) 0x34, (byte) 0xA5, (byte) 0xE5, (byte) 0xF1, (byte) 0x71, (byte) 0xD8, (byte) 0x31,
			(byte) 0x15, (byte) 0x04, (byte) 0xC7, (byte) 0x23, (byte) 0xC3, (byte) 0x18, (byte) 0x96, (byte) 0x05,
			(byte) 0x9A, (byte) 0x07, (byte) 0x12, (byte) 0x80, (byte) 0xE2, (byte) 0xEB, (byte) 0x27, (byte) 0xB2,
			(byte) 0x75, (byte) 0x09, (byte) 0x83, (byte) 0x2C, (byte) 0x1A, (byte) 0x1B, (byte) 0x6E, (byte) 0x5A,
			(byte) 0xA0, (byte) 0x52, (byte) 0x3B, (byte) 0xD6, (byte) 0xB3, (byte) 0x29, (byte) 0xE3, (byte) 0x2F,
			(byte) 0x84, (byte) 0x53, (byte) 0xD1, (byte) 0x00, (byte) 0xED, (byte) 0x20, (byte) 0xFC, (byte) 0xB1,
			(byte) 0x5B, (byte) 0x6A, (byte) 0xCB, (byte) 0xBE, (byte) 0x39, (byte) 0x4A, (byte) 0x4C, (byte) 0x58,
			(byte) 0xCF, (byte) 0xD0, (byte) 0xEF, (byte) 0xAA, (byte) 0xFB, (byte) 0x43, (byte) 0x4D, (byte) 0x33,
			(byte) 0x85, (byte) 0x45, (byte) 0xF9, (byte) 0x02, (byte) 0x7F, (byte) 0x50, (byte) 0x3C, (byte) 0x9F,
			(byte) 0xA8, (byte) 0x51, (byte) 0xA3, (byte) 0x40, (byte) 0x8F, (byte) 0x92, (byte) 0x9D, (byte) 0x38,
			(byte) 0xF5, (byte) 0xBC, (byte) 0xB6, (byte) 0xDA, (byte) 0x21, (byte) 0x10, (byte) 0xFF, (byte) 0xF3,
			(byte) 0xD2, (byte) 0xCD, (byte) 0x0C, (byte) 0x13, (byte) 0xEC, (byte) 0x5F, (byte) 0x97, (byte) 0x44,
			(byte) 0x17, (byte) 0xC4, (byte) 0xA7, (byte) 0x7E, (byte) 0x3D, (byte) 0x64, (byte) 0x5D, (byte) 0x19,
			(byte) 0x73, (byte) 0x60, (byte) 0x81, (byte) 0x4F, (byte) 0xDC, (byte) 0x22, (byte) 0x2A, (byte) 0x90,
			(byte) 0x88, (byte) 0x46, (byte) 0xEE, (byte) 0xB8, (byte) 0x14, (byte) 0xDE, (byte) 0x5E, (byte) 0x0B,
			(byte) 0xDB, (byte) 0xE0, (byte) 0x32, (byte) 0x3A, (byte) 0x0A, (byte) 0x49, (byte) 0x06, (byte) 0x24,
			(byte) 0x5C, (byte) 0xC2, (byte) 0xD3, (byte) 0xAC, (byte) 0x62, (byte) 0x91, (byte) 0x95, (byte) 0xE4,
			(byte) 0x79, (byte) 0xE7, (byte) 0xC8, (byte) 0x37, (byte) 0x6D, (byte) 0x8D, (byte) 0xD5, (byte) 0x4E,
			(byte) 0xA9, (byte) 0x6C, (byte) 0x56, (byte) 0xF4, (byte) 0xEA, (byte) 0x65, (byte) 0x7A, (byte) 0xAE,
			(byte) 0x08, (byte) 0xBA, (byte) 0x78, (byte) 0x25, (byte) 0x2E, (byte) 0x1C, (byte) 0xA6, (byte) 0xB4,
			(byte) 0xC6, (byte) 0xE8, (byte) 0xDD, (byte) 0x74, (byte) 0x1F, (byte) 0x4B, (byte) 0xBD, (byte) 0x8B,
			(byte) 0x8A, (byte) 0x70, (byte) 0x3E, (byte) 0xB5, (byte) 0x66, (byte) 0x48, (byte) 0x03, (byte) 0xF6,
			(byte) 0x0E, (byte) 0x61, (byte) 0x35, (byte) 0x57, (byte) 0xB9, (byte) 0x86, (byte) 0xC1, (byte) 0x1D,
			(byte) 0x9E, (byte) 0xE1, (byte) 0xF8, (byte) 0x98, (byte) 0x11, (byte) 0x69, (byte) 0xD9, (byte) 0x8E,
			(byte) 0x94, (byte) 0x9B, (byte) 0x1E, (byte) 0x87, (byte) 0xE9, (byte) 0xCE, (byte) 0x55, (byte) 0x28,
			(byte) 0xDF, (byte) 0x8C, (byte) 0xA1, (byte) 0x89, (byte) 0x0D, (byte) 0xBF, (byte) 0xE6, (byte) 0x42,
			(byte) 0x68, (byte) 0x41, (byte) 0x99, (byte) 0x2D, (byte) 0x0F, (byte) 0xB0, (byte) 0x54, (byte) 0xBB,
			(byte) 0x16 };

	static byte[] SBoxInv = { (byte) 0x52, (byte) 0x09, (byte) 0x6A, (byte) 0xD5, (byte) 0x30, (byte) 0x36, (byte) 0xA5,
			(byte) 0x38, (byte) 0xBF, (byte) 0x40, (byte) 0xA3, (byte) 0x9E, (byte) 0x81, (byte) 0xF3, (byte) 0xD7,
			(byte) 0xFB, (byte) 0x7C, (byte) 0xE3, (byte) 0x39, (byte) 0x82, (byte) 0x9B, (byte) 0x2F, (byte) 0xFF,
			(byte) 0x87, (byte) 0x34, (byte) 0x8E, (byte) 0x43, (byte) 0x44, (byte) 0xC4, (byte) 0xDE, (byte) 0xE9,
			(byte) 0xCB, (byte) 0x54, (byte) 0x7B, (byte) 0x94, (byte) 0x32, (byte) 0xA6, (byte) 0xC2, (byte) 0x23,
			(byte) 0x3D, (byte) 0xEE, (byte) 0x4C, (byte) 0x95, (byte) 0x0B, (byte) 0x42, (byte) 0xFA, (byte) 0xC3,
			(byte) 0x4E, (byte) 0x08, (byte) 0x2E, (byte) 0xA1, (byte) 0x66, (byte) 0x28, (byte) 0xD9, (byte) 0x24,
			(byte) 0xB2, (byte) 0x76, (byte) 0x5B, (byte) 0xA2, (byte) 0x49, (byte) 0x6D, (byte) 0x8B, (byte) 0xD1,
			(byte) 0x25, (byte) 0x72, (byte) 0xF8, (byte) 0xF6, (byte) 0x64, (byte) 0x86, (byte) 0x68, (byte) 0x98,
			(byte) 0x16, (byte) 0xD4, (byte) 0xA4, (byte) 0x5C, (byte) 0xCC, (byte) 0x5D, (byte) 0x65, (byte) 0xB6,
			(byte) 0x92, (byte) 0x6C, (byte) 0x70, (byte) 0x48, (byte) 0x50, (byte) 0xFD, (byte) 0xED, (byte) 0xB9,
			(byte) 0xDA, (byte) 0x5E, (byte) 0x15, (byte) 0x46, (byte) 0x57, (byte) 0xA7, (byte) 0x8D, (byte) 0x9D,
			(byte) 0x84, (byte) 0x90, (byte) 0xD8, (byte) 0xAB, (byte) 0x00, (byte) 0x8C, (byte) 0xBC, (byte) 0xD3,
			(byte) 0x0A, (byte) 0xF7, (byte) 0xE4, (byte) 0x58, (byte) 0x05, (byte) 0xB8, (byte) 0xB3, (byte) 0x45,
			(byte) 0x06, (byte) 0xD0, (byte) 0x2C, (byte) 0x1E, (byte) 0x8F, (byte) 0xCA, (byte) 0x3F, (byte) 0x0F,
			(byte) 0x02, (byte) 0xC1, (byte) 0xAF, (byte) 0xBD, (byte) 0x03, (byte) 0x01, (byte) 0x13, (byte) 0x8A,
			(byte) 0x6B, (byte) 0x3A, (byte) 0x91, (byte) 0x11, (byte) 0x41, (byte) 0x4F, (byte) 0x67, (byte) 0xDC,
			(byte) 0xEA, (byte) 0x97, (byte) 0xF2, (byte) 0xCF, (byte) 0xCE, (byte) 0xF0, (byte) 0xB4, (byte) 0xE6,
			(byte) 0x73, (byte) 0x96, (byte) 0xAC, (byte) 0x74, (byte) 0x22, (byte) 0xE7, (byte) 0xAD, (byte) 0x35,
			(byte) 0x85, (byte) 0xE2, (byte) 0xF9, (byte) 0x37, (byte) 0xE8, (byte) 0x1C, (byte) 0x75, (byte) 0xDF,
			(byte) 0x6E, (byte) 0x47, (byte) 0xF1, (byte) 0x1A, (byte) 0x71, (byte) 0x1D, (byte) 0x29, (byte) 0xC5,
			(byte) 0x89, (byte) 0x6F, (byte) 0xB7, (byte) 0x62, (byte) 0x0E, (byte) 0xAA, (byte) 0x18, (byte) 0xBE,
			(byte) 0x1B, (byte) 0xFC, (byte) 0x56, (byte) 0x3E, (byte) 0x4B, (byte) 0xC6, (byte) 0xD2, (byte) 0x79,
			(byte) 0x20, (byte) 0x9A, (byte) 0xDB, (byte) 0xC0, (byte) 0xFE, (byte) 0x78, (byte) 0xCD, (byte) 0x5A,
			(byte) 0xF4, (byte) 0x1F, (byte) 0xDD, (byte) 0xA8, (byte) 0x33, (byte) 0x88, (byte) 0x07, (byte) 0xC7,
			(byte) 0x31, (byte) 0xB1, (byte) 0x12, (byte) 0x10, (byte) 0x59, (byte) 0x27, (byte) 0x80, (byte) 0xEC,
			(byte) 0x5F, (byte) 0x60, (byte) 0x51, (byte) 0x7F, (byte) 0xA9, (byte) 0x19, (byte) 0xB5, (byte) 0x4A,
			(byte) 0xD, (byte) 0x2D, (byte) 0xE5, (byte) 0x7A, (byte) 0x9F, (byte) 0x93, (byte) 0xC9, (byte) 0x9C,
			(byte) 0xEF, (byte) 0xA0, (byte) 0xE0, (byte) 0x3B, (byte) 0x4D, (byte) 0xAE, (byte) 0x2A, (byte) 0xF5,
			(byte) 0xB0, (byte) 0xC8, (byte) 0xEB, (byte) 0xBB, (byte) 0x3C, (byte) 0x83, (byte) 0x53, (byte) 0x99,
			(byte) 0x61, (byte) 0x17, (byte) 0x2B, (byte) 0x04, (byte) 0x7E, (byte) 0xBA, (byte) 0x77, (byte) 0xD6,
			(byte) 0x26, (byte) 0xE1, (byte) 0x69, (byte) 0x14, (byte) 0x63, (byte) 0x55, (byte) 0x21, (byte) 0xC,
			(byte) 0x7D };

	public static void main(String[] args) {
//		System.out.print("Le bloc \"State\" en entrée vaut : \n");
//		afficher_le_bloc(State);
//		chiffrer();
//		System.out.print("Le bloc \"State\" en sortie vaut : \n");
//		afficher_le_bloc(State);

//		System.out.print("Le bloc \"State\" en entrée vaut : \n");
//		afficher_le_bloc(State);
//		dechiffrer();
//		System.out.print("Le bloc \"State\" en sortie vaut : \n");
//		afficher_le_bloc(State);

		/* SubBytes */
//		 System.out.print("Le bloc \"State\" en entrée vaut : \n");
//		 afficher_le_bloc(State);
//		 SubBytes();
//		 System.out.print("Le bloc \"State\" en sortie vaut : \n");
//		 afficher_le_bloc(State);

		/* SubBytesiNV */
		// System.out.print("Le bloc \"State\" en entrée vaut : \n");
		// afficher_le_bloc(State);
		// Inv_SubBytes();
		// System.out.print("Le bloc \"State\" en sortie vaut : \n");
		// afficher_le_bloc(State);

		/* ShiftRows */
		 System.out.print("Le bloc \"State\" en entrée vaut : \n");
		 afficher_le_bloc(State);
		 ShiftRows();
		 System.out.print("Le bloc \"State\" en sortie vaut : \n");
		 afficher_le_bloc(State);

		/* ShiftRowsInv */
		// System.out.print("Le bloc \"State\" en entrée vaut : \n");
		// afficher_le_bloc(State);
		// ShiftRowsInv();
		// System.out.print("Le bloc \"State\" en sortie vaut : \n");
		// afficher_le_bloc(State);

		/* ShiftRows */
		// System.out.print("Le bloc \"State\" en entrée m vaut : \n");
		// afficher_le_bloc(State);
		// MixColumns();
		// System.out.print("Le bloc \"State\" en sortie vaut : \n");
		// afficher_le_bloc(State);

		// afficher_le_bloc(State);
		// AddRoundKey(1);
		// System.out.print("Le bloc \"State\" en sortie vaut : \n");
		// afficher_le_bloc(State);

	}
	public static String toHex(byte[] donnees) {
		return javax.xml.bind.DatatypeConverter.printHexBinary(donnees);
	}
	public static void afficher_le_bloc(byte[] M) {
		//String[] M = toHex(mot);
		for (int i = 0; i < 4; i++) { // Lignes 0 à 3
			System.out.print("          ");
			for (int j = 0; j < 4; j++) { // Colonnes 0 à 3
				System.out.print(String.format("0x%02X ",M[4 * j + i]));
			}
			System.out.print("\n");
		}
	}

	public static void chiffrer() {
		int i;
		AddRoundKey(0);
		for (i = 1; i < Nr; i++) {
			SubBytes();
			ShiftRows();
			MixColumns();
			AddRoundKey(i);
		}
		SubBytes();
		ShiftRows();
		AddRoundKey(Nr);
	}

	public static void dechiffrer() {

		int i;
		AddRoundKey(0);
		for (i = 1; i < Nr; i++) {
			ShiftRowsInv();
			Inv_SubBytes();
			AddRoundKey(i);
			MixColumnsInv();

		}
		ShiftRowsInv();
		Inv_SubBytes();
		AddRoundKey(Nr);
	}

	public static byte gmul(byte a, byte b) {
		byte p = 0;
		byte hi_bit_set;
		int i;
		for (i = 0; i < 8; i++) {
			if ((b & 1) == 1)
				p ^= a;
			hi_bit_set = (byte) (a & 0x80);
			a <<= 1;
			if (hi_bit_set == 0x80)
				a ^= 0x1b;
			b >>= 1;
		}
		return (byte) (p & 0xFF);
	}

	/* Partie à compléter pour ce TP */

	public static void SubBytes() {
		//System.out.print("taile "+State.length);
		for (int i = 0; i < State.length-1; i++)
			State[i] = SBox[(int) State[i]];

	};

	// public void generateInvSBox()
	// {
	// byte [] T;
	// for (int i = 0; i < 256;i++)
	// T[(int)SBox[i]] = i;
	//
	// // System.out.print("%d ",(int)SBox[i]);
	//
	//
	// for (int i = 0; i < 256; ++i)
	// {
	// System.out.print(T[i]);
	// }
	// }

	/* Inverse de SubBytes */

	public static void Inv_SubBytes() {

		for (int i = 0; i < State.length; i++)
			State[i] = SBoxInv[(int) State[i]];

	};

	public static void ShiftRows() {

		byte tmp = State[1];

		State[1] = State[5];
		State[5] = State[9];
		State[9] = State[13];
		State[13] = tmp;

		byte tmp1 = State[2];
		byte tmp2 = State[6];

		State[2] = State[10];
		State[6] = State[14];
		State[10] = tmp1;
		State[14] = tmp2;

		tmp1 = State[3];
		tmp2 = State[7];
		byte tmp3 = State[11];

		State[3] = State[15];
		State[7] = tmp1;
		State[11] = tmp2;
		State[15] = tmp3;

	};

	public static void ShiftRowsInv() {

		byte tmp = State[13];

		State[13] = State[9];
		State[9] = State[5];
		State[5] = State[1];
		State[1] = tmp;

		byte tmp1 = State[10];
		byte tmp2 = State[14];

		State[10] = State[2];
		State[14] = State[6];
		State[2] = tmp1;
		State[6] = tmp2;

		tmp1 = State[3];

		State[3] = State[7];
		State[7] = State[11];
		;
		State[11] = State[15];
		;
		State[15] = tmp1;

	};

	/**
	 * Applique la colonne obtenu à State
	 */
	public static void applySate(byte[] t, int j) {
		for (int i = 0; i < 4; i++) {
			State[i + 4 * j] = t[i];
		}

	}

	/**
	 * Initalisae le vecteur
	 */
	public static void initialise(byte[] vect) {
		for (int i = 0; i < 4; i++) {
			vect[i] = 0x00;
		}
	}

	public static void MixColumns() {

		// byte[] vecteur;
		byte vecteur[] = new byte[4];

		int i = 0;
		do {

			for (int j = 0; j < vecteur.length; j++) {
				vecteur[j] = State[4 * i + j];
			}
			applySate(ProduitMatrice(M, vecteur), i);
			initialise(vecteur);
			i++;

		} while (i < 4);

	};

	public static void MixColumnsInv() {

		// byte vecteur[4];
		byte vecteur[] = new byte[4];

		int i = 0;
		do {

			for (int j = 0; j < vecteur.length; j++) {
				vecteur[j] = State[4 * i + j];
			}
			applySate(ProduitMatrice(MInv, vecteur), i);
			initialise(vecteur);
			i++;

		} while (i < 4);

	};

	/**
	 * Produit Matrice * vecteur
	 */
	public static byte[] ProduitMatrice(byte[] matrice, byte[] vect) {
		byte vecteur[] = new byte[4];
		int j = 0;
		int k = 0;

		for (j = 0; j < vect.length; j++) {
			for (k = 0; k < 4; k++) {
				vecteur[j] ^= gmul(matrice[4 * k + j], vect[k]);
			}
		}

		return vecteur;

	}

	static void AddRoundKey(int r) {
		// byte cleRond[16];
		byte cleRond[] = new byte[16];
		for (int i = 0; i < 16; ++i) {
			State[i] = (byte) (W[16 * r + i] ^ State[i]);
		}

	}
	
	

}