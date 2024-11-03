import java.util.Map;

class ClientWorker implements Runnable {
    int id;
    private Client client;
    private Map<DocumentType, Office> typeOfficeMap;
    private Document lastReceivedDocument;

    public ClientWorker(int id, Client client, Map<DocumentType, Office> typeOfficeMap) {
        this.id = id;
        this.client = client;
        this.typeOfficeMap = typeOfficeMap;
    }

    public void setLastReceivedDocument(Document lastReceivedDocument) {
        this.lastReceivedDocument = lastReceivedDocument;
    }

    @Override
    public void run() {
        String clientName = "[Client " + this.id + "]";
        DocumentType currentType = client.getNextRequiredType();
        while (currentType != null) {
            try {
                DocumentRequest dr = new DocumentRequest(currentType, client);
                Office office = typeOfficeMap.get(currentType);
                Thread.sleep(100); // go to that office
                office.getRequests().add(dr);
                System.out.println(clientName + ": Requesting document " + currentType.getId() + ".");
                synchronized (this) {
                    wait();
                }
                client.getDocuments().put(currentType, lastReceivedDocument);
                currentType = client.getNextRequiredType();
                System.out.println(clientName + ": Received document " + lastReceivedDocument.getType().getId() + " with signature " + lastReceivedDocument.getSignature() + ".");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(clientName + ": Finally got document " + client.getRequiredType().getId() + ".");
    }
}