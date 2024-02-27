package com.ruoyi.charity.domain.bo;

import java.lang.Object;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharityServiceUpdateFundRaisingStatusInputBO {
  private BigInteger _raiseId;

  private BigInteger _status;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_raiseId);
    args.add(_status);
    return args;
  }
}
