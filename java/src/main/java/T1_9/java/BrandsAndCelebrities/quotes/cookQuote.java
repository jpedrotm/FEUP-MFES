package sadf.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class cookQuote {
  private static int hc = 0;
  private static cookQuote instance = null;

  public cookQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static cookQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new cookQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof cookQuote;
  }

  public String toString() {

    return "<cook>";
  }
}
