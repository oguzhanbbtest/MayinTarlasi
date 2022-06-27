# __<div align="center">Mayın Tarlası</div>__  
<div align="center"><img src="https://i4.hurimg.com/i/hurriyet/75/750x422/5ad97d2c67b0a81ba09f93d4.jpg"/></div>


- Metin tabanlı bir oyundur.
- Tarla boyutunu (Satır*Sütun) kullanıcı belirler.
- Kullanıcı matris üzerinden bir nokta seçer, satır ve sütun değerlerini girer.
- Seçilen noktanın dizinin sınırları içerisinde olup olmadığını kontrol edilir ve koşul sağlanmazsa tekrar nokta istenir.
- Kullanıcının girdiği noktada mayın var ise oyunu kaybeder. Mayın yok ise, ilgili noktaya değen tüm konumlarına bakılır 
  (sağı, solu, yukarısı,aşşağısı ve çaprazları) etrafındaki mayınların sayısının toplamı ilgili noktaya yazılmalı. 
- Noktaya değen herhangi bir mayın yok ise "0" değeri atanmalı.
- Kullanıcı hiç bir mayına basmadan tüm noktaları seçebilirse oyunu kazanmalı.


#### Sınıfımız 
     public class MineSweeper {}

#### Sınıf Nitelikleri 
    int rowNumber;         
    int colNumber;
    String[][]map;    
    String[][]board;  /
    int size;
    boolean oyun = true; 
    Random rand = new Random();  
    Scanner sc = new Scanner(System.in);

#### Kurucu metodumuz.
    MineSweeper(int rowNumber, int colNumber) {\
    this.rowNumber = rowNumber;\
    this.colNumber = colNumber;\
    this.map = new  String[rowNumber][colNumber];\
    this.board = new  String[rowNumber][colNumber];\
    this.size = rowNumber*colNumber;\
    }
#### Run metot.
    public void run(){
    mapHazirligi();
    mayinHazirligi();
    runMech();
    }

#### RunEngine metot.

     public void runMech(){
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
#### Haritamızı hazırladığımız metot.
    public void mapHazirligi(){
    for (int i = 0; i < rowNumber; i++) {
    for (int j = 0; j < colNumber; j++) {
    board[i][j] = " - ";
    }
    }
    }
#### Mayınlarımızı yerleştirdiğimiz metot.
    public void mayinHazirligi() { // 
    int randRow;
    int randColm;
    int count = 0;
    map = new String[rowNumber][colNumber];
    for (int i = 0; i < rowNumber; i++) {
    for (int j = 0; j < colNumber; j++) {
    map[i][j] = " - ";
    }
    }
    while (count != (size / 4)) { 
    randRow = rand.nextInt(rowNumber); 
    randColm = rand.nextInt(colNumber); 

            if (map[randRow][randColm].equals(" - ")) {
                    map[randRow][randColm] = " * ";
                    count++;
            }
        }
    }

#### Oyuncumuzun mayına basıp basmadığını kontrol eden metot.
    public void mayinKontrol ( int sa, int su){ 
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

#### Haritamızı bastırdığımız metot.
    public void printMap ( String[][] xmap){

        for (String[] strings : xmap) {
            for (int j = 0; j < xmap[0].length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
        System.out.println();
    }