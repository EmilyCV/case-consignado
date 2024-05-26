package br.com.consigned.consigned_model.enums;

public enum DocumentType {
    CPF(1);

    private final int code;

    DocumentType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static DocumentType forCode(int code) {
        for (DocumentType documentType : DocumentType.values()) {
            if (documentType.getCode() == code) {
                return documentType;
            }
        }
        return null;
    }
}
