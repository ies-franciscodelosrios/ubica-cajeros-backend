package bancaMach.backend.api_cashier_services;

import bancaMach.backend.api_cahier_repositories.IncidenceRepository;
import bancaMach.backend.api_cashier_exceptions.RecordNotFoundException;
import bancaMach.backend.api_cashier_models.dataobject.Incidence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenceService {

    @Autowired
    IncidenceRepository incidenceRepository;

    /**
     * Method that creates or updates an incidence
     * @param i
     */
    public Incidence createOrUpdateIncidence(Incidence i) {
        if (i.getId() != null) {
            Optional<Incidence> incidence = incidenceRepository.findById(i.getId());
            if (incidence.isPresent()) {
                i = incidenceRepository.save(i);
            } else {
                throw new RecordNotFoundException("Incidence not found.", i);
            }
        } else {
            i = incidenceRepository.save(i);
        }
        return i;
    }

    /**
     * Method that get all the incidences from the database
     *
     * @return
     */
    public List<Incidence> getAllIncidences() {
        return incidenceRepository.findAll();
    }

    /**
     * Method that get an incidence by the id
     *
     * @param id
     * @return
     */
    public Incidence getIncidenceById(Long id) {
        Optional<Incidence> incidence = incidenceRepository.findById(id);
        if (incidence.isPresent()) {
            return incidence.get();
        } else {
            throw new RecordNotFoundException("Incidence not found.", id);
        }
    }

    /**
     * Method that deletes an incidence by the id
     */
    public void deleteIncidence(Long id) {
        Optional<Incidence> i = incidenceRepository.findById(id);
        if (i.isPresent()) {
            incidenceRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Incidence not found.", id);
        }
    }
}
