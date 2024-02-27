package com.ruoyi.charity.domain.bo;

import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharityControllerAddSignTraceInputBO {
  private String _signAddress;

  private BigInteger _activiteId;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_signAddress);
    args.add(_activiteId);
    return args;
  }
}
