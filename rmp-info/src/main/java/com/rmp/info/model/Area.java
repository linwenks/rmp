package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 地区
 * 
 * t_area
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class Area extends Model {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Long pid;

    /**
     * 
     */
    private String name;
}