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
public class CharityControllerScoringOfActiviteInputBO {
  private BigInteger _activitieId;

  private BigInteger _score;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_activitieId);
    args.add(_score);
    return args;
  }
}
