package T1_9.java.BrandsAndCelebrities.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class actorQuote {
  private static int hc = 0;
  private static actorQuote instance = null;

  public actorQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static actorQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new actorQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof actorQuote;
  }

  public String toString() {

    return "<actor>";
  }
}
