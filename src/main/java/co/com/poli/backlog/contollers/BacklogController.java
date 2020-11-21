package co.com.poli.backlog.contollers;

import co.com.poli.backlog.domain.Backlog;
import co.com.poli.backlog.model.ErrorMessage;
import co.com.poli.backlog.services.IBacklogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BacklogController {

    @Autowired
    private IBacklogService iBacklogService;

    @PostMapping(value = "/backlog")
    public ResponseEntity<?> addNewBacklog(@Valid @RequestBody Backlog backlog, BindingResult result){
        System.out.println(result);
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        Backlog backlogDB = iBacklogService.createNewBacklog(backlog);
        return ResponseEntity.status(HttpStatus.CREATED).body(backlogDB);
    }


    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> { // mapeamos la lista de errores
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage()); // obtenemos el tipo de error y el mensaje
                    return error;
                }).collect(Collectors.toList()); // como retorna un string toca convertirlo a una lista

        ErrorMessage errorMessage = new ErrorMessage("01",errors);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }
}
