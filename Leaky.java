import java.util.Scanner;
public class Leaky 
{
    public static void main(String args[]) 
    {
        Scanner ip = new Scanner(System.in);

        int bucket_remaining = 0, sent, received;
        int[] buf = new int[30]; 

        System.out.println("Enter the bucket capacity");
        int bucket_capacity = ip.nextInt();

        System.out.println("Enter the bucket rate (Rate at which the bucket sends the packets)");
        int bucket_rate = ip.nextInt();

        System.out.println("Enter the number of packets to be sent");
        int n = ip.nextInt();

        System.out.println("Enter the packets sizes one by one");
        for (int i = 0; i < n; i++) 
            buf[i] = ip.nextInt();

       System.out.println(String.format("%s\t%s\t%s\t%s\t%s","TimeΔt","P_size","accepted","sent","remaining"));
        //System.out.printf("%s\t%s\t%s\t%s\t%s\t","TimeΔt,P_size,accepted,sent,reamining");
        //System.out.printf  for above
        for (int i = 0; i < n; i++) 
        {
            if (buf[i] != 0) 
            {
                if (bucket_remaining + buf[i] > bucket_capacity) 
                    received = -1;

                else
                {
                    received = buf[i];
                    bucket_remaining += buf[i];
                }

            } 
            else
                received = 0;

            if (bucket_remaining != 0) 
            {
                if (bucket_remaining < bucket_rate) 
                {
                    sent = bucket_remaining;
                    bucket_remaining = 0;
                } 

                else 
                {
                    sent = bucket_rate;
                    bucket_remaining -= bucket_rate;
                }

            } 
            else 
                sent = 0;

            if (received == -1) 
                System.out.println(String.format("%d\t%d\t%s\t%d\t%d", i + 1, buf[i], "dropped", sent, bucket_remaining));
            else 
                System.out.println(String.format("%d\t%d\t%d\t%d\t%d", i + 1, buf[i], received, sent, bucket_remaining));

        }
    }
}
