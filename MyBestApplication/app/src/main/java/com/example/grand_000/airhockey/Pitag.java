package com.example.grand_000.airhockey;


import java.util.Scanner;

public class Pitag {
    int day;
    int month;
    int year;
    public Pitag()
    {
        this.day=0;
        this.month=0;
        this.year=0;
    }
    public Pitag(int day, int month, int year)
    {
        this.day=day;
        this.month=month;
        this.year=year;
    }
    public void setDay(int day)
    {
        this.day=day;
    }
    public void setMonth(int month )
    {
        this.month=month;
    }
    public void setYear(int year)
    {
        this.year=year ;
    }
    public int getDay()
    {
        return this.day ;
    }
    public int getMonth()
    {
        return this.month;
    }
    public int getYear()
    {
        return this.year;
    }
    public static int sumDate_1(Pitag s)
    {
        int num = s.day+s.month+s.year;
        if(num>9&&num<13)return num;
        else{
            int sum = 0;
            while (num > 0)
            {
                sum = sum + num % 10;
                num = num / 10;
            }

            return sum;}
    }
    public static int sumDate_2(int sumDatex)
    {
        return sumDatex>9&&sumDatex<13||sumDatex<10?sumDatex: sumDate_2(sumDatex%10+sumDate_2(sumDatex/10));
    }
    public static boolean nBig_9_Sm_13(int sumDatex)
    {
        return sumDatex>9&&sumDatex<13?true:false;
    }
    public static int compute( boolean b, int n )
    {
        return n - 9 * ((n - 1) / 9);
    }
    public static String date_Note_3(int numNote)
    {
        String[] Notes= {"n0T @ N0t$", "A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab"};
        return Notes[numNote];
    }
    public static int nN2(int numNote)
    {
        int n=numNote;
        if(n==11) return n=1;
        else if(n==12) return n=2;
        else return n+=2;
    }
    public static int nN1(int numNote)
    {
        int n=numNote;
        if(n==12) return n=1;
        else return n++;
    }
    public static String[] diatonicScale_4_0(int numNote)
    {
        int n=numNote;


        String[] Notes= {"n0T @ N0t$", "A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab"};
        String[] diaScaleM=new String[9];
        diaScaleM[0]="n0T @ N0t$";
        diaScaleM[1]=Notes[n];
        if(n==11) n=1;
        else if(n==12)  n=2;
        else n+=2;
        diaScaleM[2]=Notes[n];
        if(n==11) n=1;
        else if(n==12)  n=2;
        else n+=2;
        diaScaleM[3]=Notes[n];
        if(n==12) n=1;
        else n++;
        diaScaleM[4]=Notes[n];

        if(n==11) n=1;
        else if(n==12)  n=2;
        else n+=2;
        diaScaleM[5]=Notes[n];

        if(n==11) n=1;
        else if(n==12)  n=2;
        else n+=2;
        diaScaleM[6]=Notes[n];

        if(n==11) n=1;
        else if(n==12)  n=2;
        else n+=2;
        diaScaleM[7]=Notes[n];
        if(n==12) n=1;
        else n++;
        diaScaleM[8]=Notes[n];
        return diaScaleM;
    }
    public static String[] diatonicScale_4_0_Minor(int numNote)
    {
        int n=numNote;


        String[] Notes= {"n0T @ N0t$", "A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab"};
        String[] diaScaleM=new String[9];
        diaScaleM[0]="n0T @ N0t$";
        diaScaleM[1]=Notes[n];
        if(n==11) n=1;
        else if(n==12)  n=2;
        else n+=2;
        diaScaleM[2]=Notes[n];
        if(n==12) n=1;
        else n++;
        diaScaleM[3]=Notes[n];
        if(n==11) n=1;
        else if(n==12)  n=2;
        else n+=2;
        diaScaleM[4]=Notes[n];

        if(n==11) n=1;
        else if(n==12)  n=2;
        else n+=2;
        diaScaleM[5]=Notes[n];

        if(n==12) n=1;
        else n++;
        diaScaleM[6]=Notes[n];

        if(n==11) n=1;
        else if(n==12)  n=2;
        else n+=2;
        diaScaleM[7]=Notes[n];
        if(n==11) n=1;
        else if(n==12)  n=2;
        else n+=2;
        diaScaleM[8]=Notes[n];
        return diaScaleM;
    }

    public String toString()
    {
        String m=String.valueOf(this.month), d=String.valueOf(this.day);
        int l=String.valueOf(this.month).length(), l1=String.valueOf(this.day).length();
        if (l<2) m= "0"+this.month;
        if (l1<2) d= "0"+this.day;
        return "Date entered: "+"<"+d+"/"+m+"/"+this.year+">\n\n"+"Your Major Scale is: ";
    }
    public static String printS(Pitag p)
    {
        String s="";
        int num =sumDate_1(p);
        int numm=sumDate_2(num);
        String[] diatonicScale = diatonicScale_4_0(numm);
        String[] diatonicScaleMinor = diatonicScale_4_0_Minor(numm);
        s+="Your Major Scale is:\n";
        for(int i=1; i<diatonicScale.length; i++)
        {
            s+=diatonicScale[i]+"\n";

        }
        s+="Your Minor Scale is:\n";
        for (int i = 1; i < diatonicScaleMinor.length; i++) {
            s+=diatonicScaleMinor[i]+"\n";
        }

        return s;
    }
}

