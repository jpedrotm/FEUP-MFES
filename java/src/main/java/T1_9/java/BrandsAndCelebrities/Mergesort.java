package sadf;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Mergesort {
  private static VDMSeq getPartContracts(final VDMSeq c, final Number i, final Number j) {

    VDMSeq result = SeqUtil.seq();
    Number k = i;
    Boolean whileCond_3 = true;
    while (whileCond_3) {
      whileCond_3 = k.longValue() <= j.longValue();
      if (!(whileCond_3)) {
        break;
      }

      {
        result = SeqUtil.conc(Utils.copy(result), SeqUtil.seq(((Contract) Utils.get(c, k))));
        k = k.longValue() + 1L;
      }
    }

    return Utils.copy(result);
  }

  public Mergesort() {}

  public static VDMSeq sortContracts(final VDMSeq s) {

    return Mergesort(Utils.copy(s));
  }

  private static VDMSeq Mergesort(final VDMSeq s) {

    VDMSeq casesExpResult_1 = null;

    Number casesExp_1 = s.size();
    Number intPattern_1 = casesExp_1;
    Boolean success_2 = Utils.equals(intPattern_1, 0L);

    if (!(success_2)) {
      Number intPattern_2 = casesExp_1;
      success_2 = Utils.equals(intPattern_2, 1L);

      if (success_2) {
        casesExpResult_1 = Utils.copy(s);
      } else {
        {
          final Number halfSize = Math.round(Utils.floor(Utils.divide((1.0 * s.size()), 2L)));
          final VDMSeq s1 = getPartContracts(Utils.copy(s), 1L, halfSize);
          final VDMSeq s2 = getPartContracts(Utils.copy(s), halfSize.longValue() + 1L, s.size());

          casesExpResult_1 = Merge(Mergesort(Utils.copy(s1)), Mergesort(Utils.copy(s2)));
        }
      }

    } else {
      casesExpResult_1 = Utils.copy(s);
    }

    return Utils.copy(casesExpResult_1);
  }

  private static VDMSeq Merge(final VDMSeq s1, final VDMSeq s2) {

    throw new UnsupportedOperationException();
  }

  public String toString() {

    return "Mergesort{}";
  }
}
