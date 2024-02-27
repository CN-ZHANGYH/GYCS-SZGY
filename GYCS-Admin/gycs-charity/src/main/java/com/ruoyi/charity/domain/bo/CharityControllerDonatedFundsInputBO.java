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
public class CharityControllerDonatedFundsInputBO {
  private String _donorAddress;

  private String _destAddress;

  private BigInteger _amount;

  private String _transType;

  private String _source;

  private String _desc;

  private BigInteger _raiseId;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_donorAddress);
    args.add(_destAddress);
    args.add(_amount);
    args.add(_transType);
    args.add(_source);
    args.add(_desc);
    args.add(_raiseId);
    return args;
  }
}
