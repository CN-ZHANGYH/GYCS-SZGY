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
public class CharityControllerInitiateFundRaisingInputBO {
  private String _title;

  private String _desc;

  private String _promoterAddress;

  private BigInteger _startTime;

  private BigInteger _endTime;

  private BigInteger _totalAmount;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_title);
    args.add(_desc);
    args.add(_promoterAddress);
    args.add(_startTime);
    args.add(_endTime);
    args.add(_totalAmount);
    return args;
  }
}
