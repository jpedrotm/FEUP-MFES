package CelebsAndBrands.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class entertainerQuote {
  private static int hc = 0;
  private static entertainerQuote instance = null;

  public entertainerQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static entertainerQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new entertainerQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof entertainerQuote;
  }

  public String toString() {

    return "<entertainer>";
  }
}
