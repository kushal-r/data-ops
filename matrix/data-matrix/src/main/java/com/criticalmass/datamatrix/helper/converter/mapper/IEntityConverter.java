package com.criticalmass.datamatrix.helper.converter.mapper;

import com.criticalmass.datamatrix.dto.sentinel.AbstractDTO;
import com.criticalmass.datamatrix.entity.sentinel.BaseEntity;

/**
 * Date: 19/06/20
 *
 * @author Kushal Roy
 */
public interface IEntityConverter<S extends AbstractDTO, T extends BaseEntity> {

  public T map(S source);

  public S map(T target);
}
