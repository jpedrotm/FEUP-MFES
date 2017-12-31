package T1_9.java.BrandsAndCelebrities.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class musicQuote {
  private static int hc = 0;
  private static musicQuote instance = null;

  public musicQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static musicQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new musicQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof musicQuote;
  }

  public String toString() {

    return "<music>";
  }
}
