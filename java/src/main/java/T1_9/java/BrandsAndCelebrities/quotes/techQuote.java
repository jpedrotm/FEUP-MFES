package T1_9.java.BrandsAndCelebrities.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class techQuote {
  private static int hc = 0;
  private static techQuote instance = null;

  public techQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static techQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new techQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof techQuote;
  }

  public String toString() {

    return "<tech>";
  }
}
