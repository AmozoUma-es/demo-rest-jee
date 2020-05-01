package es.uma.informatica.sii.ejb.demo.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Alejandro
 */
@XmlRootElement
@XmlSeeAlso(Contacto.class)
public class Contactos extends ArrayList<Contacto> {
    public Contactos() {
        super();
    }
    
    public Contactos(Collection<? extends Contacto> c) {
        super(c);
    }
    
    @XmlElement(name="contacto")
    public List<Contacto> getContactos() {
        return this;
    }
    
    public void setContactos(List<Contacto> contactos) {
        this.addAll(contactos);
    }
}