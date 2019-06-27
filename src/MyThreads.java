import java.io.*;
import java.io.File;

public class MyThreads extends Thread {
    private static final String fileExtension = ".txt";

    private File root; // логический диск

    private void ProcessAllDir (File dir) {

        File[] directories = dir.listFiles(); // получает все файлы и директории
        if (directories != null) {
            for (File obj : directories) {

                if (obj.isDirectory()) {
                    ProcessAllDir(obj); // если находит еще одну директорию внутри другой, начинает опрашивать ее на наличие других
                    continue;
                }

                if (obj.getName().endsWith(fileExtension)) { // обрабатываем найденный файл в директории
                    //System.out.println(obj.getPath());
                    try {
                        FileInputStream fstream = new FileInputStream(obj);
                        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                        String strLine;
                        while ((strLine = br.readLine()) != null){
                            //System.out.println(strLine);
                            if (strLine.matches("(.*)" + Main.searchText + "(.*)")) { // поиск по заданному слову в найденных файлах
                                System.out.println(obj.getPath());
                                break; // для того, чтобы после нахождения первого совпадения, не продолжал искать дальше в файле
                            }
                        }
                    } catch (IOException e){
                        System.out.println("Ошибка чтения: " + e);
                    }


                    //System.out.println(obj.getPath());
                }
            }
        }
    }

    public MyThreads (File root) {
        this.root = root;
    }

    @Override
    public void run() {
        ProcessAllDir(root);
        System.out.println("Поиск на диске " + root + " завершен");
    }
}
