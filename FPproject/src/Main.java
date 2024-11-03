import java.util.*;

public class Main {
    private static List<DocumentType> generateDocumentTypes() {
        DocumentType d1 = new DocumentType(1, List.of());
        DocumentType d2 = new DocumentType(2, List.of()); //nu are dependente
        DocumentType d3 = new DocumentType(3, List.of());
        DocumentType d4 = new DocumentType(4, List.of(d2, d3)); //are dependente
        DocumentType d5 = new DocumentType(5, List.of(d1, d3));
        DocumentType d6 = new DocumentType(6, List.of(d1, d2));
        DocumentType d7 = new DocumentType(7, List.of(d4, d5));
        DocumentType d8 = new DocumentType(8, List.of(d5, d6));
        DocumentType d9 = new DocumentType(9, List.of(d7, d8));

        return List.of(d1, d2, d3, d4, d5, d6, d7, d8, d9);  //imi returneaza o lista cu toate documentele care pot fi emise
    }

    static int NUM_OFFICES = 3;
    static int MIN_NUM_COUNTERS_PER_OFFICE = 3;
    static int MAX_NUM_COUNTERS_PER_OFFICE = 5;
    static int NUM_CLIENTS = 10;
    static Random rnd = new Random();

    static List<Office> generateOffices(List<DocumentType> documentTypes) {
        List<Office> offices = new ArrayList<>();
        int remainder = documentTypes.size() % NUM_OFFICES;
        int countPerOffice = documentTypes.size() / NUM_OFFICES;
        int start = 0;
        for (int i = 0; i < NUM_OFFICES; ++i) {
            int end = start + countPerOffice;
            if (remainder > 0) {
                end += 1;
                remainder -= 1;
            }
            Office office = new Office(
                    i,
                    documentTypes.subList(start, end),
                    rnd.nextInt(MIN_NUM_COUNTERS_PER_OFFICE, MAX_NUM_COUNTERS_PER_OFFICE + 1)
            );
            offices.add(office);
            start = end;
        }
        return offices;
    }

    static Map<DocumentType, Office> connectTypesAndOffices(List<DocumentType> documentTypes, List<Office> offices) {
        Map<DocumentType, Office> typeOfficeMap = new HashMap<>();
        for (DocumentType type : documentTypes) {
            for (Office office : offices) {
                if (office.getCompatibleDocumentTypes().contains(type)) {
                    typeOfficeMap.put(type, office);
                    break;
                }
            }
        }  //iau fiecare document si vad in ce birou se afla si fac un dictionar unde cheia este tipul documentului, iar valoarea este biroul
        return typeOfficeMap;
    }

    static List<DocumentType> getDocumentsRequiringOtherDocuments(List<DocumentType> documentTypes) {
        return documentTypes.stream().filter(documentType -> !documentType.getRequiredDocs().isEmpty()).toList(); //imi returneaza o lista doar cu documentele care au dependente
    }


    public static void main(String[] args) throws InterruptedException {//o excepție verificată (checked exception)
        List<DocumentType> documentTypes = new ArrayList<>(generateDocumentTypes());
        List<DocumentType> documentsRequiringOtherDocuments = getDocumentsRequiringOtherDocuments(documentTypes);
        Collections.shuffle(documentTypes);

        List<Office> offices = generateOffices(documentTypes);
        Map<DocumentType, Office> typeOfficeMap = connectTypesAndOffices(documentTypes, offices);
        Map<Client, ClientWorker> clientWorkerMap = new HashMap<>();

        System.out.println("==========================");
        for (Office office : offices) {
            System.out.print("Office " + office.getId() + " documents: ");
            for (DocumentType type : office.getCompatibleDocumentTypes()) {
                System.out.print(type.getId() + " ");
            }
            System.out.println();
        }
        System.out.println("==========================");//afiseaza id-ul biroului si  id-ul documentelor pe care le poate emite fiecare

        List<Thread> clientThreads = new ArrayList<>();
        List<Thread> counterThreads = new ArrayList<>();

        for (int i = 0; i < NUM_CLIENTS; ++i) {
            Client client = new Client(
                    documentsRequiringOtherDocuments.get(rnd.nextInt(documentsRequiringOtherDocuments.size()))
            );    //un client cere unul din documentele care au dependente
            ClientWorker clientWorker = new ClientWorker(i, client, typeOfficeMap);//are un id, un client, dictionarul cu relatia dintre tipul de document si biroul corespunzator
            clientWorkerMap.put(client, clientWorker);  //pune in dictionar
            Thread thread = new Thread(clientWorker);
            thread.start();//porneste firul
            clientThreads.add(thread);//il adauga in lista de fire
        }

        for (Office office : offices) {
            for (int i = 0; i < office.getNumCounters(); ++i) {
                CounterWorker counterWorker = new CounterWorker(i, office, clientWorkerMap); //muncitorul de la ghiseu va avea acelasi id ca si ghiseul, biroul din care face parte, dictionarul cu relatia client muncitor de la ghiseu
                Thread thread = new Thread(counterWorker);
                thread.start();
                counterThreads.add(thread);
            }
        }

        for (Thread thread : clientThreads) {
            thread.join();
        }

        for (Thread thread : counterThreads) {
            thread.interrupt();
            thread.join();
        }
    }
}