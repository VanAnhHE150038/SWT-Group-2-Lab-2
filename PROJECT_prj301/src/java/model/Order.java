
package model;


public class Order {
      private int id;
      private String date;
      private String cusName;
      private double totalmoney;
      private String status;
    public Order() {
    }
    public Order(int id,String date, String cusName,  double totalmoney, String status) {
        this.id = id;
        this.cusName = cusName;
        this.date = date;
        this.totalmoney = totalmoney;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }

      
}
