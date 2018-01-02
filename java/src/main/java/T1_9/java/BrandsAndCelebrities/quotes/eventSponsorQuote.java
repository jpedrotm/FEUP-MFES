package sadf.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class eventSponsorQuote {
  private static int hc = 0;
  private static eventSponsorQuote instance = null;

  public eventSponsorQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static eventSponsorQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new eventSponsorQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof eventSponsorQuote;
  }

  public String toString() {

    return "<eventSponsor>";
  }
}
