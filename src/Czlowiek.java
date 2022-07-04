

import java.util.ArrayList;
import java.util.List;



public class Czlowiek extends Osobnik{

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

    public static void Oswajanie(Osobnik czlowiek, List<Osobnik> NiedzwiedzieObok){

        Osobnik misiek = NiedzwiedzieObok.get(Symulacja.Losowanie(NiedzwiedzieObok.size()));
        Symulacja.ileKawaleri=Symulacja.WszyscyKawalerzysci.size();

        //WszyscyKawalerzysci.clear();
        Kawaleria nowyKawalerzysta = new Kawaleria(Symulacja.ileKawaleri+1);
        Symulacja.osobnicy.add(nowyKawalerzysta);
        int index = Symulacja.osobnicy.indexOf(czlowiek);
        Symulacja.osobnicy.set(index, nowyKawalerzysta); //podmiana czlowieka na kawalerzyste

        Symulacja.WszyscyKawalerzysci.add(nowyKawalerzysta);

        Symulacja.TablicaPol[czlowiek.aktualneWspY][czlowiek.aktualneWspX].IloscLudzi--;
        Symulacja.TablicaPol[misiek.aktualneWspY][misiek.aktualneWspX].IloscNiedzwiedzi--; //usuwanie niedzwiedzia i czlowieka z mapy (od tej pory nie widzac ich na mapie

        Symulacja.osobnicy.remove(misiek); Symulacja.osobnicy.remove(czlowiek);  //usuniecie obiektow z listy, od tej pory nie beda nigdzie uwzgledniani
        List<Pole> PolaDlaKawalerii = new ArrayList<>();
        for(int p=0; p<Symulacja.DlugoscMapy; p++){
            for(int q=0; q<Symulacja.SzerokoscMapy; q++){
                //wzorek:(x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)
                if((q-czlowiek.aktualneWspX)*(q-czlowiek.aktualneWspX)+(p- czlowiek.aktualneWspY)*(p- czlowiek.aktualneWspY)<=9){
                    PolaDlaKawalerii.add(Symulacja.TablicaPol[p][q]);
                }
            }
        }

        for (int m = 0; m < PolaDlaKawalerii.size(); m++)   //posortowanie ktore z pol w zasiegu ruchu jest najblizej
        {
            for (int n = 0; n < PolaDlaKawalerii.size()-m-1; n++)
            {
                if ((PolaDlaKawalerii.get(n).WspX - czlowiek.aktualneWspX)*(PolaDlaKawalerii.get(n).WspX - czlowiek.aktualneWspX) + (PolaDlaKawalerii.get(n).WspY - czlowiek.aktualneWspY)*(PolaDlaKawalerii.get(n).WspY - czlowiek.aktualneWspY) > (PolaDlaKawalerii.get(n+1).WspX - czlowiek.aktualneWspX)*(PolaDlaKawalerii.get(n+1).WspX - czlowiek.aktualneWspX) + (PolaDlaKawalerii.get(n+1).WspY - czlowiek.aktualneWspY)*(PolaDlaKawalerii.get(n+1).WspY - czlowiek.aktualneWspY))
                {
                    Pole zamiennik = PolaDlaKawalerii.get(n);
                    PolaDlaKawalerii.set(n , PolaDlaKawalerii.get(n+1));
                    PolaDlaKawalerii.set(n+1 , zamiennik);
                }
            }
        }

        int WspX_docelowe = -5;
        int WspY_docelowe = -5;

        for(int m=0;m<PolaDlaKawalerii.size();m++)     //sprawdzenie czy nie zajete pole przez inny rodzaj
        {
            if (Symulacja.TablicaPol[PolaDlaKawalerii.get(m).WspY][PolaDlaKawalerii.get(m).WspX].IloscLudzi == 0 && Symulacja.TablicaPol[PolaDlaKawalerii.get(m).WspY][PolaDlaKawalerii.get(m).WspX].IloscNiedzwiedzi == 0)
            {
                WspX_docelowe = PolaDlaKawalerii.get(m).WspX;
                WspY_docelowe = PolaDlaKawalerii.get(m).WspY;
                break;
            }
        }
        Symulacja.TablicaPol[WspY_docelowe][WspX_docelowe].IloscKawalerii++;
        nowyKawalerzysta.aktualneWspX=WspX_docelowe;
        nowyKawalerzysta.aktualneWspY=WspY_docelowe;


    }
}
