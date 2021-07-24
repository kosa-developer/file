package managedModal;

import java.io.Serializable;

public class Roles
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer rid;
  private String name;
  private String description;

  public Roles()
  {
  }

  public Roles(Integer rid, String name, String description)
  {
    this.rid = rid;
    this.name = name;
    this.description = description;
  }

  public Roles(Integer rid) {
    this.rid = rid;
  }

  public Integer getRid() {
    return this.rid;
  }

  public void setRid(Integer rid) {
    this.rid = rid;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}