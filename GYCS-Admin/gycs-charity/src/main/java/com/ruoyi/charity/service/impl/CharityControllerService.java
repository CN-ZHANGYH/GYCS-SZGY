package com.ruoyi.charity.service.impl;


import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import com.ruoyi.charity.domain.bo.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class CharityControllerService {

  public static final String SUCCESS = "Success";
  public static final String ABI = com.ruoyi.charity.utils.IOUtil.readResourceAsString("abi/CharityController.abi");

  public static final String BINARY = com.ruoyi.charity.utils.IOUtil.readResourceAsString("bin/ecc/CharityController.bin");

  public static final String SM_BINARY = com.ruoyi.charity.utils.IOUtil.readResourceAsString("bin/sm/CharityController.bin");

  @Value("${system.contract.charityControllerAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse InitiateFundRaising(CharityControllerInitiateFundRaisingInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "InitiateFundRaising", input.toArgs());
  }

  public CallResponse bankTransferIndex() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "bankTransferIndex", Arrays.asList());
  }

  public CallResponse donationTraceAddressMap(CharityControllerDonationTraceAddressMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "donationTraceAddressMap", input.toArgs());
  }

  public TransactionResponse updateFundRaisingStatus(CharityControllerUpdateFundRaisingStatusInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "updateFundRaisingStatus", input.toArgs());
  }

  public TransactionResponse withdraw(CharityControllerWithdrawInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "withdraw", input.toArgs());
  }

  public CallResponse getCertificateInfoDetail(CharityControllerGetCertificateInfoDetailInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getCertificateInfoDetail", input.toArgs());
  }

  public CallResponse getFundRaisingOtherInfo(CharityControllerGetFundRaisingOtherInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getFundRaisingOtherInfo", input.toArgs());
  }

  public CallResponse rolesAuth() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "rolesAuth", Arrays.asList());
  }

  public TransactionResponse init_user(CharityControllerInit_userInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "init_user", input.toArgs());
  }

  public TransactionResponse init_logistic(CharityControllerInit_logisticInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "init_logistic", input.toArgs());
  }

  public CallResponse getLogisticInfo(CharityControllerGetLogisticInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getLogisticInfo", input.toArgs());
  }

  public CallResponse RaiseFundBankTransferMap(CharityControllerRaiseFundBankTransferMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "RaiseFundBankTransferMap", input.toArgs());
  }

  public CallResponse charityRaiseFundMap(CharityControllerCharityRaiseFundMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "charityRaiseFundMap", input.toArgs());
  }

  public CallResponse getOrgInfo(CharityControllerGetOrgInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getOrgInfo", input.toArgs());
  }

  public CallResponse orgMap(CharityControllerOrgMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "orgMap", input.toArgs());
  }

  public CallResponse raiseCount() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "raiseCount", Arrays.asList());
  }

  public CallResponse userMap(CharityControllerUserMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "userMap", input.toArgs());
  }

  public TransactionResponse voteOfRaiseFund(CharityControllerVoteOfRaiseFundInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "voteOfRaiseFund", input.toArgs());
  }

  public CallResponse BankTransferRecordMap(CharityControllerBankTransferRecordMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "BankTransferRecordMap", input.toArgs());
  }

  public TransactionResponse InitiateWelfareActivitie(CharityControllerInitiateWelfareActivitieInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "InitiateWelfareActivitie", input.toArgs());
  }

  public CallResponse getWelfareActivitieOtherInfo(CharityControllerGetWelfareActivitieOtherInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getWelfareActivitieOtherInfo", input.toArgs());
  }

  public TransactionResponse addLogisticsTrace(CharityControllerAddLogisticsTraceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "addLogisticsTrace", input.toArgs());
  }

  public CallResponse orgCount() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "orgCount", Arrays.asList());
  }

  public CallResponse charityTraceAddressMap(CharityControllerCharityTraceAddressMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "charityTraceAddressMap", input.toArgs());
  }

  public CallResponse getVoteInfo(CharityControllerGetVoteInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getVoteInfo", input.toArgs());
  }

  public TransactionResponse getWelfareActivitieInfoDetail(CharityControllerGetWelfareActivitieInfoDetailInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "getWelfareActivitieInfoDetail", input.toArgs());
  }

  public TransactionResponse scoringOfRaiseFund(CharityControllerScoringOfRaiseFundInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "scoringOfRaiseFund", input.toArgs());
  }

  public TransactionResponse getDonationTaceByBankTransfer(CharityControllerGetDonationTaceByBankTransferInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "getDonationTaceByBankTransfer", input.toArgs());
  }

  public CallResponse BankTransferIdsMap(CharityControllerBankTransferIdsMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "BankTransferIdsMap", input.toArgs());
  }

  public TransactionResponse selectDonationTraceInfo(CharityControllerSelectDonationTraceInfoInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "selectDonationTraceInfo", input.toArgs());
  }

  public TransactionResponse donatedMaterials(CharityControllerDonatedMaterialsInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "donatedMaterials", input.toArgs());
  }

  public TransactionResponse getRaiseFundBankTransferRecord(CharityControllerGetRaiseFundBankTransferRecordInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "getRaiseFundBankTransferRecord", input.toArgs());
  }

  public CallResponse logisticCount() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "logisticCount", Arrays.asList());
  }

  public TransactionResponse updateUserBalance(CharityControllerUpdateUserBalanceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "updateUserBalance", input.toArgs());
  }

  public TransactionResponse addSignTrace(CharityControllerAddSignTraceInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "addSignTrace", input.toArgs());
  }

  public TransactionResponse init_org(CharityControllerInit_orgInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "init_org", input.toArgs());
  }

  public CallResponse userCount() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "userCount", Arrays.asList());
  }

  public CallResponse selectCharityTraceInfo(CharityControllerSelectCharityTraceInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "selectCharityTraceInfo", input.toArgs());
  }

  public TransactionResponse donationByBankTransfer(CharityControllerDonationByBankTransferInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "donationByBankTransfer", input.toArgs());
  }

  public TransactionResponse getFundRaisingInfoDetail(CharityControllerGetFundRaisingInfoDetailInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "getFundRaisingInfoDetail", input.toArgs());
  }

  public CallResponse getFundRaisingApplyStatus(CharityControllerGetFundRaisingApplyStatusInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getFundRaisingApplyStatus", input.toArgs());
  }

  public TransactionResponse scoringOfActivite(CharityControllerScoringOfActiviteInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "scoringOfActivite", input.toArgs());
  }

  public TransactionResponse uploadCertificate(CharityControllerUploadCertificateInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "uploadCertificate", input.toArgs());
  }

  public CallResponse logisticMap(CharityControllerLogisticMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "logisticMap", input.toArgs());
  }

  public CallResponse activiteCount() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "activiteCount", Arrays.asList());
  }

  public CallResponse charityActivitieMap(CharityControllerCharityActivitieMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "charityActivitieMap", input.toArgs());
  }

  public TransactionResponse getUserInfo(CharityControllerGetUserInfoInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "getUserInfo", input.toArgs());
  }

  public TransactionResponse donatedFunds(CharityControllerDonatedFundsInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "donatedFunds", input.toArgs());
  }
}
