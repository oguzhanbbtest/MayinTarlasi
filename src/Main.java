import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int row,column;
        System.out.println("Mayin Tarlasina Hos Geldiniz");
        System.out.println("Lutfen Oynamak İstediğiniz Boyutları Seciniz");

        System.out.print("Satir Sayisi : ");
        row = scan.nextInt();
        System.out.print("Sutun Sayisi : ");
        column = scan.nextInt();

        MineSweeper mayintarlasi = new MineSweeper(row,column);
        mayintarlasi.run();

    }
}
