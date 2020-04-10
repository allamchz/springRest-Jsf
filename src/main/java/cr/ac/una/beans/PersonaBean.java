package cr.ac.una.beans;

import cr.ac.una.entities.Persona;
import cr.ac.una.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;


@Component
public class PersonaBean {

    @Autowired
    PersonaService personaService;
    private Persona persona = new Persona();


    private List<Persona> personas;

    @PostConstruct
    public void init() {
        personas = personaService.getAllPersonas();

    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public void create() {
        personaService.createPersona(persona);
        addMessage("Aviso", "Registro insertado correctamente.");
        personas = personaService.getAllPersonas();
    }

    public void delete(){
        Long idPersona = new Long(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PersonaId"));
        personaService.deletePersona(idPersona);
        addMessage("Aviso", "Registro eliminado correctamente.");
        personas = personaService.getAllPersonas();
    }
    public String carga(){
        Long idPersona = new Long(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("PersonaId"));
        Optional <Persona> personaOptional = personaService.findPersonaById(idPersona);
        if (personaOptional.isPresent())
            persona = personaOptional.get();
        return "personaUpdate";
    }
    public void actualiza(){
        personas = personaService.getAllPersonas();
    }

    public void update(){
        personaService.updatePersona(persona);
        addMessage("Aviso", "Registro modificado correctamente.");
        personas = personaService.getAllPersonas();
    }
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
