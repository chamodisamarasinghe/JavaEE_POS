package dto;

public class CustomerDTO {
    private String cusId;
    private String cusName;
    private String cusAddress;
    private String cusSalary;

    public CustomerDTO(String id, String name, String address, double salary) {
    }

    public CustomerDTO(String cusId, String cusName, String cusAddress, String cusSalary) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.cusSalary = cusSalary;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusSalary() {
        return cusSalary;
    }

    public void setCusSalary(String cusSalary) {
        this.cusSalary = cusSalary;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusAddress='" + cusAddress + '\'' +
                ", cusSalary='" + cusSalary + '\'' +
                '}';
    }
}
