package managedModal;

import java.io.Serializable;

public class TestCategory
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer category_id;
  private String category_desc;

  public Integer getCategory_id()
  {
    return this.category_id;
  }

  public void setCategory_id(Integer category_id) {
    this.category_id = category_id;
  }

  public String getCategory_desc() {
    return this.category_desc;
  }

  public void setCategory_desc(String category_desc) {
    this.category_desc = category_desc;
  }
}