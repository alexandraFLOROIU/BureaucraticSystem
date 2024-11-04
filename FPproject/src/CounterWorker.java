import java.util.Map;
import java.util.Random;

class CounterWorker implements Runnable {
    int id;
    private Office office;
    private Map<Client, ClientWorker> clientWorkerMap;
    private Random rnd = new Random();

    public CounterWorker(int id, Office office, Map<Client, ClientWorker> clientWorkerMap) {
        this.id = id;
        this.office = office;
        this.clientWorkerMap = clientWorkerMap;
    }

    @Override
    public void run() {
        String counterName = "[Counter " + this.id + " / Office " + this.office.getId() + "]";
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (rnd.nextInt(4) == 0) {
                    // coffee break
                    System.out.println(counterName + ": Taking a coffee break...");
                    Thread.sleep(2000);
                }
                DocumentRequest dr = office.getRequests().take();
                ClientWorker clientWorker = this.clientWorkerMap.get(dr.getClient());
                System.out.println(counterName + ": Inviting client " + clientWorker.id + ".");
                Thread.sleep(100);
                synchronized (clientWorker) {
                    clientWorker.setLastReceivedDocument(new Document(
                            dr.getType(), "office " + office.getId() + " - counter " + this.id
                    ));
                    clientWorker.notify();
                }
                System.out.println(counterName + ": Notified client " + clientWorker.id + ".");
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println(counterName + ": Finally my shift is over...");
    }
}