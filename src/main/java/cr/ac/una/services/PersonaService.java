package cr.ac.una.services;

import cr.ac.una.entities.Persona;
import cr.ac.una.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getAllPersonas() {
        List<Persona> list = new ArrayList<Persona>();
        personaRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public Persona createPersona(Persona persona){
        return personaRepository.save(persona);
    }

    public Persona updatePersona(Persona persona){
        return personaRepository.save(persona);
    }
    public void deletePersona(Long idPersona){
        personaRepository.deleteById(idPersona);
    }
    public Optional<Persona> findPersonaById(Long idPersona){
        return personaRepository.findById(idPersona);
    }



}
