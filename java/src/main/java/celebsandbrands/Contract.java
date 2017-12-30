package celebsandbrands;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Contract {
  private Number durationTime;
  private Globals.Date startDate;
  private Globals.Date finalDate;
  private Brand brand;
  private Project project;
  private Object role;
  private Celebrity celebrity = null;
  private Number totalPrice;

  public void cg_init_Contract_1(
      final Number durationT,
      final Brand b,
      final Project p,
      final Celebrity c,
      final Number price,
      final Globals.Date sDate,
      final Object r) {

    durationTime = durationT;
    brand = b;
    project = p;
    celebrity = c;
    totalPrice = price;
    startDate = Utils.copy(sDate);
    finalDate = Globals.calculateFinalDate(Utils.copy(startDate), durationTime);
    role = r;
    return;
  }

  public Contract(
      final Number durationT,
      final Brand b,
      final Project p,
      final Celebrity c,
      final Number price,
      final Globals.Date sDate,
      final Object r) {

    cg_init_Contract_1(durationT, b, p, c, price, Utils.copy(sDate), r);
  }

  public Number getDurationTime() {

    return durationTime;
  }

  public Brand getBrand() {

    return brand;
  }

  public Project getProject() {

    return project;
  }

  public Celebrity getCelebrity() {

    return celebrity;
  }

  public Number getTotalPrice() {

    return totalPrice;
  }

  public Globals.Date getStartDate() {

    return Utils.copy(startDate);
  }

  public Globals.Date getFinalDate() {

    return Utils.copy(finalDate);
  }

  public Contract() {}

  private static Boolean projectExistsInBrand(final Brand brnd, final Project proj) {

    return SetUtil.inSet(proj, brnd.projects);
  }

  public String toString() {

    return "Contract{"
        + "durationTime := "
        + Utils.toString(durationTime)
        + ", startDate := "
        + Utils.toString(startDate)
        + ", finalDate := "
        + Utils.toString(finalDate)
        + ", brand := "
        + Utils.toString(brand)
        + ", project := "
        + Utils.toString(project)
        + ", role := "
        + Utils.toString(role)
        + ", celebrity := "
        + Utils.toString(celebrity)
        + ", totalPrice := "
        + Utils.toString(totalPrice)
        + "}";
  }
}
