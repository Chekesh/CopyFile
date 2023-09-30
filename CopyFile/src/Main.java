import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) {
        //File[] file = {new File("C:\\Users\\Chekesh\\Desktop\\Системка.txt")
        //        , new File("C:\\Users\\Chekesh\\Desktop\\копия сис")};
        String[] file_1 = {"C:\\Users\\Chekesh\\Desktop\\Системка.txt", "C:\\Users\\Chekesh\\Desktop\\копия сис.txt"};
        String[] file_2 = {"C:\\Users\\Chekesh\\Desktop\\Параллелизм.txt", "C:\\Users\\Chekesh\\Desktop\\копия пар.txt"};

        long startTime = System.currentTimeMillis();
        oneThread(file_1);
        oneThread(file_2);
        long endTime = System.currentTimeMillis();
        System.out.println("С одним потоком: " + (endTime-startTime));


        /*ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(new Runnable() {
            String file
            @Override
            public void run() {
                oneThread(file_1);
            }
        });*/


        //Thread[] threads = new Thread[2];
        long startTwoTime = System.currentTimeMillis();
        twoThread(file_1);
        twoThread(file_2);
        long endTwoTime = System.currentTimeMillis();
        System.out.println("С двумя потоками: " + (endTwoTime-startTwoTime));

        open(file_1);


    }
    static void twoThread(String[] file) {
        Thread thread = new Thread(() -> {
            oneThread(file);
            /*try (BufferedReader br = new BufferedReader(new FileReader(file[0]));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(file[1]))){
                String line;
                while ((line = br.readLine()) != null){
                    bw.write(line + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void oneThread(String[] file){
        try (BufferedReader br = new BufferedReader(new FileReader(file[0]));
             BufferedWriter bw = new BufferedWriter(new FileWriter(file[1]))){
            String line;
            while ((line = br.readLine()) != null){
                bw.write(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*static void oneThread(String[] file){
        try (FileReader fr = new FileReader(file[0])) {
            try (FileWriter fw = new FileWriter(file[1])){

                int charr;
                while ((charr = fr.read()) != -1){
                    fw.write(charr);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }*/

    static void open(String[] file) {
        try {
            ProcessBuilder prok = new ProcessBuilder("notepad.exe", "Системка");
            prok.directory(new File("C:\\Users\\Chekesh\\Desktop"));
            prok.start();
            ProcessBuilder prok_2 = new ProcessBuilder("notepad.exe", "копия сис");
            prok_2.directory(new File("C:\\Users\\Chekesh\\Desktop"));
            prok_2.start();
            ProcessBuilder prok_3 = new ProcessBuilder("notepad.exe", "Параллелизм");
            prok_3.directory(new File("C:\\Users\\Chekesh\\Desktop"));
            prok_3.start();
            ProcessBuilder prok_4 = new ProcessBuilder("notepad.exe", "копия пар");
            prok_4.directory(new File("C:\\Users\\Chekesh\\Desktop"));
            prok_4.start();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}