import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args)
    {

        Towar[] towars = new Towar[6];

        towars[0] = new Towar(12.9,"nic");
        towars[1] = new Towar(29.0,"piłka");
        towars[2] = new Towar(15.35,"samochodzik",2002,2,28);
        towars[3] = new Towar(1.99,"chleb",2022,5,30);
        towars[4] = new Towar(2.50,"woda", 2021,12,31);
        towars[5] = new Towar(798.56,"telefon",2018,10,27);

        try{
            PrintWriter writer = new PrintWriter("baza.txt");

            Towar.writeToFile(towars,writer);

            writer.close();


            BufferedReader reader = new BufferedReader(new FileReader("baza.txt"));

            Towar[] towars2 = Towar.readFromFile(reader);

            for(int i=0;i< towars2.length;i++) System.out.println(towars2[i]);

            reader.close();

        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }


    }
}