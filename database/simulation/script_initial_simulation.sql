INSERT INTO tsimu_segment_type (SEGM_TYPE) VALUES ('Varejo');
INSERT INTO tsimu_segment_type (SEGM_TYPE) VALUES ('Uniclass');
INSERT INTO tsimu_segment_type (SEGM_TYPE) VALUES ('Person');

INSERT INTO tsimu_agreement_type (AGMR_TYPE) VALUES ('EP');
INSERT INTO tsimu_agreement_type (AGMR_TYPE) VALUES ('OP');
INSERT INTO tsimu_agreement_type (AGMR_TYPE) VALUES ('INSS');

INSERT INTO tsimu_installment_details (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (1, 1, 24);
INSERT INTO tsimu_installment_details (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (1, 2, 36);
INSERT INTO tsimu_installment_details (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (1, 3, 48);
INSERT INTO tsimu_installment_details (ACCOUNT_HOLDER, ID_SEGMENT, QTD_INSTALLM_MAX) VALUES (0, NULL, 12);