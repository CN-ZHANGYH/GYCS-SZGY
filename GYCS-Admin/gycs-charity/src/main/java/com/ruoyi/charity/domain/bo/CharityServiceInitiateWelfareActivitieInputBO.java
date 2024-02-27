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
public class CharityServiceInitiateWelfareActivitieInputBO {
  private String _title;

  private BigInteger _startTime;

  private BigInteger _endTime;

  private String _logisticType;

  private String _logisticAddress;

  private String _lncomeAddress;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_title);
    args.add(_startTime);
    args.add(_endTime);
    args.add(_logisticType);
    args.add(_logisticAddress);
    args.add(_lncomeAddress);
    return args;
  }
}
