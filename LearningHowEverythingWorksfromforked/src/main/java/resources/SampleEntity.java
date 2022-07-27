
package resources;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TODO alsaad This type ...
 *
 */
@Entity
@Table(name = "Sampletable")
public class SampleEntity {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "aname")
  private String aname;

  @Column(name = "bname")
  private String bname;

  @Id
  /**
   * @return id
   */
  public int getId() {

    return this.id;
  }

  /**
   * @param id new value of {@link #getid}.
   */
  public void setId(int id) {

    this.id = id;
  }

  /**
   * @return aname
   */
  public String getAname() {

    return this.aname;
  }

  /**
   * @param aname new value of {@link #getaname}.
   */
  public void setAname(String aname) {

    this.aname = aname;
  }

  /**
   * @return bname
   */
  public String getBname() {

    return this.bname;
  }

  /**
   * @param bname new value of {@link #getbname}.
   */
  public void setBname(String bname) {

    this.bname = bname;
  }

  @Override
  public String toString() {

    return "SampleEntity [id=" + this.id + ", aname=" + this.aname + ", bname=" + this.bname + "]";
  }

}