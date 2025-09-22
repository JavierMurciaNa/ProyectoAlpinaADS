import java.util.Random;
import java.util.concurrent.*;

public class LoadSimulation {

    // Cantidad máxima de usuarios concurrentes
    private static final int MAX_USERS = 50_000;

    // Pool de threads simulando servidores balanceados
    private static final ExecutorService loadBalancer =
            Executors.newFixedThreadPool(50); // 50 "servidores"

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando simulación con " + MAX_USERS + " usuarios concurrentes...");

        CountDownLatch latch = new CountDownLatch(MAX_USERS);
        Random random = new Random();

        long start = System.currentTimeMillis();

        for (int i = 0; i < MAX_USERS; i++) {
            loadBalancer.submit(() -> {
                try {
                    // Simula tiempo de respuesta de la petición (0.5s a 2s)
                    int responseTime = 500 + random.nextInt(1500);
                    Thread.sleep(responseTime);

                    // Validar que no supere los 3s
                    if (responseTime < 3000) {
                        System.out.println("✅ Respuesta en " + responseTime + " ms");
                    } else {
                        System.out.println("❌ Respuesta lenta: " + responseTime + " ms");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }

        // Espera a que todos los usuarios terminen
        latch.await();
        long end = System.currentTimeMillis();

        System.out.println("\nPrueba de carga finalizada.");
        System.out.println("Tiempo total: " + (end - start) + " ms");
        loadBalancer.shutdown();
    }
}
