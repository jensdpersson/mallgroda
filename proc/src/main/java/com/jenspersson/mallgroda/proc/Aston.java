package com.jenspersson.mallgroda.proc;

public interface Aston {

   public void apply(WidgetTemplate template);

   /*
   * @return this
   * */
   public Aston add(Aston child) throws AstonIsLeafException;

   public Aston clear();

}
