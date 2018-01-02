package sadf.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class productPlacementQuote {
  private static int hc = 0;
  private static productPlacementQuote instance = null;

  public productPlacementQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static productPlacementQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new productPlacementQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof productPlacementQuote;
  }

  public String toString() {

    return "<productPlacement>";
  }
}
