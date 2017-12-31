package T1_9.java.BrandsAndCelebrities;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Project {
  private String name;
  private VDMSet contracts;
  private Number maxNumContracts;
  private VDMMap desiredNumPerRole;
  private VDMMap maxPricePerRole;
  private VDMSet desiredCelebTypes;
  private Number duration;

  public void cg_init_Project_1(
      final String n,
      final Number m,
      final VDMMap d,
      final VDMMap b,
      final VDMSet ct,
      final Number t) {

    name = n;
    contracts = SetUtil.set();
    maxNumContracts = m;
    desiredNumPerRole = Utils.copy(d);
    maxPricePerRole = Utils.copy(b);
    desiredCelebTypes = Utils.copy(ct);
    duration = t;
    return;
  }

  public Project(
      final String n,
      final Number m,
      final VDMMap d,
      final VDMMap b,
      final VDMSet ct,
      final Number t) {

    cg_init_Project_1(n, m, Utils.copy(d), Utils.copy(b), Utils.copy(ct), t);
  }

  public String getName() {

    return name;
  }

  public VDMSet getContracts() {

    return Utils.copy(contracts);
  }

  public Number getNumContracts() {

    return contracts.size();
  }

  public Number getMaxNumContracts() {

    return maxNumContracts;
  }

  public void addContract(final Contract c) {

    contracts = SetUtil.union(Utils.copy(contracts), SetUtil.set(c));
  }

  public Number getDuration() {

    return duration;
  }

  public VDMMap getDesiredNumPerRole() {

    return Utils.copy(desiredNumPerRole);
  }

  public Number getDesiredNumForRole(final Object role) {

    if (!(SetUtil.inSet(role, MapUtil.dom(Utils.copy(desiredNumPerRole))))) {
      return 0L;

    } else {
      return ((Number) Utils.get(desiredNumPerRole, role));
    }
  }

  public Number getTotalNumDesiredRoles() {

    return getSumDesiredRoles(
        MapUtil.dom(Utils.copy(desiredNumPerRole)), Utils.copy(desiredNumPerRole));
  }

  public Number getSumDesiredRoles(final VDMSet roles, final VDMMap numRoleMap) {

    if (Utils.empty(roles)) {
      return 0L;

    } else {
      Number letBeStExp_1 = null;
      Object role = null;
      Boolean success_1 = false;
      VDMSet set_2 = Utils.copy(roles);
      for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext() && !(success_1); ) {
        role = ((Object) iterator_2.next());
        success_1 = true;
      }
      if (!(success_1)) {
        throw new RuntimeException("Let Be St found no applicable bindings");
      }

      letBeStExp_1 =
          ((Number) Utils.get(numRoleMap, role)).longValue()
              + getSumDesiredRoles(
                      SetUtil.diff(Utils.copy(roles), SetUtil.set(role)), Utils.copy(numRoleMap))
                  .longValue();
      return letBeStExp_1;
    }
  }

  public VDMSet getDesiredRoles() {

    return MapUtil.dom(Utils.copy(desiredNumPerRole));
  }

  public VDMSet getDesiredCelebTypes() {

    return Utils.copy(desiredCelebTypes);
  }

  public Number getMaxPriceForRole(final Object role) {

    if (!(SetUtil.inSet(role, MapUtil.dom(Utils.copy(maxPricePerRole))))) {
      return 0L;

    } else {
      return ((Number) Utils.get(maxPricePerRole, role));
    }
  }

  public VDMMap getBudgetPerRole() {

    return Utils.copy(maxPricePerRole);
  }

  public Number getTotalNumBudgetedRoles() {

    return MapUtil.dom(Utils.copy(maxPricePerRole)).size();
  }

  public VDMSet getBudgetedRoles() {

    return MapUtil.dom(Utils.copy(maxPricePerRole));
  }

  public VDMSet getAppropriateCelebs(final VDMSet celebs) {

    VDMSet setCompResult_2 = SetUtil.set();
    VDMSet set_3 = Utils.copy(celebs);
    for (Iterator iterator_3 = set_3.iterator(); iterator_3.hasNext(); ) {
      Celebrity celeb = ((Celebrity) iterator_3.next());
      Boolean andResult_45 = false;

      if (!(Utils.empty(SetUtil.intersect(celeb.getRoles(), getDesiredRoles())))) {
        if (!(Utils.empty(SetUtil.intersect(celeb.getType(), getDesiredCelebTypes())))) {
          andResult_45 = true;
        }
      }

      if (andResult_45) {
        setCompResult_2.add(celeb);
      }
    }
    return Utils.copy(setCompResult_2);
  }

  public Boolean proposeContract(final Contract contract, final Celebrity celeb) {

    Boolean andResult_46 = false;

    if (celeb.hasSpaceForNewContract(contract)) {
      if (celeb.checkIfContractIsGood(contract)) {
        andResult_46 = true;
      }
    }

    if (!(andResult_46)) {
      return false;
    }

    addContract(contract);
    celeb.addContract(contract);
    return true;
  }

  public Project() {}

  public String toString() {

    return "Project{"
        + "name := "
        + Utils.toString(name)
        + ", contracts := "
        + Utils.toString(contracts)
        + ", maxNumContracts := "
        + Utils.toString(maxNumContracts)
        + ", desiredNumPerRole := "
        + Utils.toString(desiredNumPerRole)
        + ", maxPricePerRole := "
        + Utils.toString(maxPricePerRole)
        + ", desiredCelebTypes := "
        + Utils.toString(desiredCelebTypes)
        + ", duration := "
        + Utils.toString(duration)
        + "}";
  }
}
