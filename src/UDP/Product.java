package UDP;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 20161107;
    public String id, code, name;
    public int quantity;
}
