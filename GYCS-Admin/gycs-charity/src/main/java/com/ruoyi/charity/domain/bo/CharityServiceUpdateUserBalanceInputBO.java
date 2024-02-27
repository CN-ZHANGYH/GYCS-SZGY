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
public class CharityServiceUpdateUserBalanceInputBO {
  private String _userAddress;

  private BigInteger _balance;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_userAddress);
    args.add(_balance);
    return args;
  }
}
