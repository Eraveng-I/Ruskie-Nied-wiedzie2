public class Czlowiek extends Osobnik{

 //   int WspX = Pole.WspX;
  //  int WspY = Pole.WspY;
 public Czlowiek(int indeks){
     max_zdrowie=400;
     zdrowie=max_zdrowie;
     szybkosc_bazowa=4;
     this.rodzaj=1;
     this.indeks=indeks;
     this.PoziomSily = 40;
     this.pole=pole;

     this.szybkosc=szybkosc_bazowa;
     this.ZakresWidzenia=9;
 }


    int IloscZabitychNiedzwiedzi;

    public boolean Oswajanie(){
        boolean Oswojenie;
        return Oswojenie= generator.nextBoolean();
    }

    public void PozeranieNiedzwiedzia(Osobnik osobnik){
    }
}
