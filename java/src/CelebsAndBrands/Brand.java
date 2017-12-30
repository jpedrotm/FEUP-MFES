package CelebsAndBrands;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Brand {
  private String name;
  public VDMSet projects;

  public void cg_init_Brand_1(final String n) {

    name = n;
    projects = SetUtil.set();
  }

  public Brand(final String n) {

    cg_init_Brand_1(n);
  }

  public String getName() {

    return name;
  }

  public VDMSet getProjects() {

    return Utils.copy(projects);
  }

  public Number getNumProjects() {

    return projects.size();
  }

  public void addProject(final Project p) {

    projects = SetUtil.union(Utils.copy(projects), SetUtil.set(p));
  }

  public void removeProject(final Project p) {

    projects = SetUtil.diff(Utils.copy(projects), SetUtil.set(p));
  }

  public Brand() {}

  public String toString() {

    return "Brand{"
        + "name := "
        + Utils.toString(name)
        + ", projects := "
        + Utils.toString(projects)
        + "}";
  }
}
