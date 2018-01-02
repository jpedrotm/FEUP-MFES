package T1_9.java.BrandsAndCelebrities;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Celebrity {
  private String name;
  private VDMSet type;
  private Number minPrice;
  private Number maxTime;
  private VDMSet roles;
  private VDMSeq contracts = SeqUtil.seq();
  private Number maxContracts = 2L;

  public void cg_init_Celebrity_1(
      final String n,
      final VDMSet celebsTypes,
      final Number minP,
      final Number maxT,
      final VDMSet rs,
      final Number maxC) {

    name = n;
    type = Utils.copy(celebsTypes);
    minPrice = minP;
    maxTime = maxT;
    roles = Utils.copy(rs);
    maxContracts = maxC;
    return;
  }

  public Celebrity(
      final String n,
      final VDMSet celebsTypes,
      final Number minP,
      final Number maxT,
      final VDMSet rs,
      final Number maxC) {

    cg_init_Celebrity_1(n, Utils.copy(celebsTypes), minP, maxT, Utils.copy(rs), maxC);
  }

  public String getName() {

    return name;
  }

  public VDMSet getType() {

    return Utils.copy(type);
  }

  public Number getMinPrice() {

    return minPrice;
  }

  public Number getMaxTime() {

    return maxTime;
  }

  public VDMSet getRoles() {

    return Utils.copy(roles);
  }

  public VDMSeq getContracts() {

    return Utils.copy(contracts);
  }

  public void addContract(final Contract c) {

    contracts = SeqUtil.conc(Utils.copy(contracts), SeqUtil.seq(c));
    contracts = Mergesort.sortContracts(Utils.copy(contracts));
  }

  public void removeContract(final Contract c) {

    VDMSeq tmpContracts = SeqUtil.seq();
    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 = contracts.size() > 0L;
      if (!(whileCond_1)) {
        break;
      }

      {
        if (!(Utils.equals(((Contract) contracts.get(0)), c))) {
          tmpContracts =
              SeqUtil.conc(Utils.copy(tmpContracts), SeqUtil.seq(((Contract) contracts.get(0))));
        }

        contracts = SeqUtil.tail(Utils.copy(contracts));
      }
    }

    contracts = Utils.copy(tmpContracts);
  }

  public Boolean checkIfContractIsGood(final Contract c) {

    Boolean andResult_18 = false;

    if (c.getDurationTime().longValue() <= maxTime.longValue()) {
      Boolean andResult_19 = false;

      if (minPrice.longValue() <= c.getTotalPrice().longValue()) {
        if (contracts.size() < maxContracts.longValue()) {
          andResult_19 = true;
        }
      }

      if (andResult_19) {
        andResult_18 = true;
      }
    }

    if (andResult_18) {
      return true;

    } else {
      Boolean andResult_20 = false;

      if (c.getDurationTime().longValue() <= maxTime.longValue()) {
        Boolean andResult_21 = false;

        if (minPrice.longValue() <= c.getTotalPrice().longValue()) {
          if (hasSpaceForNewContract(c)) {
            andResult_21 = true;
          }
        }

        if (andResult_21) {
          andResult_20 = true;
        }
      }

      if (andResult_20) {
        return true;

      } else {
        return false;
      }
    }
  }

  public Boolean hasSpaceForNewContract(final Contract c) {

    Number numOfDifferentContractDates = 0L;
    for (Iterator iterator_4 = SeqUtil.elems(Utils.copy(contracts)).iterator();
        iterator_4.hasNext();
        ) {
      Contract contract = (Contract) iterator_4.next();
      Boolean orResult_1 = false;

      if (Globals.compareDates(contract.getFinalDate(), c.getStartDate())) {
        orResult_1 = true;
      } else {
        orResult_1 = Globals.compareDates(c.getFinalDate(), contract.getStartDate());
      }

      if (orResult_1) {
        numOfDifferentContractDates = numOfDifferentContractDates.longValue() + 1L;
      }
    }
    return contracts.size() - numOfDifferentContractDates.longValue() < maxContracts.longValue();
  }

  public Number numActiveContracts() {

    VDMSet setCompResult_1 = SetUtil.set();
    VDMSet set_1 = SeqUtil.elems(Utils.copy(contracts));
    for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext(); ) {
      Contract contract = ((Contract) iterator_1.next());
      Boolean andResult_22 = false;

      if (Globals.compareDates(contract.getStartDate(), Utils.copy(Platform.currentDate))) {
        if (Globals.compareDates(Utils.copy(Platform.currentDate), contract.getFinalDate())) {
          andResult_22 = true;
        }
      }

      if (andResult_22) {
        setCompResult_1.add(contract);
      }
    }
    return setCompResult_1.size();
  }

  public Celebrity() {}

  public String toString() {

    return "Celebrity{"
        + "name := "
        + Utils.toString(name)
        + ", type := "
        + Utils.toString(type)
        + ", minPrice := "
        + Utils.toString(minPrice)
        + ", maxTime := "
        + Utils.toString(maxTime)
        + ", roles := "
        + Utils.toString(roles)
        + ", contracts := "
        + Utils.toString(contracts)
        + ", maxContracts := "
        + Utils.toString(maxContracts)
        + "}";
  }
}
