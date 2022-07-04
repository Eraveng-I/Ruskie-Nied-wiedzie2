
public class Pole {

    int IloscLudzi;
    int IloscNiedzwiedzi;   //Czy to potrzebne skoro mamy listy i mozemy wyciagnac ilosc :/ ?
    int IloscKawalerii;     //

    /*List<Czlowiek> LudzieNaPolu = new LinkedList<>();
    List<Niedzwiedz> NiedzwiedzieNaPolu = new LinkedList<>();
    List<Kawaleria> KawaleriaNaPolu = new LinkedList<>();*/

    public  int WspX;
    public  int WspY;
    public int ID;
  //  public final int ID_pola;
  //  private static int ile_juz_jest=0;

    int numerTerenu;
    double SpowolnienieNiedzwiedzia;
    double SpowolnienieCzlowieka;

    int IloscZwlokLudzi;
    int IloscZwlokNiedzwiedzi;

    public Pole(int WspX, int WspY){
        this.WspX = WspX;
        this.WspY = WspY;
        ID=WspY*Symulacja.SzerokoscMapy+WspX;
       // ile_juz_jest=ile_juz_jest+1;
        //ID_pola=ile_juz_jest;

    }

    public void ustawienie_szybkosci()
    {
        switch (numerTerenu)
        {
            case 1://miasto
                SpowolnienieCzlowieka=1.4;
                SpowolnienieNiedzwiedzia=0.6;
                break;

            case 2://wieś
                SpowolnienieCzlowieka=1.2;
                SpowolnienieNiedzwiedzia=0.8;
                break;

            case 3://łąka
                SpowolnienieCzlowieka=1;
                SpowolnienieNiedzwiedzia=1;
                break;

            case 4://las
                SpowolnienieCzlowieka=0.8;
                SpowolnienieNiedzwiedzia=1.2;
                break;

            case 5://góry
                SpowolnienieCzlowieka=0.6;
                SpowolnienieNiedzwiedzia=1.4;
                break;
        }
    }



    /*public void PrzypiszTeren(Pole pole){
        numerTerenu = Teren.numer;
    }

    public void PrzypiszLudzi(int ileLudzi, int ileMIASTO, int ileWIES){
        int q =ileLudzi/(ileMIASTO+ileWIES);

    }
    public void PrzypiszNiedzwiedzi(){}


    //public int IloscLudzi(List LudzieNaPolu){ return LudzieNaPolu.size(); }
    ///public int IloscNiedzwiedzi(List NiedzwiedzieNaPolu){ return NiedzwiedzieNaPolu.size(); }
    //public int IloscKawalerii(List KawaleriaNaPolu){ return KawaleriaNaPolu.size(); }*/
}
