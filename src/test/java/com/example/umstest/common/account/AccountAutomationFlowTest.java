package com.example.umstest.common.account;


import com.example.umstest.ApiTest;
import com.example.umstest.TestUtil;
import com.example.umstest.common.BaseChecker;
import com.example.umstest.common.enums.AccountEnum;
import com.example.umstest.common.enums.UserEnum;
import com.example.umstest.common.model.Account;
import com.example.umstest.common.model.User;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class AccountAutomationFlowTest extends ApiTest{
    // Khởi tạo account
    private final Account checkingEditAccount = accountHandler.findByName(AccountEnum.ACCOUNT_CHECKING);
    private Account originalAccount;
    // Khởi tạo user Đăng nhâp
    User adminUser = userHandler.findByName(UserEnum.ADMIN_USER);
    private String accountId;
    private int accountOrderNumber;
    AccountCaseTemplate accountCaseTemplate = new AccountCaseTemplate(adminUser);

    @Test(description="Lấy danh sách các dịch vụ hỗ trợ trên hệ thống")
    public void getAllAccountListTest(){
        Response response = accountCaseTemplate.getAllAccountList();
        BaseChecker.assertNotNullResponse(response);

        //Get account id
        List<Account> accounts  = Arrays.asList(response.body().jsonPath().getObject("data",Account[].class));
        accountOrderNumber = TestUtil.getRandomObject(accounts);
        originalAccount = accounts.get(accountOrderNumber);
        accountId  = originalAccount.getId();
    }

    @Test(priority = 1, description="Chỉnh sửa thông tin Account.")
    public void editAccountTest(){
        BaseChecker.assertNotNullResponse( accountCaseTemplate.editAccount(accountId, checkingEditAccount));
    }

    @Test(priority = 2 , description="Kiểm tra thông tin Account.")
    public void checkEditedAccount(){
        Response response = accountCaseTemplate.getAllAccountList();
        BaseChecker.assertNotNullResponse(response);

        //Get account list then check edited information
        List<Account> accounts  = Arrays.asList(response.body().jsonPath().getObject("data",Account[].class));
        BaseChecker.CheckChangedAccountInformation(accounts,accountOrderNumber,checkingEditAccount);
    }
    @Test(priority = 3, description="Trả lại thông tin Account như ban đầu")
    public void revertOriginalData() {
        Response response = accountCaseTemplate.editAccount(accountId, originalAccount);
        BaseChecker.assertNotNullResponse(response);
        response
                .then()
                .assertThat()
                .body("data.fullName", equalTo(originalAccount.getFullName()))
                .body("data.userName", equalTo(originalAccount.getUserName()));
    }
}
