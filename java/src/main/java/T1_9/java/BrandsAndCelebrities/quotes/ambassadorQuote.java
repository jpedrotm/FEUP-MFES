package T1_9.java.BrandsAndCelebrities.quotes;


import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ambassadorQuote {
  private static int hc = 0;
  private static ambassadorQuote instance = null;

  public ambassadorQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ambassadorQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ambassadorQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ambassadorQuote;
  }

  public String toString() {

    return "<ambassador>";
  }
}
