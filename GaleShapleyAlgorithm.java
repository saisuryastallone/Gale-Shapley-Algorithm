
import java.util.*;

public class GaleShapleyAlgorithm
{

    // This function returns true if woman 'w' prefers man 'm1' over man 'm'
    static boolean wPrefersM1OverM(int prefer[][], int w, int m, int m1)
    {
        // Check if w prefers m over her current engagement m1
        int N= prefer[0].length;
        for (int i = 0; i < N; i++)
        {
            // If m1 comes before m in list of w,
            // then w prefers her current engagement,
            // else don't do anything
            if (prefer[w][i] == m1)
                return true;

            // If m comes before m1 in w's list,
            // then free her current engagement
            // and engage her with m
            if (prefer[w][i] == m)
                return false;
        }
        return false;
    }

    // Prints stable matching for N Men and
    // N Women. Men are numbered as 0 to
    // N-1. Women are numbered as N to 2N-1.
    static void stableMarriage(int prefer[][])
    {
        // Stores partner of women. This is our
        // output array that stores pairing information.
        // The value of wPartner[i] indicates the partner
        // assigned to woman N+i. Note that the woman
        // numbers between N and 2*N-1. The value -1
        // indicates that (N+i)'th woman is free
        int N= prefer[0].length;
        int wPartner[] = new int[N];

        // An array to store availability of men.
        // If mFree[i] is false, then man 'i' is
        // free, otherwise engaged.
        boolean mFree[] = new boolean[N];

        // Initialize all men and women as free
        Arrays.fill(wPartner, -1);
        int freeCount = N;

        // While there are free men
        while (freeCount > 0)
        {
            // Pick the first free man
            // (we could pick any)
            int m;
            for (m = 0; m < N; m++)
                if (mFree[m] == false)
                    break;

            // One by one go to all women
            // according to m's preferences.
            // Here m is the picked free man
            for (int i = 0; i < N &&
                    mFree[m] == false; i++)
            {
                int w = prefer[m][i];

                // The woman of preference is free,
                // w and m become partners (Note that
                // the partnership maybe changed later).
                // So we can say they are engaged not married
                if (wPartner[w - N] == -1)
                {
                    wPartner[w - N] = m;
                    mFree[m] = true;
                    freeCount--;
                }

                else // If w is not free
                {
                    // Find current engagement of w
                    int m1 = wPartner[w - N];

                    // If w prefers m over her current engagement m1,
                    // then break the engagement between w and m1 and
                    // engage m with w.
                    if (wPrefersM1OverM(prefer, w, m, m1) == false)
                    {
                        wPartner[w - N] = m;
                        mFree[m] = true;
                        mFree[m1] = false;
                    }
                } // End of Else
            } // End of the for loop that goes
            // to all women in m's list
        } // End of main while loop


// Print the solution
        System.out.println("Woman Man");
        for (int i = 0; i < N; i++)
        {
            System.out.print(" ");
            System.out.println(i + N + "	 " + wPartner[i]);
        }
    }

    // Driver Code
    public static void main(String[] args)
    {
        System.out.println("Welcome to Stable Matching :)");
        System.out.println("Enter the number of pairs of men and women : ");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int prefer[][] = new int[2*N][N];

        for(int i=0;i<2*N;i++){
            if(i<=(N-1)) {
                System.out.println("Enter men "+i+"'s preferences from ["+N+"-"+(2*N-1)+"] : ");
            }
            else{
                System.out.println("Enter women "+(i-N)+"'s preferences from ["+0+"-"+(N-1)+"] : ");
            }
            for(int j=0;j<N;j++){
                prefer[i][j] = sc.nextInt();
            }
        }
        stableMarriage(prefer);
    }
}

