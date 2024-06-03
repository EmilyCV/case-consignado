package br.com.consigned.consigned_model.enums;

import lombok.Getter;

@Getter
public enum SegmentType {
    VAREJO(1, 24),
    UNICLASS(2, 36),
    PERSON(3, 48),
    NAO_CORRENTISTA(null,12);

    private final Integer code;
    private final int installment;

    SegmentType(Integer code, int installment) {
        this.code = code;
        this.installment = installment;
    }

    public static SegmentType forCode(Integer code) {
        for (SegmentType segmentType : SegmentType.values()) {
            if (segmentType.getCode().equals(code)) {
                return segmentType;
            }
        }
        return null;
    }

}
