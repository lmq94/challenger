package app.service;

import app.dto.WorkPlaceDTO;
import app.domain.WorkPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.repository.WorkPlaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkPlaceService {

    @Autowired
    private WorkPlaceRepository workPlaceRepository;

    private List<WorkPlaceDTO> converToDTO(List<WorkPlace> workPlaces){
        List<WorkPlaceDTO> newList = new ArrayList<>();
        for(WorkPlace w: workPlaces){
            WorkPlaceDTO workPlaceDTO = new WorkPlaceDTO(w);
            newList.add(workPlaceDTO);

        }
        return newList;
    }

    public List<WorkPlaceDTO> getWorkPlaces (){
        return this.converToDTO(this.workPlaceRepository.findAll());
    }

    public String CreateWorkPlace(WorkPlaceDTO workPlaceDTO){

        WorkPlace newWorkPlace = new WorkPlace(workPlaceDTO.getCountry(), workPlaceDTO.getName(), workPlaceDTO.getYellowAlerts(), workPlaceDTO.getRedAlerts(), workPlaceDTO.getReadings());

         this.workPlaceRepository.save(newWorkPlace);

         return "Planta creada con exito!";
    }

    public WorkPlaceDTO updateWorkPlace(WorkPlaceDTO workPlaceDTO, Long id){
        Optional<WorkPlace> workPlaceSearch = this.workPlaceRepository.findById(id);

        if(workPlaceSearch.isPresent()) {
            WorkPlace workPlace = workPlaceSearch.get();
            workPlace.setCountry(workPlaceDTO.getCountry());
            workPlace.setName(workPlaceDTO.getName());
            workPlace.setYellowAlerts(workPlaceDTO.getYellowAlerts());
            workPlace.setRedAlerts(workPlaceDTO.getRedAlerts());
            workPlace.setReadings(workPlaceDTO.getReadings());
            this.workPlaceRepository.save(workPlace);
        }
        return workPlaceDTO;
    }

    public void deleteWorkPlace(Long id){
        Optional<WorkPlace> workPlaceSearch = this.workPlaceRepository.findById(id);

        if(workPlaceSearch.isPresent()) {
            this.workPlaceRepository.deleteById(id);
        }
    }


}




