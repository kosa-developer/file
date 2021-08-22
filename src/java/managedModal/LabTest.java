package managedModal;

import java.io.Serializable;

public class LabTest 
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer request_id,category_id;
  private Integer test_id;
  private String test_name,category_desc;
  private String test_normal_values;
  private String requested_by;
  private Integer price;

    public String getRequested_by() {
        return requested_by;
    }

    public void setRequested_by(String requested_by) {
        this.requested_by = requested_by;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_desc() {
        return category_desc;
    }

    public void setCategory_desc(String category_desc) {
        this.category_desc = category_desc;
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
  public LabTest(){}
  public LabTest(Integer request_id,Integer test_id,String category_desc,String test_name,Integer price,String requested_by){
  this.request_id=request_id;
  this.test_id=test_id;
  this.test_name=test_name;
  this.test_normal_values=test_normal_values;
  this.requested_by=requested_by;
  this.price=price;
  this.category_desc=category_desc;
  
  }
}