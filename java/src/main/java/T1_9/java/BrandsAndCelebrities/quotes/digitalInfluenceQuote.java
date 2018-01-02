package sadf.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class digitalInfluenceQuote {
  private static int hc = 0;
  private static digitalInfluenceQuote instance = null;

  public digitalInfluenceQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static digitalInfluenceQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new digitalInfluenceQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof digitalInfluenceQuote;
  }

  public String toString() {

    return "<digitalInfluence>";
  }
}
