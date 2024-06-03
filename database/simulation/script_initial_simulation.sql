INSERT INTO TSIMU_SEGMENT_TYPE (ID_SEGM, SEGM_TYPE) VALUES (1, 'Varejo');
INSERT INTO TSIMU_SEGMENT_TYPE (ID_SEGM, SEGM_TYPE) VALUES (2,  'Uniclass');
INSERT INTO TSIMU_SEGMENT_TYPE (ID_SEGM, SEGM_TYPE) VALUES (3, 'Person');

INSERT INTO TSIMU_AGREEMENT_TYPE (ID_AGRM,  AGMR_TYPE, INTEREST_RATE) VALUES (1, 'EP', 2.6);
INSERT INTO TSIMU_AGREEMENT_TYPE (ID_AGRM,  AGMR_TYPE, INTEREST_RATE) VALUES (2, 'OP', 2.2);
INSERT INTO TSIMU_AGREEMENT_TYPE (ID_AGRM,  AGMR_TYPE, INTEREST_RATE) VALUES (3, 'INSS', 1.6);

INSERT INTO TSIMU_INSTALLMENT_DETAILS (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (1, 1, 24);
INSERT INTO TSIMU_INSTALLMENT_DETAILS (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (1, 2, 36);
INSERT INTO TSIMU_INSTALLMENT_DETAILS (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (1, 3, 48);
INSERT INTO TSIMU_INSTALLMENT_DETAILS (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (0, NULL, 12);

INSERT INTO SIMU_INTEREST_DISCOUNT (ACCOUNT_HOLDER, DISCOUNT) VALUES (1, 5.0);
INSERT INTO SIMU_INTEREST_DISCOUNT (ACCOUNT_HOLDER) VALUES (0);