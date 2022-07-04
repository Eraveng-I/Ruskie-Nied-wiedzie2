public class Kawaleria extends Osobnik{

    int IloscZabitychNiedzwiedzi;

    public Kawaleria(int indeks) {
        max_zdrowie = 1000;
        zdrowie = max_zdrowie;
        rodzaj=3;
        this.indeks = indeks;
        this.PoziomSily = 1000;
        this.pole = pole;
        this.szybkosc = 8;
        this.ZakresWidzenia = 15;

    }



    public void PozeranieNiedzwiedzia(Osobnik osobnik){}
    public void PozeranieCzlowieka(Osobnik osobnik){}
}

