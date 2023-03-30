package vdcb.psychoexpert.modelo.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import vdcb.psychoexpert.App;
import vdcb.psychoexpert.modelo.dto.PreguntaOttisDto;

public class OttisDao {

    //Archivos de configuracion o de base XML
    public static final String PREGUNTAS_OTTIS_XML = "G:/Mi unidad/Universidad/6° semestre/SB Conocimiento/proyecto SE"
            + "/PsychoExpert/src/main/java/vdcb/psychoexpert/modelo/preguntas_cuestionario.ottis.xml";
    public static final String PREGUNTAS_OTTIS_RUTA = "imagenes/preguntas_ottis/reglas.dir";

    public List<PreguntaOttisDto> obtenerPreguntas() {
        var lista = new ArrayList<PreguntaOttisDto>();

        try {
            File archivo = new File(PREGUNTAS_OTTIS_XML);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();

            System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());

            NodeList listaEmpleados = document.getElementsByTagName("pregunta");

            for (int temp = 0; temp < listaEmpleados.getLength(); temp++) {
                Node nodo = listaEmpleados.item(temp);
                System.out.println("Elemento:" + nodo.getNodeName());
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    var n = element.getAttribute("n");
                    var r = element.getAttribute("respuesta");
                    System.out.println("n: " + n);
                    System.out.println("respuesta: " + r);
                    lista.add(new PreguntaOttisDto(Integer.parseInt(n),r));
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

}
