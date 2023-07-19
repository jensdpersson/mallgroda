package com.jenspersson.mallgroda.proc;

public interface Aston extends WidgetTemplatePopulator {

   /*
   * @return this
   * */
   public Aston add(Aston child) throws AstonIsLeafException;

   public Aston clear();
   
   public Directive directive();

}
