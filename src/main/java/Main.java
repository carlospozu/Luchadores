import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {


        public static void main(String[] args) {
            for (int i = 0; i < 10; i++){
                Luchador t = new Luchador();
                t.setName("Luchador " + i);
                t.start();
            }
        }
    }

    class Luchador extends Thread {


        @Override
        public void run() {
                Cuadrilatero.AddParticipante(this);
        }
    }

 class Cuadrilatero {

    private static final int NUM_ACCESO_SIMULTANEOS = 2;
    static Semaphore semaphore = new Semaphore(NUM_ACCESO_SIMULTANEOS,true);
    static Luchador luchador1 = null;
    static Luchador luchador2 = null;


    static void AddParticipante(Luchador luchador){

        try {
            semaphore.acquire();

            if(luchador1==null) {
                luchador1 = luchador;

            }else
                luchador2=luchador;
            if(luchador1!=null && luchador2!=null) {
                System.out.println("Comienza la pelea de: " + luchador1.getName() + " y " + luchador2.getName());
                luchar();
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void luchar() {

        boolean pelea = new Random().nextBoolean();
        if (pelea) {
            System.out.println("el " + luchador1.getName() + " gana");
            luchador2 = null;
        } else {
            System.out.println("el " + luchador2.getName() + " gana");
            luchador1 = null;

        }
    }

}


