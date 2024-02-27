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
public class CharityServiceInit_userInputBO {
  private String _userAddress;

  private String _userName;

  private String _cardId;

  private String _designation;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_userAddress);
    args.add(_userName);
    args.add(_cardId);
    args.add(_designation);
    return args;
  }
}
