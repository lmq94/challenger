package app.Controller;

import app.dto.WorkPlaceDTO;
import app.dto.WorkPlaceSummaryDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.WorkPlaceService;

@RestController
@RequestMapping("/workPlaces")
public class WorkPlaceController {

    @Autowired
    private WorkPlaceService workPlaceService;

    @GetMapping("")
    public ResponseEntity<?> getAllWorkPlaces(){
        return ResponseEntity.ok(this.workPlaceService.getWorkPlaces());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createWorkPlace(@RequestBody @Valid WorkPlaceDTO workPlaceDTO){
       return ResponseEntity.status(HttpStatus.CREATED).body(this.workPlaceService.CreateWorkPlace(workPlaceDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateWorkPlace(@RequestBody @Valid WorkPlaceDTO workPlaceDTO, @PathVariable Long id){
        return ResponseEntity.ok(this.workPlaceService.updateWorkPlace(workPlaceDTO, id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteWorkPlace(@PathVariable Long id){
        this.workPlaceService.deleteWorkPlace(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("La con la id " + id + "fue borrada con exito");
    }

    @GetMapping("/summaries")
    public ResponseEntity<?> getWorkPlaceSummaries() {
        return ResponseEntity.ok(workPlaceService.getWorkPlaceSummaries());
    }


}
