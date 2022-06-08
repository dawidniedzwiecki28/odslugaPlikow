import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Towar {

    private double cena;
    private String nazwa;
    private Date dataWydania;
    public Towar()
    {
        this.cena = 0.0;
        this.nazwa = " ";
        this.dataWydania = new GregorianCalendar(2000,01,01).getTime();

    }
    public Towar(double cena, String nazwa) {
        this();
        this.cena = cena;
        this.nazwa = nazwa;
    }
    public Towar(double cena, String nazwa, int year, int month, int day) {
        this(cena, nazwa);
        GregorianCalendar calendar = new GregorianCalendar(year,month-1,day);
        this.dataWydania = calendar.getTime();
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Date getDataWydania() {
        return dataWydania;
    }

    public void setDataWydania(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar(year,month-1,day);
        this.dataWydania = calendar.getTime();
    }

    @Override
    public String toString() {
        return "cena=" + cena + "zł" +
                ", nazwa=" + nazwa + '\'' +
                ", dataWydania=" + dataWydania;
    }

    public static  void writeToFile(Towar[] towars, PrintWriter outS)
    {
        outS.println(towars.length);
        GregorianCalendar calendar = new GregorianCalendar();

        for (Towar towar : towars) {
            //outS.println(towar.toString());
            calendar.setTime(towar.getDataWydania());
            outS.println(towar.getNazwa() + "|" +
                        towar.getCena() + "|" +
                        calendar.get(Calendar.YEAR) + "|" +
                        calendar.get(Calendar.MONTH) + "|" +
                        calendar.get(Calendar.DAY_OF_MONTH));
        }
    }
    public  static Towar[] readFromFile(BufferedReader inS) throws IOException
    {
        int lenght = Integer.parseInt(inS.readLine());
        Towar[] towar = new Towar[lenght];
        for(int i=0;i<lenght;i++)
        {
            String line = inS.readLine();
            StringTokenizer tokenizer = new StringTokenizer(line,"|");

            String name = tokenizer.nextToken();
            double price = Double.parseDouble(tokenizer.nextToken());
            int year = Integer.parseInt(tokenizer.nextToken());
            int month = Integer.parseInt(tokenizer.nextToken());
            int day = Integer.parseInt(tokenizer.nextToken());

            towar[i] = new Towar(price,name,year,month,day);
        }
        return towar;
    }
}
