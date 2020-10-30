package com.pyas.test.util;

import java.sql.*;
import java.util.UUID;

public class TestData {
    private static final String URL = "jdbc:oracle:thin:@172.16.90.152:1521/market";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String USERNAME = "ec_clear";
    private static final String PASSWORD = "ec_clear";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection conn = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        //建立Statement对象
        //建立ProparedStatement对象
        String uuid = UUID.randomUUID().toString();

        String app_sheet_serial_no = "'669948691448070144000001'";
        String ta_serial_no = "'202010190000000001'";
        String ta_no = "'88'";
        String transaction_cfm_date = "'20201019'";

        String investor_id = "'38700000000000684655'";

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql1 = "insert into E_ACK_TRADE (TA_SERIAL_NO, APP_SHEET_SERIAL_NO, TA_NO, FUND_CODE, RETURN_CODE, TRANSACTION_ACCOUNT_ID, DISTRIBUTOR_CODE, APPLICATION_AMOUNT, BUSINESS_CODE, TA_ACCOUNT_ID, DEPOSIT_ACCT, REGION_CODE, TRANSACTION_CFM_DATE, DOWN_LOAD_DATE, CHARGE, AGENCY_FEE, CONFIRMED_VOL, CONFIRMED_AMOUNT, NAV, BRANCH_CODE, TRANSACTION_DATE, TRANSACTION_TIME, INDIVIDUAL_OR_INSTITUTION, APPLICATION_VOL, TRADING_PRICE, STAMP_DUTY, VALID_PERIOD, TRANSFER_FEE, FROM_TA_FLAG, SHARE_CLASS, FEE_CALCULATOR, FUTURE_SUBSCRIBE_DATE, CURRENCY_TYPE, BUSINESS_FINISH_FLAG, DISCOUNT_RATE_OF_COMMISSION, DATE_OF_PERIODIC_SUBS, ORIGINAL_APP_SHEET_NO, OTHER_FEE1, RATE_FEE, LARGE_BUY_FLAG, VARIETY_CODE_OF_PERIODIC_SUBS, SERIAL_NO_OF_PERIODIC_SUBS, LARGE_REDEMPTION_FLAG, ORIGINAL_SERIAL_NO, ORIGINAL_SUBS_DATE, REDEMPTION_DATE_IN_ADVANCE, TOTAL_BACKEND_LOAD, ORIGINAL_CFM_DATE, REDEMPTION_REASON, DETAIL_FLAG, BREACH_FEE, BREACH_FEE_BACK_TO_FUND, PUNISH_FEE, ACHIEVEMENT_PAY, ACHIEVEMENT_COMPEN, UNDISTRIBUTE_MONETARY_INCOME, UNDISTR_MONETARY_INCOME_FLAG, TAKE_INCOME_FLAG, FORCE_REDEMPTION_TYPE, TARGET_DISTRIBUTOR_CODE, TRANSFER_DIRECTION, TARGET_BRANCH_CODE, TARGET_TRANSACTION_ACCOUNT_ID, TARGET_REGION_CODE, SHARE_REGISTER_DATE, DEF_DIVIDEND_METHOD, DIVIDEND_RATIO, TARGET_TA_ACCOUNT_ID, SPECIFICATION, CODE_OF_TARGET_FUND, CFM_VOL_OF_TARGET_FUND, TAX, TARGET_NAV, TARGET_FUND_PRICE, MIN_FEE, TARGET_REGISTRAR_CODE, TARGET_SHARE_TYPE, CHANGE_FEE, RECUPERATE_FEE, BACKEN_LOAD_DISCOUNT, CHANGE_AGENCY_FEE, RECUPERATE_AGENCY_FEE, BASIS_FOR_CALCULATING_DIVIDEND, VOL_OF_DIVIDEND_REINVESTMENT, DIVIDENT_DATE, DIVIDEND_AMOUNT, XRDATE, REGISTRATION_DATE, DIVIDEND_PER_UNIT, TOTAL_FROZEN_VOL, OTHER_FEE2, FROZEN_BALANCE, DRAW_BONUS_UNIT, FROZEN_SHARESFOR_REINVEST, DIVIDEND_TYPE, SETTLE_STATUS, DEAL_STATUS, WORK_DATE, MAPP_SHEET_SERIAL_NO, MTRANSACTION_ACCOUNT_ID) values (" + ta_serial_no + ", " + app_sheet_serial_no + "," + ta_no + " , '003536', '0000', '38700000000682965', '387', 0.00, '124', '641A03944211', null, '02', " + transaction_cfm_date + ", '20201019', 0.00, 0.00, 100.00, 100.00, 1.0000, '387', '20200812', '220020', '1', 100.00, 1.0000, 0.00, 0, 0.00, '0', '0', null, null, '156', '1', 0.0000, '20200813', null, 0.00, 0.00000000, null, null, null, '0', null, null, null, 0.00, null, null, '0', 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, null, null, null, null, null, null, null, null, '20201019', null, 0.00, null, null, null, 0.00, 0.00, 0.0000, 0.0000, 0.00, null, null, 0.00, 0.00, null, 0.00, 0.00, null, null, null, null, null, null, null, 0.00, 0.00, 0.00, null, null, null, 'Y', null, '20201018', '202008122200201035540', '360000006000935093010003')";
            stmt.executeUpdate(sql1);

            String sql2 = "insert into APP_TRADE (APP_SHEET_SERIAL_NO, INVESTOR_ID, TA_ACCOUNT_ID, TRANSACTION_ACCOUNT_ID, INDIVIDUAL_OR_INSTITUTION, TA_NO, DISTRIBUTOR_CODE, MERCHANT_CODE, WORK_DATE, BUSINESS_CODE, FUND_CODE, CURRENCY_TYPE, APPLICATION_VOL, APPLICATION_AMOUNT, CONFIRMED_AMOUNT, CONFIRMED_VOL, TRANSACTION_DATE, TRANSACTION_TIME, BRANCH_CODE, REGION_CODE, ACCEPT_METHOD, ORIGINAL_APP_SHEET_NO, CODE_OF_TARGET_FUND, TARGET_DISTRIBUTOR_CODE, TARGET_BRANCH_CODE, TARGET_TA_ACCOUNT_ID, TARGET_TRANSACTION_ACCOUNT_ID, LARGE_FLAG, TAKE_INCOME_FLAG, MAPP_SHEET_SERIAL_NO, MTRANSACTION_ACCOUNT_ID, APP_FROM_FLAG, APPLY_STATUS, EXPORT_STATUS, RETURN_CODE, RETURN_MSG, TRANSACTION_CFM_DATE, INSERT_TIME, UPDATE_TIME, VERSION, TARGET_SHARE_TYPE, SHARE_CLASS, DIVIDEND_RATIO, TA_SERIAL_NO, BANK_NO) values (" + app_sheet_serial_no + ", " + investor_id + ", null, '38700000000682962', '1', '64', '387', '3870', '20201019', '098', '003536', '156', 100.00, 0.00, 0.00, 100.00, '20201019', '053003', '387', '02', '2', null, null, '387', '387', '640000000055', '0108A01515939', null, null, '202010180530091035099', '360000006000935069010003', null, 'A', 'Y', '0000', null, " + transaction_cfm_date + ", sysdate,sysdate, 0, 'A', 'A', 100.00, '202008140000028872', '20')";
            stmt.executeUpdate(sql2);
            conn.commit();
        } catch (SQLException e) {
            System.out.println("执行失败！" + e);
        }finally {
            stmt.close();
            conn.close();
        }




           /* try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO PUBLISHER (CODE, PUBLISHER_NAME) VALUES (?, ?)")) {
                stmt.executeUpdate();
            }
            // stmt is auto closed here, even if SQLException is thrown

            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO BOOK (ISBN, BOOK_NAME, PUBLISHER_CODE) VALUES (?, ?, ?)")){

                 stmt.executeUpdate();

             }*/


}
}
