package ee.bcs.valiit.controller.model;

public class OneRental {

    private String carNumber;
    private String clientFullname;
    private String rentFrom;
    private String rentTo;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }


    public String getClientFullname() {
        return clientFullname;
    }

    public void setClientFullname(String clientFullname) {
        this.clientFullname = clientFullname;
    }

    public String getRentFrom() {
        return rentFrom;
    }

    public void setRentFrom(String rentFrom) {
        this.rentFrom = rentFrom;
    }

    public String getRentTo() {
        return rentTo;
    }

    public void setRentTo(String rentTo) {
        this.rentTo = rentTo;
    }
}
