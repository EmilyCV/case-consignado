package br.com.consigned.userverificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TCLI_SEGMENT_TYPE")
public class SegmentTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SEGM")
    private int id;

    @Column(name = "SEGM_TYPE", unique = true)
    private String segmentType;
}
