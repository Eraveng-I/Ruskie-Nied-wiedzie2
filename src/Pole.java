public class Pole {

    int IloscLudzi;
    int IloscNiedzwiedzi;
    int IloscKawalerii;

    public  int WspX;
    public  int WspY;
    public int ID;

    int numerTerenu;
    double SpowolnienieNiedzwiedzia;
    double SpowolnienieCzlowieka;

    int IloscZwlokLudzi;
    int IloscZwlokNiedzwiedzi;

    public Pole(int WspX, int WspY){
        this.WspX = WspX;
        this.WspY = WspY;
        ID=WspY*Symulacja.SzerokoscMapy+WspX;
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

}
