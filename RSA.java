import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
public class RSA
{
    private BigInteger p, q, N, phi, e, d;
    private int bitLength = 1024;
    private Random r;

    public RSA() 
    {
        r = new Random();

        p = BigInteger.probablePrime(bitLength, r);
        q = BigInteger.probablePrime(bitLength, r);

        System.out.println("Prime number p is " + p);
        System.out.println("Prime number q is " + q);

        N = p.multiply(q);
        e = BigInteger.probablePrime(bitLength / 2, r);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        while ( phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) 
            e = e.add(BigInteger.ONE);

        System.out.println("Public key is " + e);
        d = e.modInverse(phi);
        System.out.println("Private key is " + d);

    }

    public static void main(String[] args) throws IOException 
    {
        RSA rsa = new RSA();
        Scanner ip=new Scanner(System.in);
       
        System.out.println("Enter the plain text: ");
        String testString = ip.next();




        System.out.println("Encrypting string: " + testString);
      

        byte[] encrypted = rsa.encrypt(testString.getBytes());
        byte[] decrypted = rsa.decrypt(encrypted);

        System.out.println("The encrypeted string is "+encrypted);
        System.out.println("Decrypted string: " + new String(decrypted, StandardCharsets.UTF_8));

    }

    
    public byte[] encrypt(byte[] message) 
    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    public byte[] decrypt(byte[] message) 
    {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }

}
