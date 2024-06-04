package cmu.pasta.mu2;

public class RecursionPower {
    public RecursionPower(){

    }
    public static int power(int x,int m){
        if (m==1)
            return x;
        else
            return x* power(x,m-1);
    }
}
