package E;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
    
public class RSA {
    private static int LG_MAX = 10;
    // LG_MAX est la longueur maximale du texte clair (en nombre de caractères ASCII)
    
    private static String message, messageDechiffré ;
    
    private static BigInteger code, code_chiffre, code_dechiffre ;
    private static BigInteger n ;    // Le module de la clef publique
    private static BigInteger e ;    // L'exposant de la clef publique
    private static BigInteger d ;    // L'exposant de la clef privée
    private static BigInteger p ;    
    private static BigInteger q ;    
    private static BigInteger w ;    
    
    
    public static void main(String[] args) {
        message = "Alfred";                     // C'est le message ASCII à chiffrer
        os2ip(); // <----------------------------------------------------- Exercice 2
        System.out.println("Message de " + message.length() + " caractères codé par "
                           + code);
        long startTime = System.nanoTime();
        fabrique(); // <-------------------------------------------------- Exercice 1 
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Clef publique (n) : " + n);
        System.out.println("Clef publique (e) : " + e);
        System.out.println("Clef privée (d)   : " + d);
        System.out.println("Clef privée (p)   : " + p);
        System.out.println("Clef privée (q)   : " + q);
        System.out.println("Clef privée (n)   : " + n);
        System.out.println("Clef privée (w)   : " + w);
        System.out.println("LE TEMPS DE CALCULE EST : "+duration);
        /* Chiffrement */
        code_chiffre = code.modPow(e, n);
        System.out.println("Code chiffré      : " + code_chiffre);
        /* Déchiffrement */
        code_dechiffre = code_chiffre.modPow(d, n);
        System.out.println("Code déchiffré    : " + code_dechiffre);
        /* Retour à la chaîne de caractères */
        i2osp(); // <----------------------------------------------------- Exercice 3
        System.out.println("Message déchiffré : " + messageDechiffré);
    }

    static boolean est_probablement_premier(BigInteger n)
    {
        /*
          Modifiez cette fonction afin qu'elle retourne si oui
          ou non l'entier n est un nombre premier, avec un taux 
          d'erreur inférieur à 1/1000 000 000 000 000 000.
        */  
        return n.isProbablePrime(1000);// la probabalité de presistion est 1/pow(2^100)			     
    }
    static BigInteger generateNbr(int bits){
        SecureRandom random = new SecureRandom();
        BigInteger n= new BigInteger(bits, random);
        
        while(!est_probablement_premier(n)){
//        	cpt++;
        	n=new BigInteger(bits, random);
        }
        
        return n;
    }
    
    static BigInteger pgcd(BigInteger a, BigInteger b)
    {
      BigInteger r;
      while (!b.equals(BigInteger.ZERO))
        {
          r = a.mod(b);
          a = b;
          b = r;
        }
      return a;
    }
    
	static void fabrique() {
//		n = new BigInteger("196520034100071057065009920573", 10);
//		e = new BigInteger("7", 10);
//		d = new BigInteger("56148581171448620129544540223", 10);

		BigInteger val = new BigInteger("1");
		p = generateNbr(1024);
		q = generateNbr(1024);
		n = p.multiply(q);
		w = p.subtract(val).multiply(q.subtract(val));
		SecureRandom random = new SecureRandom();
		
		do 
	        d = new BigInteger(2048, random).mod(w);	
		while (!pgcd(d, w).equals(BigInteger.ONE));
		

		e=d.modInverse(w);
		

	}
    
    static void os2ip() {
//        code = new BigInteger("71933831046500", 10);
    	byte [] bytes; // 8 octer
    	bytes = message.getBytes();
    	code = new BigInteger(bytes);
    	
    	int l =  message.length();
    	for (int i = 0; i <l; i++) {
			message = message + bytes[l-1-i]*Math.pow(256, i);  
		}
    }
    
    static void i2osp() {
        messageDechiffré = "Alfred";
    }  
}

/*
  $ make
  javac *.java 
  $ java RSA
  Message de 6 caractères codé par 71933831046500
  Clef publique (n) : 196520034100071057065009920573
  Clef publique (e) : 7
  Clef privée (d)   : 56148581171448620129544540223
  Code chiffré      : 45055523945410630249803126960
  Code déchiffré    : 71933831046500
  Message déchiffré : Alfred
  $ make
  javac *.java 
  $ java RSA
  Message de 6 caractères codé par 71933831046500
  Clef publique (n) : 16030892766762842431340770210743036685031398079921553670604685486569286656606605595631225776977235841385444053108119117583512126256994204056608207489789843098528364927014755351774272172033675560634957816099973125577252317756472338260132789745526498461934134997517242632492781502437184042928092412552614439386554580469944958405794663512768805525125398746656203495698863562460606952192777731756933050677561945329887692965305208866537474206424649676076215454488192033687912480859440407832297462421827997413801358456441362944296749071007302495790239766670317209239851687609894689591009328799675082867777410155073861046373
  Clef publique (e) : 65537
  Clef privée (d)   : 9708502584994998490927493929602989548329892881762767065723178768664036916561883761701074981891549667280898949720940045728208436320553274623598574168328713132743195507167182506247171254528229595520110406655903281416011481937750997231253649465186791033372992630902533837124654742080532137019027234298385142732230978418956266501279510883188069572386849161422361779286163075281592096969347680186375873739144649265540663588175935242430348639507261039476863831234908279114835989501398488944103778312539088385386460829754324904254033503310708682092578403088242533994291075874942217014420782887844958939621076485086812511233
  Code chiffré      : 7641292418053626962665278875580142015436460693577851491986938480114503327125526178265350136700682289592344443712694725930902760855426496212236767617337463872232819344134167623007333019186753732712166095984572956817430043705938253278806854139247820333438321470755767267235030054425043622595048582419904482851580978445154834596054769781348588115772614694966955178203514232958280538491681561814416551716032551912706735393315194946034135755318871886272457362041587641660881976673827763481534604491121402419146647773577086036781911424740442920947275720115487215153942722314144483330241566289484443670774019111345130196205
  Code déchiffré    : 71933831046500
  Message déchiffré : Alfred
*/
