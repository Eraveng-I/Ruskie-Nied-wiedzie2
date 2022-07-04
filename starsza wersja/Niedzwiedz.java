

public class Niedzwiedz extends Osobnik{

    public Niedzwiedz(int indeks)
    {
        max_zdrowie=600;
        zdrowie=max_zdrowie;
        szybkosc_bazowa=5;
        this.rodzaj=2;
        this.PoziomSily = 50;
        this.pole=pole;
        this.szybkosc=szybkosc_bazowa;
        this.ZakresWidzenia=7;

    }

    int IloscZabitychLudzi;
    boolean OswojenieSie;

    public boolean OswojenieSie(Osobnik osobnik){
        return true;
    }
    public void PozeranieCzlowieka(Osobnik osobnik){}
}
