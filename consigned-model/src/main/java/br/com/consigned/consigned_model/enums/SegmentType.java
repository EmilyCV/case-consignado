package br.com.consigned.consigned_model.enums;

public enum SegmentType {
    VAREJO(1),
    UNICLASS(2),
    PERSON(3);

    private final Integer code;

    SegmentType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static SegmentType forCode(int code) {
        for (SegmentType segmentType : SegmentType.values()) {
            if (segmentType.getCode() == code) {
                return segmentType;
            }
        }
        return null;
    }
}
