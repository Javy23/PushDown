package Model;

import javafx.scene.control.TextArea;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Automata {

    private HashMap<String, String> map = new HashMap<>();
    private Stack<String> pila = new Stack<>();
    public int i;
    public String a;
    public String x;
    public boolean si = false;

    public void mapFor(){

        this.map.put("S", "F p1 TipoDat ID igual ValorInicial coma ID Operador ValorFinal coma ID OperadorAritmético p2 Llave Llave2");
        this.map.put("F", "for");
        this.map.put("p1", "\\(");
        this.map.put("TipoDat", "int");
        this.map.put("ID", "(([a-z|A-Z])+([0-9]*))+");
        this.map.put("igual", "=");
        this.map.put("ValorInicial", "([0-9])+");
        this.map.put("coma", ";");
        this.map.put("Operador", "(<=|>=)");
        this.map.put("ValorFinal", "([0-9])+");
        this.map.put("OperadorAritmético", "(\\+\\+|--)");
        this.map.put("p2", "\\)");
        this.map.put("Llave", "\\{");
        this.map.put("Llave2", "\\}");

        this.i = 0;
        this.a = "";
        this.getPila().push("S");
    }
    public void mapEach(){

        this.map.put("S", "F p1 TipoDat ID 2p lista p2 Llave Llave2");
        this.map.put("F", "for");
        this.map.put("p1", "\\(");
        this.map.put("TipoDat", "((int)|(String)|(float)|(char))");
        this.map.put("ID", "(([a-z|A-Z])+([0-9]*))+");
        this.map.put("2p", ":");
        this.map.put("lista", "(([a-z|A-Z])+([0-9]*))+");
        this.map.put("p2", "\\)");
        this.map.put("Llave", "\\{");
        this.map.put("Llave2", "\\}");

        this.i = 0;
        this.a = "";
        this.getPila().push("S");
    }

    public boolean pushDown(String cadena, TextArea proceso) {

        this.si = false;
        boolean bandera = true;
        this.a = cadena;
        this.x = this.getPila().peek();
        if(this.getMap().containsKey(this.x)) {

            String nt = this.getPila().pop();
            String[] pro = this.getMap().get(nt).split("\s");
            proceso.appendText("Evaluando "+ nt + "\t-> Terminal : "+ Arrays.toString(pro)+ "\n");
            proceso.appendText("Se hace pop a la pila y push a su terminal\n");

            for (int j=pro.length-1; j>=0; j--)
            {
                this.getPila().push(pro[j]);
            }
        }
        else{

            this.x = this.getPila().peek();
            Pattern patternName = Pattern.compile (this.x);
            Matcher c = patternName.matcher (this.a);

            if(c.matches ()){

                proceso.appendText(this.a + "\tEs aceptado por el lenguaje\n");
                proceso.appendText("Se hace pop a la pila\n\n");
                this.getPila().pop();
                this.si = true;

            }
            else
            {
                proceso.appendText(this.a + "\tNo fue aceptado por el lenguaje\n");
                bandera = false;
            }
        }

        return bandera;
    }

    public Stack<String> getPila() {
        return pila;
    }

    public void setPila(Stack<String> pila) {
        this.pila = pila;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }
}
