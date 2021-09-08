package ee.bcs.valiit.controller.model;

public class AccountOverview {

        private String firstName;
        private String lastName;
        private String address;
        private String accnr;
        private String balance;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccnr() {
        return accnr;
    }

    public void setAccnr(String accnr) {
        this.accnr = accnr;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
