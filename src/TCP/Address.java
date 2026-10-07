package TCP;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 20180801L;
    private int id;
    private String code, addressLine, city, postalCode;
    public String getAddressLine() { return addressLine; }
    public void setAddressLine(String addressLine) { this.addressLine = addressLine; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
}
