class DocumentRequest {
    private DocumentType type;
    private Client client;

    public DocumentRequest(DocumentType type, Client client) {
        this.type = type;
        this.client = client;
    }

    public DocumentType getType() {
        return type;
    }

    public Client getClient() {
        return client;
    }
}
