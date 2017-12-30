package celebsandbrands;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Platform {
  private VDMSet brands;
  private VDMSet celebs;
  public static Globals.Date currentDate = new Globals.Date(2016L, 1L, 1L);

  public void cg_init_Platform_2(final Globals.Date d) {

    brands = SetUtil.set();
    celebs = SetUtil.set();
    currentDate = Utils.copy(d);
    return;
  }

  public void cg_init_Platform_1() {

    brands = SetUtil.set();
    celebs = SetUtil.set();
    return;
  }

  public Platform() {

    cg_init_Platform_1();
  }

  public Platform(final Globals.Date d) {

    cg_init_Platform_2(Utils.copy(d));
  }

  public VDMSet getBrands() {

    return Utils.copy(brands);
  }

  public VDMSet getCelebs() {

    return Utils.copy(celebs);
  }

  public void addBrand(final Brand brand) {

    brands = SetUtil.union(Utils.copy(brands), SetUtil.set(brand));
  }

  public void createBrand(final String name) {

    addBrand(new Brand(name));
  }

  public void addCelebrity(final Celebrity celeb) {

    celebs = SetUtil.union(Utils.copy(celebs), SetUtil.set(celeb));
  }

  public void createCelebrity(
      final String name,
      final VDMSet celebsTypes,
      final Number minP,
      final Number maxT,
      final VDMSet rs,
      final Number maxC) {

    addCelebrity(new Celebrity(name, Utils.copy(celebsTypes), minP, maxT, Utils.copy(rs), maxC));
  }

  public void removeBrand(final Brand brand) {

    brands = SetUtil.diff(Utils.copy(brands), SetUtil.set(brand));
  }

  public void removeCelebrity(final Celebrity celeb) {

    celebs = SetUtil.diff(Utils.copy(celebs), SetUtil.set(celeb));
  }

  public void step(final Globals.Date d) {

    currentDate = Utils.copy(d);
    updatePlatform();
  }

  public void step() {

    currentDate = Globals.calculateFinalDate(Utils.copy(Platform.currentDate), 1L);
    updatePlatform();
  }

  public void updatePlatform() {

    for (Iterator iterator_5 = celebs.iterator(); iterator_5.hasNext(); ) {
      Celebrity c = (Celebrity) iterator_5.next();
      VDMSeq contracts = c.getContracts();
      for (Iterator iterator_6 = SeqUtil.elems(Utils.copy(contracts)).iterator();
          iterator_6.hasNext();
          ) {
        Contract cns = (Contract) iterator_6.next();
        if (Globals.compareDates(cns.getFinalDate(), Utils.copy(Platform.currentDate))) {
          c.removeContract(cns);
        }
      }
    }
  }

  public String toString() {

    return "Platform{"
        + "brands := "
        + Utils.toString(brands)
        + ", celebs := "
        + Utils.toString(celebs)
        + ", currentDate := "
        + Utils.toString(currentDate)
        + "}";
  }
}
