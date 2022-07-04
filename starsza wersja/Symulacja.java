
import java.util.*;
import java.lang.Math;

//import static java.lang.Math.
import static java.lang.Math.*;

public class Symulacja {
    //Wielkosc mapy
    int ilePOL;
    int ileSkupiskoDanegoTerenu;
    public static int DlugoscMapy;
    public static int SzerokoscMapy;
    public static int porownajnik;

    public static Pole[][] TablicaPol;

    //Ilosc pol dla roznych terenow
    static int ileWIES;
    static int ileLAS;
    static int ileMIASTO;
    static int ileGORY;
    static int ileLAKA;

    int maxCzas;

    //Ilosci osobnikow
    public static int ileKawaleri;
    public static int ileLudzi;
    public static int ileNiedzwiedzi;

    //tablice

    static List<Osobnik> osobnicy = new ArrayList<>();
    static List<Czlowiek> WszyscyLudzie = new ArrayList<>();
    static List<Niedzwiedz> WszystkieNiedzwiedzie = new ArrayList<>();
    static List<Kawaleria> WszyscyKawalerzysci = new ArrayList<>();

    //metody

    public static void PodajPARAMETRY()
    {
        System.out.println("Witaj w symulacji! ");

        Scanner scanner=new Scanner(System.in);

        while(true)     //DlugoscMapy
        {
            System.out.print("Podaj dlugosc mapy: ");
            DlugoscMapy=scanner.nextInt();

            if (DlugoscMapy > 0)
            {
                break;
            }
            System.out.println("Podaj dodatnią długość mapy bo jestem idiotoodporna ~ symulacja");
        }

        while(true)     //SzerokoscMapy
        {
            System.out.print("Podaj szerokosc mapy: ");
            SzerokoscMapy=scanner.nextInt();

            if (SzerokoscMapy > 0)
            {
                break;
            }
            System.out.println("Podaj dodatnią szerokość mapy bo jestem idiotoodporna ~ symulacja");
        }

        while(true)     //ileNiedzwiedzi
        {
            System.out.print("Podaj ilość niedźwiedzi:  ");
            ileNiedzwiedzi=scanner.nextInt();

            if (ileNiedzwiedzi > 0)
            {
                break;
            }
            System.out.println("Podaj dodatnią ilość niedźwiedzi bo jestem idiotoodporna ~ symulacja");
        }

        while(true)     //ileLudzi
        {
            System.out.print("Podaj ilość ludzi:  ");
            ileLudzi=scanner.nextInt();

            if (ileLudzi > 0)
            {
                break;
            }
            System.out.println("Podaj dodatnią ilość ludzi bo jestem idiotoodporna ~ symulacja");
        }
    }

    public static void start(int DlugoscMapy, int SzerokoscMapy)
    {
        int ilePOL=DlugoscMapy*SzerokoscMapy;
        //Generowanie mapy
        TablicaPol = new Pole[DlugoscMapy][SzerokoscMapy];
        for(int i=0; i<DlugoscMapy; i++)
        {
            for(int j=0; j<SzerokoscMapy; j++)
            {
                TablicaPol[i][j]=new Pole(j,i);
                TablicaPol[i][j].numerTerenu=0;
            }
        }
    }

    public static void UstawTeren(int DlugoscMapy, int SzerokoscMapy)
    {
        Scanner scanner=new Scanner(System.in);
        int p;
        int ileSkupiskGORY=0;
        int ileSkupiskLAS=0;
        int ileSkupiskWIES=0;
        int ileSkupiskMIASTO=0;

        while (true)
        {
            while (true)
            {
                System.out.println("Ile % terenu ma być miastami?"); //x4 dla kazdego terenu poza laka
                p = scanner.nextInt();
                ileMIASTO = p * DlugoscMapy * SzerokoscMapy / 100;

                if(p>=0 && p<=100)
                {
                    break;
                }

                System.out.println("Podaj p w zakresie od 0 do 100 bo jestem idiotoodporna ~ symulacja");
            }

            while (true)
            {
                System.out.println("Ile % terenu ma być wsiami?"); //x4 dla kazdego terenu poza laka
                p = scanner.nextInt();
                ileWIES = p * DlugoscMapy * SzerokoscMapy / 100;

                if(p>=0 && p<=100)
                {
                    break;
                }

                System.out.println("Podaj p w zakresie od 0 do 100 bo jestem idiotoodporna ~ symulacja");
            }

            while (true)
            {
                System.out.println("Ile % terenu ma być lasem?"); //x4 dla kazdego terenu poza laka
                p = scanner.nextInt();
                ileLAS = p * DlugoscMapy * SzerokoscMapy / 100;

                if(p>=0 && p<=100)
                {
                    break;
                }

                System.out.println("Podaj p w zakresie od 0 do 100 bo jestem idiotoodporna ~ symulacja");
            }

            while (true)
            {
                System.out.println("Ile % terenu ma być gorami?"); //x4 dla kazdego terenu poza laka
                p = scanner.nextInt();
                ileGORY = p * DlugoscMapy * SzerokoscMapy / 100;

                if(p>=0 && p<=100)
                {
                    break;
                }

                System.out.println("Podaj p w zakresie od 0 do 100 bo jestem idiotoodporna ~ symulacja");
            }

            ileLAKA = DlugoscMapy * SzerokoscMapy - ileGORY - ileLAS - ileWIES - ileMIASTO;

            if(ileLAKA>=0 && ileLAKA<=DlugoscMapy*SzerokoscMapy)
            {
                break;
            }

            System.out.println("Podaj poprawne dane bo jestem idiotoodporna ~ symulacja");
        }

        System.out.println("Podaj ilosc skupisk kazdego terenu");

        while(true)     //GORY
        {
            System.out.print("Podaj ilosc skupisk gor: ");
            ileSkupiskGORY = scanner.nextInt();

            if (!(ileGORY > 0 && ileSkupiskGORY <= 0))
            {
                break;
            }
            System.out.println("Podaj dodatnią ilość skupisk bo jestem idiotoodporna ~ symulacja");
        }

        while(true)     //LASY
        {
            System.out.print("Podaj ilosc skupisk lasu: ");
            ileSkupiskLAS=scanner.nextInt();

            if (!(ileLAS > 0 && ileSkupiskLAS <= 0))
            {
                break;
            }
            System.out.println("Podaj dodatnią ilość skupisk bo jestem idiotoodporna ~ symulacja");
        }

        while(true)     //WSIE
        {
            System.out.print("Podaj ilosc skupisk wsi: ");
            ileSkupiskWIES = scanner.nextInt();

            if (!(ileWIES > 0 && ileSkupiskWIES <= 0)) {
                break;
            }
            System.out.println("Podaj dodatnią ilość skupisk bo jestem idiotoodporna ~ symulacja");
        }

        while(true)     //MIASTA
        {
            System.out.print("Podaj ilosc skupisk miasta: ");
            ileSkupiskMIASTO=scanner.nextInt();

            if (!(ileMIASTO > 0 && ileSkupiskMIASTO <= 0)) {
                break;
            }
            System.out.println("Podaj dodatnią ilość skupisk bo jestem idiotoodporna ~ symulacja");
        }

        //losowanie przydzialu terenow

        for(int skupisko=1;skupisko<=ileSkupiskGORY;skupisko++)//przypisanie GORY
        {
            Random losownik = new Random();
            int centrum_x = losownik.nextInt(SzerokoscMapy);
            int centrum_y = losownik.nextInt(DlugoscMapy);
            int wielkosc_tego_skupiska=ileGORY/ileSkupiskGORY;

            if(skupisko<=ileGORY-ileSkupiskGORY*wielkosc_tego_skupiska)      //liczymy ile trzeba dopelnic i tyle skupisk na poczatku sie dopelni
            {
                wielkosc_tego_skupiska=wielkosc_tego_skupiska+1;
            }

            int licznik_pol = 0;
            int promien =0;

            //liczymy maksymalna potrzebna ilosc pol przy danym promieniu
            for(promien =0; ;promien++)
            {
                licznik_pol = 0;
                for(int y=centrum_y-promien;y<=centrum_y+promien;y++)
                {
                    for(int x= (int) (centrum_x -  Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)))) ; x<= (int) (centrum_x +  Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)))) ;x++)
                    {
                        if((x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)
                        {
                            if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                            {//tu dodac warunek nie wpierdalac terenu-juz jest
                                if(TablicaPol[y][x].numerTerenu==0)
                                {
                                    licznik_pol=licznik_pol+1;
                                }
                            }
                        }
                    }
                }

                if(licznik_pol>=wielkosc_tego_skupiska)
                {
                    break;
                }
            }

            Pole[] max_plama = new Pole[licznik_pol];
            int m=0;

            //zapis pol do tablicy do posortowania
            for(int y=centrum_y-promien;y<=centrum_y+promien;y++)
            {
                for(int x=centrum_x - (int) Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)));x<=centrum_x + (int) Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)));x++)
                {
                    if((x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)
                    {
                        if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                        {//tu dodac warunek nie wpierdalac terenu-juz jest
                            if(TablicaPol[y][x].numerTerenu==0)
                            {
                                max_plama[m]=TablicaPol[y][x];
                                m=m+1;
                            }
                        }
                    }
                }
            }

            //posortowanie
            for (int i = 0; i < max_plama.length; i++)
            {
                for (int j = 0; j < max_plama.length-i-1; j++)
                {
                    if ((max_plama[j].WspX -centrum_x)*(max_plama[j].WspX-centrum_x)+(max_plama[j].WspY-centrum_y)*(max_plama[j].WspY-centrum_y) > (max_plama[j+1].WspX -centrum_x)*(max_plama[j+1].WspX-centrum_x)+(max_plama[j+1].WspY-centrum_y)*(max_plama[j+1].WspY-centrum_y))
                    {
                        Pole zamiennik = max_plama[j];
                        max_plama[j] = max_plama[j + 1];
                        max_plama[j + 1] = zamiennik;
                    }
                }
            }

            for(int i = 0; i<wielkosc_tego_skupiska;i++)
            {
                TablicaPol[max_plama[i].WspY][max_plama[i].WspX].numerTerenu=5;     //dla terenu GORY
            }
        }

        for(int skupisko=1;skupisko<=ileSkupiskLAS;skupisko++)//przypisanie LAS
        {
            Random losownik = new Random();
            int centrum_x = losownik.nextInt(SzerokoscMapy);
            int centrum_y = losownik.nextInt(DlugoscMapy);
            int wielkosc_tego_skupiska=ileLAS/ileSkupiskLAS;

            if(skupisko<=ileLAS-ileSkupiskLAS*wielkosc_tego_skupiska)      //liczymy ile trzeba dopelnic i tyle skupisk na poczatku sie dopelni
            {
                wielkosc_tego_skupiska=wielkosc_tego_skupiska+1;
            }

            int licznik_pol = 0;
            int promien =0;

            //liczymy maksymalna potrzebna ilosc pol przy danym promieniu
            for(promien =0; ;promien++)
            {
                licznik_pol = 0;
                for(int y=centrum_y-promien;y<=centrum_y+promien;y++)
                {
                    for(int x= (int) (centrum_x -  Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)))) ; x<= (int) (centrum_x +  Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)))) ;x++)
                    {
                        if((x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)
                        {
                            if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                            {//tu dodac warunek nie wpierdalac terenu-juz jest
                                if(TablicaPol[y][x].numerTerenu==0)
                                {
                                    licznik_pol=licznik_pol+1;
                                }
                            }
                        }
                    }
                }

                if(licznik_pol>=wielkosc_tego_skupiska)
                {
                    break;
                }
            }

            Pole[] max_plama = new Pole[licznik_pol];
            int m=0;

            //zapis pol do tablicy do posortowania
            for(int y=centrum_y-promien;y<=centrum_y+promien;y++)
            {
                for(int x=centrum_x - (int) Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)));x<=centrum_x + (int) Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)));x++)
                {
                    if((x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)
                    {
                        if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                        {//tu dodac warunek nie wpierdalac terenu-juz jest
                            if(TablicaPol[y][x].numerTerenu==0)
                            {
                                max_plama[m]=TablicaPol[y][x];
                                m=m+1;
                            }
                        }
                    }
                }
            }

            //posortowanie
            for (int i = 0; i < max_plama.length; i++)
            {
                for (int j = 0; j < max_plama.length-i-1; j++)
                {
                    if ((max_plama[j].WspX -centrum_x)*(max_plama[j].WspX-centrum_x)+(max_plama[j].WspY-centrum_y)*(max_plama[j].WspY-centrum_y) > (max_plama[j+1].WspX -centrum_x)*(max_plama[j+1].WspX-centrum_x)+(max_plama[j+1].WspY-centrum_y)*(max_plama[j+1].WspY-centrum_y))
                    {
                        Pole zamiennik = max_plama[j];
                        max_plama[j] = max_plama[j + 1];
                        max_plama[j + 1] = zamiennik;
                    }
                }
            }

            for(int i = 0; i<wielkosc_tego_skupiska;i++)
            {
                TablicaPol[max_plama[i].WspY][max_plama[i].WspX].numerTerenu=4;     //dla terenu WIES
            }
        }

        for(int skupisko=1;skupisko<=ileSkupiskWIES;skupisko++)//przypisanie WIES
        {
            Random losownik = new Random();
            int centrum_x = losownik.nextInt(SzerokoscMapy);
            int centrum_y = losownik.nextInt(DlugoscMapy);
            int wielkosc_tego_skupiska=ileWIES/ileSkupiskWIES;

            if(skupisko<=ileWIES-ileSkupiskWIES*wielkosc_tego_skupiska)      //liczymy ile trzeba dopelnic i tyle skupisk na poczatku sie dopelni
            {
                wielkosc_tego_skupiska=wielkosc_tego_skupiska+1;
            }

            int licznik_pol = 0;
            int promien =0;

            //liczymy maksymalna potrzebna ilosc pol przy danym promieniu
            for(promien =0; ;promien++)
            {
                licznik_pol = 0;
                for(int y=centrum_y-promien;y<=centrum_y+promien;y++)
                {
                    for(int x= (int) (centrum_x -  Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)))) ; x<= (int) (centrum_x +  Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)))) ;x++)
                    {
                        if((x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)
                        {
                            if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                            {//tu dodac warunek nie wpierdalac terenu-juz jest
                                if(TablicaPol[y][x].numerTerenu==0)
                                {
                                    licznik_pol=licznik_pol+1;
                                }
                            }
                        }
                    }
                }

                if(licznik_pol>=wielkosc_tego_skupiska)
                {
                    break;
                }
            }

            Pole[] max_plama = new Pole[licznik_pol];
            int m=0;

            //zapis pol do tablicy do posortowania
            for(int y=centrum_y-promien;y<=centrum_y+promien;y++)
            {
                for(int x=centrum_x - (int) Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)));x<=centrum_x + (int) Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)));x++)
                {
                    if((x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)
                    {
                        if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                        {//tu dodac warunek nie wpierdalac terenu-juz jest
                            if(TablicaPol[y][x].numerTerenu==0)
                            {
                                max_plama[m]=TablicaPol[y][x];
                                m=m+1;
                            }
                        }
                    }
                }
            }

            //posortowanie
            for (int i = 0; i < max_plama.length; i++)
            {
                for (int j = 0; j < max_plama.length-i-1; j++)
                {
                    if ((max_plama[j].WspX -centrum_x)*(max_plama[j].WspX-centrum_x)+(max_plama[j].WspY-centrum_y)*(max_plama[j].WspY-centrum_y) > (max_plama[j+1].WspX -centrum_x)*(max_plama[j+1].WspX-centrum_x)+(max_plama[j+1].WspY-centrum_y)*(max_plama[j+1].WspY-centrum_y))
                    {
                        Pole zamiennik = max_plama[j];
                        max_plama[j] = max_plama[j + 1];
                        max_plama[j + 1] = zamiennik;
                    }
                }
            }

            for(int i = 0; i<wielkosc_tego_skupiska;i++)
            {
                TablicaPol[max_plama[i].WspY][max_plama[i].WspX].numerTerenu=2;     //dla terenu WIES
            }
        }

        for(int skupisko=1;skupisko<=ileSkupiskMIASTO;skupisko++)//przypisanie MIASTO
        {
            Random losownik = new Random();
            int centrum_x = losownik.nextInt(SzerokoscMapy);
            int centrum_y = losownik.nextInt(DlugoscMapy);
            int wielkosc_tego_skupiska=ileMIASTO/ileSkupiskMIASTO;

            if(skupisko<=ileMIASTO-ileSkupiskMIASTO*wielkosc_tego_skupiska)      //liczymy ile trzeba dopelnic i tyle skupisk na poczatku sie dopelni
            {
                wielkosc_tego_skupiska=wielkosc_tego_skupiska+1;
            }

            int licznik_pol = 0;
            int promien =0;

            //liczymy maksymalna potrzebna ilosc pol przy danym promieniu
            for(promien =0; ;promien++)
            {
                licznik_pol = 0;
                for(int y=centrum_y-promien;y<=centrum_y+promien;y++)
                {
                    for(int x= (int) (centrum_x -  Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)))) ; x<= (int) (centrum_x +  Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)))) ;x++)
                    {
                        if((x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)
                        {
                            if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                            {//tu dodac warunek nie wpierdalac terenu-juz jest
                                if(TablicaPol[y][x].numerTerenu==0)
                                {
                                    licznik_pol=licznik_pol+1;
                                }
                            }
                        }
                    }
                }

                if(licznik_pol>=wielkosc_tego_skupiska)
                {
                    break;
                }
            }

            Pole[] max_plama = new Pole[licznik_pol];
            int m=0;

            //zapis pol do tablicy do posortowania
            for(int y=centrum_y-promien;y<=centrum_y+promien;y++)
            {
                for(int x=centrum_x - (int) Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)));x<=centrum_x + (int) Math.floor(Math.sqrt(promien*promien-(y-centrum_y)*(y-centrum_y)));x++)
                {
                    if((x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)
                    {
                        if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                        {//tu dodac warunek nie wpierdalac terenu-juz jest
                            if(TablicaPol[y][x].numerTerenu==0)
                            {
                                max_plama[m]=TablicaPol[y][x];
                                m=m+1;
                            }
                        }
                    }
                }
            }

            //posortowanie
            for (int i = 0; i < max_plama.length; i++)
            {
                for (int j = 0; j < max_plama.length-i-1; j++)
                {
                    if ((max_plama[j].WspX -centrum_x)*(max_plama[j].WspX-centrum_x)+(max_plama[j].WspY-centrum_y)*(max_plama[j].WspY-centrum_y) > (max_plama[j+1].WspX -centrum_x)*(max_plama[j+1].WspX-centrum_x)+(max_plama[j+1].WspY-centrum_y)*(max_plama[j+1].WspY-centrum_y))
                    {
                        Pole zamiennik = max_plama[j];
                        max_plama[j] = max_plama[j + 1];
                        max_plama[j + 1] = zamiennik;
                    }
                }
            }

            for(int i = 0; i<wielkosc_tego_skupiska;i++)
            {
                TablicaPol[max_plama[i].WspY][max_plama[i].WspX].numerTerenu=1;     //dla terenu MIASTO
            }
        }

        for(int i=0; i<DlugoscMapy; i++)
        {
            for(int j=0; j<SzerokoscMapy; j++)
            {
                if(TablicaPol[i][j].numerTerenu==0)
                {
                    TablicaPol[i][j].numerTerenu=3;
                }
            }
        }

        for(int i=0; i<DlugoscMapy; i++)//ustawienie sybkosci osobnikow na kazdym polu
        {
            for(int j=0; j<SzerokoscMapy; j++)
            {
                TablicaPol[i][j].ustawienie_szybkosci();
            }
        }
    }

    public static void wypisanieTerenow()
    {
        //wypisanie mapy
        for(int i=0; i<DlugoscMapy; i++)
        {
            for(int j=0; j<SzerokoscMapy; j++)
            {
                System.out.print(" " + TablicaPol[i][j].numerTerenu + " ");
            }
            System. out. println( "" );
        }

        System.out.println("");
    }

    public static void UstawienieOsobnikow()
    {
        for(int i=0; i<ileLudzi; i++)
        {
            WszyscyLudzie.add(new Czlowiek(i));
        }

        for(int i=0; i<ileNiedzwiedzi; i++)
        {
            WszystkieNiedzwiedzie.add(new Niedzwiedz(i));
        }

        List<Pole> polaMiasta = new ArrayList<>(ileMIASTO);
        List<Pole> polaWsi = new ArrayList<>(ileWIES);
        List<Pole> polaLasu = new ArrayList<>(ileLAS);
        List<Pole> polaGor = new ArrayList<>(ileGORY);

        for(int i=0; i<DlugoscMapy; i++)    //segregacja nieneutralnych terenow
        {
            for(int j=0; j<SzerokoscMapy; j++)
            {
                switch (TablicaPol[i][j].numerTerenu)
                {
                    case 1:
                        polaMiasta.add(TablicaPol[i][j]);
                        break;

                    case 2:
                        polaWsi.add(TablicaPol[i][j]);
                        break;

                    case 4:
                        polaLasu.add(TablicaPol[i][j]);
                        break;

                    case 5:
                        polaGor.add(TablicaPol[i][j]);
                        break;
                }
            }
        }

        for(int ktorepole=0;ktorepole<polaMiasta.size()+polaWsi.size();ktorepole++)     //podział ludzi
        {
            int dawka=ileLudzi/(3*ileMIASTO+ileWIES);

            if(ktorepole<polaMiasta.size())     //najpierw przydzial do miasta
            {
                if(ktorepole<ileLudzi-(3*ileMIASTO+ileWIES)*dawka)      //liczymy ile trzeba dopelnic i tyle pol na poczatku sie dopelni
                {
                    TablicaPol[polaMiasta.get(ktorepole).WspY][polaMiasta.get(ktorepole).WspX].IloscLudzi = 3*dawka+1;
                    if(ileLudzi>ileMIASTO*(3*dawka+1)+ ktorepole+ileWIES*(dawka+1))
                    {
                        TablicaPol[polaMiasta.get(ktorepole).WspY][polaMiasta.get(ktorepole).WspX].IloscLudzi = 3*dawka+2;

                        if(ileLudzi>ileMIASTO*(3*dawka+2)+ ktorepole+ileWIES*(dawka+1))
                        {
                            TablicaPol[polaMiasta.get(ktorepole).WspY][polaMiasta.get(ktorepole).WspX].IloscLudzi = 3*dawka+3;
                        }
                    }
                }
                else
                {
                    TablicaPol[polaMiasta.get(ktorepole).WspY][polaMiasta.get(ktorepole).WspX].IloscLudzi = 3*dawka;
                }
            }
            else        //teraz przydzial do wsi
            {
                if(ktorepole<ileLudzi-(3*ileMIASTO+ileWIES)*dawka)      //liczymy ile trzeba dopelnic i tyle pol na poczatku sie dopelni
                {
                    TablicaPol[polaWsi.get(ktorepole-polaMiasta.size()).WspY][polaWsi.get(ktorepole-polaMiasta.size()).WspX].IloscLudzi = dawka+1;
                }
                else
                {
                    TablicaPol[polaWsi.get(ktorepole-polaMiasta.size()).WspY][polaWsi.get(ktorepole-polaMiasta.size()).WspX].IloscLudzi = dawka;
                }
            }
        }

        for(int ktorepole=0;ktorepole<polaGor.size()+polaLasu.size();ktorepole++)     //podział niedzwiedzi
        {
            int dawka=ileNiedzwiedzi/(3*ileGORY+ileLAS);

            if(ktorepole<polaGor.size())     //najpierw przydzial do miasta
            {
                if(ktorepole<ileNiedzwiedzi-(3*ileGORY+ileLAS)*dawka)      //liczymy ile trzeba dopelnic i tyle pol na poczatku sie dopelni
                {
                    TablicaPol[polaGor.get(ktorepole).WspY][polaGor.get(ktorepole).WspX].IloscNiedzwiedzi = 3*dawka+1;
                    if(ileNiedzwiedzi>ileGORY*(3*dawka+1)+ ktorepole+ileLAS*(dawka+1))
                    {
                        TablicaPol[polaGor.get(ktorepole).WspY][polaGor.get(ktorepole).WspX].IloscNiedzwiedzi = 3*dawka+2;

                        if(ileNiedzwiedzi>ileGORY*(3*dawka+2)+ ktorepole+ileLAS*(dawka+1))
                        {
                            TablicaPol[polaGor.get(ktorepole).WspY][polaGor.get(ktorepole).WspX].IloscNiedzwiedzi = 3*dawka+3;
                        }
                    }
                }
                else
                {
                    TablicaPol[polaGor.get(ktorepole).WspY][polaGor.get(ktorepole).WspX].IloscNiedzwiedzi = 3*dawka;
                }
            }
            else        //teraz przydzial do wsi
            {
                if(ktorepole<ileNiedzwiedzi-(3*ileGORY+ileLAS)*dawka)      //liczymy ile trzeba dopelnic i tyle pol na poczatku sie dopelni
                {
                    TablicaPol[polaLasu.get(ktorepole-polaGor.size()).WspY][polaLasu.get(ktorepole-polaGor.size()).WspX].IloscNiedzwiedzi = dawka+1;
                }
                else
                {
                    TablicaPol[polaLasu.get(ktorepole-polaGor.size()).WspY][polaLasu.get(ktorepole-polaGor.size()).WspX].IloscNiedzwiedzi = dawka;
                }
            }
        }

        int licznikLudzi=0;
        int licznikNiedzwiedzi=0;
        for(int i=0; i<DlugoscMapy; i++)
        {
            for(int j=0; j<SzerokoscMapy; j++)
            {
                for(int k=0;k<TablicaPol[i][j].IloscLudzi;k++)  //przypisanie ludziom ich pol
                {
                    WszyscyLudzie.get(licznikLudzi).aktualneWspX=j;
                    WszyscyLudzie.get(licznikLudzi).aktualneWspY=i;
                    licznikLudzi=licznikLudzi+1;
                }

                for(int k=0;k<TablicaPol[i][j].IloscNiedzwiedzi;k++) //przypisanie niedzwiedziom ich pol
                {
                    WszystkieNiedzwiedzie.get(licznikNiedzwiedzi).aktualneWspX=j;
                    WszystkieNiedzwiedzie.get(licznikNiedzwiedzi).aktualneWspY=i;
                    licznikNiedzwiedzi=licznikNiedzwiedzi+1;
                }
            }
        }

        //warunki czy dodawac ludzi i niedzwiedzi do osbnikow zeby mimo to wypisac ich polozenie i mape
        if(ileMIASTO+ileWIES>0)
        {
            osobnicy.addAll(WszyscyLudzie);
        }

        if(ileGORY+ileLAS>0)
        {
            osobnicy.addAll(WszystkieNiedzwiedzie);
        }
    }

    public static void wypisanieOsobnikow()
    {
        //wypisanie osobnikow
        for(int i=0; i<DlugoscMapy; i++)
        {
            for(int j=0; j<SzerokoscMapy; j++)
            {
                System.out.print(" ");
                if(TablicaPol[i][j].IloscLudzi>0)       //ile ludzi
                {
                    System.out.print(TablicaPol[i][j].IloscLudzi + "c");
                }
                else
                {
                    if(TablicaPol[i][j].IloscNiedzwiedzi>0)     //ile niedzwiedzi
                    {
                        System.out.print(TablicaPol[i][j].IloscNiedzwiedzi + "n");
                    }
                    else
                    {
                        if(TablicaPol[i][j].IloscKawalerii>0)   //ile kawalerii
                        {
                            System.out.print(TablicaPol[i][j].IloscKawalerii + "k");
                        }
                        else
                        {
                            System.out.print(0+" ");
                        }
                    }
                }

                System.out.print(" ");
            }
            System. out. println( "" );
        }
        System.out.println("");
    }

    public static void UstawienieSzybkosciKolejki()
    {
        for(int i=0;i<osobnicy.size();i++)
        {
            switch(osobnicy.get(i).rodzaj)
            {
                case 1:
                    osobnicy.get(i).szybkosc= (int) Math.round(osobnicy.get(i).szybkosc_bazowa*TablicaPol[osobnicy.get(i).aktualneWspY][osobnicy.get(i).aktualneWspX].SpowolnienieCzlowieka);
                    break;

                case 2:
                    osobnicy.get(i).szybkosc= (int) Math.round(osobnicy.get(i).szybkosc_bazowa*TablicaPol[osobnicy.get(i).aktualneWspY][osobnicy.get(i).aktualneWspX].SpowolnienieNiedzwiedzia);
                    break;

                case 3:

                    break;
            }
        }

        //posortowanie
        for (int i = 0; i < osobnicy.size(); i++)
        {
            for (int j = 0; j < osobnicy.size()-i-1; j++)
            {
                if (osobnicy.get(j).szybkosc<osobnicy.get(j+1).szybkosc)
                {
                    Osobnik zamiennik = osobnicy.get(j);
                    osobnicy.set(j,osobnicy.get(j+1));
                    osobnicy.set(j+1,zamiennik);
                }
            }
        }

    }

    public static void WyborAkcji()
    {
        List <Osobnik> wrogowie = new ArrayList<>();
        for(int i=0;i<osobnicy.size();i++)
        {
            wrogowie.clear();
            int sila_sojusznikow = 0;
            int sila_wrogow =0;
            List <Pole> TablicaPolZwlok = new ArrayList<>();

            //- lecimy po polach x, y w zakresie widzenia
            for(int y=osobnicy.get(i).aktualneWspY-osobnicy.get(i).ZakresWidzenia;y<=osobnicy.get(i).aktualneWspY+osobnicy.get(i).ZakresWidzenia;y++)
            {
                for(int x=osobnicy.get(i).aktualneWspX - (int) Math.floor(Math.sqrt(osobnicy.get(i).ZakresWidzenia*osobnicy.get(i).ZakresWidzenia-(y-osobnicy.get(i).aktualneWspY)*(y-osobnicy.get(i).aktualneWspY)));x<=osobnicy.get(i).aktualneWspX + (int) Math.floor(Math.sqrt(osobnicy.get(i).ZakresWidzenia*osobnicy.get(i).ZakresWidzenia-(y-osobnicy.get(i).aktualneWspY)*(y-osobnicy.get(i).aktualneWspY)));x++)
                {
                    if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                    {
                        if(osobnicy.get(i).rodzaj==2)   //skanowanie sil wrogow i sojusznikow dla niedzwiedzi
                        {
                            if(TablicaPol[y][x].IloscLudzi>0 || TablicaPol[y][x].IloscKawalerii>0) //szukanie wrogow
                            {
                                sila_wrogow=sila_wrogow+1*TablicaPol[y][x].IloscLudzi+6*TablicaPol[y][x].IloscKawalerii;

                                for(int j=0; j<osobnicy.size();j++) //dodanie wrogow wg wspolrzednych i rodzaju osobnika
                                {
                                    if(osobnicy.get(j).aktualneWspX==x && osobnicy.get(j).aktualneWspY==y)
                                    {
                                        if(osobnicy.get(j).rodzaj==1 || osobnicy.get(j).rodzaj==3)
                                        {
                                            wrogowie.add(osobnicy.get(j));
                                        }
                                    }
                                }
                            }
                            else
                            {
                                if(TablicaPol[y][x].IloscNiedzwiedzi>0)
                                {
                                    sila_sojusznikow = sila_sojusznikow + 2 *TablicaPol[y][x].IloscNiedzwiedzi;
                                }
                            }

                            if(TablicaPol[y][x].IloscZwlokLudzi>0) //sprawdzanie czy jest posiłek dla niedźwiedzia
                            {
                                TablicaPolZwlok.add(TablicaPol[y][x]);
                            }
                        }
                        else
                        {
                            if(TablicaPol[y][x].IloscNiedzwiedzi>0)
                            {
                                sila_wrogow=sila_wrogow+2 *TablicaPol[y][x].IloscNiedzwiedzi;

                                for(int j=0; j<osobnicy.size();j++)
                                {
                                    if(osobnicy.get(j).aktualneWspX==x && osobnicy.get(j).aktualneWspY==y)
                                    {
                                        if(osobnicy.get(j).rodzaj==2)
                                        {
                                            wrogowie.add(osobnicy.get(j));
                                        }
                                    }
                                }
                            }
                            else
                            {
                                if(TablicaPol[y][x].IloscLudzi>0 || TablicaPol[y][x].IloscKawalerii>0)
                                {
                                    sila_sojusznikow = sila_sojusznikow + 1*TablicaPol[y][x].IloscLudzi+6*TablicaPol[y][x].IloscKawalerii;
                                }
                            }

                            if(TablicaPol[y][x].IloscZwlokNiedzwiedzi>0)    //sprawdzamy czy jest posiłek dla ludzi
                            {
                                TablicaPolZwlok.add(TablicaPol[y][x]);
                            }
                            else    //jezeli to kawaleria to dodamy pola ze zwlokami ludzi chyba ze mieli tez niedzwiedzi to juz dodano
                            {
                                if(osobnicy.get(i).rodzaj==3)
                                {
                                    if(TablicaPol[y][x].IloscZwlokLudzi>0)
                                    {
                                        TablicaPolZwlok.add(TablicaPol[y][x]);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(sila_wrogow==0)  //co jeśli nie ma wrogow
            {
                if(!(osobnicy.get(i).zdrowie==osobnicy.get(i).max_zdrowie || TablicaPolZwlok.size()<=0))    //czy sa zwloki w zasiegu widzenia i czy zdrowie<max
                {
                    List <Pole> TablicaPolZwlokWZasieguRuchu = new ArrayList<>();
                    for(int j=0;j<TablicaPolZwlok.size();j++)   //ustalenie ile pol jest w zasiegu ruchu
                    {
                        if((TablicaPolZwlok.get(j).WspX - osobnicy.get(i).aktualneWspX)*(TablicaPolZwlok.get(j).WspX - osobnicy.get(i).aktualneWspX) + (TablicaPolZwlok.get(j).WspY - osobnicy.get(i).aktualneWspY)*(TablicaPolZwlok.get(j).WspY - osobnicy.get(i).aktualneWspX) <= (osobnicy.get(i).szybkosc +1)*(osobnicy.get(i).szybkosc +1))
                        {//wzorek:(x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)

                            TablicaPolZwlokWZasieguRuchu.add(TablicaPolZwlok.get(j));
                        }
                    }

                    if(TablicaPolZwlokWZasieguRuchu.size()>0)   //czy są zwłoki w zasięgu ruchu?
                    {
                        for (int m = 0; m < TablicaPolZwlokWZasieguRuchu.size(); m++)   //posortowanie ktore z pol w zasiegu ruchu jest najblizej
                        {
                            for (int n = 0; n < TablicaPolZwlokWZasieguRuchu.size()-m-1; n++)
                            {
                                if ((TablicaPolZwlokWZasieguRuchu.get(n).WspX - osobnicy.get(i).aktualneWspX)*(TablicaPolZwlokWZasieguRuchu.get(n).WspX - osobnicy.get(i).aktualneWspX) + (TablicaPolZwlokWZasieguRuchu.get(n).WspY - osobnicy.get(i).aktualneWspY)*(TablicaPolZwlokWZasieguRuchu.get(n).WspY - osobnicy.get(i).aktualneWspY) > (TablicaPolZwlokWZasieguRuchu.get(n+1).WspX - osobnicy.get(i).aktualneWspX)*(TablicaPolZwlokWZasieguRuchu.get(n+1).WspX - osobnicy.get(i).aktualneWspX) + (TablicaPolZwlokWZasieguRuchu.get(n+1).WspY - osobnicy.get(i).aktualneWspY)*(TablicaPolZwlokWZasieguRuchu.get(n+1).WspY - osobnicy.get(i).aktualneWspY))
                                {
                                    Pole zamiennik = TablicaPolZwlokWZasieguRuchu.get(n);
                                    TablicaPolZwlokWZasieguRuchu.set(n , TablicaPolZwlokWZasieguRuchu.get(n+1));
                                    TablicaPolZwlokWZasieguRuchu.set(n+1 , zamiennik);
                                }
                            }
                        }

                        int WspX_posilku=TablicaPolZwlokWZasieguRuchu.get(0).WspX;
                        int WspY_posilku=TablicaPolZwlokWZasieguRuchu.get(0).WspY;
                        List <Pole> Otoczenie_posilku = new ArrayList<>();

                        for (int m = WspY_posilku-1; m <= WspY_posilku+1; m++) //dodanie otoczenia posilku
                        {
                            for (int n = WspX_posilku-1; n <= WspX_posilku+1; n++)
                            {
                                if(n>=0 && n<SzerokoscMapy && m>=0 && m<DlugoscMapy && !(n==WspX_posilku && m==WspY_posilku))
                                {
                                    Otoczenie_posilku.add(TablicaPol[m][n]);
                                }
                            }
                        }

                        for (int m = 0; m < Otoczenie_posilku.size(); m++)   //posortowanie otoczenia posilku
                        {
                            for (int n = 0; n < Otoczenie_posilku.size()-m-1; n++)
                            {
                                if ((Otoczenie_posilku.get(n).WspX - osobnicy.get(i).aktualneWspX)*(Otoczenie_posilku.get(n).WspX - osobnicy.get(i).aktualneWspX) + (Otoczenie_posilku.get(n).WspY - osobnicy.get(i).aktualneWspY)*(Otoczenie_posilku.get(n).WspY - osobnicy.get(i).aktualneWspY) > (Otoczenie_posilku.get(n+1).WspX - osobnicy.get(i).aktualneWspX)*(Otoczenie_posilku.get(n+1).WspX - osobnicy.get(i).aktualneWspX) + (Otoczenie_posilku.get(n+1).WspY - osobnicy.get(i).aktualneWspY)*(Otoczenie_posilku.get(n+1).WspY - osobnicy.get(i).aktualneWspY))
                                {
                                    Pole zamiennik = Otoczenie_posilku.get(n);
                                    Otoczenie_posilku.set(n , Otoczenie_posilku.get(n+1));
                                    Otoczenie_posilku.set(n+1 , zamiennik);
                                }
                            }
                        }

                        int WspX_docelowe = -5;
                        int WspY_docelowe = -5;

                        for(int m=0;m<Otoczenie_posilku.size();m++)     //sprawdzenie czy nie zajete pole przez inny rodzaj
                        {
                            if (osobnicy.get(i).rodzaj==1)
                            {
                                if (TablicaPol[Otoczenie_posilku.get(m).WspY][Otoczenie_posilku.get(m).WspX].IloscNiedzwiedzi == 0 && TablicaPol[Otoczenie_posilku.get(m).WspY][Otoczenie_posilku.get(m).WspX].IloscKawalerii == 0)
                                {
                                    WspX_docelowe = Otoczenie_posilku.get(m).WspX;
                                    WspY_docelowe = Otoczenie_posilku.get(m).WspY;
                                    break;
                                }
                            }

                            if (osobnicy.get(i).rodzaj==2)
                            {
                                if (TablicaPol[Otoczenie_posilku.get(m).WspY][Otoczenie_posilku.get(m).WspX].IloscLudzi == 0 && TablicaPol[Otoczenie_posilku.get(m).WspY][Otoczenie_posilku.get(m).WspX].IloscKawalerii == 0)
                                {
                                    WspX_docelowe = Otoczenie_posilku.get(m).WspX;
                                    WspY_docelowe = Otoczenie_posilku.get(m).WspY;
                                    break;
                                }
                            }

                            if (osobnicy.get(i).rodzaj==3)
                            {
                                if (TablicaPol[Otoczenie_posilku.get(m).WspY][Otoczenie_posilku.get(m).WspX].IloscLudzi == 0 && TablicaPol[Otoczenie_posilku.get(m).WspY][Otoczenie_posilku.get(m).WspX].IloscNiedzwiedzi == 0)
                                {
                                    WspX_docelowe = Otoczenie_posilku.get(m).WspX;
                                    WspY_docelowe = Otoczenie_posilku.get(m).WspY;
                                    break;
                                }
                            }
                        }

                        //TUTAJ ODPALENIE FUNKCJI PORUSZANIA
                        Osobnik.Ruch_na_konkretne_pole(osobnicy.get(i), TablicaPol[WspY_docelowe][WspX_docelowe]);

                        Pozeranie(osobnicy.get(i),WspX_posilku,WspY_posilku);
                    }
                    else
                        {
                            for (int m = 0; m < TablicaPolZwlok.size(); m++)   //posortowanie pol w zasiegu wzroku
                            {
                                for (int n = 0; n < TablicaPolZwlok.size()-m-1; n++)
                                {
                                    if ((TablicaPolZwlok.get(n).WspX - osobnicy.get(i).aktualneWspX)*(TablicaPolZwlok.get(n).WspX - osobnicy.get(i).aktualneWspX) + (TablicaPolZwlok.get(n).WspY - osobnicy.get(i).aktualneWspY)*(TablicaPolZwlok.get(n).WspY - osobnicy.get(i).aktualneWspY) > (TablicaPolZwlok.get(n+1).WspX - osobnicy.get(i).aktualneWspX)*(TablicaPolZwlok.get(n+1).WspX - osobnicy.get(i).aktualneWspX) + (TablicaPolZwlok.get(n+1).WspY - osobnicy.get(i).aktualneWspY)*(TablicaPolZwlok.get(n+1).WspY - osobnicy.get(i).aktualneWspY))
                                    {
                                        Pole zamiennik = TablicaPolZwlok.get(n);
                                        TablicaPolZwlok.set(n , TablicaPolZwlok.get(n+1));
                                        TablicaPolZwlok.set(n+1 , zamiennik);
                                    }
                                }
                            }

                            int WspX_posilku=TablicaPolZwlok.get(0).WspX;
                            int WspY_posilku=TablicaPolZwlok.get(0).WspY;
                            List <Pole> Obszar_Ruchu = new ArrayList<>();

                            for(int y=osobnicy.get(i).aktualneWspY-osobnicy.get(i).szybkosc;y<=osobnicy.get(i).aktualneWspY+osobnicy.get(i).szybkosc;y++)   //budowa obszaru ruchu
                            {
                                for(int x=osobnicy.get(i).aktualneWspX - (int) Math.floor(Math.sqrt(osobnicy.get(i).szybkosc*osobnicy.get(i).szybkosc-(y-osobnicy.get(i).aktualneWspY)*(y-osobnicy.get(i).aktualneWspY)));x<=osobnicy.get(i).aktualneWspX + (int) Math.floor(Math.sqrt(osobnicy.get(i).szybkosc*osobnicy.get(i).szybkosc-(y-osobnicy.get(i).aktualneWspY)*(y-osobnicy.get(i).aktualneWspY)));x++)
                                {
                                    if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                                    {
                                        Obszar_Ruchu.add(TablicaPol[y][x]);
                                    }
                                }
                            }

                            for (int m = 0; m < Obszar_Ruchu.size(); m++)   //posortowanie obszar ruchu do posilku
                            {
                                for (int n = 0; n < Obszar_Ruchu.size()-m-1; n++)
                                {
                                    if ((Obszar_Ruchu.get(n).WspX - WspX_posilku)*(Obszar_Ruchu.get(n).WspX - WspX_posilku) + (Obszar_Ruchu.get(n).WspY - WspY_posilku)*(Obszar_Ruchu.get(n).WspY - WspY_posilku) > (Obszar_Ruchu.get(n+1).WspX - WspX_posilku)*(Obszar_Ruchu.get(n+1).WspX - WspX_posilku) + (Obszar_Ruchu.get(n+1).WspY - WspY_posilku)*(Obszar_Ruchu.get(n+1).WspY - WspY_posilku))
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
                                if (osobnicy.get(i).rodzaj==1)
                                {
                                    if (TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscNiedzwiedzi == 0 && TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscKawalerii == 0)
                                    {
                                        WspX_docelowe = Obszar_Ruchu.get(m).WspX;
                                        WspY_docelowe = Obszar_Ruchu.get(m).WspY;
                                        break;
                                    }
                                }

                                if (osobnicy.get(i).rodzaj==2)
                                {
                                    if (TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscLudzi == 0 && TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscKawalerii == 0)
                                    {
                                        WspX_docelowe = Obszar_Ruchu.get(m).WspX;
                                        WspY_docelowe = Obszar_Ruchu.get(m).WspY;
                                        break;
                                    }
                                }

                                if (osobnicy.get(i).rodzaj==3)
                                {
                                    if (TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscLudzi == 0 && TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscNiedzwiedzi == 0)
                                    {
                                        WspX_docelowe = Obszar_Ruchu.get(m).WspX;
                                        WspY_docelowe = Obszar_Ruchu.get(m).WspY;
                                        break;
                                    }
                                }
                            }

                            //TUTAJ ODPALENIE FUNKCJI PORUSZANIA
                            Osobnik.Ruch_na_konkretne_pole(osobnicy.get(i), TablicaPol[WspY_docelowe][WspX_docelowe]);
                        }
                }
                else
                    {
                        Random losownik = new Random();
                        int docelowe_y;
                        int docelowe_x;

                        while(true)
                        {
                            docelowe_y = (losownik.nextInt(2 * osobnicy.get(i).szybkosc + 1) - osobnicy.get(i).szybkosc);   //zmiana
                            docelowe_x = losownik.nextInt(2 * ((int) Math.floor(Math.sqrt(osobnicy.get(i).szybkosc * osobnicy.get(i).szybkosc - (docelowe_y) * (docelowe_y)))) + 1) - ((int) Math.floor(Math.sqrt(osobnicy.get(i).szybkosc * osobnicy.get(i).szybkosc - (docelowe_y) * (docelowe_y)))); //zmiana

                            docelowe_y = osobnicy.get(i).aktualneWspY + docelowe_y;
                            docelowe_x = osobnicy.get(i).aktualneWspX + docelowe_x;

                            if (docelowe_x >= 0 && docelowe_x < SzerokoscMapy && docelowe_y >= 0 && docelowe_y < DlugoscMapy) {
                                break;
                            }
                        }
                        //osobnicy.get(i).aktualneWspY
                        //((int) Math.floor(Math.sqrt(osobnicy.get(i).szybkosc*osobnicy.get(i).szybkosc-(docelowe_y)*(docelowe_y))))

                        //TUTAJ ODPALENIE FUNKCJI PORUSZANIA
                        Osobnik.Ruch_na_konkretne_pole(osobnicy.get(i), TablicaPol[docelowe_y][docelowe_x]);
                    }
            }
            else
                {
                    if(sila_sojusznikow>sila_wrogow)
                    {
                        List <Osobnik> NiedzwiedzieWOtoczeniu = new ArrayList<>();

                        for(int k=0;k<osobnicy.size();k++)  //dodanie do listy otaczających niedżwiedzi
                        {
                            if(osobnicy.get(k).rodzaj==2)
                            {
                                if (osobnicy.get(k).aktualneWspX>=osobnicy.get(i).aktualneWspX-1 && osobnicy.get(k).aktualneWspX<=osobnicy.get(i).aktualneWspX+1)
                                {
                                    if (osobnicy.get(k).aktualneWspY>=osobnicy.get(i).aktualneWspY-1 && osobnicy.get(k).aktualneWspY<=osobnicy.get(i).aktualneWspY+1)
                                    {
                                        NiedzwiedzieWOtoczeniu.add(osobnicy.get(k));
                                    }
                                }
                            }
                        }

                        Random los = new Random();  //sprawdzenie losu do oswajania
                        int obupolna_zgoda = los.nextInt(4)+1;

                        if(osobnicy.get(i).rodzaj==1 && NiedzwiedzieWOtoczeniu.size()>0 && obupolna_zgoda==4)//sprawdza czy na polu przyleglym jest niedzwiedz
                        {
                            //czlowiek wykonaj oswojenie
                        }
                        else
                            {
                                //atak
                                porownajnik=i;
                                Osobnik.Atak(osobnicy.get(i), wrogowie);
                                i=porownajnik;
                            }
                    }
                    else
                        {
                            if(sila_sojusznikow<sila_wrogow)
                            {
                                //uciekaj
                                int docelowe_y=osobnicy.get(i).aktualneWspY;
                                int docelowe_x=osobnicy.get(i).aktualneWspX;
                                int dystans=0;
                                int max_dystans=0;
                                List <Pole> Obszar_Ruchu = new ArrayList<>();

                                for(int y=osobnicy.get(i).aktualneWspY-osobnicy.get(i).szybkosc;y<=osobnicy.get(i).aktualneWspY+osobnicy.get(i).szybkosc;y++)   //budowa obszaru ruchu
                                {
                                    for(int x=osobnicy.get(i).aktualneWspX - (int) Math.floor(Math.sqrt(osobnicy.get(i).szybkosc*osobnicy.get(i).szybkosc-(y-osobnicy.get(i).aktualneWspY)*(y-osobnicy.get(i).aktualneWspY)));x<=osobnicy.get(i).aktualneWspX + (int) Math.floor(Math.sqrt(osobnicy.get(i).szybkosc*osobnicy.get(i).szybkosc-(y-osobnicy.get(i).aktualneWspY)*(y-osobnicy.get(i).aktualneWspY)));x++)
                                    {
                                        if(x>=0 && x<SzerokoscMapy && y>=0 && y<DlugoscMapy)
                                        {
                                            Obszar_Ruchu.add(TablicaPol[y][x]);
                                        }
                                    }
                                }

                                List <Pole> Pola_wrogow = new ArrayList<>();

                                for(int y=0;y<DlugoscMapy;y++)  //budowanie pol wrogow
                                {
                                    for(int x=0;x<SzerokoscMapy;x++)
                                    {
                                        if(osobnicy.get(i).rodzaj==2)   //czy to niedzwiedzi
                                        {
                                            if(TablicaPol[y][x].IloscLudzi>0 || TablicaPol[y][x].IloscKawalerii>0) //szukanie wrogow
                                            {
                                                Pola_wrogow.add(TablicaPol[y][x]);
                                            }
                                        }
                                        else
                                        {
                                            if(TablicaPol[y][x].IloscNiedzwiedzi>0)
                                            {
                                                Pola_wrogow.add(TablicaPol[y][x]);
                                            }
                                        }
                                    }
                                }

                                for(int m=0;m<Obszar_Ruchu.size();m++)  // obliczanie gdzie uciekac
                                {
                                    dystans=0;

                                    if(osobnicy.get(i).rodzaj==2)
                                    {
                                        if(TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscLudzi == 0 && TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscKawalerii == 0)
                                        {
                                            for (int n = 0; n < Pola_wrogow.size(); n++)
                                            {
                                                dystans = dystans + (int) Math.floor(Math.sqrt((Pola_wrogow.get(n).WspX - Obszar_Ruchu.get(m).WspX) * (Pola_wrogow.get(n).WspX - Obszar_Ruchu.get(m).WspX) + (Pola_wrogow.get(n).WspY - Obszar_Ruchu.get(m).WspY) * (Pola_wrogow.get(n).WspY - Obszar_Ruchu.get(m).WspY)));
                                            }
                                        }
                                    }
                                    else
                                        {
                                            if(TablicaPol[Obszar_Ruchu.get(m).WspY][Obszar_Ruchu.get(m).WspX].IloscNiedzwiedzi == 0)
                                            {
                                                for (int n = 0; n < Pola_wrogow.size(); n++)
                                                {
                                                    dystans = dystans + (int) Math.floor(Math.sqrt((Pola_wrogow.get(n).WspX - Obszar_Ruchu.get(m).WspX) * (Pola_wrogow.get(n).WspX - Obszar_Ruchu.get(m).WspX) + (Pola_wrogow.get(n).WspY - Obszar_Ruchu.get(m).WspY) * (Pola_wrogow.get(n).WspY - Obszar_Ruchu.get(m).WspY)));
                                                }
                                            }
                                        }

                                    if(dystans>max_dystans)
                                    {
                                        max_dystans=dystans;
                                        docelowe_x = Obszar_Ruchu.get(m).WspX;
                                        docelowe_y = Obszar_Ruchu.get(m).WspY;
                                    }
                                }

                                //TUTAJ ODPALENIE FUNKCJI PORUSZANIA
                                Osobnik.Ruch_na_konkretne_pole(osobnicy.get(i), TablicaPol[docelowe_y][docelowe_x]);
                            }
                        }
                }
        }
    }

    public static void Pozeranie(Osobnik jednostka, int X_jedzenia, int Y_jedzenia)
    {
        switch (jednostka.rodzaj)
        {
            case 1:
                TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokNiedzwiedzi=TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokNiedzwiedzi-1;
                jednostka.zdrowie=jednostka.zdrowie+WszystkieNiedzwiedzie.get(0).max_zdrowie;
                break;

            case 2:
                TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokLudzi=TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokLudzi-1;
                jednostka.zdrowie=jednostka.zdrowie+WszyscyLudzie.get(0).max_zdrowie;
                break;

            case 3:
                if(TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokLudzi>0 && TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokNiedzwiedzi>0)  //wybierz kogo zjesc jak są 2 rodzaje
                {
                    if(jednostka.max_zdrowie-jednostka.zdrowie>WszyscyLudzie.get(0).max_zdrowie)
                    {
                        TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokNiedzwiedzi=TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokNiedzwiedzi-1;
                        jednostka.zdrowie=jednostka.zdrowie+WszystkieNiedzwiedzie.get(0).max_zdrowie;
                    }
                    else
                    {
                        TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokLudzi=TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokLudzi-1;
                        jednostka.zdrowie=jednostka.zdrowie+WszyscyLudzie.get(0).max_zdrowie;
                    }
                }
                else
                {
                    if(TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokNiedzwiedzi>0)  //co jak sa niedzwiedzie
                    {
                        TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokNiedzwiedzi=TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokNiedzwiedzi-1;
                        jednostka.zdrowie=jednostka.zdrowie+WszystkieNiedzwiedzie.get(0).max_zdrowie;
                    }
                    else
                    {
                        if(TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokLudzi>0)    //co jak sa ludzie
                        {
                            TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokLudzi=TablicaPol[Y_jedzenia][X_jedzenia].IloscZwlokLudzi-1;
                            jednostka.zdrowie=jednostka.zdrowie+WszyscyLudzie.get(0).max_zdrowie;
                        }
                    }
                }
                break;
        }

        if(jednostka.zdrowie>jednostka.max_zdrowie) //nie wykraczamy poza max zdrowie
        {
            jednostka.zdrowie= jednostka.max_zdrowie;
        }
    }

    public void UsuwanieNiedzwiedzia(){ }
    public void UsuwanieCzlowieka(){ }
    public void TworzenieKawalerii(){ }
    public void Stop(){ }

    public static int Losowanie(int n){
        Random generator = new Random();
        int q;
        return q=generator.nextInt(n);
    }

    public static void main(String[] args)
    {

        Symulacja.PodajPARAMETRY();

        Symulacja.start(DlugoscMapy, SzerokoscMapy);

        Symulacja.UstawTeren(DlugoscMapy, SzerokoscMapy);

        System.out.println("Ukształtowanie terenu: ");

        Symulacja.wypisanieTerenow();

        System.out.println("Ustawienie poczatkowe osobnikow: ");

        Symulacja.UstawienieOsobnikow();

        Symulacja.wypisanieOsobnikow();

        //UstawienieSzybkosciKolejki();

        //Glowna petla programu
        for(int i=1;i<20;i++)
        {
            int zliczanieLudzi = 0; int zliczanieNiedzwiedzi = 0; int zliczanieKawalerii =0;

            UstawienieSzybkosciKolejki();
            Symulacja.WyborAkcji();

            System.out.println("Tura "+i);
            System.out.println("Ilosc osobnikow: "+osobnicy.size());

            Symulacja.wypisanieOsobnikow();

            for(int p=0; p<DlugoscMapy; p++){
                for(int q=0; q<SzerokoscMapy; q++){
                    if(TablicaPol[p][q].IloscLudzi>0){
                        zliczanieLudzi+=TablicaPol[p][q].IloscLudzi;
                    } else if(TablicaPol[p][q].IloscNiedzwiedzi>0){
                        zliczanieNiedzwiedzi+=TablicaPol[p][q].IloscNiedzwiedzi;
                    } else if(TablicaPol[p][q].IloscKawalerii>0){
                        zliczanieKawalerii+=TablicaPol[p][q].IloscKawalerii;
                    }
                }
            }

            if(zliczanieLudzi<=0 && zliczanieKawalerii<=0){
                System.out.println("Wygrały niedzwiedzie! Niech Wam sie powodzi w tym lesie!");
                System.out.println("Przetrwalo niedzwiedzi: "+zliczanieNiedzwiedzi);
                break;
            }

            if(zliczanieNiedzwiedzi<=0){
                System.out.println("(Jakims cudem) wygrali ludzie!");
                System.out.print("Przetrwalo "+zliczanieLudzi+" ludzi oraz "+zliczanieKawalerii+" jakże mężnych kawalerzystów");
                break;
            }


            //Thread.sleep(1000);
            /*if(WszystkieNiedzwiedzie.isEmpty())
            {
                System.out.println("Wygrali (jakims cudem) ludzie!");
                break;
            }else
                {
                    if(WszyscyLudzie.isEmpty())
                    {
                        System.out.println("Wygraly niedzwiedzie! Nie dawajcie sie oswoic!");
                        break;
                    }
                }*/

        }

    }
}
