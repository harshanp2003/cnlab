import java.util.Scanner;

class crc {
    public static void main(String args[])
   {
        Scanner ip = new Scanner(System.in);
        int n; 

        System.out.println("Enter the size of the data:");
        n = ip.nextInt();

        int data[] = new int[n];

        System.out.println("Enter the data, bit by bit:");

        for (int i = 0; i < n; i++) 
        {
            data[i] = ip.nextInt();
        }


        System.out.println("Enter the size of the divisor:");

        n = ip.nextInt();
        int divisor[] = new int[n];

        System.out.println("Enter the divisor, bit by bit:");

        for (int i = 0; i < n; i++) 
        {
            divisor[i] = ip.nextInt();
        }

        int remainder[] = divide(data, divisor);

        System.out.println("The remainder generated is : ");
        for (int i = 0; i < remainder.length - 1; i++) 
        {
            System.out.print(remainder[i]);
        }

        System.out.println("\nThe CRC code generated is:");

        for (int i = 0; i < data.length; i++) 
        {
            System.out.print(data[i]);
        }

        for (int i = 0; i < remainder.length - 1; i++) 
        {
            System.out.print(remainder[i]);
        }
        System.out.println();

        int sent_data[] = new int[data.length + remainder.length - 1];

        System.out.println("Enter the data to be sent:");

        for (int i = 0; i < sent_data.length; i++) 
        {
            sent_data[i] = ip.nextInt();
        }
        receive(sent_data, divisor);

    }

    static int[] divide(int old_data[], int divisor[]) 
    {
        int remainder[], i;
        int data[] = new int[old_data.length + divisor.length];


        System.arraycopy(old_data, 0, data, 0, old_data.length);

        remainder = new int[divisor.length];

        System.arraycopy(data, 0, remainder, 0, divisor.length);


        for (i = 0; i < old_data.length; i++) 
        {
          
            if (remainder[0] == 1) 
            {
                for (int j = 1; j < divisor.length; j++) 
                {
                    remainder[j - 1] = exor(remainder[j], divisor[j]);
                 
                }
            } 
            else 
            {
                for (int j = 1; j < divisor.length; j++) 
                {
                    remainder[j - 1] = exor(remainder[j], 0);
                  
                }
            }
           
            remainder[divisor.length - 1] = data[i + divisor.length];
            
        }
        return remainder;
    }

    static int exor(int a, int b) 
    {
        if (a == b) 
        {
            return 0;
        }
        return 1;
    }

    static void receive(int data[], int divisor[]) 
    {
        int remainder[] = divide(data, divisor);
        for (int i = 0; i < remainder.length; i++) 
        {
            if (remainder[i] != 0) 
            {
                System.out.println("There is an error in received data...");
                return;
            }
        }
        System.out.println("Data was received without any error.");
    }

}
