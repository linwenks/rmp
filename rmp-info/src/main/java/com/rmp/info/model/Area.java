package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 地区
 * 
 * t_area
 *
 */
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