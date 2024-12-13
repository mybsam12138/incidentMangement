package com.sam.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incident {
    private String id;
    private String title;
    private String description;
    private String status;
}