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
public class CharityControllerDonatedMaterialsInputBO {
  private String _userAddress;

  private BigInteger _materialType;

  private String _materialName;

  private BigInteger _materialCount;

  private String _logisticAddress;

  private String _destAddress;

  private BigInteger _activiteId;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_userAddress);
    args.add(_materialType);
    args.add(_materialName);
    args.add(_materialCount);
    args.add(_logisticAddress);
    args.add(_destAddress);
    args.add(_activiteId);
    return args;
  }
}
