USE case_consigned;

INSERT INTO TCON_CLIENT (doc_client, name_client, account_holder, segment, agreement, inclusion_date)
VALUES
	(MD5('11111111111'), 'Michael Jackson', TRUE, 'Varejo', 'EP', NOW()),
	(MD5('22222222222'), 'Lebron James', TRUE, 'Uniclass', 'OP', NOW()),
	(MD5('33333333333'), 'Madonna', TRUE, 'Person', 'INSS', NOW()),
	(MD5('44444444444'), 'Marta Vieira da Silva', FALSE, NULL, 'EP', NOW()),
	(MD5('55555555555'), 'Messi', FALSE, NULL, 'INSS', NOW());
