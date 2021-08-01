package managedModal;

import java.io.Serializable;

public class LabTest extends TestCategory
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer request_id;
  private Integer test_id;
  private String test_name;
  private String test_normal_values;
  private String requested_by;
  private Integer price;

    public String getRequested_by() {
        return requested_by;
    }

    public void setRequested_by(String requested_by) {
        this.requested_by = requested_by;
    }

  
  public Integer getRequest_id()
  {
    return this.request_id;
  }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

  public void setRequest_id(Integer request_id) {
    this.request_id = request_id;
  }

  public Integer getTest_id() {
    return this.test_id;
  }

  public void setTest_id(Integer test_id) {
    this.test_id = test_id;
  }

  public String getTest_name() {
    return this.test_name;
  }

  public void setTest_name(String test_name) {
    this.test_name = test_name;
  }

  public String getTest_normal_values() {
    return this.test_normal_values;
  }

  public void setTest_normal_values(String test_normal_values) {
    this.test_normal_values = test_normal_values;
  }
}