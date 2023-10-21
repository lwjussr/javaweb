/**
 * 作者：兰文捷
 * 时间：2023.8.23
 * 功能：货物信息的实体类
 */
package bean;

public class goods {
    private int goodId;				    //商品编号
    private String goodName;	        //商品名称
    private String manufacturer;	    //生产厂家
    private float buyPrice;			    //商品进价
    private float sellPrice;			//商品售价
    private int goodNum;				//商品数量

    public goods() {
    }

    public goods(int good_id, String good_name, String manufacturer, float buy_price, float sell_price, int good_num) {
        this.goodId = good_id;
        this.goodName = good_name;
        this.manufacturer = manufacturer;
        this.buyPrice = buy_price;
        this.sellPrice = sell_price;
        this.goodNum = good_num;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }
}
