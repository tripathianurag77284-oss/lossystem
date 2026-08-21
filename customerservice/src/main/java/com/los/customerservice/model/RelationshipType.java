package com.los.customerservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "relationship_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relationship_type_id")
    private Long relationshipTypeId;

    @Column(name = "relationship_type")
    private String relationshipType;

    @Column(name = "relationship_description")
    private String relationshipDescription;
}