package app;

import java.util.ArrayList;
import java.util.List;

public class TodosLosPuntajes {
    protected List<Puntaje> puntajes = new ArrayList<Puntaje>();
    
    public TodosLosPuntajes(){

    }

    public List<Puntaje> getPuntajes() {
        return puntajes;
   }
   public void setPuntajes(List<Puntaje> puntajes) {
        this.puntajes = puntajes;
   }
}