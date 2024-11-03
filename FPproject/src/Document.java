class Document {
    private DocumentType type;
    private String signature;

    public Document(DocumentType type, String signature) {
        this.type = type;
        this.signature = signature;
    }

    public DocumentType getType() {
        return type;
    }

    public String getSignature() {
        return signature;
    }
}