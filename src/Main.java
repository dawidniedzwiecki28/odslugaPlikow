import java.io.*;

public class Main {

    public static void main(String[] args)
    {

        Towar[] towar = new Towar[6];

        towar[0] = new Towar();
        towar[1] = new Towar(29.0, "pilka");
        towar[2] = new Towar(15.35,"samochodzik",2002,2,28);
        towar[3] = new Towar(1.99,"chleb",2022,5,30);
        towar[4] = new Towar(2.50,"woda", 2021,12,31);
        towar[5] = new Towar(798.56,"telefon",2018,10,27);

        try
        {
//            RandomAccessFile RAF = new RandomAccessFile("baza.txt", "rw");
//            Towar.writeToFile(towar, RAF);
//            RAF.seek(0);
//            Towar[] towarki = Towar.readFromFile(RAF);
//
//            for(int i=0;i< towarki.length;i++)
//            {
//                System.out.println(towarki[i].getCena());
//                System.out.println(towarki[i].getNazwa());
//                System.out.println(towarki[i].getDataWydania());
//                System.out.println("-------------------------------");
//            }
//
//            try
//            {
//                Towar b = new Towar();
//                b.czytajRekord(RAF,9);
//                System.out.println(b);
//            }
//            catch (BrakRekordu e)
//            {
//                System.out.println(e.getMessage());
//            }
//
//            RAF.close();

            ObjectOutputStream outS = new ObjectOutputStream(new FileOutputStream("baza.txt"));
            outS.writeObject(towar);

            outS.close();

            ObjectInputStream inS = new ObjectInputStream(new FileInputStream("baza.txt"));
            Towar[] a = (Towar[]) inS.readObject();
            for (int i=0;i<a.length;i++)
                System.out.println(a[i].getNazwa());

            inS.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}