package T1_9.java.BrandsAndCelebrities.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class speakerQuote {
  private static int hc = 0;
  private static speakerQuote instance = null;

  public speakerQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static speakerQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new speakerQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof speakerQuote;
  }

  public String toString() {

    return "<speaker>";
  }
}
