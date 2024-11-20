import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        CityHall cityHall=new CityHall();
        List<DocumentType> documentTypes = new ArrayList<>(cityHall.generateDocumentTypes());
        List<DocumentType> documentsRequiringOtherDocuments = CityHall.getDocumentsRequiringOtherDocuments(documentTypes);
        Collections.shuffle(documentTypes);

        List<Office> offices = cityHall.generateOffices(documentTypes);
        Map<DocumentType, Office> typeOfficeMap = CityHall.connectTypesAndOffices(documentTypes, offices);
        Map<Client, ClientWorker> clientWorkerMap = new HashMap<>();

        System.out.println("==========================");
        for (Office office : offices) {
            System.out.print("Office " + office.getId() + " documents: ");
            for (DocumentType type : office.getCompatibleDocumentTypes()) {
                System.out.print(type.getId() + " ");
            }
            System.out.println();
        }
        System.out.println("==========================");

        List<Thread> clientThreads = new ArrayList<>();
        List<Thread> counterThreads = new ArrayList<>();

        for (int i = 0; i < CityHall.NUM_CLIENTS; ++i) {
            Client client = new Client(
                    //the client need to request a document with at least one dependency and it's random
                    documentsRequiringOtherDocuments.get(CityHall.rnd.nextInt(documentsRequiringOtherDocuments.size()))
            );
            ClientWorker clientWorker = new ClientWorker(i, client, typeOfficeMap);
            clientWorkerMap.put(client, clientWorker);

            //debuging messages
            System.out.println("Client " + clientWorker.id + " want to get document " + client.getRequiredType().getId() + " named " + client.getRequiredType().getName());
            StringBuilder requiredDocs = new StringBuilder();
            for(DocumentType d : client.getRequiredType().getRequiredDocs()){
                requiredDocs.append(d.getName()).append(" from office " + typeOfficeMap.get(d).getId()).append(", ");
            }
            requiredDocs.setLength(requiredDocs.length() - 2);
            System.out.println("For recieving that document, the client needs: " + requiredDocs);

            Thread thread = new Thread(clientWorker);
            thread.start();
            clientThreads.add(thread);
        }

        for (Office office : offices) {
            for (int i = 0; i < office.getNumCounters(); ++i) {
                CounterWorker counterWorker = new CounterWorker(i, office, clientWorkerMap);
                Thread thread = new Thread(counterWorker);
                thread.start();
                counterThreads.add(thread);
            }
        }

        for (Thread thread : clientThreads) {
            thread.join();
        }

        for (Thread thread : counterThreads) {
            thread.interrupt();                     //intrerupts every counter
            thread.join();                          //joins and waits for everyone to finish
        }
    }
}