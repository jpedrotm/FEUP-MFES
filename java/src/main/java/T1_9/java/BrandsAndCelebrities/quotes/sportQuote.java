package sadf.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class sportQuote {
  private static int hc = 0;
  private static sportQuote instance = null;

  public sportQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static sportQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new sportQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof sportQuote;
  }

  public String toString() {

    return "<sport>";
  }
}
