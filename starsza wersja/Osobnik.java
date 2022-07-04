
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static java.lang.Math.sqrt;

abstract public class Osobnik {

    Random generator = new Random();

    int max_zdrowie;
    int rodzaj;
    public int indeks;
    int PoziomSily;
    int zdrowie;
    int wiek;
    int szybkosc_bazowa;
    int szybkosc;
    int aktualneWspX;
    int aktualneWspY;
    Pole pole;
    int ZakresWidzenia;

    public static void Atak(Osobnik agresor, List<Osobnik> wrogowie)    //atak kompletny
    {

        //szukanie najblizszego wroga wsrod listy wrogow w polu widzenia

        List <Osobnik> WrogowieWZasieguRuchu = new ArrayList<>();
        for(int j=0;j<wrogowie.size();j++)   //ustalenie ile pol jest w zasiegu ruchu
        {
            if((wrogowie.get(j).aktualneWspX - agresor.aktualneWspX)*(wrogowie.get(j).aktualneWspX - agresor.aktualneWspX) + (wrogowie.get(j).aktualneWspY - agresor.aktualneWspY)*(wrogowie.get(j).aktualneWspY - agresor.aktualneWspX) <= (agresor.szybkosc +1)*(agresor.szybkosc +1))
            {//wzorek:(x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)

                WrogowieWZasieguRuchu.add(wrogowie.get(j));
            }
        }

        if(WrogowieWZasieguRuchu.size()>0)   //czy są wrogowie w zasięgu ruchu?
        {
            for (int m = 0; m < WrogowieWZasieguRuchu.size(); m++)   //posortowanie ktore z pol w zasiegu ruchu jest najblizej
            {
                for (int n = 0; n < WrogowieWZasieguRuchu.size()-m-1; n++)
                {
                    if ((WrogowieWZasieguRuchu.get(n).aktualneWspX - agresor.aktualneWspX)*(WrogowieWZasieguRuchu.get(n).aktualneWspX - agresor.aktualneWspX) + (WrogowieWZasieguRuchu.get(n).aktualneWspY - agresor.aktualneWspY)*(WrogowieWZasieguRuchu.get(n).aktualneWspY - agresor.aktualneWspY) > (WrogowieWZasieguRuchu.get(n+1).aktualneWspX - agresor.aktualneWspX)*(WrogowieWZasieguRuchu.get(n+1).aktualneWspX - agresor.aktualneWspX) + (WrogowieWZasieguRuchu.get(n+1).aktualneWspY - agresor.aktualneWspY)*(WrogowieWZasieguRuchu.get(n+1).aktualneWspY - agresor.aktualneWspY))
                    {
                        Osobnik zamiennik = WrogowieWZasieguRuchu.get(n);
                        WrogowieWZasieguRuchu.set(n , WrogowieWZasieguRuchu.get(n+1));
                        WrogowieWZasieguRuchu.set(n+1 , zamiennik);
                    }
                }
            }

            int WspX_wroga=WrogowieWZasieguRuchu.get(0).aktualneWspX;
            int WspY_wroga=WrogowieWZasieguRuchu.get(0).aktualneWspY;
            List <Pole> Otoczenie_wroga = new ArrayList<>();

            for (int m = WspY_wroga-1; m <= WspY_wroga+1; m++) //dodanie otoczenia wroga
            {
                for (int n = WspX_wroga-1; n <= WspX_wroga+1; n++)
                {
                    if(n>=0 && n< Symulacja.SzerokoscMapy && m>=0 && m< Symulacja.DlugoscMapy && !(n==WspX_wroga && m==WspY_wroga))
                    {
                        Otoczenie_wroga.add(Symulacja.TablicaPol[m][n]);
                    }
                }
            }

            for (int m = 0; m < Otoczenie_wroga.size(); m++)   //posortowanie otoczenia wroga
            {
                for (int n = 0; n < Otoczenie_wroga.size()-m-1; n++)
                {
                    if ((Otoczenie_wroga.get(n).WspX - agresor.aktualneWspX)*(Otoczenie_wroga.get(n).WspX - agresor.aktualneWspX) + (Otoczenie_wroga.get(n).WspY - agresor.aktualneWspY)*(Otoczenie_wroga.get(n).WspY - agresor.aktualneWspY) > (Otoczenie_wroga.get(n+1).WspX - agresor.aktualneWspX)*(Otoczenie_wroga.get(n+1).WspX - agresor.aktualneWspX) + (Otoczenie_wroga.get(n+1).WspY - agresor.aktualneWspY)*(Otoczenie_wroga.get(n+1).WspY - agresor.aktualneWspY))
                    {
                        Pole zamiennik = Otoczenie_wroga.get(n);
                        Otoczenie_wroga.set(n , Otoczenie_wroga.get(n+1));
                        Otoczenie_wroga.set(n+1 , zamiennik);
                    }
                }
            }

            int WspX_docelowe = -5;
            int WspY_docelowe = -5;

            for(int m=0;m<Otoczenie_wroga.size();m++)     //sprawdzenie czy nie zajete pole przez inny rodzaj
            {
                if (agresor.rodzaj==1)
                {
                    if (Symulacja.TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscNiedzwiedzi == 0 && Symulacja.TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscKawalerii == 0)
                    {
                        WspX_docelowe = Otoczenie_wroga.get(m).WspX;
                        WspY_docelowe = Otoczenie_wroga.get(m).WspY;
                        break;
                    }
                }

                if (agresor.rodzaj==2)
                {
                    if (Symulacja.TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscLudzi == 0 && Symulacja.TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscKawalerii == 0)
                    {
                        WspX_docelowe = Otoczenie_wroga.get(m).WspX;
                        WspY_docelowe = Otoczenie_wroga.get(m).WspY;
                        break;
                    }
                }

                if (agresor.rodzaj==3)
                {
                    if (Symulacja.TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscLudzi == 0 && Symulacja.TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscNiedzwiedzi == 0)
                    {
                        WspX_docelowe = Otoczenie_wroga.get(m).WspX;
                        WspY_docelowe = Otoczenie_wroga.get(m).WspY;
                        break;
                    }
                }
            }

            //TUTAJ ODPALENIE FUNKCJI PORUSZANIA
            Ruch_na_konkretne_pole(agresor, Symulacja.TablicaPol[WspY_docelowe][WspX_docelowe]);

            WrogowieWZasieguRuchu.get(0).zdrowie-=agresor.PoziomSily;


            if(WrogowieWZasieguRuchu.get(0).zdrowie<=0)
            {
                if(WrogowieWZasieguRuchu.get(0).rodzaj==1){
                    Symulacja.TablicaPol[WrogowieWZasieguRuchu.get(0).aktualneWspY][WrogowieWZasieguRuchu.get(0).aktualneWspX].IloscLudzi--;
                }else if(WrogowieWZasieguRuchu.get(0).rodzaj==2){
                    Symulacja.TablicaPol[WrogowieWZasieguRuchu.get(0).aktualneWspY][WrogowieWZasieguRuchu.get(0).aktualneWspX].IloscNiedzwiedzi--;
                }else if(WrogowieWZasieguRuchu.get(0).rodzaj==3){
                    Symulacja.TablicaPol[WrogowieWZasieguRuchu.get(0).aktualneWspY][WrogowieWZasieguRuchu.get(0).aktualneWspX].IloscKawalerii--;
                }
                Symulacja.osobnicy.remove(WrogowieWZasieguRuchu.get(0));
                if( Symulacja.osobnicy.indexOf(WrogowieWZasieguRuchu.get(0)) <= Symulacja.porownajnik)
                {
                    Symulacja.porownajnik=Symulacja.porownajnik-1;
                }
            }

        }
        else
            {
                for (int m = 0; m < wrogowie.size(); m++)   //posortowanie wrogow w zasiegu wzroku
                {
                    for (int n = 0; n < wrogowie.size()-m-1; n++)
                    {
                        if ((wrogowie.get(n).aktualneWspX - agresor.aktualneWspX)*(wrogowie.get(n).aktualneWspX - agresor.aktualneWspX) + (wrogowie.get(n).aktualneWspY - agresor.aktualneWspY)*(wrogowie.get(n).aktualneWspY - agresor.aktualneWspY) > (wrogowie.get(n+1).aktualneWspX - agresor.aktualneWspX)*(wrogowie.get(n+1).aktualneWspX - agresor.aktualneWspX) + (wrogowie.get(n+1).aktualneWspY - agresor.aktualneWspY)*(wrogowie.get(n+1).aktualneWspY - agresor.aktualneWspY))
                        {
                            Osobnik zamiennik = wrogowie.get(n);
                            wrogowie.set(n , wrogowie.get(n+1));
                            wrogowie.set(n+1 , zamiennik);
                        }
                    }
                }

                int WspX_najblizszego_wroga=wrogowie.get(0).aktualneWspX;
                int WspY_najblizszego_wroga=wrogowie.get(0).aktualneWspY;
                List <Pole> Obszar_Ruchu = new ArrayList<>();

                for(int y=agresor.aktualneWspY-agresor.szybkosc;y<=agresor.aktualneWspY+agresor.szybkosc;y++)   //budowa obszaru ruchu
                {
                    for(int x=agresor.aktualneWspX - (int) Math.floor(Math.sqrt(agresor.szybkosc*agresor.szybkosc-(y-agresor.aktualneWspY)*(y-agresor.aktualneWspY)));x<=agresor.aktualneWspX + (int) Math.floor(Math.sqrt(agresor.szybkosc*agresor.szybkosc-(y-agresor.aktualneWspY)*(y-agresor.aktualneWspY)));x++)
                    {
                        if(x>=0 && x<Symulacja.SzerokoscMapy && y>=0 && y<Symulacja.DlugoscMapy)
                        {
                            Obszar_Ruchu.add(Symulacja.TablicaPol[y][x]);
                        }
                    }
                }

                for (int m = 0; m < Obszar_Ruchu.size(); m++)   //posortowanie obszar ruchu do wroga
                {
                    for (int n = 0; n < Obszar_Ruchu.size()-m-1; n++)
                    {
                        if ((Obszar_Ruchu.get(n).WspX - WspX_najblizszego_wroga)*(Obszar_Ruchu.get(n).WspX - WspX_najblizszego_wroga) + (Obszar_Ruchu.get(n).WspY - WspY_najblizszego_wroga)*(Obszar_Ruchu.get(n).WspY - WspY_najblizszego_wroga) > (Obszar_Ruchu.get(n+1).WspX - WspX_najblizszego_wroga)*(Obszar_Ruchu.get(n+1).WspX - WspX_najblizszego_wroga) + (Obszar_Ruchu.get(n+1).WspY - WspY_najblizszego_wroga)*(Obszar_Ruchu.get(n+1).WspY - WspY_najblizszego_wroga))
                        {
                            Pole zamiennik = Obszar_Ruchu.get(n);
                            Obszar_Ruchu.set(n , Obszar_Ruchu.get(n+1));
                            Obszar_Ruchu.set(n+1 , zamiennik);
                        }
                    }
                }

                int WspX_docelowe = -4;
                int WspY_docelowe = -4;

                for(int m=0;m<Obszar_Ruchu.size();m++)     //sprawdzenie czy nie zajete pole przez inny rodzaj
                {
                    if (agresor.rodzaj==1)
                    {
                        if (Symulacja.TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscNiedzwiedzi == 0 && Symulacja.TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscKawalerii == 0)
                        {
                            WspX_docelowe = Obszar_Ruchu.get(m).WspX;
                            WspY_docelowe = Obszar_Ruchu.get(m).WspY;
                            break;
                        }
                    }

                    if (agresor.rodzaj==2)
                    {
                        if (Symulacja.TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscLudzi == 0 && Symulacja.TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscKawalerii == 0)
                        {
                            WspX_docelowe = Obszar_Ruchu.get(m).WspX;
                            WspY_docelowe = Obszar_Ruchu.get(m).WspY;
                            break;
                        }
                    }

                    if (agresor.rodzaj==3)
                    {
                        if (Symulacja.TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscLudzi == 0 && Symulacja.TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscNiedzwiedzi == 0)
                        {
                            WspX_docelowe = Obszar_Ruchu.get(m).WspX;
                            WspY_docelowe = Obszar_Ruchu.get(m).WspY;
                            break;
                        }
                    }
                }

                //TUTAJ ODPALENIE FUNKCJI PORUSZANIA
                Osobnik.Ruch_na_konkretne_pole(agresor, Symulacja.TablicaPol[WspY_docelowe][WspX_docelowe]);
            }

    }


    public void PrzyjmijAtak(Osobnik osobnik){}
    public void ZmniejszZdrowie(Osobnik osobnik){}
    public void ZwiekszZdrowie(Osobnik osobnik){}

    public static void Ruch_na_konkretne_pole(Osobnik osobnik, Pole konkretne_pole){
        int p=osobnik.aktualneWspX;
        int q=osobnik.aktualneWspY;

        osobnik.aktualneWspX= konkretne_pole.WspX;
        osobnik.aktualneWspY=konkretne_pole.WspY;

        if (osobnik.rodzaj == 1) {
            Symulacja.TablicaPol[q][p].IloscLudzi--;
            konkretne_pole.IloscLudzi++;
        } else if (osobnik.rodzaj == 2) {
            Symulacja.TablicaPol[q][p].IloscNiedzwiedzi--;
            konkretne_pole.IloscNiedzwiedzi++;
        } else if (osobnik.rodzaj == 3) {
            Symulacja.TablicaPol[q][p].IloscKawalerii--;
            konkretne_pole.IloscKawalerii++;
        }
    }

    // public void IleWidziWrogow(Osobnik osobnik){ }

    //public int IleWrogowJestBlisko(){ return IleJestBlisko; }

    //public boolean CzyJestMartwyWrog(int rodzaj){ return TakCzyNie; }

    //public int IleWidziSojusznikow(){ return IloscSojusznikow; }

    //public boolean WykonajOswojenie(){ return TakCzyNie; }



}

