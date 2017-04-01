package com.psi.androidhttpclient.wfss.buss.funds.model.querymultiplefund;

import java.util.List;

/**
 * 3.1 基金列表查询
 * Created by gwluo on 2016/10/25.
 */

public class WFSSQueryMultipleFundResult {
    private String isNextPage;//	是否还有下一页 Y-有下一页 N-没有下一页
    private List<FundList> items;//	基金列表	body
    private String changeOfWeek;//周涨跌幅

    public String getChangeOfWeek() {
        return changeOfWeek;
    }

    public void setChangeOfWeek(String changeOfWeek) {
        this.changeOfWeek = changeOfWeek;
    }

    public String getIsNextPage() {
        return isNextPage;
    }

    public void setIsNextPage(String isNextPage) {
        this.isNextPage = isNextPage;
    }

    public List<FundList> getItems() {
        return items;
    }

    public void setItems(List<FundList> items) {
        this.items = items;
    }

    public class FundList {
        /*基金内部代码，用于系统之间对接使用，可唯一代表一只基金，基金详情查询时需要上送此字段*/
        private String fundId;
        private String fundBakCode;//	基金公共代码
        private String fundName;//	基金名称
        private String fundType;//	基金类型
        private String jzTime;//	单位净值时间
        private String fundStatus;//	基金状态
        private String transCurrency;//	交易币种
        private String levelOfRisk;//	风险级别
        private String fundCompany;//	基金公司
        private String dwjz;//	单位净值
        private String currPercentDiff;//	日涨跌幅
        private String changeOfWeek;//	周涨跌幅
        private String changeOfMonth;//	月涨跌幅
        private String changeOfQuarter;//	季涨跌幅
        private String changeOfHalfYear;//	半年涨跌幅
        private String changeOfYear;//	年涨跌幅
        private String thisYearPriceChange;//	今年以来涨跌幅
        private String yieldOfWeek;//	七日年化收益率
        private String yieldOfTenThousand;//	万份收益
        private String productType;//	产品种类

        public String getFundId() {
            return fundId;
        }

        public void setFundId(String fundId) {
            this.fundId = fundId;
        }

        public String getFundBakCode() {
            return fundBakCode;
        }

        public void setFundBakCode(String fundBakCode) {
            this.fundBakCode = fundBakCode;
        }

        public String getFundName() {
            return fundName;
        }

        public void setFundName(String fundName) {
            this.fundName = fundName;
        }

        public String getFundType() {
            return fundType;
        }

        public void setFundType(String fundType) {
            this.fundType = fundType;
        }

        public String getJzTime() {
            return jzTime;
        }

        public void setJzTime(String jzTime) {
            this.jzTime = jzTime;
        }

        public String getFundStatus() {
            return fundStatus;
        }

        public void setFundStatus(String fundStatus) {
            this.fundStatus = fundStatus;
        }

        public String getTransCurrency() {
            return transCurrency;
        }

        public void setTransCurrency(String transCurrency) {
            this.transCurrency = transCurrency;
        }

        public String getLevelOfRisk() {
            return levelOfRisk;
        }

        public void setLevelOfRisk(String levelOfRisk) {
            this.levelOfRisk = levelOfRisk;
        }

        public String getFundCompany() {
            return fundCompany;
        }

        public void setFundCompany(String fundCompany) {
            this.fundCompany = fundCompany;
        }

        public String getDwjz() {
            return dwjz;
        }

        public void setDwjz(String dwjz) {
            this.dwjz = dwjz;
        }

        public String getCurrPercentDiff() {
            return currPercentDiff;
        }

        public void setCurrPercentDiff(String currPercentDiff) {
            this.currPercentDiff = currPercentDiff;
        }

        public String getChangeOfMonth() {
            return changeOfMonth;
        }

        public void setChangeOfMonth(String changeOfMonth) {
            this.changeOfMonth = changeOfMonth;
        }

        public String getChangeOfQuarter() {
            return changeOfQuarter;
        }

        public void setChangeOfQuarter(String changeOfQuarter) {
            this.changeOfQuarter = changeOfQuarter;
        }

        public String getChangeOfHalfYear() {
            return changeOfHalfYear;
        }

        public void setChangeOfHalfYear(String changeOfHalfYear) {
            this.changeOfHalfYear = changeOfHalfYear;
        }

        public String getChangeOfYear() {
            return changeOfYear;
        }

        public void setChangeOfYear(String changeOfYear) {
            this.changeOfYear = changeOfYear;
        }

        public String getThisYearPriceChange() {
            return thisYearPriceChange;
        }

        public void setThisYearPriceChange(String thisYearPriceChange) {
            this.thisYearPriceChange = thisYearPriceChange;
        }

        public String getYieldOfWeek() {
            return yieldOfWeek;
        }

        public void setYieldOfWeek(String yieldOfWeek) {
            this.yieldOfWeek = yieldOfWeek;
        }

        public String getYieldOfTenThousand() {
            return yieldOfTenThousand;
        }

        public void setYieldOfTenThousand(String yieldOfTenThousand) {
            this.yieldOfTenThousand = yieldOfTenThousand;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getChangeOfWeek() {
            return changeOfWeek;
        }

        public void setChangeOfWeek(String changeOfWeek) {
            this.changeOfWeek = changeOfWeek;
        }
    }
}
