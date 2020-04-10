package cr.ac.una.api;

import cr.ac.una.entities.Persona;
import cr.ac.una.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaRest {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        return ResponseEntity.ok(personaService.getAllPersonas());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.createPersona(persona));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> findById(@PathVariable Long id) {
        Optional<Persona> persona = personaService.findPersonaById(id);
        if (!persona.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(persona.get());
    }

    @PutMapping
    public ResponseEntity<Persona> update(@Valid @RequestBody Persona persona) {
        if (!personaService.findPersonaById(persona.getId()).isPresent()) {

            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(personaService.updatePersona(persona));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!personaService.findPersonaById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }

        personaService.deletePersona(id);

        return ResponseEntity.ok().build();
    }
}
