package managedModal;

import java.io.Serializable;

public class Staff
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String fullname;

  public String getFullname()
  {
    return this.fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Staff(String fullname) {
    this.fullname = fullname;
  }

  public Staff()
  {
  }
}