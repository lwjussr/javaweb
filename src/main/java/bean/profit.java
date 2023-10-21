/**
 * 作者：兰文捷
 * 时间：2023.8.24
 * 功能：利润信息的实体类
 */
package bean;

public class profit {
    //成本
    private double cost;
    //收入
    private double income;
    //利润
    private double profit;
    //发生时间
    private String sellTime;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getSellTime() {
        return sellTime;
    }

    public void setSellTime(String sellTime) {
        this.sellTime = sellTime;
    }

    public profit() {
    }

    public profit(double cost, double income, double profit, String sellTime) {
        this.cost = cost;
        this.income = income;
        this.profit = profit;
        this.sellTime = sellTime;
    }
}
