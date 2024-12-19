import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class AttaccantiRicevitori extends Thread {

    private String ruolo;
    private int id;
    private Semaphore[] posti = new Semaphore[3];

    public AttaccantiRicevitori(String ruolo, int id) {
        this.ruolo = ruolo;
        this.id = id;
    }

    private void riceviPalla() {
        int mioPosto = id - 1;
        int prossimoPosto = (mioPosto + 1) % posti.length;

        try {
            posti[mioPosto].acquire();
            System.out.println("Ricevitore " + id + " riceve la palla.");
            TimeUnit.SECONDS.sleep(1);
            posti[prossimoPosto].release();
        } catch (Exception e) {
            System.out.println("Ricevitore " + id + " interrotto durante l'azione.");
        }
    }

    private void attaccaPalla() {
        int mioPosto = id - 1;

        try {
            posti[mioPosto].acquire();
            System.out.println("Attaccante " + id + " attacca sul posto " + (mioPosto + 1));
            TimeUnit.SECONDS.sleep(1);
            posti[mioPosto].release();
        } catch (Exception e) {
            System.out.println("Attaccante " + id + " interrotto durante l'azione.");
        }
    }

    public void run() {
        while (true) {
            if (ruolo.equals("ricevitore")) {
                riceviPalla();
            } else if (ruolo.equals("attaccante")) {
                attaccaPalla();
            }
        }
    }

    public static void main(String[] args) {

    }

}
