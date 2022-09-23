package mtrx.Matrix;
import mtrx.Utility.*;

public class Frac {

    public static class Type {
        private int a, b;

        private int fpb(int a, int b) {
            if (b == 0) {
                return a;
            }
    
            else if (a < b) {
                return fpb (b, a);
            }
    
            else {
                return fpb (b, a % b);
            }
        }

        // KONSTRUKTOR 
        public Type(int pembilang, int penyebut)
        {
            // KAMUS LOKAL
            int c;
            
            c = fpb(abs(pembilang), abs(penyebut));

            if (penyebut < 0) {
                c *= -1;
            }


            this.a = pembilang / c;
            this.b = penyebut / c;
        }

        public Type(Type frac)
        {
            // KAMUS LOKAL
            int c;
            
            c = fpb(abs(frac.getTop()), frac.getBot());

            if (frac.getBot() < 0) {
                c *= -1;
            }


            this.a = frac.getTop() / c;
            this.b = frac.getBot() / c;
        }


        
        private int abs(int x) {
            if (x < 0) {
                return -x;
            }

            else {
                return x;
            }
        }

        public int getTop() {
            return this.a;
        }

        public int getBot() {
            return this.b;
        }

        public void setTop(int pembilang) {
            this.a = pembilang;
        }

        public void setBot(int penyebut) {
            this.b = penyebut;
        }

        public double getMag() {
            return ((double) this.a) / ((double) this.b);
        }
    }

    public static void copy(Type thisFrac, Type fracIn) {
        thisFrac.setTop(fracIn.getTop());
        thisFrac.setBot(fracIn.getBot());
    }

    public static Type intToFrac(int num){
        return new Type(num, 1);
    }

    public static Type add(Type frac1, Type frac2) {
        // KAMUS LOKAL
        int a, b;

        // ALGORITMA
        
        a = frac1.getTop() * frac2.getBot() + frac1.getBot() * frac2.getTop();
        b = frac1.getBot() * frac2.getBot();

        return new Type(a, b);
    }

    public static Type add(Type frac1, int k) {
        // KAMUS LOKAL


        // ALGORITMA
        
        return new Type(frac1.getTop() + frac1.getBot() * k, frac1.getBot());
    }

    public static Type multp(Type frac1, Type frac2) {

        // KAMUS LOKAL
        int a, b;
        // ALGORITMA

        a = frac1.getTop() * frac2.getTop();
        b = frac1.getBot() * frac2.getBot();

        return new Type(a, b);
    }

    public static Type multp(Type frac1, int k) {

        // KAMUS LOKAL
        // ALGORITMA

        return new Type(frac1.getTop() * k, frac1.getBot());
    }
}
