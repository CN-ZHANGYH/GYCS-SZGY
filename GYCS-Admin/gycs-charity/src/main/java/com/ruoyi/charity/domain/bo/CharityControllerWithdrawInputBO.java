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
public class CharityControllerWithdrawInputBO {
  private BigInteger _raiseId;

  private String _destAddress;

  private BigInteger _withdrawAmount;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_raiseId);
    args.add(_destAddress);
    args.add(_withdrawAmount);
    return args;
  }
}
