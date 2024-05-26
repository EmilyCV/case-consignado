package br.com.consigned.consigned_model.enums;

public enum AgreementType {
    EP(1),
    OP(2),
    INSS(3);

    private final int code;

    AgreementType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AgreementType forCode(int code) {
        for (AgreementType agreementType : AgreementType.values()) {
            if (agreementType.getCode() == code) {
                return agreementType;
            }
        }
        return null;
    }
}
