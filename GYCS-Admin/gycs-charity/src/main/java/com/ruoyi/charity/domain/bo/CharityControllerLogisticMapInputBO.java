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
public class CharityControllerLogisticMapInputBO {
  private String arg0;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(arg0);
    return args;
  }
}