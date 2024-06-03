package br.com.consigned.consignedsimulatorservice.service;

import br.com.consigned.consignedsimulatorservice.model.InformationCalculation;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

@Service
public class InformationCalculationService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public InformationCalculationService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public InformationCalculation findInformation(int idAgreement, Integer idSegment, Boolean accountHolder) {
        String sql =
                " SELECT  " +
                        "    agr.INTEREST_RATE, " +
                        "    inst.QTD_INSTALLM_MAX, " +
                        "    disc.DISCOUNT " +
                        " FROM  " +
                        "     TSIMU_AGREEMENT_TYPE agr " +
                        " LEFT JOIN  " +
                        "     TSIMU_SEGMENT_TYPE seg ON :id_segment = seg.ID_SEGM " +
                        " LEFT JOIN  " +
                        "     TSIMU_INSTALLMENT_DETAILS inst ON (:id_segment IS NULL OR inst.ID_SEGMENT = :id_segment) " +
                        " LEFT JOIN  " +
                        "     TSIMU_INTEREST_DISCOUNT disc ON :account_holder = disc.ACCOUNT_HOLDER " +
                        " WHERE agr.ID_AGRM = :id_agreement " +
                        " AND (CASE WHEN :id_segment IS NULL THEN inst.ID_SEGMENT IS NULL ELSE inst.ID_SEGMENT = :id_segment END)" +
                        " LIMIT 1;";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id_agreement", idAgreement)
                .addValue("id_segment", idSegment)
                .addValue("account_holder", accountHolder);

        return jdbcTemplate.queryForObject(sql, parameters, (rs, rowNum) -> {
            InformationCalculation info = new InformationCalculation();
            info.setInterestRate(rs.getBigDecimal("INTEREST_RATE"));
            info.setQtdInstallments(rs.getInt("QTD_INSTALLM_MAX"));
            info.setDiscount(rs.getBigDecimal("DISCOUNT"));
            return info;
        });
    }
}
