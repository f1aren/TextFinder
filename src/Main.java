import java.io.*;

public class Main  {
    public static String searchText;

    public static void main (String[] args) {
        System.out.println("Данная программа ищет заданный текст в *.txt файлах на всех логических дисках, \nкоторые были найдены в компьютере, используются потоки");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // для ввода данных с консоли
        File[] roots = File.listRoots(); // поиск доступных логических дисков


        System.out.println("\nНайдено логических дисков: " + roots.length);

        System.out.print("Введите текст: ");
        try {
            searchText = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nРезультат поиска:");
        for (int i = 0; i < roots.length; i++) {
            MyThreads thread = new MyThreads(roots[i]);
            //System.out.println(thread.root);
            thread.start();
            //System.out.println(thread.getName());
        }

    }
}
