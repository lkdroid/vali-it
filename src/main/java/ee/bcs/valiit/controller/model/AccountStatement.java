package ee.bcs.valiit.controller.model;

public class AccountStatement {

    private String actionaccnr;
    private String actiontype;
    private Double sum;
    private Double newbalance;
    private Integer clientid;

    public String getActionaccnr() {
        return actionaccnr;
    }

    public void setActionaccnr(String actionaccnr) {
        this.actionaccnr = actionaccnr;
    }

    public String getActiontype() {
        return actiontype;
    }

    public void setActiontype(String actiontype) {
        this.actiontype = actiontype;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getNewbalance() {
        return newbalance;
    }

    public void setNewbalance(Double newbalance) {
        this.newbalance = newbalance;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }
}
