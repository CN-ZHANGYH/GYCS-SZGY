package com.ruoyi.charity.domain.bo;

import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharityTraceUpdateLogisticInfoInputBO {
  private String _logisticAddress;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_logisticAddress);
    return args;
  }
}
