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
public class UserServiceInit_logisticInputBO {
  private String _logistcAddress;

  private String _logisticName;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_logistcAddress);
    args.add(_logisticName);
    return args;
  }
}
