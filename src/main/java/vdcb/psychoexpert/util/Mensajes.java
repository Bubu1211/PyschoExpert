package vdcb.psychoexpert.util;

import java.util.HashMap;

public class Mensajes {
    private HashMap<String, Object> mensajes;
    
    
    public Mensajes(){
        mensajes = new HashMap<>();
    }
    
    public void set(String clave, Object o){
        mensajes.put(clave, o);
    }
    
    public Object get(String clave){
        var o = mensajes.get(clave);
        mensajes.remove(clave);
        return o;
    }
    
    public Object getPersistence(String clave){
        var o = mensajes.get(clave);
        return o;
    }
}
