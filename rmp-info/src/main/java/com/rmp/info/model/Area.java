package com.rmp.info.model;

import java.io.Serializable;

import com.rmp.info.base.model.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 地区
 * 
 * t_area
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class Area extends Model implements Serializable {
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

    private static final long serialVersionUID = 1L;
}