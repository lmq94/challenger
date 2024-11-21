package app.service;

import app.dto.WorkPlaceDTO;
import app.domain.WorkPlace;
import app.dto.WorkPlaceSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import app.repository.WorkPlaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkPlaceService {

    @Autowired
    private WorkPlaceRepository workPlaceRepository;

    private List<WorkPlaceDTO> converToDTO(List<WorkPlace> workPlaces) {
        List<WorkPlaceDTO> newList = new ArrayList<>();
        for (WorkPlace w : workPlaces) {
            WorkPlaceDTO workPlaceDTO = new WorkPlaceDTO(w);
            newList.add(workPlaceDTO);
        }
        return newList;
    }

    public List<WorkPlaceDTO> getWorkPlaces() {
        return this.converToDTO(this.workPlaceRepository.findAll());
    }

    public String CreateWorkPlace(WorkPlaceDTO workPlaceDTO) {
        Optional<WorkPlace> existingWorkPlace = this.workPlaceRepository.findByName(workPlaceDTO.getName());
        if (existingWorkPlace.isPresent()) {
            throw new DataIntegrityViolationException("Ya existe una planta con el nombre: " + workPlaceDTO.getName());
        }

        try {
            WorkPlace newWorkPlace = new WorkPlace(workPlaceDTO.getCountry(), workPlaceDTO.getName(),
                    workPlaceDTO.getYellowAlerts(), workPlaceDTO.getRedAlerts(),
                    workPlaceDTO.getReadings(),workPlaceDTO.getSensors());

            this.workPlaceRepository.save(newWorkPlace);
            return "Planta creada con exito!";
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al crear la planta: " + workPlaceDTO.getName());
        }
    }

    public WorkPlaceDTO updateWorkPlace(WorkPlaceDTO workPlaceDTO, Long id) {
        Optional<WorkPlace> workPlaceSearch = this.workPlaceRepository.findById(id);

        if (workPlaceSearch.isPresent()) {
            WorkPlace workPlace = workPlaceSearch.get();
            workPlace.setCountry(workPlaceDTO.getCountry());
            workPlace.setName(workPlaceDTO.getName());
            workPlace.setYellowAlerts(workPlaceDTO.getYellowAlerts());
            workPlace.setRedAlerts(workPlaceDTO.getRedAlerts());
            workPlace.setReadings(workPlaceDTO.getReadings());
            workPlace.setSensors(workPlaceDTO.getSensors());
            this.workPlaceRepository.save(workPlace);
            return new WorkPlaceDTO(workPlace);
        } else {
            throw new IllegalArgumentException("Planta no encontrada con id: " + id);
        }
    }

    public void deleteWorkPlace(Long id) {
        Optional<WorkPlace> workPlaceSearch = this.workPlaceRepository.findById(id);

        if (workPlaceSearch.isPresent()) {
            this.workPlaceRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Planta no encontrada con id: " + id);
        }
    }

    public WorkPlaceSummaryDTO getWorkPlaceSummaries() {
        List<WorkPlace> workplaces = workPlaceRepository.findAll();

        int totalYellowAlerts = workplaces.stream().mapToInt(WorkPlace::getYellowAlerts).sum();
        int totalRedAlerts = workplaces.stream().mapToInt(WorkPlace::getRedAlerts).sum();
        int totalSensors = workplaces.stream().mapToInt(WorkPlace::getSensors).sum();
        int totalReadings = workplaces.stream().mapToInt(WorkPlace::getReadings).sum();

        return new WorkPlaceSummaryDTO(totalYellowAlerts, totalRedAlerts, totalSensors, totalReadings);


    }
}



