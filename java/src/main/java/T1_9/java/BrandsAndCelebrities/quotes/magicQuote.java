package T1_9.java.BrandsAndCelebrities.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class magicQuote {
  private static int hc = 0;
  private static magicQuote instance = null;

  public magicQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static magicQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new magicQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof magicQuote;
  }

  public String toString() {

    return "<magic>";
  }
}
