import java.util.Random;
import java.util.Scanner;

public class MineSweeper { // Oyunumuz metin tabanlı olucak.
    int rowNumber;         // ve çoklu dizileri kullanarak yapıcağımız için oyuncudan bir limit istiyoruz.
    int colNumber;
    String[][]map;    // mayinları koyacağımız bir harita
    String[][]board;  // oyuncunu oynacağı bir tahtaya ihtiyacımız var.
    int size;
    boolean oyun = true; // bir boolean oluşturduk bu oyunumuza dinamiklik katıcak
    Random rand = new Random();  // Mayinlarida rastgele bir şekilde yerleştireceğimiz için random sınıfını kullanıyoruz.
    Scanner sc = new Scanner(System.in);

    MineSweeper(int rowNumber, int colNumber){ // her zamanki gibi bir kurucu method oluşturuyoruz.
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.map = new  String[rowNumber][colNumber];
        this.board = new  String[rowNumber][colNumber];
        this.size = rowNumber*colNumber;
    }
    public void run(){  //
        mapHazirligi();
        mayinHazirligi();
        runEngine();
    }
    public void runEngine(){
        int satir,sutun,ilerleme = 0;
        printMap(map);

        System.out.println("===== Oyun Basladi ====");
        while (oyun){ // bu sayede oyun true olduğu sürece yani mayina basmadığımızda oyun devam edicek.
            printMap(board);
            System.out.print(" Satir : ");
            satir = sc.nextInt();
            System.out.print(" Sutun : ");
            sutun = sc.nextInt();

            if(satir < 0 || satir >= rowNumber ){
                System.out.println("Gecersiz Parametre...");
                continue;
            }if(sutun < 0 || sutun >= colNumber ){  /// 4 * 3 = 12 ===> 12/4 = 3 === 12 - 3 = 8;
                System.out.println("Gecersiz Parametre...");
                continue;
            }
            if(!map[satir][sutun].equals(" * ")){
                mayinKontrol(satir,sutun);
                ilerleme++;
                if(ilerleme == (size - (size/4))){
                    System.out.println("***********=  HARIKA !! HIC MAYINA BASMADAN TARLAYI DOLASTIN :))  =***********");
                    printMap(board);
                    break;
                }
            }else {
                oyun = false;
                System.out.println("***********=  Game Over  =***********");
            }
        }
    }

    public void mapHazirligi(){
            for (int i = 0; i < rowNumber; i++) {
                for (int j = 0; j < colNumber; j++) {
                    board[i][j] = " - ";
                }
            }
    }
    public void mayinHazirligi() { // ver her parametre girişinde oyunun hazırlanması gerekiyor.
        int randRow;
        int randColm;
        int count = 0;
        map = new String[rowNumber][colNumber];
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                map[i][j] = " - ";
            }
        }
        while (count != (size / 4)) {   // mayin miktarı size'ın  4 te 1 i kadar olmalı.
            randRow = rand.nextInt(rowNumber); //
            randColm = rand.nextInt(colNumber); //

            if (map[randRow][randColm].equals(" - ")) {
                    map[randRow][randColm] = " * ";
                    count++;
            }
        }
    }
    public void mayinKontrol ( int sa, int su){ // eğer oyuncu değer girdiğinde mayin yoksa girdiği parametreleri buraya göndersin;
        int count = 0;
        for(int i = -1; i<2;i++) {
            for (int j = -1; j < 2; j++) {
                if (sa + i < 0 || su + j < 0 || sa + i >= rowNumber || su + j >= colNumber || (i == 0 && colNumber == 0)){
                    continue;
                }if (map[sa + i][su + j].equals(" * ")){
                    count++;
                }
            }
        }
        board[sa][su] =" "+count+" ";
    }
    public void printMap ( String[][] xmap){ // ve oluşturduğumuz diziyi ekrana bastırmak için bir method oluşturduk.

        for (String[] strings : xmap) {
            for (int j = 0; j < xmap[0].length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
