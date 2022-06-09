import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Towar implements Serializable
{

    public String getHaslo() {
        return haslo;
    }

    private transient String haslo = "tajne";
    public static final int DLUGOSC_NAZWY = 30;
    public static final int DLUGOSC_REKORDU = (Character.SIZE * DLUGOSC_NAZWY + Double.SIZE + 3 * Integer.SIZE )/8;
    private double cena;
    private String nazwa;
    private Date dataWydania;
    public Towar()
    {
        this.cena = 0.0;
        this.nazwa = " ";
        this.dataWydania = new GregorianCalendar(2000,1,1).getTime();

    }
    public Towar(double cena, String nazwa) {
        this();
        this.cena = cena;
        this.nazwa = nazwa;
    }
    public Towar(double cena, String nazwa, int year, int month, int day) {
        this(cena, nazwa);
        GregorianCalendar calendar = new GregorianCalendar(year,month,day);
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

    public static  void writeToFile(Towar[] towars, DataOutput outS) throws IOException {

        for (int i=0;i< towars.length;i++) towars[i].zapiszDane(outS);
    }
    public  static Towar[] readFromFile(RandomAccessFile RAF) throws IOException
    {
        int ileRekordow = (int)(RAF.length()/Towar.DLUGOSC_REKORDU);
        Towar[] towar = new Towar[ileRekordow];

        for(int i=0;i<ileRekordow;i++)
        {
            towar[i] = new Towar();
            towar[i].czytajDane(RAF);
        }
        return towar;
    }

    public void zapiszDane(DataOutput outS) throws IOException {
        outS.writeDouble(this.cena);

        StringBuffer stringB = new StringBuffer(DLUGOSC_NAZWY);
        stringB.append(this.nazwa);
        stringB.setLength(DLUGOSC_NAZWY);
        outS.writeChars(stringB.toString());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(this.dataWydania);

        outS.writeInt(calendar.get(Calendar.YEAR));
        outS.writeInt(calendar.get(Calendar.MONTH));
        outS.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
    }
    public void czytajDane(DataInput inS) throws IOException {

        this.cena = inS.readDouble();

        StringBuffer tstring = new StringBuffer(Towar.DLUGOSC_NAZWY);
        for (int i=0;i<Towar.DLUGOSC_NAZWY;i++)
        {
            char tCh = inS.readChar();
            if(tCh != '\0')
                tstring.append(tCh);
        }
        this.nazwa = tstring.toString();

        int rok = inS.readInt();
        int miesiac = inS.readInt();
        int day = inS.readInt();

        GregorianCalendar calendar = new GregorianCalendar(rok,miesiac-1,day);
        this.dataWydania = calendar.getTime();

    }
    public void czytajRekord(RandomAccessFile RAF, int n) throws IOException, BrakRekordu {
        if(n <= RAF.length()/Towar.DLUGOSC_REKORDU) {
            RAF.seek((n - 1) * Towar.DLUGOSC_REKORDU);
            this.czytajDane(RAF);
        }
        else throw new BrakRekordu("Niestety, nie ma rekordu");
    }
    private void readObject(ObjectInputStream inS) throws IOException, ClassNotFoundException
    {
        inS.defaultReadObject();
        if(haslo != null)
            if (!haslo.equals("tajne"))
                throw new IOException("Dane są nieprawidłowe");
        System.out.println("z metody readObject");

    }
    private void writeObject(ObjectOutputStream outS) throws IOException
    {
        outS.defaultWriteObject();
    }

}
