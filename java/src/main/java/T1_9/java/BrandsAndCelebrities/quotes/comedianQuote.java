package T1_9.java.BrandsAndCelebrities.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class comedianQuote {
  private static int hc = 0;
  private static comedianQuote instance = null;

  public comedianQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static comedianQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new comedianQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof comedianQuote;
  }

  public String toString() {

    return "<comedian>";
  }
}
