INSERT INTO tsimu_segment_type (ID_SEGM, SEGM_TYPE) VALUES (1, 'Varejo');
INSERT INTO tsimu_segment_type (ID_SEGM, SEGM_TYPE) VALUES (2,  'Uniclass');
INSERT INTO tsimu_segment_type (ID_SEGM, SEGM_TYPE) VALUES (3, 'Person');

INSERT INTO tsimu_agreement_type (ID_AGRM,  AGMR_TYPE) VALUES (1, 'EP');
INSERT INTO tsimu_agreement_type (ID_AGRM,  AGMR_TYPE) VALUES (2, 'OP');
INSERT INTO tsimu_agreement_type (ID_AGRM,  AGMR_TYPE) VALUES (3, 'INSS');

INSERT INTO tsimu_installment_details (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (1, 1, 24);
INSERT INTO tsimu_installment_details (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (1, 2, 36);
INSERT INTO tsimu_installment_details (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (1, 3, 48);
INSERT INTO tsimu_installment_details (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (0, NULL, 12);